package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Constante;
import com.ulacit.matriculas.matriculasulacit.Modelos.Response;
import com.ulacit.matriculas.matriculasulacit.Modelos.DetalleMatricula;
import com.ulacit.matriculas.matriculasulacit.Modelos.Materia;
import com.ulacit.matriculas.matriculasulacit.Modelos.Matricula;
import com.ulacit.matriculas.matriculasulacit.Repository.DetalleMatriculaRepository;
import com.ulacit.matriculas.matriculasulacit.Repository.MateriaRepository;
import com.ulacit.matriculas.matriculasulacit.Repository.MatriculaRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/detalle-matricula")
public class DetalleMatriculaController {
    
    @Autowired
    DetalleMatriculaRepository detalleMatriculaRepository;
    
    @Autowired
    MatriculaRepository matriculaRepository;
    
    @Autowired
    MateriaRepository MateriaRepository;
    
    private Response response;

    /* @ApiOperation(value = "Retorna el listado de todas las detalle matricula")*/
    @RequestMapping(method = RequestMethod.GET, value = "/{idMatricula}")
    public Response GetAll(@PathVariable("idMatricula") Integer idMatricula) {
        response = new Response();
        
        ArrayList<DetalleMatricula> listaDetalleMatriculaFiltro = new ArrayList<DetalleMatricula>();
        
        try {
            List<DetalleMatricula> listaDetalleMatricula = detalleMatriculaRepository.findAll();
            for(DetalleMatricula detalles : listaDetalleMatricula) {
                if (detalles.getMatricula().getIdMatricula() == idMatricula) {
                    listaDetalleMatriculaFiltro.add(detalles);
                }
            }
            response.setResponse(listaDetalleMatriculaFiltro);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }
    
    /*@ApiOperation(value = "Agrega una nueva detalle matricula")*/
    @RequestMapping(method = RequestMethod.POST)
    public Response Create(@RequestBody DetalleMatricula detalleObj) {
        response = new Response();
        
        try {
            if (detalleObj != null) {
                response.setRequest(detalleObj);
                
                Matricula matriculaObj = matriculaRepository.findByIdMatriculaInAndEliminadoIn(detalleObj.getMatricula().getIdMatricula(), false);
                
                Materia materiaObj = MateriaRepository.findOne(detalleObj.getMateria().getIdMateria());
                
                if (matriculaObj != null && materiaObj != null) {
                    detalleObj.setMatricula(matriculaObj);
                    detalleObj.setMateria(materiaObj);
                    detalleMatriculaRepository.save(detalleObj);
                    response.setResponse(detalleObj);
                }
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(Constante.badRequest);
        }

        return response;
    }
}
