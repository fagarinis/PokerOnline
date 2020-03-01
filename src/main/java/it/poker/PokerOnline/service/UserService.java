package it.poker.PokerOnline.service;

import java.math.BigDecimal;

import it.poker.PokerOnline.model.User;

public interface UserService extends IBaseService<User> {
	
	public final static BigDecimal CreditoInizialeAllaRegistrazione = BigDecimal.ZERO;

	public User eseguiAccesso(String username, String password);

	public User registraUser(User o);

	public boolean usernameDisponibile(String username);

}
