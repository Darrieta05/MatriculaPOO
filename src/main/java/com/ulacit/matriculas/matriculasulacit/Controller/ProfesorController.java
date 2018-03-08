package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Constants;
import com.ulacit.matriculas.matriculasulacit.Modelos.Matricula;
import com.ulacit.matriculas.matriculasulacit.Modelos.Profesor;
import com.ulacit.matriculas.matriculasulacit.Modelos.ResponseObject;
import com.ulacit.matriculas.matriculasulacit.Repository.MatriculaRepository;
import com.ulacit.matriculas.matriculasulacit.Repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/profesor")
public class ProfesorController {

    @Autowired
    ProfesorRepository profesorRepository;

    private ResponseObject response;
    private Date currentDate;


    /*@ApiOperation(value = "Retorna el listado de todas las profe")*/
    @RequestMapping(method = RequestMethod.GET)
    public ResponseObject GetAll() {
        response = new ResponseObject();

        try {
            List<Profesor> listaProfesor = profesorRepository.findByDeleted(false);
            response.setResponse(listaProfesor);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constants.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Retorna el matricula filtrando idProfesor")*/
    @RequestMapping(method = RequestMethod.GET, value = "/{idProfesor}")
    public ResponseObject GetById(@PathVariable("idProfesor") Integer idProfesor) {
        response = new ResponseObject();

        try {
            Profesor profesor = profesorRepository.findByProfesorIdInAndDeletedIn(idProfesor, false);
            response.setResponse(profesor);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constants.badRequest);
        }

        return response;
    }


    /*@ApiOperation(value = "Agrega una nueva profesor")*/
    @RequestMapping(method = RequestMethod.POST)
    public ResponseObject Create(@RequestBody Profesor profesorObj) {
        response = new ResponseObject();

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
            response.setHttpStatus(Constants.badRequest);
        }

        return response;
    }


    /*@ApiOperation(value = "Modifica la información de una profesor")*/
    @RequestMapping(method = RequestMethod.PUT, value = "/{idProfesor}")
    public ResponseObject Update(@PathVariable("idProfesor") Integer idProfesor, @RequestBody Profesor profesorObj) {
        response = new ResponseObject();
        Profesor profesor;
        currentDate = new Date();

        try {
            if (profesorObj != null) {
                profesorObj.setIdProfesor(idProfesor);
                response.setRequest(profesorObj);

                profesor = profesorRepository.findByProfesorIdInAndDeletedIn(idProfesor, false);

                if (profesor != null)

                {

                    profesor.setIdProfesor(profesorObj.getIdProfesor());
                    profesor.setEspecialidad(profesorObj.getEspecialidad());
                    profesor.setDeleted(false);
                    profesor.setCreationDate(profesorObj.getCreationDate());
                    profesor.setUpdatedBy(profesorObj.getUpdatedBy());
                    profesor.setUpdatedDate(currentDate);
                    profesor.setCreatedBy(profesorObj.getCreatedBy());


                    profesorRepository.save(profesor);

                    response.setResponse(profesorObj);
                } else {
                    throw new Exception(Constants.itemNotFound);
                }
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constants.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Elimina la información de una profesor")*/
    @RequestMapping(method = RequestMethod.DELETE, value = "/{idProfesor}")
    public ResponseObject Delete(@PathVariable("idProfesor") Integer idProfesor) {

        ResponseObject response = new ResponseObject();
        Profesor profesorStored;
        try {
            response.setRequest(idProfesor);
            profesorStored = profesorRepository.findOne(idProfesor);

            if (profesorStored != null) {
                profesorStored.setDeleted(true);
                profesorStored.setUpdatedDate(new Date());
                profesorRepository.save(profesorStored);
                response.setResponse(Constants.itemDeleted);
            } else {
                throw new Exception(Constants.itemNotFound);
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constants.badRequest);
        }
        return response;
    }
}
