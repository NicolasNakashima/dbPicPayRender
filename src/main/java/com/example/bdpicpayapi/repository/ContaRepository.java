package com.example.bdpicpayapi.repository;

import com.example.bdpicpayapi.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, String> {
}
