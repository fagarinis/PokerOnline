package it.poker.PokerOnline.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.poker.PokerOnline.model.Tavolo;
import it.poker.PokerOnline.model.User;
import it.poker.PokerOnline.repository.TavoloRepository;

@Service
public class TavoloServiceImpl implements TavoloService {

	@Autowired
	private TavoloRepository tavoloRepository;

	@Autowired
	private UserService userService;

	@Transactional(readOnly = true)
	@Override
	public List<Tavolo> listAll() {
		return (List<Tavolo>) tavoloRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Tavolo caricaSingolo(Long id) {
		return tavoloRepository.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void aggiorna(Tavolo o) {
		tavoloRepository.save(o);
	}

	@Transactional
	@Override
	public void inserisciNuovo(Tavolo o) {
		tavoloRepository.save(o);
	}

	@Transactional
	@Override
	public void rimuovi(Tavolo o) {
		tavoloRepository.delete(o);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Tavolo> findByExample(Tavolo example) {
		// FIXME non funziona la ricerca per data perche nell'example c'è un tipo Date
		// da convertire in
		// formato yyyy-MM-dd sul DB
		ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING);
		return (List<Tavolo>) tavoloRepository.findAll(Example.of(example, matcher));
	}

	@Transactional(readOnly = true)
	@Override
	public Tavolo caricaTavoloConGiocatori(Long id) {
		return tavoloRepository.findByIdWithPlayers(id);
	}

	@Transactional
	@Override
	public boolean rimuoviTavoloSeVuoto(Long id) {
		Tavolo tavoloDelete = caricaTavoloConGiocatori(id);

		if (tavoloDelete.hasPlayersIn()) {
			return false;
		}

		rimuovi(tavoloDelete);
		return true;
	}

	@Transactional
	@Override
	public boolean modificaTavoloSeVuoto(Tavolo tavoloModificato) {
		Tavolo tavoloPersist = caricaTavoloConGiocatori(tavoloModificato.getId());

		if (tavoloPersist.hasPlayersIn()) {
			return false;
		}

		tavoloPersist.setCifraMinimaPerEntrare(tavoloModificato.getCifraMinimaPerEntrare());
		tavoloPersist.setDenominazione(tavoloModificato.getDenominazione());
		tavoloPersist.setEsperienzaMin(tavoloModificato.getEsperienzaMin());
		return true;
	}

	@Transactional
	@Override
	public void creaNuovoTavolo(Tavolo tavoloInsert, Long idCreatore) {
		User userCreatore = userService.caricaSingolo(idCreatore);
		tavoloInsert.setUserCreatore(userCreatore);
		tavoloInsert.setDataCreazione(new Date());

		tavoloRepository.save(tavoloInsert);
	}

}
