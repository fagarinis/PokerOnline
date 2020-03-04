package it.poker.PokerOnline.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.poker.PokerOnline.model.Tavolo;
import it.poker.PokerOnline.model.User;
import it.poker.PokerOnline.service.TavoloService;
import it.poker.PokerOnline.service.UserService;

@Controller()
@RequestMapping("/play")
public class PlayController {

	@Autowired
	private UserService userService;

	@Autowired
	private TavoloService tavoloService;

	@Autowired
	private HttpSession session;

	@RequestMapping(method = RequestMethod.GET)
	public String homePlay(Model model) {
		return "/play/homePlayManagement";
	}

	@RequestMapping(value = "/PrepareSearch", method = RequestMethod.GET)
	public String prepareSearch() {
		
		return "/play/search";
	}

	@RequestMapping(value = "/ExecuteSearch", method = RequestMethod.POST)
	public String executeSearch(HttpSession session, Model model, @RequestParam String denominazione,
			@RequestParam String dataCreazione, @RequestParam String cifraMinimaPerEntrare,
			@RequestParam(required = false) Long idCreatore, @RequestParam(required = false) Long idPartecipante) {

		//FIXME sul conferma ricerca, elencare solo i tavoli in cui esperienza minima richiesta <= esperienza accumulata.
		
		Tavolo tavoloExample = new Tavolo();
		tavoloExample.setDenominazione(denominazione);
		try {
			tavoloExample.setCifraMinimaPerEntrare(new BigDecimal(cifraMinimaPerEntrare));
		} catch (Exception e) {
		}
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dataCreazione);
			tavoloExample.setDataCreazione(date);
		} catch (Exception e) {
		}

		model.addAttribute("listaTavoliAttr", tavoloService.findByExample(tavoloExample));

		return "/play/result";
	}

	@RequestMapping(value = "/PrepareCompraCredito", method = RequestMethod.GET)
	public String prepareCompraCredito() {
		return "/play/compraCredito";
	}

	@RequestMapping(value = "/ExecuteCompraCredito", method = RequestMethod.POST)
	public String executeCompraCredito(@RequestParam BigDecimal cifra, Model model) {
		if (cifra == null) {
			return "/play/compraCredito";
		}

		userService.aggiungiCredito(userInSessione().getId(), cifra);
		aggiornaUtenteInSessione();
		if (cifra.compareTo(BigDecimal.ZERO) > 0) {
			model.addAttribute("messaggioConferma", "Acquisto avvenuto con successo");
		}
		return "/play/homePlayManagement";
	}

	@RequestMapping(value = "/ExecuteGoToLastGame", method = RequestMethod.GET)
	public String executeGoToLastGame() {
		// TODO
		return "/play/gioca";
	}
	
	@RequestMapping(value = "/{id}/PrepareGioca", method = RequestMethod.GET)
	public String prepareGioca(@PathVariable("id") Long id, Model model) {
		
		userService.giocaSuTavolo(userInSessione(), id);
		aggiornaUtenteInSessione();
		
		
		return "/play/gioca";
	}

	private User userInSessione() {
		return (User) session.getAttribute("userInfo");
	}

	private void aggiornaUtenteInSessione() {
		session.setAttribute("userInfo", userService.caricaSingoloConRuoli(userInSessione().getId()));
	}

}
