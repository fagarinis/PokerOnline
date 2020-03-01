package it.poker.PokerOnline.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.poker.PokerOnline.model.enm.CodiceRuolo;
import it.poker.PokerOnline.model.enm.StatoUser;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cognome;
	@Column(unique = true, nullable = false, length = 55)
	private String username;
	@Column(nullable = false, length = 200)
	private String password;
	@Temporal(TemporalType.DATE)
	private Date dataRegistrazione;

	@Enumerated(EnumType.STRING)
	private StatoUser stato;
	private Long esperienzaAccumulata;
	private BigDecimal creditoAcc;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tavoloDiGioco_id")
	private Tavolo tavoloDiGioco;

	@OneToMany(mappedBy = "userCreatore")
	private List<Tavolo> tavoliCreati = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Role> ruoli = new ArrayList<>();

	public User() {
	}

	public User(Long id) {
		this.id = id;
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

	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public StatoUser getStato() {
		return stato;
	}

	public void setStato(StatoUser stato) {
		this.stato = stato;
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

	public Tavolo getTavoloDiGioco() {
		return tavoloDiGioco;
	}

	public void setTavoloDiGioco(Tavolo tavoloDiGioco) {
		this.tavoloDiGioco = tavoloDiGioco;
	}

	public List<Tavolo> getTavoliCreati() {
		return tavoliCreati;
	}

	public void setTavoliCreati(List<Tavolo> tavoliCreati) {
		this.tavoliCreati = tavoliCreati;
	}

	public List<Role> getRuoli() {
		return ruoli;
	}

	public void setRuoli(List<Role> ruoli) {
		this.ruoli = ruoli;
	}

	public boolean isAdmin() {
		for (Role role : this.getRuoli()) {
			if (role.getCodice() == CodiceRuolo.ADMIN) {
				return true;
			}
		}
		return false;
	}

}
