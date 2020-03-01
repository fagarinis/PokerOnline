package it.poker.PokerOnline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.poker.PokerOnline.model.Role;
import it.poker.PokerOnline.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Role> listAll() {
		return (List<Role>) roleRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Role caricaSingolo(Long id) {
		return roleRepository.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void aggiorna(Role o) {
		roleRepository.save(o);
	}

	@Transactional
	@Override
	public void inserisciNuovo(Role o) {
		roleRepository.save(o);
	}

	@Transactional
	@Override
	public void rimuovi(Role o) {
		roleRepository.delete(o);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Role> findByExample(Role example) {
		ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING);
		return (List<Role>) roleRepository.findAll(Example.of(example, matcher));
	}

}
