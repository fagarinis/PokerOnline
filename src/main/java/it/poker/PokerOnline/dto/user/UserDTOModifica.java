package it.poker.PokerOnline.dto.user;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.poker.PokerOnline.model.Role;
import it.poker.PokerOnline.model.User;
import it.poker.PokerOnline.model.enm.StatoUser;

public class UserDTOModifica {

	@NotNull(message = "Il campo id non può essere nullo")
	private Long id;
	@NotBlank(message = "Il campo nome non può essere vuoto")
	private String nome;
	@NotBlank(message = "Il campo cognome non può essere vuoto")
	private String cognome;
	@NotBlank(message = "Il campo username non può essere vuoto")
	private String username;
	@NotBlank(message = "Il campo password non può essere vuoto")
	private String password;

	private List<Role> ruoli = new ArrayList<>();

	@NotNull
	@Min(value = 0L, message = "Il valore di esperienza accumulata deve essere positivo")
	private Long esperienzaAccumulata;

	@NotNull
	@Min(value = 0L, message = "Il valore di credito account deve essere positivo")
	private BigDecimal creditoAcc;

	@NotNull(message = "Il campo password non può essere vuoto")
	private StatoUser stato;

	@Override
	public String toString() {
		return "UserDTOModifica [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", username=" + username
				+ ", password=" + password + ", ruoli=" + ruoli + ", esperienzaAccumulata=" + esperienzaAccumulata
				+ ", creditoAcc=" + creditoAcc + ", stato=" + stato + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getEsperienzaAccumulata() {
		return esperienzaAccumulata;
	}

	public void setEsperienzaAccumulata(Long esperienzaAccumulata) {
		this.esperienzaAccumulata = esperienzaAccumulata;
	}

	public BigDecimal getCreditoAcc() {
		return creditoAcc;
	}

	public void setCreditoAcc(BigDecimal creditoAcc) {
		this.creditoAcc = creditoAcc;
	}

	public StatoUser getStato() {
		return stato;
	}

	public void setStato(StatoUser stato) {
		this.stato = stato;
	}

	public List<Role> getRuoli() {
		return ruoli;
	}

	public void setRuoli(List<Role> ruoli) {
		this.ruoli = ruoli;
	}

	public static User buildModelFromDTO(UserDTOModifica userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setNome(userDTO.getNome());
		user.setCognome(userDTO.getCognome());
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setEsperienzaAccumulata(userDTO.getEsperienzaAccumulata());
		user.setCreditoAcc(userDTO.getCreditoAcc());
		user.setStato(userDTO.getStato());
		user.setRuoli(userDTO.getRuoli());

		return user;
	}

}
