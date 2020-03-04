package it.poker.PokerOnline.model;

import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Tavolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long esperienzaMin; // esperienza minima richiesta per entrare al tavolo
	private BigDecimal cifraMinimaPerEntrare;
	private String denominazione;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCreazione;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "userCreatore_id", nullable = false)
	private User userCreatore;

	@OneToMany(mappedBy = "tavoloDiGioco")
	private List<User> usersGiocatori = new ArrayList<>();

	@Override
	public String toString() {
		return "Tavolo [id=" + id + ", esperienzaMin=" + esperienzaMin + ", cifraMinimaPerEntrare="
				+ cifraMinimaPerEntrare + ", denominazione=" + denominazione + ", dataCreazione=" + dataCreazione + "]";
	}

	public Tavolo() {
	}

	public Tavolo(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEsperienzaMin() {
		return esperienzaMin;
	}

	public void setEsperienzaMin(Long esperienzaMin) {
		this.esperienzaMin = esperienzaMin;
	}

	public BigDecimal getCifraMinimaPerEntrare() {
		return cifraMinimaPerEntrare;
	}

	public void setCifraMinimaPerEntrare(BigDecimal cifraMinimaPerEntrare) {
		this.cifraMinimaPerEntrare = cifraMinimaPerEntrare;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public User getUserCreatore() {
		return userCreatore;
	}

	public void setUserCreatore(User userCreatore) {
		this.userCreatore = userCreatore;
	}

	public List<User> getUsersGiocatori() {
		return usersGiocatori;
	}

	public void setUsersGiocatori(List<User> usersGiocatori) {
		this.usersGiocatori = usersGiocatori;
	}

}
