package com.fafica.web.api;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fafica.web.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
