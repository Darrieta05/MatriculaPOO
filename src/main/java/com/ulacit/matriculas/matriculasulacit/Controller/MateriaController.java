package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.*;
import com.ulacit.matriculas.matriculasulacit.Repository.AulaRepository;
import com.ulacit.matriculas.matriculasulacit.Repository.CarreraRepository;
import com.ulacit.matriculas.matriculasulacit.Repository.MateriaRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/materia")
public class MateriaController {

    @Autowired
    MateriaRepository materiaRepository;

    @Autowired
    AulaRepository aulaRepository;

    @Autowired
    CarreraRepository carreraRepository;

    private Response response;

    @ApiOperation(value = "Retorna el listado de todas las materias")
    @RequestMapping(method = RequestMethod.GET)
    public Response GetAll() {
        response = new Response();

        try {
            List<Materia> listaMateria = materiaRepository.findAll();
            response.setResponse(listaMateria);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    @ApiOperation(value = "Obtiene un alumno filtrándolo por el parámetro idMateria")
    @RequestMapping(method = RequestMethod.GET, value = "/{idMateria}")
    public Response GetById(@PathVariable("idMateria") Integer idMateria) {
        response = new Response();

        try {
            Materia materiaObj = materiaRepository.findOne(idMateria);
            response.setResponse(materiaObj);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Agrega una nueva materia")*/
    @RequestMapping(method = RequestMethod.POST)
    public Response Create(@RequestBody Materia materiaObj) {
        response = new Response();

        try {
            if (materiaObj != null) {
                response.setRequest(materiaObj);

                Aula aulaObj = aulaRepository.findOne(materiaObj.getAula().getIdAula());
                Carrera carreraObj = carreraRepository.findOne(materiaObj.getCarrera().getIdCarrera());

                if (aulaObj != null && carreraObj != null) {
                    materiaObj.setAula(aulaObj);
                    materiaObj.setCarrera(carreraObj);
                    materiaRepository.save(materiaObj);
                    response.setResponse(materiaObj);
                }
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    @ApiOperation(value = "Modifica la información de una materia")
    @RequestMapping(method = RequestMethod.PUT, value = "/{idMateria}")
    public Response Update(@PathVariable("idMateria") Integer idMateria, @RequestBody Materia materiaObj) {
        response = new Response();
        Materia materiaStored;

        try {
            if (materiaObj != null) {
                materiaObj.setIdMateria(idMateria);

                response.setRequest(materiaObj);

                materiaStored = materiaRepository.findOne(idMateria);

                if (materiaStored != null) {
                    materiaObj.setIdMateria(materiaObj.getIdMateria());
                    response.setRequest(materiaObj);

                    materiaRepository.save(materiaObj);
                    response.setResponse(materiaObj);

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

    @ApiOperation(value = "Elimina lógicamente una materia")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{idMateria}")
    public Response Delete(@PathVariable("idMateria") Integer idMateria) {

        response = new Response();
        Materia materiaStored;
        try {
            response.setRequest(idMateria);
            materiaStored = materiaRepository.findOne(idMateria);

            if (materiaStored != null) {
                materiaRepository.save(materiaStored);
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
