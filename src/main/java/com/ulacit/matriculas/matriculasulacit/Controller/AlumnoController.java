package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Alumno;
import com.ulacit.matriculas.matriculasulacit.Modelos.Constants;
import com.ulacit.matriculas.matriculasulacit.Modelos.Matricula;
import com.ulacit.matriculas.matriculasulacit.Modelos.ResponseObject;
import com.ulacit.matriculas.matriculasulacit.Repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/alumno")
public class AlumnoController {

    @Autowired
    AlumnoRepository alumnoRepository;

    private ResponseObject response;
    private Date currentDate;


    /* @ApiOperation(value = "Retorna el listado de todos los alumnos")*/
    @RequestMapping(method = RequestMethod.GET)
    public ResponseObject GetAll() {
        response = new ResponseObject();

        try {
            List<Alumno> listaAlumno = alumnoRepository.findByDeleted(false);
            response.setResponse(listaAlumno);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constants.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Obtiene un alumno filtrándolo por el parámetro alumnoId")*/
    @RequestMapping(method = RequestMethod.GET, value = "/{alumnoId}")
    public ResponseObject GetById(@PathVariable("alumnoId") Integer alumnoId) {
        response = new ResponseObject();

        try {
            Alumno contact = alumnoRepository.findByAlumnoIdInAndDeletedIn(alumnoId, false);
            response.setResponse(contact);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constants.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Agrega una nueva matricula")*/
    @RequestMapping(method = RequestMethod.POST)
    public ResponseObject Create(@RequestBody Alumno alumnoObj) {
        response = new ResponseObject();

        try {
            if (alumnoObj != null) {
                response.setRequest(alumnoObj);

                alumnoObj.setUpdatedBy(alumnoObj.getCreatedBy());
                alumnoObj.setCreationDate(currentDate);
                alumnoObj.setUpdatedDate(currentDate);
                alumnoObj.setDeleted(false);

                alumnoRepository.save(alumnoObj);
                response.setResponse(alumnoObj);
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constants.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Modifica la información de un alumno")*/
    @RequestMapping(method = RequestMethod.PUT, value = "/{alumnoId}")
    public ResponseObject Update(@PathVariable("alumnoId") Integer alumnoId, @RequestBody Alumno alumnoObj) {
        response = new ResponseObject();
        Alumno alumno;
        currentDate = new Date();

        try {
            if (alumnoObj != null) {
                alumnoObj.setAlumnoId(alumnoId);
                response.setRequest(alumnoObj);

                alumno = alumnoRepository.findByAlumnoIdInAndDeletedIn(alumnoId, false);

                if (alumno != null)

                {
                    alumno.setBeca(alumnoObj.getBeca());
                    alumno.setCarrera(alumnoObj.getCarrera());
                    alumno.setDeleted(false);
                    alumno.setCreationDate(alumnoObj.getCreationDate());
                    alumno.setUpdatedBy(alumnoObj.getUpdatedBy());
                    alumno.setUpdatedDate(currentDate);
                    alumno.setCreatedBy(alumnoObj.getCreatedBy());


                    alumnoRepository.save(alumno);

                    response.setResponse(alumnoObj);
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

    @RequestMapping(method = RequestMethod.DELETE, value = "/{alumnoId}")
    public ResponseObject Delete(@PathVariable("alumnoId") Integer alumnoId) {

        ResponseObject response = new ResponseObject();
        Alumno alumnoStored;
        try {
            response.setRequest(alumnoId);
            alumnoStored = alumnoRepository.findOne(alumnoId);

            if (alumnoStored != null) {
                alumnoStored.setDeleted(true);
                alumnoStored.setUpdatedDate(new Date());
                alumnoRepository.save(alumnoStored);
                response.setResponse(Constants.itemDeleted);
            } else {
                throw new Exception(Constants.itemNotFound);
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constants.badRequest);
        }
        return response;
    }

}
