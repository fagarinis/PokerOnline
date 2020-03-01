package it.poker.PokerOnline.service;

import java.math.BigDecimal;

import it.poker.PokerOnline.model.User;
import it.poker.PokerOnline.model.enm.StatoUser;

public interface UserService extends IBaseService<User> {

	public final static BigDecimal CreditoInizialeAllaRegistrazione = BigDecimal.ZERO;

	public User eseguiAccesso(String username, String password);

	public User registraUser(User o);

	public boolean usernameDisponibile(String username);

	public User caricaSingoloConTavoli(Long id);

	public User caricaSingoloConRuoli(Long id);

	public boolean usernameOccupatoDaDiversoId(Long idUser, String username);

	public void aggiornaConRuoli(User o);
	
	public Long caricaIdTavoloDiGioco(Long userId);

	public void inserisciNuovoUser(User userInsert, StatoUser stato);

}
