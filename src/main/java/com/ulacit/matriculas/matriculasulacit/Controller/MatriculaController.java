package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Constants;
import com.ulacit.matriculas.matriculasulacit.Modelos.Matricula;
import com.ulacit.matriculas.matriculasulacit.Modelos.ResponseObject;
import com.ulacit.matriculas.matriculasulacit.Repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/matricula")
public class MatriculaController {

    @Autowired
    MatriculaRepository matriculaRepository;

    private ResponseObject response;
    private Date currentDate;


    /*@ApiOperation(value = "Retorna el listado de todas las matriculas")*/
    @RequestMapping(method = RequestMethod.GET)
    public ResponseObject GetAll() {
        response = new ResponseObject();

        try {
            List<Matricula> listaMatricula = matriculaRepository.findByDeleted(false);
            response.setResponse(listaMatricula);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constants.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Retorna el matricula filtrando idMatricula")*/
    @RequestMapping(method = RequestMethod.GET, value = "/{idMatricula}")
    public ResponseObject GetById(@PathVariable("idMatricula") Integer idMatricula) {
        response = new ResponseObject();

        try {
            Matricula matricula = matriculaRepository.findByIdMatriculaInAndDeletedIn(idMatricula, false);
            response.setResponse(matricula);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constants.badRequest);
        }

        return response;
    }


    /*@ApiOperation(value = "Agrega una nueva matricula")*/
    @RequestMapping(method = RequestMethod.POST)
    public ResponseObject Create(@RequestBody Matricula matriculaObj) {
        response = new ResponseObject();

        try {
            if (matriculaObj != null) {
                response.setRequest(matriculaObj);

                matriculaObj.setUpdatedBy(matriculaObj.getCreatedBy());
                matriculaObj.setCreationDate(currentDate);
                matriculaObj.setUpdatedDate(currentDate);
                matriculaObj.setDeleted(false);

                matriculaRepository.save(matriculaObj);
                response.setResponse(matriculaObj);
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constants.badRequest);
        }

        return response;
    }


    /*@ApiOperation(value = "Modifica la información de una matricula")*/
    @RequestMapping(method = RequestMethod.PUT, value = "/{idMatricula}")
    public ResponseObject Update(@PathVariable("idMatricula") Integer idMatricula, @RequestBody Matricula matriculaObj) {
        response = new ResponseObject();
        Matricula matricula;
        currentDate = new Date();

        try {
            if (matriculaObj != null) {
                matriculaObj.setIdMatricula(idMatricula);
                response.setRequest(matriculaObj);

                matricula = matriculaRepository.findByIdMatriculaInAndDeletedIn(idMatricula, false);

                if (matricula != null)

                {

                    matricula.setIdMatricula(matriculaObj.getIdMatricula());
                    matricula.setUsuario(matriculaObj.getUsuario());
                    matricula.setAlumno(matriculaObj.getAlumno());
                    matricula.setFecha(matriculaObj.getFecha());
                    matricula.setMonto(matriculaObj.getMonto());
                    matricula.setTotal(matriculaObj.getTotal());
                    matricula.setDeleted(false);
                    matricula.setCreationDate(matriculaObj.getCreationDate());
                    matricula.setUpdatedBy(matriculaObj.getUpdatedBy());
                    matricula.setUpdatedDate(currentDate);
                    matricula.setCreatedBy(matriculaObj.getCreatedBy());


                    matriculaRepository.save(matricula);

                    response.setResponse(matriculaObj);
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

   /* @RequestMapping(method = RequestMethod.DELETE, value = "/{idMatricula}")
    public ResponseObject Delete(@PathVariable("idMatricula") Integer idMatricula) {

        ResponseObject response = new ResponseObject();
        Matricula matriculaStored;
        try {
            response.setRequest(idMatricula);
            matriculaStored = matriculaRepository.findOne(idMatricula);

            if (matriculaStored != null) {
                matriculaStored.setDeleted(true);
                matriculaStored.setUpdatedDate(new Date());
                matriculaRepository.save(matriculaStored);
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
