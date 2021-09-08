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

import br.edu.ifpe.gus.projetoArquitetura.entity.Time;
import br.edu.ifpe.gus.projetoArquitetura.service.TimeService;
import br.edu.ifpe.gus.projetoArquitetura.utils.Operacao;


@RestController
public class TimeController {
	
	@Autowired
	private TimeService timeService;
	
	@RequestMapping(value = "/api/time", method=RequestMethod.GET)
	public @ResponseBody List<Time> listaTimes() {
		
		return timeService.pegaTodosTimes();
	}
	
	@RequestMapping(value = "/api/time/{timeId}", method=RequestMethod.GET)
	public @ResponseBody Time listaTimePorId(@PathVariable Long timeId) {
		
		return timeService.pegaTimePorId(timeId);
	}
		
	@RequestMapping(value = "/api/time", method=RequestMethod.POST)
	public ResponseEntity<Time> salvaTime(@RequestBody Time time, UriComponentsBuilder uriBuilder) {
		timeService.salvaTime(time);
		URI uri = uriBuilder.path("/api/time/{id}").buildAndExpand(time.getId()).toUri();
		return ResponseEntity.created(uri).body(time);
	}
	
	@RequestMapping(value = "/api/time/{timeId}", method=RequestMethod.PUT)
	@Transactional
	public ResponseEntity<Time> atualizaTime(@PathVariable Long timeId, @RequestBody Time time, UriComponentsBuilder uriBuilder) {
		var timeAtualizado = timeService.pegaTimePorId(timeId);
		timeAtualizado.setNome(time.getNome());
		timeAtualizado.setSede(time.getSede());
		timeAtualizado.setEstadio(time.getEstadio());
		timeService.salvaTime(timeAtualizado);
		URI uri = uriBuilder.path("/api/time/{id}").buildAndExpand(timeAtualizado.getId()).toUri();
		return ResponseEntity.created(uri).body(timeAtualizado);
	}
	
	@RequestMapping(value = "/api/time/{timeId}", method=RequestMethod.DELETE)
	public ResponseEntity<Operacao> removeTime(@PathVariable Long timeId) {
		timeService.removeTimePorId(timeId);
		return ResponseEntity.ok(new Operacao("Removido com sucesso!"));
	}
}
