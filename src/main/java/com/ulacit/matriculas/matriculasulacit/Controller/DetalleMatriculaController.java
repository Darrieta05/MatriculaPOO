package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Constante;
import com.ulacit.matriculas.matriculasulacit.Modelos.Response;
import com.ulacit.matriculas.matriculasulacit.Modelos.DetalleMatricula;
import com.ulacit.matriculas.matriculasulacit.Repository.DetalleMatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/detalle-matricula")
public class DetalleMatriculaController {
    
    @Autowired
    DetalleMatriculaRepository detalleMatriculaRepository;

    private Response response;
    private Date currentDate;


    /* @ApiOperation(value = "Retorna el listado de todas las detalle matricula")*/
    @RequestMapping(method = RequestMethod.GET)
    public Response GetAll() {
        response = new Response();

        try {
            List<DetalleMatricula> listaDetalleMatricula = detalleMatriculaRepository.findByDeleted(false);
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
            DetalleMatricula detalleMatricula = detalleMatriculaRepository.findByIdDetalleMatriculaInAndDeletedIn(idDetalleMatricula, false);
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

                detalleObj.setUpdatedBy(detalleObj.getCreatedBy());
                detalleObj.setCreationDate(currentDate);
                detalleObj.setUpdatedDate(currentDate);
                detalleObj.setDeleted(false);

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
        DetalleMatricula detalleMatricula;
        currentDate = new Date();

        try {
            if (detalleObj != null) {
                detalleObj.setIdDetalleMatricula(idDetalleMatricula);
                response.setRequest(detalleObj);

                detalleMatricula = detalleMatriculaRepository.findByIdDetalleMatriculaInAndDeletedIn(idDetalleMatricula, false);

                if (detalleMatricula != null)

                {

                    detalleMatricula.setIdDetalleMatricula(detalleObj.getIdDetalleMatricula());
                    detalleMatricula.setMateria(detalleObj.getMateria());
                    detalleMatricula.setMatricula(detalleObj.getMatricula());
                    detalleMatricula.setDeleted(false);
                    detalleMatricula.setCreationDate(detalleObj.getCreationDate());
                    detalleMatricula.setUpdatedBy(detalleObj.getUpdatedBy());
                    detalleMatricula.setUpdatedDate(currentDate);
                    detalleMatricula.setCreatedBy(detalleObj.getCreatedBy());


                    detalleMatriculaRepository.save(detalleMatricula);

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
            detalleStored = detalleMatriculaRepository.findByIdDetalleMatriculaInAndDeletedIn(idDetalleMatricula, false);

            if (detalleStored != null) {
                detalleStored.setDeleted(true);
                detalleStored.setUpdatedDate(new Date());
                detalleMatriculaRepository.save(detalleStored);
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
