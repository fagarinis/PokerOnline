package it.poker.PokerOnline.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.poker.PokerOnline.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long>, QueryByExampleExecutor<Role> {

}
