package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Constante;
import com.ulacit.matriculas.matriculasulacit.Modelos.Response;
import com.ulacit.matriculas.matriculasulacit.Modelos.Contacto;
import com.ulacit.matriculas.matriculasulacit.Repository.ContactoRepository;
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

    private Response response;
    private Date currentDate;


    /* @ApiOperation(value = "Retorna el listado de todas las contacto")*/
    @RequestMapping(method = RequestMethod.GET)
    public Response GetAll() {
        response = new Response();

        try {
            List<Contacto> listaContacto = contactoRepository.findByDeleted(false);
            response.setResponse(listaContacto);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Obtiene un alumno filtrándolo por el parámetro idContacto")*/
    @RequestMapping(method = RequestMethod.GET, value = "/{idContacto}")
    public Response GetById(@PathVariable("idContacto") Integer idContacto) {
        response = new Response();

        try {
            Contacto contacto = contactoRepository.findByIdContactoInAndDeletedIn(idContacto, false);
            response.setResponse(contacto);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Agrega una nueva contacto")*/
    @RequestMapping(method = RequestMethod.POST)
    public Response Create(@RequestBody Contacto contactoObj) {
        response = new Response();

        try {
            if (contactoObj != null) {
                response.setRequest(contactoObj);

                contactoObj.setUpdatedBy(contactoObj.getCreatedBy());
                contactoObj.setCreationDate(currentDate);
                contactoObj.setUpdatedDate(currentDate);
                contactoObj.setDeleted(false);

                contactoRepository.save(contactoObj);
                response.setResponse(contactoObj);
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Modifica la información de un contacto")*/
    @RequestMapping(method = RequestMethod.PUT, value = "/{idContacto}")
    public Response Update(@PathVariable("idContacto") Integer idContacto, @RequestBody Contacto contactoObj) {
        response = new Response();
        Contacto contacto;
        currentDate = new Date();

        try {
            if (contactoObj != null) {
                contactoObj.setIdContacto(idContacto);
                response.setRequest(contactoObj);

                contacto = contactoRepository.findByIdContactoInAndDeletedIn(idContacto, false);

                if (contacto != null)

                {

                    contacto.setIdContacto(contactoObj.getIdContacto());
                    contacto.setNombre(contactoObj.getNombre());
                    contacto.setDescripcion(contactoObj.getDescripcion());
                    contacto.setPersona(contactoObj.getPersona());
                    contacto.setTipo(contactoObj.getTipo());
                    contacto.setDeleted(false);
                    contacto.setCreationDate(contactoObj.getCreationDate());
                    contacto.setUpdatedBy(contactoObj.getUpdatedBy());
                    contacto.setUpdatedDate(currentDate);
                    contacto.setCreatedBy(contactoObj.getCreatedBy());


                    contactoRepository.save(contacto);

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
            contactoStored = contactoRepository.findByIdContactoInAndDeletedIn(idContacto, false);

            if (contactoStored != null) {
                contactoStored.setDeleted(true);
                contactoStored.setUpdatedDate(new Date());
                contactoRepository.save(contactoStored);
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
