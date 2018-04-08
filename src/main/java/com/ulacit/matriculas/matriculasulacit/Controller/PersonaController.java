package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Constante;
import com.ulacit.matriculas.matriculasulacit.Modelos.Response;
import com.ulacit.matriculas.matriculasulacit.Modelos.Persona;
import com.ulacit.matriculas.matriculasulacit.Repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/persona")
public class PersonaController {
    
    @Autowired
    PersonaRepository personaRepository;

    private Response response;
    private Date fechaActual;


    /*@ApiOperation(value = "Retorna el listado de todas las personas")*/
    @RequestMapping(method = RequestMethod.GET)
    public Response GetAll() {
        response = new Response();

        try {
            List<Persona> listaPersona = personaRepository.findByEliminado(false);
            response.setResponse(listaPersona);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Retorna el matricula filtrando idPersona")*/
    @RequestMapping(method = RequestMethod.GET, value = "/{idPersona}")
    public Response GetById(@PathVariable("idPersona") Integer idPersona) {
        response = new Response();

        try {
            Persona persona = personaRepository.findByIdPersonaInAndEliminadoIn(idPersona, false);
            response.setResponse(persona);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }


    /*@ApiOperation(value = "Agrega una nueva persona")*/
    @RequestMapping(method = RequestMethod.POST)
    public Response Create(@RequestBody Persona personaObj) {
        response = new Response();

        try {
            if (personaObj != null) {
                response.setRequest(personaObj);

                personaObj.setCreadoPor(personaObj.getCreadoPor());
                personaObj.setFechaCreacion(fechaActual);
                personaObj.setFechaActualizacion(fechaActual);
                personaObj.setEliminado(false);

                personaRepository.save(personaObj);
                response.setResponse(personaObj);
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }


    /*@ApiOperation(value = "Modifica la información de una persona")*/
    @RequestMapping(method = RequestMethod.PUT, value = "/{idPersona}")
    public Response Update(@PathVariable("idPersona") Integer idPersona, @RequestBody Persona personaObj) {
        response = new Response();
        Persona personaStored;
        fechaActual = new Date();

        try {
            if (personaObj != null) {
                personaObj.setIdPersona(idPersona);

                response.setRequest(personaObj);

                personaStored = personaRepository.findOne(idPersona);

                if (personaStored != null) {
                    personaObj.setIdPersona(personaStored.getIdPersona());

                    response.setRequest(personaObj);

                    personaObj.setCreadoPor(personaObj.getCreadoPor());
                    personaObj.setFechaCreacion(fechaActual);
                    personaObj.setFechaActualizacion(fechaActual);
                    personaObj.setEliminado(false);

                    personaRepository.save(personaObj);
                    response.setResponse(personaObj);

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

    @RequestMapping(method = RequestMethod.DELETE, value = "/{idPersona}")
    public Response Delete(@PathVariable("idPersona") Integer idPersona) {

        Response response = new Response();
        Persona personaStored;
        try {
            response.setRequest(idPersona);
            personaStored = personaRepository.findByIdPersonaInAndEliminadoIn(idPersona, false);

            if (personaStored != null) {
                personaStored.setEliminado(true);
                personaStored.setFechaActualizacion(new Date());
                personaRepository.delete(personaStored);
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
