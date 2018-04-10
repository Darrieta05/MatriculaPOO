package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Constante;
import com.ulacit.matriculas.matriculasulacit.Modelos.Response;
import com.ulacit.matriculas.matriculasulacit.Modelos.Profesor;
import com.ulacit.matriculas.matriculasulacit.Modelos.Profesor_Id;
import com.ulacit.matriculas.matriculasulacit.Repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/profesor")
public class ProfesorController {
    
    @Autowired
    ProfesorRepository profesorRepository;

    private Response response;


    /*@ApiOperation(value = "Retorna el listado de todas las profe")*/
    @RequestMapping(method = RequestMethod.GET)
    public Response GetAll() {
        response = new Response();

        try {
            List<Profesor> listaProfesor = profesorRepository.findAll();
            response.setResponse(listaProfesor);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Retorna el matricula filtrando idProfesor")*/
    @RequestMapping(method = RequestMethod.GET, value = "/{idProfesor}")
    public Response GetById(@PathVariable("idProfesor") Profesor_Id idProfesor) {
        response = new Response();

        try {
            Profesor profesor = profesorRepository.findOne(idProfesor.getIdProfesor());
            response.setResponse(profesor);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }


    /*@ApiOperation(value = "Agrega una nueva profesor")*/
    @RequestMapping(method = RequestMethod.POST)
    public Response Create(@RequestBody Profesor profesorObj) {
        response = new Response();

        try {
            if (profesorObj != null) {
                response.setRequest(profesorObj);

                profesorRepository.save(profesorObj);
                response.setResponse(profesorObj);
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }


    /*@ApiOperation(value = "Modifica la información de una profesor")*/
    @RequestMapping(method = RequestMethod.PUT, value = "/{idProfesor}")
    public Response Update(@PathVariable("idProfesor") Profesor_Id idProfesor, @RequestBody Profesor profesorObj) {
        response = new Response();
        Profesor profesorStored;

        try {
            if (profesorObj != null) {
                profesorObj.setProfesorKey(idProfesor);

                response.setRequest(profesorObj);

                profesorStored = profesorRepository.findOne(idProfesor.getIdProfesor());

                if (profesorStored != null) {
                    profesorObj.setProfesorKey(profesorStored.getProfesorKey());
                    response.setRequest(profesorObj);

                    profesorRepository.save(profesorObj);
                    response.setResponse(profesorObj);

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

    //@ApiOperation(value = "Elimina la información de una profesor")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{idProfesor}")
    public Response Delete(@PathVariable("idProfesor") Profesor_Id idProfesor) {

        Response response = new Response();
        Profesor profesorStored;
        try {
            response.setRequest(idProfesor);
            profesorStored = profesorRepository.findOne(idProfesor.getIdProfesor());

            if (profesorStored != null) {
                profesorRepository.delete(profesorStored);
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
