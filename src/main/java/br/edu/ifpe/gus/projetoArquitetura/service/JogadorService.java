package br.edu.ifpe.gus.projetoArquitetura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpe.gus.projetoArquitetura.entity.Jogador;
import br.edu.ifpe.gus.projetoArquitetura.repository.TimeRepository;
import br.edu.ifpe.gus.projetoArquitetura.repository.JogadorRepository;

@Service
public class JogadorService {

	@Autowired
	private JogadorRepository jogadorRepository;
	
	public List<Jogador> pegaTodosJogadores() {
		return jogadorRepository.findAll();
	}

	public Jogador pegaJogadorPorId(Long id) {
		if (id > 0) {
			return jogadorRepository.findById(id).get();
		} else {
			return null;
		}
	}

	public void salvaJogador(Jogador jogador) {
		jogadorRepository.save(jogador);
	}

	public void removeJogadorPorId(Long jogadorId) {
		jogadorRepository.deleteById(jogadorId);
	}

}
