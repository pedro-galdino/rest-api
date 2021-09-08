package br.edu.ifpe.gus.projetoArquitetura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpe.gus.projetoArquitetura.entity.Jogador;

public interface JogadorRepository extends JpaRepository<Jogador, Long>{
	
}

