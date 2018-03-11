package com.ulacit.matriculas.matriculasulacit.Controller;


import com.ulacit.matriculas.matriculasulacit.Modelos.Alumno;
import com.ulacit.matriculas.matriculasulacit.Modelos.Constants;
import com.ulacit.matriculas.matriculasulacit.Modelos.ResponseObject;
import com.ulacit.matriculas.matriculasulacit.Repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /*@ApiOperation(value = "Obtiene un alumno filtrándolo por el parámetro idAlumno")*/
    @RequestMapping(method = RequestMethod.GET, value = "/{idAlumno}")
    public ResponseObject GetById(@PathVariable("idAula") Integer idAlumno) {
        response = new ResponseObject();

        try {
            Alumno alumno = alumnoRepository.findByIdAlumnoInAndDeletedIn(idAlumno, false);
            response.setResponse(alumno);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constants.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Agrega una nueva alumno")*/
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
    @RequestMapping(method = RequestMethod.PUT, value = "/{idAlumno}")
    public ResponseObject Update(@PathVariable("idAlumno") Integer idAlumno, @RequestBody Alumno alumnoObj) {
        response = new ResponseObject();
        Alumno alumno;
        currentDate = new Date();

        try {
            if (alumnoObj != null) {
                alumnoObj.setIdAlumno(idAlumno);
                response.setRequest(alumnoObj);

                alumno = alumnoRepository.findByIdAlumnoInAndDeletedIn(idAlumno, false);

                if (alumno != null)

                {
                    alumno.setIdAlumno(alumnoObj.getIdAlumno());
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

    /*@RequestMapping(method = RequestMethod.DELETE, value = "/{idAlumno}")
    public ResponseObject Delete(@PathVariable("idAlumno") Integer idAlumno) {

        ResponseObject response = new ResponseObject();
        Alumno alumnoStored;
        try {
            response.setRequest(idAlumno);
            alumnoStored = alumnoRepository.findOne(idAlumno);

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
    }*/
}
