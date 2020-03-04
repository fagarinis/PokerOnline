package it.poker.PokerOnline.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.poker.PokerOnline.model.Tavolo;

public interface TavoloRepository extends CrudRepository<Tavolo, Long>, QueryByExampleExecutor<Tavolo> {

	@Query("Select t from Tavolo t left join fetch t.usersGiocatori where t.id = ?1")
	Tavolo findByIdWithPlayers(Long id);

}
