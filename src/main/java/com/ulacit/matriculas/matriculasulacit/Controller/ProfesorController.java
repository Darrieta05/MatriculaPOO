package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Constante;
import com.ulacit.matriculas.matriculasulacit.Modelos.Response;
import com.ulacit.matriculas.matriculasulacit.Modelos.Profesor;
import com.ulacit.matriculas.matriculasulacit.Modelos.Profesor_Id;
import com.ulacit.matriculas.matriculasulacit.Repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/profesor")
public class ProfesorController {
    
    @Autowired
    ProfesorRepository profesorRepository;

    private Response response;
    private Date currentDate;


    /*@ApiOperation(value = "Retorna el listado de todas las profe")*/
    @RequestMapping(method = RequestMethod.GET)
    public Response GetAll() {
        response = new Response();

        try {
            List<Profesor> listaProfesor = profesorRepository.findByDeleted(false);
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
            Profesor profesor = profesorRepository.findByIdProfesorInAndDeletedIn(idProfesor, false);
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

                profesorObj.setUpdatedBy(profesorObj.getCreatedBy());
                profesorObj.setCreationDate(currentDate);
                profesorObj.setUpdatedDate(currentDate);
                profesorObj.setDeleted(false);

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
        Profesor profesor;
        currentDate = new Date();

        try {
            if (profesorObj != null) {
                profesorObj.setIdProfesor(idProfesor);
                response.setRequest(profesorObj);

                profesor = profesorRepository.findByIdProfesorInAndDeletedIn(idProfesor, false);

                if (profesor != null)

                {
                    profesor.setIdProfesor(profesorObj.getIdProfesor());
                    //profesor.setPersona(profesorObj.getPersona());
                    profesor.setEspecialidad(profesorObj.getEspecialidad());
                    profesor.setDeleted(false);
                    profesor.setCreationDate(profesorObj.getCreationDate());
                    profesor.setUpdatedBy(profesorObj.getUpdatedBy());
                    profesor.setUpdatedDate(currentDate);
                    profesor.setCreatedBy(profesorObj.getCreatedBy());


                    profesorRepository.save(profesor);

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
            profesorStored = profesorRepository.findByIdProfesorInAndDeletedIn(idProfesor, false);

            if (profesorStored != null) {
                profesorStored.setDeleted(true);
                profesorStored.setUpdatedDate(new Date());
                profesorRepository.save(profesorStored);
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
