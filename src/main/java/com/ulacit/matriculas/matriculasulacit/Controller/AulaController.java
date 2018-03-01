package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Aula;
import com.ulacit.matriculas.matriculasulacit.Modelos.Profesor;
import com.ulacit.matriculas.matriculasulacit.Repository.AulaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AulaController {
    AulaRepository aulaRepository;

    @GetMapping("/aula")
    public List<Aula> getAllAula() {

        return aulaRepository.findAll();
    }

    @GetMapping("/aulitas")
    public String getTest() {

        return "Hola";
    }

    @PostMapping("/aula")
    public Aula createAula(@Valid @RequestBody Aula aula) {
        return aulaRepository.save(aula);
    }

    @GetMapping("/aula/{id}")
    public ResponseEntity<Aula> getAulaById(@PathVariable(value = "id") Integer aulaId) {
        Aula aula = aulaRepository.findOne(aulaId);
        if (aula == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(aula);
    }

    @PutMapping("/aula/{id}")
    public ResponseEntity<Aula> updateAula(@PathVariable(value = "id") Integer aulaId,
                                                   @RequestBody Aula aulaDetails) {
        Aula aula = aulaRepository.findOne(aulaId);
        if (aula == null) return ResponseEntity.notFound().build();

        aula.setTipo(aulaDetails.getTipo());
        aula.setArea(aulaDetails.getArea());
        aula.setNumeroAula(aulaDetails.getNumeroAula());

        Aula updatedAula = aulaRepository.save(aula);

        return ResponseEntity.ok(updatedAula);
    }

    @DeleteMapping("/aula/{id}")
    public ResponseEntity<Aula> deleteAula(@PathVariable(value = "id") Integer aulaId) {
        Aula aula = aulaRepository.findOne(aulaId);
        if (aula == null) return ResponseEntity.notFound().build();

        aulaRepository.delete(aula);
        return ResponseEntity.ok().build();
    }

}
