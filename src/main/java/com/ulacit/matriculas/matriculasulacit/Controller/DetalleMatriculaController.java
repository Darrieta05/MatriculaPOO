package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Constante;
import com.ulacit.matriculas.matriculasulacit.Modelos.Response;
import com.ulacit.matriculas.matriculasulacit.Modelos.DetalleMatricula;
import com.ulacit.matriculas.matriculasulacit.Modelos.Materia;
import com.ulacit.matriculas.matriculasulacit.Modelos.Matricula;
import com.ulacit.matriculas.matriculasulacit.Repository.DetalleMatriculaRepository;
import com.ulacit.matriculas.matriculasulacit.Repository.MateriaRepository;
import com.ulacit.matriculas.matriculasulacit.Repository.MatriculaRepository;

import java.util.ArrayList;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/detalle-matricula")
public class DetalleMatriculaController {

    @Autowired
    DetalleMatriculaRepository detalleMatriculaRepository;

    @Autowired
    MatriculaRepository matriculaRepository;

    @Autowired
    MateriaRepository MateriaRepository;

    private Response response;

    @ApiOperation(value = "Retorna el listado de todos los detalles de matricula")
    @RequestMapping(method = RequestMethod.GET, value = "/{idDetalleMatricula}")
    public Response GetById(@PathVariable("idDetalleMatricula") Integer idDetalleMatricula) {
        response = new Response();

        ArrayList<DetalleMatricula> listaDetalleMatriculaFiltro = new ArrayList<DetalleMatricula>();

        try {
            List<DetalleMatricula> listaDetalleMatricula = detalleMatriculaRepository.findAll();
            for (DetalleMatricula detalles : listaDetalleMatricula) {
                if (detalles.getMatricula().getIdMatricula() == idDetalleMatricula) {
                    listaDetalleMatriculaFiltro.add(detalles);
                }
            }
            response.setResponse(listaDetalleMatriculaFiltro);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    @ApiOperation(value = "Agrega un nuevo detalle de matricula")
    @RequestMapping(method = RequestMethod.POST)
    public Response Create(@RequestBody DetalleMatricula detalleObj) {
        response = new Response();

        try {
            if (detalleObj != null) {
                response.setRequest(detalleObj);

                Matricula matriculaObj = matriculaRepository.findByIdMatriculaInAndEliminadoIn(detalleObj.getMatricula().getIdMatricula(), false);

                Materia materiaObj = MateriaRepository.findOne(detalleObj.getMateria().getIdMateria());

                if (matriculaObj != null && materiaObj != null) {
                    detalleObj.setMatricula(matriculaObj);
                    detalleObj.setMateria(materiaObj);
                    detalleMatriculaRepository.save(detalleObj);
                    response.setResponse(detalleObj);
                }
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    @ApiOperation(value = "Modifica la información de un detalle de matricula")
    @RequestMapping(method = RequestMethod.PUT, value = "/{idDetalleMatricula}")
    public Response Update(@PathVariable("idDetalleMatricula") Integer idDetalleMatricula, @RequestBody DetalleMatricula detalleMatriculaObj) {
        response = new Response();
        DetalleMatricula detalleMatriculaStored;

        try {
            if (detalleMatriculaObj != null) {
                detalleMatriculaObj.setIdDetalleMatricula(idDetalleMatricula);

                response.setRequest(detalleMatriculaObj);

                detalleMatriculaStored = detalleMatriculaRepository.findOne(idDetalleMatricula);

                if (detalleMatriculaStored != null) {
                    detalleMatriculaObj.setIdDetalleMatricula(detalleMatriculaStored.getIdDetalleMatricula());
                    response.setRequest(detalleMatriculaObj);

                    detalleMatriculaRepository.save(detalleMatriculaObj);
                    response.setResponse(detalleMatriculaObj);

                } else {
                    throw new Exception(Constante.itemNotFound);
                }
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    @ApiOperation(value = "Elimina lógicamente una carrera3")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{idDetalleMatricula}")
    public Response Delete(@PathVariable("idDetalleMatricula") Integer idDetalleMatricula) {

        response = new Response();
        DetalleMatricula detalleMatriculaStored;
        try {
            response.setRequest(idDetalleMatricula);
            detalleMatriculaStored = detalleMatriculaRepository.findOne(idDetalleMatricula);

            if (detalleMatriculaStored != null) {
                detalleMatriculaRepository.save(detalleMatriculaStored);
                response.setResponse(Constante.itemDeleted);
            } else {
                throw new Exception(Constante.itemNotFound);
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }
        return response;
    }
}
