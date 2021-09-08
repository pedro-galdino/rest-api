package br.edu.ifpe.gus.projetoArquitetura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpe.gus.projetoArquitetura.entity.Time;
import br.edu.ifpe.gus.projetoArquitetura.repository.TimeRepository;

@Service
public class TimeService {

	@Autowired
	private TimeRepository timeRepository;

	public List<Time> pegaTodosTimes() {
		return timeRepository.findAll();
	}

	public Time pegaTimePorId(Long id) {
		if(id>0) { 
			return timeRepository.findById(id).get();
		}else {
			return null;
		}
	}

	public void salvaTime(Time time) {
		timeRepository.save(time);	
	}

	public void removeTimePorId(Long timeId) {
		timeRepository.deleteById(timeId);
	}
}
