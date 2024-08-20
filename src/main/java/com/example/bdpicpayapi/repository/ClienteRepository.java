package com.example.bdpicpayapi.repository;
import com.example.bdpicpayapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

    List<Cliente> findByNomeLikeIgnoreCase(String nome);
}
