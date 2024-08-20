package com.example.bdpicpayapi.controller;


import com.example.bdpicpayapi.model.Cliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import com.example.bdpicpayapi.service.ClienteService;

import java.util.List;

@ControllerAdvice
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    private final Validator validator;

    @Autowired
    public ClienteController(ClienteService clienteService, Validator validator) {
        this.clienteService = clienteService;
        this.validator = validator;
    }

    @GetMapping("/selecionarTodos")
    @Operation(summary = "Busca por todos os clientes", description = "Busca todos os clientes cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso!",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    public List<Cliente> selecionar() {
        return clienteService.buscarTodosClientes();
    }

    @GetMapping("/selecionarPorNome")
    @Operation(summary = "busca cliente por nome", description = "Busca o cliente selecionado pelo nome dele")
    public ResponseEntity<?> buscarClientePorNome(@RequestParam String nome) {
        List<Cliente> listaClientes = clienteService.buscarClientePorNome(nome);
        if (listaClientes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Clientes não encontrado");
        } else {
            return ResponseEntity.ok(listaClientes);
        }

    }

    @GetMapping("/selecionar/{cpf}")
    @Operation(summary = "Buscar cliente por ID", description = "Retornar um cliente pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "404",
                    description = "Cliente não encontrado", content = @Content)
    })
    public Cliente buscarClientePorCPF(@Valid @PathVariable String cpf) {
        return clienteService.buscarClientePorCPF(cpf);
    }

    @PostMapping("/inserir")
    public ResponseEntity<String> inserir(@Valid @RequestBody Cliente cliente) {
        clienteService.salvarCliente(cliente);
        return ResponseEntity.ok("Cliente inserido com sucesso");
    }

    @DeleteMapping("/deletar/{cpf}")
    @Operation(summary = "Deletar cliente por cpf", description = "Deleta um cliente pelo cpf dele")
    public ResponseEntity<String> deletar(@Valid @PathVariable String cpf) {
        clienteService.deletarClientePorCPF(cpf);
        return ResponseEntity.ok("Cliente com cpf: " + cpf + " excluído com sucesso");
    }

    @PutMapping("/atualizar/{cpf}")
    public ResponseEntity<String> atualizarCliente(@Valid @PathVariable String cpf, @RequestBody Cliente clienteAtulizado) {
       Cliente cliente = clienteService.buscarClientePorCPF(cpf);
       cliente.setCpf(clienteAtulizado.getCpf());
       cliente.setNome(clienteAtulizado.getNome());
       cliente.setEmail(clienteAtulizado.getEmail());
       cliente.setTelefone(clienteAtulizado.getTelefone());
       clienteService.salvarCliente(cliente);
       return ResponseEntity.ok("Cliente de cpf " + cpf + " atualizado com sucesso");


    }

}//fim do cliente controller
