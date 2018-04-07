package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Constante;
import com.ulacit.matriculas.matriculasulacit.Modelos.Response;
import com.ulacit.matriculas.matriculasulacit.Modelos.DetalleMatricula;
import com.ulacit.matriculas.matriculasulacit.Repository.DetalleMatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/detalle-matricula")
public class DetalleMatriculaController {
    
    @Autowired
    DetalleMatriculaRepository detalleMatriculaRepository;

    private Response response;

    /* @ApiOperation(value = "Retorna el listado de todas las detalle matricula")*/
    @RequestMapping(method = RequestMethod.GET)
    public Response GetAll() {
        response = new Response();

        try {
            List<DetalleMatricula> listaDetalleMatricula = detalleMatriculaRepository.findAll();
            response.setResponse(listaDetalleMatricula);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Obtiene un alumno filtrándolo por el parámetro idDetalle")*/
    @RequestMapping(method = RequestMethod.GET, value = "/{idDetalleMatricula}")
    public Response GetById(@PathVariable("idDetalleMatricula") Integer idDetalleMatricula) {
        response = new Response();

        try {
            DetalleMatricula detalleMatricula = detalleMatriculaRepository.findOne(idDetalleMatricula);
            response.setResponse(detalleMatricula);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Agrega una nueva detalle matricula")*/
    @RequestMapping(method = RequestMethod.POST)
    public Response Create(@RequestBody DetalleMatricula detalleObj) {
        response = new Response();

        try {
            if (detalleObj != null) {
                response.setRequest(detalleObj);

                detalleMatriculaRepository.save(detalleObj);
                response.setResponse(detalleObj);
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Modifica la información de un contacto")*/
    @RequestMapping(method = RequestMethod.PUT, value = "/{idDetalleMatricula}")
    public Response Update(@PathVariable("idDetalleMatricula") Integer idDetalleMatricula, @RequestBody DetalleMatricula detalleObj) {
        response = new Response();
        DetalleMatricula detalleMatriculaStored;

        try {
            if (detalleObj != null) {
                detalleObj.setIdDetalleMatricula(idDetalleMatricula);

                response.setRequest(detalleObj);

                detalleMatriculaStored = detalleMatriculaRepository.findOne(idDetalleMatricula);

                if (detalleMatriculaStored != null) {
                    detalleObj.setIdDetalleMatricula(detalleMatriculaStored.getIdDetalleMatricula());
                    response.setRequest(detalleObj);

                    detalleMatriculaRepository.save(detalleObj);
                    response.setResponse(detalleObj);

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
    @RequestMapping(method = RequestMethod.DELETE, value = "/{idDetalleMatricula}")
    public Response Delete(@PathVariable("idDetalleMatricula") Integer idDetalleMatricula) {

        Response response = new Response();
        DetalleMatricula detalleStored;
        try {
            response.setRequest(idDetalleMatricula);
            detalleStored = detalleMatriculaRepository.findOne(idDetalleMatricula);

            if (detalleStored != null) {
                detalleMatriculaRepository.delete(detalleStored);
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
