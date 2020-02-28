package it.poker.PokerOnline.repository;

import org.springframework.data.repository.CrudRepository;

import it.poker.PokerOnline.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
