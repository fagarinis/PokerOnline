package it.poker.PokerOnline.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.poker.PokerOnline.model.User;
import it.poker.PokerOnline.model.enm.StatoUser;

public interface UserRepository extends CrudRepository<User, Long>, QueryByExampleExecutor<User> {
	
	@Query("SELECT u from User u left join fetch u.ruoli where u.username = ?1 and u.password = ?2 and u.stato = ?3")
	public User findByUsernameAndPasswordAndStato(String username, String password, StatoUser stato);
	
	@Query("SELECT u from User u left join fetch u.ruoli where u.id = ?1")
	public Optional<User> findByIdWithRoles(Long id);
	
	@Query("SELECT u from User u left join fetch u.tavoloDiGioco tg left join fetch u.tavoliCreati tc where u.id = ?1")
	public Optional<User> findByIdWithTables(Long id);
	
	public User findByUsername(String username);
	
	@Query("SELECT u.tavoloDiGioco.id from User u where u.id = ?1")
	public Optional<Long> getIdOfPlayingTable(Long userId);
	
}
