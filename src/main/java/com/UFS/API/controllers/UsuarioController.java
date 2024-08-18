package com.UFS.API.controllers;


import com.UFS.API.dtos.UsuarioRecordDto;
import com.UFS.API.models.UsuarioModel;
import com.UFS.API.repositories.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Operation(description = "Adiciona um usuário!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário adicionado"),
            @ApiResponse(responseCode = "400", description = "Usuário não adicionado")
            }
    )
    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioModel> salvarUsuario(@RequestBody @Valid UsuarioRecordDto usuarioRecordDto){

        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioRecordDto, usuarioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuarioModel));

    }


    @Operation(description = "Lista todos os usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários listados"),
            @ApiResponse(responseCode = "400", description = "Não conseguiu listar os usuários")
            }
    )
    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioModel>> listarUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());
    }


    @Operation(description = "Lista o usuário pelo cpf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário listado"),
            @ApiResponse(responseCode = "400", description = "Não conseguiu encontrar o usuário")
            }
    )
    @GetMapping("/usuarios/{cpf}")
    public ResponseEntity<Object> listarUsuario(@PathVariable(value = "cpf") long cpf){
        Optional<UsuarioModel> usuario0 = usuarioRepository.findByCpf(cpf);
        if(usuario0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuario0.get());
    }

    @Operation(description = "Edita o usuário pelo cpf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário editado"),
            @ApiResponse(responseCode = "400", description = "Não conseguiu editar/encontrar o usuário")
            }
    )
    @PutMapping("/usuarios/{cpf}")
    public ResponseEntity<Object> atualizarUsuario(@PathVariable(value = "cpf") long cpf,
                                                   @RequestBody @Valid UsuarioRecordDto usuarioRecordDto){

        Optional<UsuarioModel> usuario0 = usuarioRepository.findByCpf(cpf);
        if(usuario0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        var usuarioModel = usuario0.get();
        BeanUtils.copyProperties(usuarioRecordDto, usuarioModel);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuarioModel));

    }

    @Operation(description = "Deleta o usuário pelo cpf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado"),
            @ApiResponse(responseCode = "400", description = "Não conseguiu deletar/encontrar o usuário")
            }
    )
    @DeleteMapping("/usuarios/{cpf}")
    public ResponseEntity<Object> removerUsuario(@PathVariable(value = "cpf") long cpf){

        Optional<UsuarioModel> usuario0 = usuarioRepository.findByCpf(cpf);
        if(usuario0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        usuarioRepository.delete(usuario0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuário removido com sucesso!");

    }

}
