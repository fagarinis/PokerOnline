package it.poker.PokerOnline.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.poker.PokerOnline.model.User;
import it.poker.PokerOnline.model.enm.StatoUser;

public interface UserRepository extends CrudRepository<User, Long>, QueryByExampleExecutor<User> {
	
	public User findByUsernameAndPasswordAndStato(String username, String password, StatoUser stato);
	
}
