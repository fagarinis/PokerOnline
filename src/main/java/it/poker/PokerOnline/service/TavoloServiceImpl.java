package it.poker.PokerOnline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.poker.PokerOnline.model.Tavolo;
import it.poker.PokerOnline.repository.TavoloRepository;

@Service
public class TavoloServiceImpl implements TavoloService {

	@Autowired
	private TavoloRepository tavoloRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Tavolo> listAll() {
		return (List<Tavolo>) tavoloRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Tavolo caricaSingolo(Long id) {
		return tavoloRepository.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void aggiorna(Tavolo o) {
		tavoloRepository.save(o);
	}

	@Transactional
	@Override
	public void inserisciNuovo(Tavolo o) {
		tavoloRepository.save(o);
	}

	@Transactional
	@Override
	public void rimuovi(Tavolo o) {
		tavoloRepository.delete(o);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Tavolo> findByExample(Tavolo example) {
		//FIXME non funziona la ricerca per data perche nell'example c'è un tipo Date da convertire in
		//formato yyyy-MM-dd sul DB
		ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING);
		return (List<Tavolo>) tavoloRepository.findAll(Example.of(example, matcher));
	}

}
