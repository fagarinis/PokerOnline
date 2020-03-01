package it.poker.PokerOnline.dto.user;

import java.util.ArrayList;
import java.util.List;

import it.poker.PokerOnline.model.Role;
import it.poker.PokerOnline.model.User;

public class UserDTOInsert extends UserDTORegistrazione {

	private List<Role> ruoli = new ArrayList<>();

	public List<Role> getRuoli() {
		return ruoli;
	}

	public void setRuoli(List<Role> ruoli) {
		this.ruoli = ruoli;
	}

	public static User buildModelFromDTO(UserDTOInsert userDTO) {
		User user = new User();
		user.setNome(userDTO.getNome());
		user.setCognome(userDTO.getCognome());
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setRuoli(userDTO.getRuoli());
		return user;
	}

}
