package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Alumno;
import com.ulacit.matriculas.matriculasulacit.Repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AlumnoController {
    
    @Autowired
    AlumnoRepository alumnoRepository;
    
    
    @GetMapping("/alumno")
    public List<Alumno> getAllAlumno() {

        return alumnoRepository.findAll();
    }
    
    @PostMapping("/alumno")
    public Alumno createAlumno(@Valid @RequestBody Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    
    @GetMapping("/alumno/{id}")
    public ResponseEntity<Alumno> getAlumnoById(@PathVariable(value = "id") Integer alumnoId) {
        Alumno alumno = alumnoRepository.findOne(alumnoId);
        if (alumno == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(alumno);
    }

    
    @PutMapping("/alumno/{id}")
    public ResponseEntity<Alumno> updateAlumno(@PathVariable(value = "id") Integer alumnoId,
                                                 @RequestBody Alumno alumnoDetails) {
        Alumno alumno = alumnoRepository.findOne(alumnoId);
        if (alumno == null) {
            return ResponseEntity.notFound().build();
        }
        alumno.setCedula(alumnoDetails.getCedula());
        alumno.setNombre(!alumnoDetails.getNombre().equals("") ? alumnoDetails.getNombre(): alumno.getNombre());
        alumno.setApellido(alumnoDetails.getApellido()!= null && !alumnoDetails.getApellido().equals("") ? alumnoDetails.getApellido(): alumno.getApellido());
        alumno.setEdad(alumnoDetails.getEdad()!= null ? alumnoDetails.getEdad(): alumno.getEdad());
        alumno.setSexo(alumnoDetails.getSexo()!= null && !alumnoDetails.getSexo().equals("") ? alumnoDetails.getSexo(): alumno.getSexo());
        alumno.setCarrera(alumnoDetails.getCarrera() != null ? alumnoDetails.getCarrera() : alumno.getCarrera());
        alumno.setBeca(alumnoDetails.getBeca()!= null && !alumnoDetails.getBeca().equals("") ? alumnoDetails.getBeca(): alumno.getBeca());

        Alumno updatedAlumno = alumnoRepository.save(alumno);
        return ResponseEntity.ok(updatedAlumno);
    }

    
    @DeleteMapping("/alumno/{id}")
    public ResponseEntity<Alumno> deleteAlumno(@PathVariable(value = "id") Integer alumnoId) {
        Alumno alumno = alumnoRepository.findOne(alumnoId);
        if (alumno == null) {
            return ResponseEntity.notFound().build();
        }
        alumnoRepository.delete(alumno);
        return ResponseEntity.ok().build();
    }
}
