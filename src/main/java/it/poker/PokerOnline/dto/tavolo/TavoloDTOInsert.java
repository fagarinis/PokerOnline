package it.poker.PokerOnline.dto.tavolo;

public class TavoloDTOInsert {

	private Long esperienzaMin; // esperienza minima richiesta per entrare al tavolo
	private Long cifraMinimaPerEntrare;
	private String denominazione;

	public Long getEsperienzaMin() {
		return esperienzaMin;
	}

	public void setEsperienzaMin(Long esperienzaMin) {
		this.esperienzaMin = esperienzaMin;
	}

	public Long getCifraMinimaPerEntrare() {
		return cifraMinimaPerEntrare;
	}

	public void setCifraMinimaPerEntrare(Long cifraMinimaPerEntrare) {
		this.cifraMinimaPerEntrare = cifraMinimaPerEntrare;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

}
