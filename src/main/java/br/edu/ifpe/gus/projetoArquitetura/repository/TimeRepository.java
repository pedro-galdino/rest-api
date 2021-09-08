package br.edu.ifpe.gus.projetoArquitetura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpe.gus.projetoArquitetura.entity.Time;

public interface TimeRepository extends JpaRepository<Time, Long>{

}
