package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Profesor;
import com.ulacit.matriculas.matriculasulacit.Repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProfesorController {
    
    @Autowired
    ProfesorRepository profesorRepository;
    
    
    @GetMapping("/profesor")
    public List<Profesor> getAllProfesor() {

        return profesorRepository.findAll();
    }
    
    @PostMapping("/profesor")
    public Profesor createProfesor(@Valid @RequestBody Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    
    @GetMapping("/profesor/{id}")
    public ResponseEntity<Profesor> getProfesorById(@PathVariable(value = "id") Integer profesorId) {
        Profesor profesor = profesorRepository.findOne(profesorId);
        if (profesor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(profesor);
    }

    
    @PutMapping("/profesor/{id}")
    public ResponseEntity<Profesor> updateProfesor(@PathVariable(value = "id") Integer profesorId,
                                                 @RequestBody Profesor profesorDetails) {
        Profesor profesor = profesorRepository.findOne(profesorId);
        if (profesor == null) {
            return ResponseEntity.notFound().build();
        }
        profesor.setCedula(profesorDetails.getCedula());
        profesor.setNombre(!profesorDetails.getNombre().equals("") ? profesorDetails.getNombre(): profesor.getNombre());
        profesor.setApellido(profesorDetails.getApellido()!= null && !profesorDetails.getApellido().equals("") ? profesorDetails.getApellido(): profesor.getApellido());
        profesor.setEdad(profesorDetails.getEdad()!= null ? profesorDetails.getEdad(): profesor.getEdad());
        profesor.setSexo(profesorDetails.getSexo()!= null && !profesorDetails.getSexo().equals("") ? profesorDetails.getSexo(): profesor.getSexo());
        profesor.setEspecialidad(profesorDetails.getEspecialidad()!= null && !profesorDetails.getEspecialidad().equals("") ? profesorDetails.getEspecialidad(): profesor.getEspecialidad());

        Profesor updatedProfesor = profesorRepository.save(profesor);
        return ResponseEntity.ok(updatedProfesor);
    }

    
    @DeleteMapping("/profesor/{id}")
    public ResponseEntity<Profesor> deleteProfesor(@PathVariable(value = "id") Integer profesorId) {
        Profesor profesor = profesorRepository.findOne(profesorId);
        if (profesor == null) {
            return ResponseEntity.notFound().build();
        }
        profesorRepository.delete(profesor);
        return ResponseEntity.ok().build();
    }
}
