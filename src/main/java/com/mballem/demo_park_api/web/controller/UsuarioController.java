package com.mballem.demo_park_api.web.controller;
import com.mballem.demo_park_api.entity.Usuario;
import com.mballem.demo_park_api.service.UsuarioService;
import com.mballem.demo_park_api.web.dto.UsuarioCreateDto;
import com.mballem.demo_park_api.web.dto.UsuarioResponseDto;
import com.mballem.demo_park_api.web.dto.mapper.UsuarioMapper;
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
    public ResponseEntity<UsuarioResponseDto> create(@RequestBody UsuarioCreateDto createDto) {
        Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id){
        Usuario user = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> updatePassword(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario user = usuarioService.editarSenha(id, usuario.getPassword());
        return ResponseEntity.ok(user);
    }



}


