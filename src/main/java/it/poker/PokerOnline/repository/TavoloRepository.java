package it.poker.PokerOnline.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.poker.PokerOnline.model.Tavolo;

public interface TavoloRepository extends CrudRepository<Tavolo, Long>, QueryByExampleExecutor<Tavolo> {

}
