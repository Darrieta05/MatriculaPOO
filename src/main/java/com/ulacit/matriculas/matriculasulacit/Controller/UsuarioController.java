package com.ulacit.matriculas.matriculasulacit.Controller;

import com.ulacit.matriculas.matriculasulacit.Modelos.Usuario;
import com.ulacit.matriculas.matriculasulacit.Repository.UsuarioRepository;
import org.apache.coyote.Response;
import org.omg.CORBA.portable.ResponseHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.certpath.OCSPResponse;

import javax.validation.Valid;
import java.util.List;

public class UsuarioController {
    UsuarioRepository usuarioRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/usuario")
    public List<Usuario> getAllAula() {

        return usuarioRepository.findAll();
    }

    @PostMapping("/usuario")
    public Usuario createAula(@Valid @RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Usuario> getAulaById(@PathVariable(value = "id") Integer usuarioId) {
        Usuario usuario = usuarioRepository.findOne(usuarioId);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(usuario);
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable(value = "id") Integer usuarioId,
                                           @RequestBody Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findOne(usuarioId);
        if (usuario == null) return ResponseEntity.notFound().build();

        usuario.setClave(passwordEncoder.encode(passwordEncoder.encode(usuarioDetails.getClave())));
        usuario.setNombre(usuarioDetails.getNombre());
        Usuario updatedUsuario = usuarioRepository.save(usuario);

        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<Usuario> deleteAula(@PathVariable(value = "id") Integer usuarioId) {
        Usuario aula = usuarioRepository.findOne(usuarioId);
        if (aula == null) return ResponseEntity.notFound().build();

        usuarioRepository.delete(aula);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findOne(usuarioDetails.getIdUsuario());
        if (usuario == null) return ResponseEntity.notFound().build();

        if (passwordEncoder.matches(usuarioDetails.getClave(), usuario.getClave())) {
            return ResponseEntity.ok().body(usuario);
        }

        return ResponseEntity.badRequest().build();
    }
}
