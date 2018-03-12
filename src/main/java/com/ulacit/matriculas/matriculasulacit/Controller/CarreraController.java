package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Constante;
import com.ulacit.matriculas.matriculasulacit.Modelos.Response;
import com.ulacit.matriculas.matriculasulacit.Modelos.Carrera;
import com.ulacit.matriculas.matriculasulacit.Repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/carrera")
public class CarreraController {
    
    @Autowired
    CarreraRepository carreraRepository;

    private Response response;
    private Date currentDate;


    /* @ApiOperation(value = "Retorna el listado de todas las carreras")*/
    @RequestMapping(method = RequestMethod.GET)
    public Response GetAll() {
        response = new Response();

        try {
            List<Carrera> listaCarrera = carreraRepository.findByDeleted(false);
            response.setResponse(listaCarrera);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Obtiene un alumno filtrándolo por el parámetro idCarrera")*/
    @RequestMapping(method = RequestMethod.GET, value = "/{idCarrera}")
    public Response GetById(@PathVariable("idCarrera") Integer idCarrera) {
        response = new Response();

        try {
            Carrera carrera = carreraRepository.findByIdCarreraInAndDeletedIn(idCarrera, false);
            response.setResponse(carrera);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Agrega una nueva carrera")*/
    @RequestMapping(method = RequestMethod.POST)
    public Response Create(@RequestBody Carrera carreraObj) {
        response = new Response();

        try {
            if (carreraObj != null) {
                response.setRequest(carreraObj);

                carreraObj.setUpdatedBy(carreraObj.getCreatedBy());
                carreraObj.setCreationDate(currentDate);
                carreraObj.setUpdatedDate(currentDate);
                carreraObj.setDeleted(false);

                carreraRepository.save(carreraObj);
                response.setResponse(carreraObj);
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Modifica la información de un carreras")*/
    @RequestMapping(method = RequestMethod.PUT, value = "/{idCarrera}")
    public Response Update(@PathVariable("idCarrera") Integer idCarrera, @RequestBody Carrera carreraObj) {
        response = new Response();
        Carrera carrera;
        currentDate = new Date();

        try {
            if (carreraObj != null) {
                carreraObj.setIdCarrera(idCarrera);
                response.setRequest(carreraObj);

                carrera = carreraRepository.findByIdCarreraInAndDeletedIn(idCarrera, false);

                if (carrera != null)

                {

                    carrera.setIdCarrera(carreraObj.getIdCarrera());
                    carrera.setCodigo(carreraObj.getCodigo());
                    carrera.setNombre(carreraObj.getNombre());
                    carrera.setTotalCreditos(carreraObj.getTotalCreditos());
                    carrera.setDeleted(false);
                    carrera.setCreationDate(carreraObj.getCreationDate());
                    carrera.setUpdatedBy(carreraObj.getUpdatedBy());
                    carrera.setUpdatedDate(currentDate);
                    carrera.setCreatedBy(carreraObj.getCreatedBy());


                    carreraRepository.save(carrera);

                    response.setResponse(carreraObj);
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

    @RequestMapping(method = RequestMethod.DELETE, value = "/{idCarrera}")
    public Response Delete(@PathVariable("idCarrera") Integer idCarrera) {

        Response response = new Response();
        Carrera carreraStored;
        try {
            response.setRequest(idCarrera);
            carreraStored = carreraRepository.findByIdCarreraInAndDeletedIn(idCarrera, false);

            if (carreraStored != null) {
                carreraStored.setDeleted(true);
                carreraStored.setUpdatedDate(new Date());
                carreraRepository.save(carreraStored);
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
