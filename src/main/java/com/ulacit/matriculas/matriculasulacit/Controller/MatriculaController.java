package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Alumno;
import com.ulacit.matriculas.matriculasulacit.Modelos.Alumno_Id;
import com.ulacit.matriculas.matriculasulacit.Modelos.Constante;
import com.ulacit.matriculas.matriculasulacit.Modelos.Response;
import com.ulacit.matriculas.matriculasulacit.Modelos.Matricula;
import com.ulacit.matriculas.matriculasulacit.Modelos.Persona;
import com.ulacit.matriculas.matriculasulacit.Modelos.Usuario;
import com.ulacit.matriculas.matriculasulacit.Repository.AlumnoRepository;
import com.ulacit.matriculas.matriculasulacit.Repository.MatriculaRepository;
import com.ulacit.matriculas.matriculasulacit.Repository.PersonaRepository;
import com.ulacit.matriculas.matriculasulacit.Repository.UsuarioRepository;
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
    
    @Autowired
    AlumnoRepository alumnoRepository;
    
    @Autowired
    PersonaRepository personaRepository;
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    private Response response;
    private Date fechaActual;


    /*@ApiOperation(value = "Retorna el listado de todas las matriculas")*/
    @RequestMapping(method = RequestMethod.GET)
    public Response GetAll() {
        response = new Response();

        try {
            List<Matricula> listaMatricula = matriculaRepository.findByEliminado(false);
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
            Matricula matricula = matriculaRepository.findByIdMatriculaInAndEliminadoIn(idMatricula, false);
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

                matriculaObj.setCreadoPor(matriculaObj.getCreadoPor());
                matriculaObj.setFechaCreacion(fechaActual);
                matriculaObj.setFechaActualizacion(fechaActual);
                matriculaObj.setEliminado(false);
                
                
                Persona p = personaRepository.findByIdPersonaInAndEliminadoIn(matriculaObj.getAlumno().getAlumnoKey().getIdAlumno(), false);
                Alumno_Id alumnoKey = new Alumno_Id(matriculaObj.getAlumno().getAlumnoKey().getIdAlumno(), p);
                
                Alumno a = alumnoRepository.findOne(alumnoKey);
                Usuario u = usuarioRepository.findOne(matriculaObj.getUsuario().getIdUsuario());
                if (a != null && u != null) {
                    matriculaObj.setAlumno(a);
                    matriculaObj.setUsuario(u);
                    matriculaRepository.save(matriculaObj);
                    response.setResponse(matriculaObj);
                }
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
        fechaActual = new Date();

        try {
            if (matriculaObj != null) {
                matriculaObj.setIdMatricula(idMatricula);

                response.setRequest(matriculaObj);

                matriculaStored = matriculaRepository.findOne(idMatricula);

                if (matriculaStored != null) {
                    matriculaObj.setIdMatricula(matriculaStored.getIdMatricula());

                    response.setRequest(matriculaObj);

                    matriculaObj.setCreadoPor(matriculaObj.getCreadoPor());
                    matriculaObj.setFechaCreacion(fechaActual);
                    matriculaObj.setFechaActualizacion(fechaActual);
                    matriculaObj.setEliminado(false);

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
            matriculaStored = matriculaRepository.findByIdMatriculaInAndEliminadoIn(idMatricula, false);

            if (matriculaStored != null) {
                matriculaStored.setEliminado(true);
                matriculaStored.setFechaActualizacion(new Date());
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
