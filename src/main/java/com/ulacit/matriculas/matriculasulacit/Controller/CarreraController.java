package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Carrera;
import com.ulacit.matriculas.matriculasulacit.Repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CarreraController {
    
    @Autowired
    CarreraRepository carreraRepository;
    
    // Obtener todas las carreras
    @GetMapping("/carrera")
    public List<Carrera> getAllCarrera() {

        return carreraRepository.findAll();
    }
    
    // Crear una carrera
    @PostMapping("/carrera")
    public Carrera createCarrera(@Valid @RequestBody Carrera objcarrera) {
        return carreraRepository.save(objcarrera);
    }

    // Obtener una carrera por id
    @GetMapping("/carrera/{id}")
    public ResponseEntity<Carrera> getMateriaById(@PathVariable(value = "id") Integer carreraId) {
        Carrera objcarrera = carreraRepository.findOne(carreraId);
        if (objcarrera == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(objcarrera);
    }
    
    // Actualizar una carrera
    @PutMapping("/carrera/{id}")
    public ResponseEntity<Carrera> updateCarrera(@PathVariable(value = "id") Integer carreraId,
                                                 @RequestBody Carrera carreraCuerpo) {
        Carrera objcarrera = carreraRepository.findOne(carreraId);
        if (objcarrera == null) {
            return ResponseEntity.notFound().build();
        }
        objcarrera.setCodigo(carreraCuerpo.getCodigo() != null && !carreraCuerpo.getCodigo().equals("") ? carreraCuerpo.getCodigo() : objcarrera.getCodigo());
        objcarrera.setNombre(carreraCuerpo.getNombre() != null && !carreraCuerpo.getNombre().equals("") ? carreraCuerpo.getNombre() : objcarrera.getNombre());
        objcarrera.setTotalCreditos(carreraCuerpo.getTotalCreditos()!= null ? carreraCuerpo.getTotalCreditos(): objcarrera.getTotalCreditos());
       
        

        Carrera updatedCarrera = carreraRepository.save(objcarrera);
        return ResponseEntity.ok(updatedCarrera);
    }
    
    // Eliminar una carrera
    @DeleteMapping("/carrera/{id}")
    public ResponseEntity<Carrera> deleteCarrera(@PathVariable(value = "id") Integer carreraId) {
        Carrera objcarrera = carreraRepository.findOne(carreraId);
        if (objcarrera == null) {
            return ResponseEntity.notFound().build();
        }
        carreraRepository.delete(objcarrera);
        return ResponseEntity.ok().build();
    }
}
