package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Constante;
import com.ulacit.matriculas.matriculasulacit.Modelos.Response;
import com.ulacit.matriculas.matriculasulacit.Modelos.Contacto;
import com.ulacit.matriculas.matriculasulacit.Modelos.Persona;
import com.ulacit.matriculas.matriculasulacit.Repository.ContactoRepository;
import com.ulacit.matriculas.matriculasulacit.Repository.PersonaRepository;
import java.util.ArrayList;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/contacto")
public class ContactoController {
    
    @Autowired
    ContactoRepository contactoRepository;
    
    @Autowired
    PersonaRepository personaRepository;

    private Response response;

    @ApiOperation(value = "Retorna el listado de todas las contacto")
    @RequestMapping(method = RequestMethod.GET)
    public Response GetAll() {
        response = new Response();

        try {
            List<Contacto> listaContacto = contactoRepository.findAll();
            response.setResponse(listaContacto);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    @ApiOperation(value = "Retorna el listado de todas las detalle matricula")
    @RequestMapping(method = RequestMethod.GET, value = "/{idPersona}")
    public Response GetById(@PathVariable("idPersona") Integer idPersona) {
        response = new Response();
        
        ArrayList<Contacto> listaContactoFiltro = new ArrayList<Contacto>();
        
        try {
            List<Contacto> listaContacto = contactoRepository.findAll();
            for(Contacto contacto : listaContacto) {
                if (contacto.getPersona().getIdPersona() == idPersona) {
                    listaContactoFiltro.add(contacto);
                }
            }
            response.setResponse(listaContactoFiltro);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    @ApiOperation(value = "Agrega una nueva contacto")
    @RequestMapping(method = RequestMethod.POST)
    public Response Create(@RequestBody Contacto contactoObj) {
        response = new Response();

        try {
            if (contactoObj != null) {
                response.setRequest(contactoObj);
                
                Persona personaObj = personaRepository.findByIdPersonaInAndEliminadoIn(contactoObj.getPersona().getIdPersona(), false);
                if (personaObj != null) {
                    contactoObj.setPersona(personaObj);
                    contactoRepository.save(contactoObj);
                    response.setResponse(contactoObj);
                }
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    @ApiOperation(value = "Modifica la información de un contacto")
    @RequestMapping(method = RequestMethod.PUT, value = "/{idContacto}")
    public Response Update(@PathVariable("idContacto") Integer idContacto, @RequestBody Contacto contactoObj) {
        response = new Response();
        Contacto contactoStored;

        try {
            if (contactoObj != null) {
                contactoObj.setIdContacto(idContacto);

                response.setRequest(contactoObj);

                contactoStored = contactoRepository.findOne(idContacto);

                if (contactoStored != null) {
                    contactoObj.setIdContacto(contactoStored.getIdContacto());
                    response.setRequest(contactoObj);

                    contactoRepository.save(contactoObj);
                    response.setResponse(contactoObj);

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

    @RequestMapping(method = RequestMethod.DELETE, value = "/{idContacto}")
    public Response Delete(@PathVariable("idContacto") Integer idContacto) {

        Response response = new Response();
        Contacto contactoStored;
        try {
            response.setRequest(idContacto);
            contactoStored = contactoRepository.findOne(idContacto);

            if (contactoStored != null) {
                contactoRepository.delete(contactoStored);
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
