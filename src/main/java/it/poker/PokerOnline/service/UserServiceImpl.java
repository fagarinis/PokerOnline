package it.poker.PokerOnline.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;

import it.poker.PokerOnline.model.User;
import it.poker.PokerOnline.model.enm.StatoUser;
import it.poker.PokerOnline.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	@Override
	public List<User> listAll() {
		return (List<User>) userRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public User caricaSingolo(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void aggiorna(User o) {
		userRepository.save(o);
	}

	@Transactional
	@Override
	public void inserisciNuovo(User o) {
		userRepository.save(o);
	}

	@Transactional
	@Override
	public void rimuovi(User o) {
		userRepository.delete(o);
	}

	@Transactional(readOnly = true)
	@Override
	public List<User> findByExample(User example) {
		ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING);
		return (List<User>) userRepository.findAll(Example.of(example, matcher), Sort.by("id"));
	}

	@Transactional(readOnly = true)
	@Override
	public User eseguiAccesso(String username, String password) {
		return userRepository.findByUsernameAndPasswordAndStato(username, password, StatoUser.ATTIVO);
	}

	@Transactional
	@Override
	public User registraUser(User o) {
		if (!usernameDisponibile(o.getUsername())) {
			return null;
		}

		o.setStato(StatoUser.CREATO);
		o.setCreditoAcc(CreditoInizialeAllaRegistrazione);
		o.setDataRegistrazione(new Date());
		o.setEsperienzaAccumulata(0L);
		inserisciNuovo(o);

		return o;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean usernameDisponibile(String username) {
		User user = new User();
		user.setUsername(username);
		return !userRepository.exists(Example.of(user));
	}

}
