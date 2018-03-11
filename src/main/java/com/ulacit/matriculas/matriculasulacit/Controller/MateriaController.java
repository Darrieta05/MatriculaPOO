package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Constants;
import com.ulacit.matriculas.matriculasulacit.Modelos.Materia;
import com.ulacit.matriculas.matriculasulacit.Modelos.ResponseObject;
import com.ulacit.matriculas.matriculasulacit.Repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/materia")
public class MateriaController {

    @Autowired
    MateriaRepository materiaRepository;

    private ResponseObject response;
    private Date currentDate;


    /* @ApiOperation(value = "Retorna el listado de todas las materias")*/
    @RequestMapping(method = RequestMethod.GET)
    public ResponseObject GetAll() {
        response = new ResponseObject();

        try {
            List<Materia> listaMateria = materiaRepository.findByDeleted(false);
            response.setResponse(listaMateria);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constants.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Obtiene un alumno filtrándolo por el parámetro idMateria")*/
    @RequestMapping(method = RequestMethod.GET, value = "/{idMateria}")
    public ResponseObject GetById(@PathVariable("idMateria") Integer idMateria) {
        response = new ResponseObject();

        try {
            Materia materia = materiaRepository.findByIdMateriaInAndDeletedIn(idMateria, false);
            response.setResponse(materia);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constants.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Agrega una nueva materia")*/
    @RequestMapping(method = RequestMethod.POST)
    public ResponseObject Create(@RequestBody Materia materiaObj) {
        response = new ResponseObject();

        try {
            if (materiaObj != null) {
                response.setRequest(materiaObj);

                materiaObj.setUpdatedBy(materiaObj.getCreatedBy());
                materiaObj.setCreationDate(currentDate);
                materiaObj.setUpdatedDate(currentDate);
                materiaObj.setDeleted(false);

                materiaRepository.save(materiaObj);
                response.setResponse(materiaObj);
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constants.badRequest);
        }

        return response;
    }

    /*@ApiOperation(value = "Modifica la información de una materia")*/
    @RequestMapping(method = RequestMethod.PUT, value = "/{idMateria}")
    public ResponseObject Update(@PathVariable("idMateria") Integer idMateria, @RequestBody Materia materiaObj) {
        response = new ResponseObject();
        Materia materia;
        currentDate = new Date();

        try {
            if (materiaObj != null) {
                materiaObj.setIdMateria(idMateria);
                response.setRequest(materiaObj);

                materia = materiaRepository.findByIdMateriaInAndDeletedIn(idMateria, false);

                if (materia != null)

                {

                    materia.setIdMateria(materiaObj.getIdMateria());
                    materia.setNombre(materiaObj.getNombre());
                    materia.setCodigo(materiaObj.getCodigo());
                    materia.setAula(materiaObj.getAula());
                    materia.setCarrera(materiaObj.getCarrera());
                    materia.setCosto(materiaObj.getCosto());
                    materia.setCreditos(materiaObj.getCreditos());
                    materia.setDeleted(false);
                    materia.setCreationDate(materiaObj.getCreationDate());
                    materia.setUpdatedBy(materiaObj.getUpdatedBy());
                    materia.setUpdatedDate(currentDate);
                    materia.setCreatedBy(materiaObj.getCreatedBy());


                    materiaRepository.save(materia);

                    response.setResponse(materiaObj);
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

  /*  @RequestMapping(method = RequestMethod.DELETE, value = "/{idMateria}")
    public ResponseObject Delete(@PathVariable("idMateria") Integer idMateria) {

        ResponseObject response = new ResponseObject();
        Materia materiaStored;
        try {
            response.setRequest(idMateria);
            materiaStored = materiaRepository.findOne(idMateria);

            if (materiaStored != null) {
                materiaStored.setDeleted(true);
                materiaStored.setUpdatedDate(new Date());
                materiaRepository.save(materiaStored);
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
