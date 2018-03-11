package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Aula;
import com.ulacit.matriculas.matriculasulacit.Modelos.Constants;
import com.ulacit.matriculas.matriculasulacit.Modelos.ResponseObject;
import com.ulacit.matriculas.matriculasulacit.Repository.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/aula")
public class AulaController {
    @Autowired
    AulaRepository aulaRepository;

    private ResponseObject response;
    private Date currentDate;


    /* @ApiOperation(value = "Retorna el listado de todas las aulas")*/
    @RequestMapping(method = RequestMethod.GET)
    public ResponseObject GetAll() {
        response = new ResponseObject();

        try {
            List<Aula> listaAula = aulaRepository.findByDeleted(false);
            response.setResponse(listaAula);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constants.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Obtiene un alumno filtrándolo por el parámetro idAula")*/
    @RequestMapping(method = RequestMethod.GET, value = "/{idAula}")
    public ResponseObject GetById(@PathVariable("idAula") Integer idAula) {
        response = new ResponseObject();

        try {
            Aula aula = aulaRepository.findByIdAulaInAndDeletedIn(idAula, false);
            response.setResponse(aula);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constants.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Agrega una nueva aula")*/
    @RequestMapping(method = RequestMethod.POST)
    public ResponseObject Create(@RequestBody Aula aulaObj) {
        response = new ResponseObject();

        try {
            if (aulaObj != null) {
                response.setRequest(aulaObj);

                aulaObj.setUpdatedBy(aulaObj.getCreatedBy());
                aulaObj.setCreationDate(currentDate);
                aulaObj.setUpdatedDate(currentDate);
                aulaObj.setDeleted(false);

                aulaRepository.save(aulaObj);
                response.setResponse(aulaObj);
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constants.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Modifica la información de un aulas")*/
    @RequestMapping(method = RequestMethod.PUT, value = "/{idAula}")
    public ResponseObject Update(@PathVariable("idAula") Integer idAula, @RequestBody Aula aulaObj) {
        response = new ResponseObject();
        Aula aula;
        currentDate = new Date();

        try {
            if (aulaObj != null) {
                aulaObj.setIdAula(idAula);
                response.setRequest(aulaObj);

                aula = aulaRepository.findByIdAulaInAndDeletedIn(idAula, false);

                if (aula != null)

                {
                    aula.setIdAula(aulaObj.getIdAula());
                    aula.setTipo(aulaObj.getTipo());
                    aula.setArea(aulaObj.getArea());
                    aula.setNumeroAula(aulaObj.getNumeroAula());
                    aula.setDeleted(false);
                    aula.setCreationDate(aulaObj.getCreationDate());
                    aula.setUpdatedBy(aulaObj.getUpdatedBy());
                    aula.setUpdatedDate(currentDate);
                    aula.setCreatedBy(aulaObj.getCreatedBy());


                    aulaRepository.save(aula);

                    response.setResponse(aulaObj);
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

    /*@RequestMapping(method = RequestMethod.DELETE, value = "/{idAula}")
    public ResponseObject Delete(@PathVariable("idAula") Integer idAula) {

        ResponseObject response = new ResponseObject();
        Aula aulaStored;
        try {
            response.setRequest(idAula);
            aulaStored = aulaRepository.findOne(idAula);

            if (aulaStored != null) {
                aulaStored.setDeleted(true);
                aulaStored.setUpdatedDate(new Date());
                aulaRepository.save(aulaStored);
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
