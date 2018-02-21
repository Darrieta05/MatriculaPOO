package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Alumno;
import com.ulacit.matriculas.matriculasulacit.Modelos.Contacto;
import com.ulacit.matriculas.matriculasulacit.Modelos.Persona;
import com.ulacit.matriculas.matriculasulacit.Repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ContactoController {
    
    @Autowired
    ContactoRepository contactoRepository;
    
    
    @GetMapping("/contacto")
    public List<Contacto> getAllContacto() {

        return contactoRepository.findAll();
    }
    
    @PostMapping("/contacto")
    public Contacto createContacto(@Valid @RequestBody Contacto contacto) {
        return contactoRepository.save(contacto);
    }

    
    @GetMapping("/contacto/{id}")
    public ResponseEntity<Contacto> getContactoById(@PathVariable(value = "id") Integer contactoId) {
        Contacto contacto = contactoRepository.findOne(contactoId);
        if (contacto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(contacto);
    }

    
    @PutMapping("/contacto/{id}")
    public ResponseEntity<Contacto> updateContacto(@PathVariable(value = "id") Integer contactoId,
                                                 @RequestBody Contacto contactoDetails) {
        Contacto contacto = contactoRepository.findOne(contactoId);
        if (contacto == null) {
            return ResponseEntity.notFound().build();
        }
        contacto.setNombre(contactoDetails.getNombre() != null && !contactoDetails.getNombre().equals("") ? contactoDetails.getNombre(): contacto.getNombre());
        contacto.setDescripcion(contactoDetails.getDescripcion()!= null && !contactoDetails.getDescripcion().equals("") ? contactoDetails.getDescripcion(): contacto.getDescripcion());
        contacto.setPersona(contactoDetails.getPersona()!= null ? contactoDetails.getPersona() : contacto.getPersona());
        contacto.setTipo(contactoDetails.getTipo()!= null && !contactoDetails.getTipo().equals("") ? contactoDetails.getTipo(): contacto.getTipo());

        Contacto updatedContacto = contactoRepository.save(contacto);
        return ResponseEntity.ok(updatedContacto);
    }

    
    @DeleteMapping("/contacto/{id}")
    public ResponseEntity<Contacto> deleteContacto(@PathVariable(value = "id") Integer contactoId) {
        Contacto contacto = contactoRepository.findOne(contactoId);
        if (contacto == null) {
            return ResponseEntity.notFound().build();
        }
        contactoRepository.delete(contacto);
        return ResponseEntity.ok().build();
    }
    
}
