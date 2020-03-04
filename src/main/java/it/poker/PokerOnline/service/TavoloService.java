package it.poker.PokerOnline.service;

import it.poker.PokerOnline.model.Tavolo;

public interface TavoloService extends IBaseService<Tavolo> {
	
	public Tavolo caricaTavoloConGiocatori(Long id);
	
	public boolean rimuoviTavoloSeVuoto(Long id);

	public void creaNuovoTavolo(Tavolo tavoloInsert, Long id);

	public boolean modificaTavoloSeVuoto(Tavolo tavoloModificato);


}
