package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Constante;
import com.ulacit.matriculas.matriculasulacit.Modelos.Response;
import com.ulacit.matriculas.matriculasulacit.Modelos.Alumno;
import com.ulacit.matriculas.matriculasulacit.Modelos.Alumno_Id;
import com.ulacit.matriculas.matriculasulacit.Modelos.Carrera;
import com.ulacit.matriculas.matriculasulacit.Modelos.Persona;
import com.ulacit.matriculas.matriculasulacit.Repository.AlumnoRepository;
import com.ulacit.matriculas.matriculasulacit.Repository.PersonaRepository;
import com.ulacit.matriculas.matriculasulacit.Repository.CarreraRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/*Aqui se esta aplicando el patron de Repository*/
@CrossOrigin
@RestController
@RequestMapping("/api/alumno")
public class AlumnoController {

    @Autowired
    AlumnoRepository alumnoRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    CarreraRepository carreraRepository;

    private Response response;

    @ApiOperation(value = "Retorna el listado de todos los alumnos")
    @RequestMapping(method = RequestMethod.GET)
    public Response GetAll() {
        response = new Response();

        try {
            List<Alumno> listaAlumno = alumnoRepository.findByEliminado(false);
            response.setResponse(listaAlumno);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    @ApiOperation(value = "Obtiene un alumno filtrándolo por el parámetro idAlumno")

    @RequestMapping(method = RequestMethod.GET, value = "/{idAlumno}")
    public Response GetById(@PathVariable("idAlumno") Integer idAlumno) {
        response = new Response();

        Persona personaObj = personaRepository.findByIdPersonaInAndEliminadoIn(idAlumno, false);
        Alumno_Id alumnoKey = new Alumno_Id(idAlumno, personaObj);
        try {
            Alumno alumno = alumnoRepository.findOne(alumnoKey);
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
                /*Aqui se aplica el patron de Adapter*/
                Persona personaObj = personaRepository.findByIdPersonaInAndEliminadoIn(alumnoObj.getAlumnoKey().getPersona().getIdPersona(), false);
                Carrera carreraObj = carreraRepository.findOne(alumnoObj.getCarrera().getIdCarrera());
                if (personaObj != null && carreraObj != null) {
                    alumnoObj.getAlumnoKey().setPersona(personaObj);
                    alumnoObj.setCarrera(carreraObj);
                    alumnoRepository.save(alumnoObj);
                    response.setResponse(alumnoObj);
                }
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    @ApiOperation(value = "Modifica la información de un alumno")

    @RequestMapping(method = RequestMethod.PUT, value = "/{idAlumno}")
    public Response Update(@PathVariable("idAlumno") Integer idAlumno, @RequestBody Alumno alumnoObj) {
        response = new Response();
        Alumno alumnoStored;

        try {
            if (alumnoObj != null) {

                response.setRequest(alumnoObj);

                Persona personaObj = personaRepository.findByIdPersonaInAndEliminadoIn(idAlumno, false);
                Alumno_Id alumnoKey = new Alumno_Id(idAlumno, personaObj);

                alumnoStored = alumnoRepository.findOne(alumnoKey);

                if (alumnoStored != null) {
                    alumnoObj.setAlumnoKey(alumnoStored.getAlumnoKey());
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

    @ApiOperation(value = "Elimina lógicamente un alumno")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{idAlumno}")
    public Response Delete(@PathVariable("idAlumno") Integer idAlumno) {

        response = new Response();
        Alumno alumnoStored;
        try {
            response.setRequest(idAlumno);
            Persona personaObj = personaRepository.findByIdPersonaInAndEliminadoIn(idAlumno, false);
            Alumno_Id alumnoKey = new Alumno_Id(idAlumno, personaObj);
            alumnoStored = alumnoRepository.findOne(alumnoKey);

            if (alumnoStored != null) {
                alumnoStored.setEliminado(true);
                alumnoRepository.save(alumnoStored);
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
