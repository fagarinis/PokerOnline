package it.poker.PokerOnline.dto.tavolo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import it.poker.PokerOnline.model.Tavolo;

public class TavoloDTOModifica extends TavoloDTOInsert {

	@NotNull(message = "Il campo id non può essere vuoto")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static Tavolo buildModelFromDTO(TavoloDTOModifica tavoloDTOModifica) {
		Tavolo tavolo = new Tavolo();
		tavolo.setId(tavoloDTOModifica.getId());
		tavolo.setEsperienzaMin(tavoloDTOModifica.getEsperienzaMin());
		tavolo.setCifraMinimaPerEntrare(tavoloDTOModifica.getCifraMinimaPerEntrare());
		tavolo.setDenominazione(tavoloDTOModifica.getDenominazione());

		return tavolo;
	}

}
