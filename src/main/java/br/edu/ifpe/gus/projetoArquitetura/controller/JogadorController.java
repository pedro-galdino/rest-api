package br.edu.ifpe.gus.projetoArquitetura.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ifpe.gus.projetoArquitetura.entity.Jogador;
import br.edu.ifpe.gus.projetoArquitetura.service.JogadorService;
import br.edu.ifpe.gus.projetoArquitetura.utils.Operacao;

@RestController
public class JogadorController {
		
		@Autowired
		private JogadorService jogadorService;
		
		@RequestMapping(value = "/api/jogador", method=RequestMethod.GET)
		public @ResponseBody List<Jogador> listaJogadores() {
	
			return jogadorService.pegaTodosJogadores();
		}
		
		@RequestMapping(value = "/api/jogador/{jogadorId}", method=RequestMethod.GET)
		public @ResponseBody Jogador listaJogadorPorId(@PathVariable Long jogadorId) {
		
			return jogadorService.pegaJogadorPorId(jogadorId);
		}
			
		@RequestMapping(value = "/api/jogador", method=RequestMethod.POST)
		public ResponseEntity<Jogador> salvaJogador(@RequestBody Jogador jogador, UriComponentsBuilder uriBuilder) {
			jogadorService.salvaJogador(jogador);
			URI uri = uriBuilder.path("/api/jogador/{id}").buildAndExpand(jogador.getId()).toUri();
			return ResponseEntity.created(uri).body(jogador);
		}
		
		@RequestMapping(value = "/api/jogador/{jogadorId}", method=RequestMethod.PUT)
		@Transactional
		public ResponseEntity<Jogador> atualizaJogador(@PathVariable Long jogadorId, @RequestBody Jogador jogador, UriComponentsBuilder uriBuilder) {
			var jogadorAtualizado = jogadorService.pegaJogadorPorId(jogadorId);
			jogadorAtualizado.setNome(jogador.getNome());
			jogadorAtualizado.setIdade(jogador.getIdade());
			jogadorAtualizado.setPosicao(jogador.getPosicao());
			jogadorAtualizado.setTime(jogador.getTime());
			jogadorService.salvaJogador(jogadorAtualizado);
			URI uri = uriBuilder.path("/api/jogador/{id}").buildAndExpand(jogadorAtualizado.getId()).toUri();
			return ResponseEntity.created(uri).body(jogadorAtualizado);
		}
		
		@RequestMapping(value = "/api/jogador/{jogadorId}", method=RequestMethod.DELETE)
		public ResponseEntity<Operacao> removeJogador(@PathVariable Long jogadorId) {
			jogadorService.removeJogadorPorId(jogadorId);
			return ResponseEntity.ok(new Operacao("Removido com sucesso!"));
		}
	}
