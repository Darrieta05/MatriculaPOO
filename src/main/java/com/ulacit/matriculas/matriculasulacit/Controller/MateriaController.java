package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Materia;
import com.ulacit.matriculas.matriculasulacit.Repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Materia")
public class MateriaController {

    @Autowired
    MateriaRepository materiaRepository;


    // Obtener todas las calles
    @GetMapping("/materia")
    public List<Materia> getAllMateria() {

        return materiaRepository.findAll();
    }

    // Crear una materia
    @PostMapping("/materia")
    public Materia createCalle(@Valid @RequestBody Materia materia) {
        return materiaRepository.save(materia);
    }

    // Obtener una materia por id
    @GetMapping("/materia/{id}")
    public ResponseEntity<Materia> getMateriaById(@PathVariable(value = "id") Integer materiaId) {
        Materia calles = materiaRepository.findOne(materiaId);
        if (calles == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(calles);
    }

    // Actualizar una materia
    @PutMapping("/materia/{id}")
    public ResponseEntity<Materia> updateMateria(@PathVariable(value = "id") Integer materiaId,
                                                 @RequestBody Materia materiaDetails) {
        Materia materia = materiaRepository.findOne(materiaId);
        if (materia == null) {
            return ResponseEntity.notFound().build();
        }
        materia.setCodigo(materiaDetails.getNombre() != null && !materiaDetails.getNombre().equals("") ? materiaDetails.getNombre() : materia.getNombre());
        materia.setCosto(materiaDetails.getCosto() != null ? materiaDetails.getCosto() : materia.getCosto());
        materia.setCreditos(materiaDetails.getCreditos() != null ? materiaDetails.getCreditos() : materia.getCreditos());
        materia.setAula(materiaDetails.getAula() != null ? materiaDetails.getAula() : materia.getAula());
        materia.setNombre(materiaDetails.getNombre() != null && !materiaDetails.getNombre().equals("") ? materiaDetails.getNombre() : materia.getNombre());
        materia.setCarrera(materiaDetails.getCarrera() != null ? materiaDetails.getCarrera() : materia.getCarrera());

        Materia updatedMateria = materiaRepository.save(materia);
        return ResponseEntity.ok(updatedMateria);
    }

    // Eliminar una materia
    @DeleteMapping("/materia/{id}")
    public ResponseEntity<Materia> deleteMateria(@PathVariable(value = "id") Integer materiaId) {
        Materia materia = materiaRepository.findOne(materiaId);
        if (materia == null) {
            return ResponseEntity.notFound().build();
        }
        materiaRepository.save(materia);
        return ResponseEntity.ok().build();
    }
}
