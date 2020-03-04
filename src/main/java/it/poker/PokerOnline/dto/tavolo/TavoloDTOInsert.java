package it.poker.PokerOnline.dto.tavolo;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.poker.PokerOnline.model.Tavolo;

public class TavoloDTOInsert {

	@NotNull(message = "Il campo non può essere vuoto")
	private Long esperienzaMin; // esperienza minima richiesta per entrare al tavolo
	@NotNull(message = "Il campo non può essere vuoto")
	private BigDecimal cifraMinimaPerEntrare;
	@NotBlank(message = "Il campo denominazione non può essere vuoto")
	private String denominazione;

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

	public static Tavolo buildModelFromDTO(TavoloDTOInsert tavoloDTOInsert) {
		Tavolo tavolo = new Tavolo();
		tavolo.setEsperienzaMin(tavoloDTOInsert.getEsperienzaMin());
		tavolo.setCifraMinimaPerEntrare(tavoloDTOInsert.getCifraMinimaPerEntrare());
		tavolo.setDenominazione(tavoloDTOInsert.getDenominazione());

		return tavolo;
	}

}
