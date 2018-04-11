package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Constante;
import com.ulacit.matriculas.matriculasulacit.Modelos.Response;
import com.ulacit.matriculas.matriculasulacit.Modelos.Aula;
import com.ulacit.matriculas.matriculasulacit.Repository.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/aula")
public class AulaController {

    @Autowired
    AulaRepository aulaRepository;

    private Response response;

    /* @ApiOperation(value = "Retorna el listado de todas las aulas")*/
    @RequestMapping(method = RequestMethod.GET)
    public Response GetAll() {
        response = new Response();

        try {
            List<Aula> listaAula = aulaRepository.findAll();
            response.setResponse(listaAula);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Obtiene un alumno filtrándolo por el parámetro idAula")*/
    @RequestMapping(method = RequestMethod.GET, value = "/{idAula}")
    public Response GetById(@PathVariable("idAula") Integer idAula) {
        response = new Response();

        try {
            Aula aula = aulaRepository.findOne(idAula);
            response.setResponse(aula);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Agrega una nueva aula")*/
    @RequestMapping(method = RequestMethod.POST)
    public Response Create(@RequestBody Aula aulaObj) {
        response = new Response();

        try {
            if (aulaObj != null) {
                response.setRequest(aulaObj);

                aulaRepository.save(aulaObj);
                response.setResponse(aulaObj);
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Modifica la información de un aulas")*/
    @RequestMapping(method = RequestMethod.PUT, value = "/{idAula}")
    public Response Update(@PathVariable("idAula") Integer idAula, @RequestBody Aula aulaObj) {
        response = new Response();
        Aula aulaStored;

        try {
            if (aulaObj != null) {
                aulaObj.setIdAula(idAula);

                response.setRequest(aulaObj);

                aulaStored = aulaRepository.findOne(idAula);

                if (aulaStored != null) {
                    aulaObj.setIdAula(aulaStored.getIdAula());
                    response.setRequest(aulaObj);

                    aulaRepository.save(aulaObj);
                    response.setResponse(aulaObj);

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
}
