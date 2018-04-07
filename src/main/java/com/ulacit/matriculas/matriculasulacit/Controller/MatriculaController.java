package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Constante;
import com.ulacit.matriculas.matriculasulacit.Modelos.Response;
import com.ulacit.matriculas.matriculasulacit.Modelos.Matricula;
import com.ulacit.matriculas.matriculasulacit.Repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/matricula")
public class MatriculaController {
    
    @Autowired
    MatriculaRepository matriculaRepository;

    private Response response;
    private Date currentDate;


    /*@ApiOperation(value = "Retorna el listado de todas las matriculas")*/
    @RequestMapping(method = RequestMethod.GET)
    public Response GetAll() {
        response = new Response();

        try {
            List<Matricula> listaMatricula = matriculaRepository.findByDeleted(false);
            response.setResponse(listaMatricula);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Retorna el matricula filtrando idMatricula")*/
    @RequestMapping(method = RequestMethod.GET, value = "/{idMatricula}")
    public Response GetById(@PathVariable("idMatricula") Integer idMatricula) {
        response = new Response();

        try {
            Matricula matricula = matriculaRepository.findByIdMatriculaInAndDeletedIn(idMatricula, false);
            response.setResponse(matricula);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }


    /*@ApiOperation(value = "Agrega una nueva matricula")*/
    @RequestMapping(method = RequestMethod.POST)
    public Response Create(@RequestBody Matricula matriculaObj) {
        response = new Response();

        try {
            if (matriculaObj != null) {
                response.setRequest(matriculaObj);

                matriculaObj.setUpdatedBy(matriculaObj.getCreatedBy());
                matriculaObj.setCreationDate(currentDate);
                matriculaObj.setUpdatedDate(currentDate);
                matriculaObj.setDeleted(false);

                matriculaRepository.save(matriculaObj);
                response.setResponse(matriculaObj);
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }


    /*@ApiOperation(value = "Modifica la información de una matricula")*/
    @RequestMapping(method = RequestMethod.PUT, value = "/{idMatricula}")
    public Response Update(@PathVariable("idMatricula") Integer idMatricula, @RequestBody Matricula matriculaObj) {
        response = new Response();
        Matricula matriculaStored;
        currentDate = new Date();

        try {
            if (matriculaObj != null) {
                matriculaObj.setIdMatricula(idMatricula);

                response.setRequest(matriculaObj);

                matriculaStored = matriculaRepository.findOne(idMatricula);

                if (matriculaStored != null) {
                    matriculaObj.setIdMatricula(matriculaStored.getIdMatricula());

                    response.setRequest(matriculaObj);
                    matriculaObj.setUpdatedBy(matriculaObj.getCreatedBy());
                    matriculaObj.setCreationDate(currentDate);
                    matriculaObj.setUpdatedDate(currentDate);
                    matriculaObj.setDeleted(false);

                    matriculaRepository.save(matriculaObj);
                    response.setResponse(matriculaObj);

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

    @RequestMapping(method = RequestMethod.DELETE, value = "/{idMatricula}")
    public Response Delete(@PathVariable("idMatricula") Integer idMatricula) {

        Response response = new Response();
        Matricula matriculaStored;
        try {
            response.setRequest(idMatricula);
            matriculaStored = matriculaRepository.findByIdMatriculaInAndDeletedIn(idMatricula, false);

            if (matriculaStored != null) {
                matriculaStored.setDeleted(true);
                matriculaStored.setUpdatedDate(new Date());
                matriculaRepository.delete(matriculaStored);
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
