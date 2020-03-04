package it.poker.PokerOnline.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;

import it.poker.PokerOnline.model.Role;
import it.poker.PokerOnline.model.Tavolo;
import it.poker.PokerOnline.model.User;
import it.poker.PokerOnline.model.enm.StatoUser;
import it.poker.PokerOnline.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final static Long PlayerRoleId = 2L;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private TavoloService tavoloService;

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
	public void aggiornaConRuoli(User o) {

		List<Role> ruoliUpdate = new ArrayList<>();
		ruoliUpdate.addAll(o.getRuoli());

		o.getRuoli().clear();
		// scorro la lista di ruoli transient che contengono solo l'id
		for (Role role : ruoliUpdate) {
			Role ruolo = roleService.caricaSingolo(role.getId());
			o.getRuoli().add(ruolo);
		}

		User userPersist = this.caricaSingoloConTavoli(o.getId());
		o.setTavoliCreati(userPersist.getTavoliCreati());
		o.setTavoloDiGioco(userPersist.getTavoloDiGioco());

		o.setDataRegistrazione(userPersist.getDataRegistrazione());

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
		o.getRuoli().add(roleService.caricaSingolo(PlayerRoleId));// ruolo player

		return o;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean usernameDisponibile(String username) {
		User user = new User();
		user.setUsername(username);
		return !userRepository.exists(Example.of(user));
	}

	@Transactional(readOnly = true)
	@Override
	public boolean usernameOccupatoDaDiversoId(Long idUser, String username) {
		User userConStessoUsername = userRepository.findByUsername(username);

		if (userConStessoUsername == null) {
			return false;
		}

		if (userConStessoUsername.getId() == idUser) {
			return false;
		}

		return true;
	}

	@Transactional(readOnly = true)
	@Override
	public User caricaSingoloConRuoli(Long id) {
		return userRepository.findByIdWithRoles(id).orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public User caricaSingoloConTavoli(Long id) {
		return userRepository.findByIdWithTables(id).orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public Long caricaIdTavoloDiGioco(Long userId) {
		return userRepository.getIdOfPlayingTable(userId).orElse(null);
	}

	@Transactional
	@Override
	public void inserisciNuovoUser(User userInsert, StatoUser stato) {
		
		userInsert.setStato(stato);
		userInsert.setEsperienzaAccumulata(0L);
		userInsert.setCreditoAcc(BigDecimal.ZERO);
		userInsert.setDataRegistrazione(new Date());
		this.inserisciNuovo(userInsert);
	}

	@Transactional
	@Override
	public void aggiungiCredito(Long idUser, BigDecimal cifra) {
		User user = this.caricaSingolo(idUser);
		user.setCreditoAcc(user.getCreditoAcc().add(cifra));
	}

	@Transactional
	@Override
	public void giocaSuTavolo(User userInSessione, Long idTavolo) {
		User userPersist = this.caricaSingolo(userInSessione.getId());
		Tavolo tavoloDiGioco = tavoloService.caricaSingolo(idTavolo);
		
		userPersist.setTavoloDiGioco(tavoloDiGioco);
	}

}
