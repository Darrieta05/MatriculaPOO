package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Constants;
import com.ulacit.matriculas.matriculasulacit.Modelos.Persona;
import com.ulacit.matriculas.matriculasulacit.Modelos.ResponseObject;
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

    private ResponseObject response;
    private Date currentDate;


    /*@ApiOperation(value = "Retorna el listado de todas las personas")*/
    @RequestMapping(method = RequestMethod.GET)
    public ResponseObject GetAll() {
        response = new ResponseObject();

        try {
            List<Persona> listaPersona = personaRepository.findByDeleted(false);
            response.setResponse(listaPersona);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constants.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Retorna el matricula filtrando idPersona")*/
    @RequestMapping(method = RequestMethod.GET, value = "/{idPersona}")
    public ResponseObject GetById(@PathVariable("idPersona") Integer idPersona) {
        response = new ResponseObject();

        try {
            Persona persona = personaRepository.findByIdPersonaInAndDeletedIn(idPersona, false);
            response.setResponse(persona);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constants.badRequest);
        }

        return response;
    }


    /*@ApiOperation(value = "Agrega una nueva persona")*/
    @RequestMapping(method = RequestMethod.POST)
    public ResponseObject Create(@RequestBody Persona personaObj) {
        response = new ResponseObject();

        try {
            if (personaObj != null) {
                response.setRequest(personaObj);

                personaObj.setUpdatedBy(personaObj.getCreatedBy());
                personaObj.setCreationDate(currentDate);
                personaObj.setUpdatedDate(currentDate);
                personaObj.setDeleted(false);

                personaRepository.save(personaObj);
                response.setResponse(personaObj);
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constants.badRequest);
        }

        return response;
    }


    /*@ApiOperation(value = "Modifica la información de una persona")*/
    @RequestMapping(method = RequestMethod.PUT, value = "/{idPersona}")
    public ResponseObject Update(@PathVariable("idPersona") Integer idPersona, @RequestBody Persona personaObj) {
        response = new ResponseObject();
        Persona persona;
        currentDate = new Date();

        try {
            if (personaObj != null) {
                personaObj.setIdPersona(idPersona);
                response.setRequest(personaObj);

                persona = personaRepository.findByIdPersonaInAndDeletedIn(idPersona, false);

                if (persona != null)

                {

                    persona.setIdPersona(personaObj.getIdPersona());
                    persona.setCedula(personaObj.getCedula());
                    persona.setApellido(personaObj.getApellido());
                    persona.setEdad(personaObj.getEdad());
                    persona.setSexo(personaObj.getSexo());
                    persona.setDeleted(false);
                    persona.setCreationDate(personaObj.getCreationDate());
                    persona.setUpdatedBy(personaObj.getUpdatedBy());
                    persona.setUpdatedDate(currentDate);
                    persona.setCreatedBy(personaObj.getCreatedBy());


                    personaRepository.save(persona);

                    response.setResponse(personaObj);
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

 /*   @RequestMapping(method = RequestMethod.DELETE, value = "/{idPersona}")
    public ResponseObject Delete(@PathVariable("idPersona") Integer idPersona) {

        ResponseObject response = new ResponseObject();
        Persona personaStored;
        try {
            response.setRequest(idPersona);
            personaStored = personaRepository.findOne(idPersona);

            if (personaStored != null) {
                personaStored.setDeleted(true);
                personaStored.setUpdatedDate(new Date());
                personaRepository.save(personaStored);
                response.setResponse(Constants.itemDeleted);
            } else {
                throw new Exception(Constants.itemNotFound);
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constants.badRequest);
        }
        return response;
    }*/

}
