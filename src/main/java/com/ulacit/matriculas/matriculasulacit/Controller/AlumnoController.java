package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Constante;
import com.ulacit.matriculas.matriculasulacit.Modelos.Response;
import com.ulacit.matriculas.matriculasulacit.Modelos.Alumno;
import com.ulacit.matriculas.matriculasulacit.Modelos.Alumno_Id;
import com.ulacit.matriculas.matriculasulacit.Repository.AlumnoRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/alumno")
public class AlumnoController {

    @Autowired
    AlumnoRepository alumnoRepository;

    private Response response;

    @ApiOperation(value = "Retorna el listado de todos los alumnos")
    @RequestMapping(method = RequestMethod.GET)
    public Response GetAll() {
        response = new Response();

        try {
            List<Alumno> listaAlumno = alumnoRepository.findAll();
            response.setResponse(listaAlumno);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    @ApiOperation(value = "Obtiene un alumno filtrándolo por el parámetro idAlumno")

    @RequestMapping(method = RequestMethod.GET, value = "/{idAlumno}")
    public Response GetById(@PathVariable("idAlumno") Alumno_Id idAlumno) {
        response = new Response();

        try {
            Alumno alumno = alumnoRepository.findOne(idAlumno.getIdAlumno());
            response.setResponse(alumno);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    @ApiOperation(value = "Agrega una nueva alumno")

    @RequestMapping(method = RequestMethod.POST)
    public Response Create(@RequestBody Alumno alumnoObj) {
        response = new Response();

        try {
            if (alumnoObj != null) {
                response.setRequest(alumnoObj);

                alumnoRepository.save(alumnoObj);
                response.setResponse(alumnoObj);
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    @ApiOperation(value = "Modifica la información de un alumno")

    @RequestMapping(method = RequestMethod.PUT, value = "/{idAlumno}")
    public Response Update(@PathVariable("idAlumno") Alumno_Id idAlumno, @RequestBody Alumno alumnoObj) {
        response = new Response();
        Alumno alumnoStored;

        try {
            if (alumnoObj != null) {
                alumnoObj.setIdAlumno(idAlumno);

                response.setRequest(alumnoObj);

                alumnoStored = alumnoRepository.findOne(idAlumno.getIdAlumno());

                if (alumnoStored != null) {
                    alumnoObj.setIdAlumno(alumnoStored.getIdAlumno());
                    response.setRequest(alumnoObj);

                    alumnoRepository.save(alumnoObj);
                    response.setResponse(alumnoObj);

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

    @RequestMapping(method = RequestMethod.DELETE, value = "/{idAlumno}")
    public Response Delete(@PathVariable("idAlumno") Alumno_Id idAlumno) {

        Response response = new Response();
        Alumno alumnoStored;
        try {
            response.setRequest(idAlumno);
            alumnoStored = alumnoRepository.findOne(idAlumno.getIdAlumno());

            if (alumnoStored != null) {

                alumnoRepository.delete(alumnoStored);
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
