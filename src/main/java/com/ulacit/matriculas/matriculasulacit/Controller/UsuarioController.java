package com.ulacit.matriculas.matriculasulacit.Controller;


import com.ulacit.matriculas.matriculasulacit.Modelos.Constante;
import com.ulacit.matriculas.matriculasulacit.Modelos.Response;
import com.ulacit.matriculas.matriculasulacit.Modelos.Usuario;
import com.ulacit.matriculas.matriculasulacit.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    private Response response;


    /*@ApiOperation(value = "Retorna el listado de todas las usuario")*/
    @RequestMapping(method = RequestMethod.GET)
    public Response GetAll() {
        response = new Response();

        try {
            List<Usuario> listaUsuario = usuarioRepository.findAll();
            response.setResponse(listaUsuario);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Retorna el matricula filtrando idUsuario")*/
    @RequestMapping(method = RequestMethod.GET, value = "/{idUsuario}")
    public Response GetById(@PathVariable("idUsuario") Integer idUsuario) {
        response = new Response();

        try {
            Usuario usuario = usuarioRepository.findOne(idUsuario);
            response.setResponse(usuario);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }


    /*@ApiOperation(value = "Agrega una nueva usuario")*/
    @RequestMapping(method = RequestMethod.POST)
    public Response Create(@RequestBody Usuario usuarioObj) {
        response = new Response();

        try {
            if (usuarioObj != null) {
                response.setRequest(usuarioObj);

                usuarioRepository.save(usuarioObj);
                response.setResponse(usuarioObj);
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }


    /*@ApiOperation(value = "Modifica la información de una usuario")*/
    @RequestMapping(method = RequestMethod.PUT, value = "/{idUsuario}")
    public Response Update(@PathVariable("idUsuario") Integer idUsuario, @RequestBody Usuario usuarioObj) {
        response = new Response();
        Usuario usuarioStored;

        try {
            if (usuarioObj != null) {
                usuarioObj.setIdUsuario(idUsuario);

                response.setRequest(usuarioObj);

                usuarioStored = usuarioRepository.findOne(idUsuario);

                if (usuarioStored != null) {
                    usuarioObj.setIdUsuario(usuarioStored.getIdUsuario());
                    response.setRequest(usuarioObj);

                    usuarioRepository.save(usuarioObj);
                    response.setResponse(usuarioObj);

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

    //@ApiOperation(value = "Elimina la información de una usuario")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{idUsuario}")
    public Response Delete(@PathVariable("idUsuario") Integer idUsuario) {

        Response response = new Response();
        Usuario usuarioStored;
        try {
            response.setRequest(idUsuario);
            usuarioStored = usuarioRepository.findOne(idUsuario);

            if (usuarioStored != null) {
                usuarioRepository.delete(usuarioStored);
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
