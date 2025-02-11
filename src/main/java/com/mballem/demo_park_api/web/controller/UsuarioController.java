package com.mballem.demo_park_api.web.controller;

import com.mballem.demo_park_api.entity.Usuario;
import com.mballem.demo_park_api.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos(){
        return ResponseEntity.ok(usuarioService.listarTodos());
    }
    @PostMapping
    public ResponseEntity<Usuario>create(@RequestBody Usuario usuario){
        if (usuario.getRole() == null) {
            throw new IllegalArgumentException("O campo 'role' não pode ser nulo");
        }
         Usuario user = usuarioService.salvar(usuario);
         return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
