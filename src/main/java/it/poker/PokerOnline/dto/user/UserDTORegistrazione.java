package it.poker.PokerOnline.dto.user;

import javax.validation.constraints.NotBlank;

import it.poker.PokerOnline.model.User;

public class UserDTORegistrazione {

	@NotBlank(message = "Il campo nome non può essere vuoto")
	private String nome;
	@NotBlank(message = "Il campo cognome non può essere vuoto")
	private String cognome;
	@NotBlank(message = "Il campo username non può essere vuoto")
	private String username;
	@NotBlank(message = "Il campo password non può essere vuoto")
	private String password;

//	private String confermaPassword;

	@Override
	public String toString() {
		return "UserDTORegistrazione [nome=" + nome + ", cognome=" + cognome + ", username=" + username + ", password="
				+ password + "]";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static User buildModelFromDTO(UserDTORegistrazione userDTO) {
		User user = new User();
		user.setNome(userDTO.getNome());
		user.setCognome(userDTO.getCognome());
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		return user;
	}

//	public String getConfermaPassword() {
//		return confermaPassword;
//	}
//
//	public void setConfermaPassword(String confermaPassword) {
//		this.confermaPassword = confermaPassword;
//	}

}
