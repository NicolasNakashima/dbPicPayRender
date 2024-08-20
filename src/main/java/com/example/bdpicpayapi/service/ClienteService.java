package com.example.bdpicpayapi.service;

import com.example.bdpicpayapi.model.Cliente;
import com.example.bdpicpayapi.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    public List<Cliente> buscarClientePorNome(String nome){
            return clienteRepository.findByNomeLikeIgnoreCase(nome);
    }
    public Cliente buscarClientePorCPF(String cpf){
        return clienteRepository.findById(cpf).orElseThrow(() -> new RuntimeException("Cliente não encontrado com o id " + cpf));
    }

    public List<Cliente> buscarTodosClientes(){
        return clienteRepository.findAll();
    }

    @Transactional
    public Cliente salvarCliente(Cliente cliente){
       return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente deletarCliente(Cliente cliente){
        clienteRepository.delete(cliente);
        return cliente;
    }

    @Transactional
    public Cliente deletarClientePorCPF(String cpf){
        Cliente cliente = buscarClientePorCPF(cpf);
        clienteRepository.delete(cliente);
        return cliente;
    }


}
