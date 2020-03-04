package it.poker.PokerOnline.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.poker.PokerOnline.dto.tavolo.TavoloDTOInsert;
import it.poker.PokerOnline.dto.tavolo.TavoloDTOModifica;
import it.poker.PokerOnline.dto.user.UserDTOInsert;
import it.poker.PokerOnline.model.Tavolo;
import it.poker.PokerOnline.model.User;
import it.poker.PokerOnline.model.enm.StatoUser;
import it.poker.PokerOnline.model.utils.ValidationErrorsUtils;
import it.poker.PokerOnline.service.TavoloService;

@Controller()
@RequestMapping("/imieitavoli")
public class TavoloController {

	// l'utente NON può modificare/cancellare i propri tavoli
	// se ci sono giocatori nel tavolo

	@Autowired
	private TavoloService tavoloService;

	@Autowired
	private HttpSession session;

	@RequestMapping(method = RequestMethod.GET)
	public String homeTavolo(Model model) {
		return "/imieitavoli/homeGestioneTavolo";
	}

	@RequestMapping(value = "/PrepareInsert", method = RequestMethod.GET)
	public String prepareInsert() {
		return "/imieitavoli/insert";
	}

	@RequestMapping(value = "/ExecuteInsert", method = RequestMethod.POST)
	public String executeInsert(@Valid @ModelAttribute("tavoloDTOInsert") TavoloDTOInsert tavoloDTOInsert,
			BindingResult bindingResult, Model model) {
		

		List<FieldError> errors = ValidationErrorsUtils.sorted(bindingResult.getFieldErrors());

		if (!errors.isEmpty()) {
			model.addAttribute("tavoloAttr", tavoloDTOInsert);
			model.addAttribute("tavoloErrors", errors);
			return "/imieitavoli/insert";
		}

		Tavolo tavoloInsert = TavoloDTOInsert.buildModelFromDTO(tavoloDTOInsert);

		tavoloService.creaNuovoTavolo(tavoloInsert, userInSessione().getId());

		model.addAttribute("messaggioConferma", "Inserimento avvenuto con successo");
		//TODO Bug: la data del tavolo appena inserito si vede in formato diverso nel result
		model.addAttribute("listaTavoliAttr", listAllTavoliUser());

		return "/imieitavoli/result";
	}

	@RequestMapping(value = "/PrepareSearch", method = RequestMethod.GET)
	public String prepareSearch() {

		return "/imieitavoli/search";
	}

	@RequestMapping(value = "/ExecuteSearch", method = RequestMethod.POST)
	public String executeSearch(HttpSession session, Model model, @RequestParam String denominazione,
			@RequestParam String dataCreazione, @RequestParam String cifraMinimaPerEntrare) {

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

		tavoloExample.setUserCreatore(userInSessione());
		model.addAttribute("listaTavoliAttr", tavoloService.findByExample(tavoloExample));

//		System.out.println(tavoloExample);

		return "/imieitavoli/result";
	}

	@RequestMapping(value = "/{id}/ExecuteDettaglio", method = RequestMethod.GET)
	public String executeDettaglio(@PathVariable("id") Long id, Model model) {
		model.addAttribute("tavoloAttr", tavoloService.caricaTavoloConGiocatori(id));

		return "/imieitavoli/dettaglio";
	}

	@RequestMapping(value = "/{id}/PrepareDelete", method = RequestMethod.GET)
	public String prepareDelete(@PathVariable("id") Long id, Model model) {
		Tavolo tavolo = tavoloService.caricaTavoloConGiocatori(id);

		if (tavolo.hasPlayersIn()) {
			model.addAttribute("listaTavoliAttr", listAllTavoliUser());
			model.addAttribute("messaggioErrore", "non è possibile cancellare un tavolo con giocatori attivi");
			return "/imieitavoli/result";
		}

		model.addAttribute("tavoloAttr", tavolo);
		return "/imieitavoli/delete";
	}

	@RequestMapping(value = "/ExecuteDelete", method = RequestMethod.POST)
	public String executeDelete(@RequestParam("id") Long id, Model model) {

		boolean rimosso = tavoloService.rimuoviTavoloSeVuoto(id);

		if (rimosso == false) {
			model.addAttribute("messaggioErrore", "non è possibile cancellare un tavolo con giocatori attivi");
		} else {
			model.addAttribute("messaggioConferma", "Tavolo cancellato con successo");
		}

		model.addAttribute("listaTavoliAttr", listAllTavoliUser());
		return "/imieitavoli/result";
	}

	@RequestMapping(value = "/{id}/PrepareModifica", method = RequestMethod.GET)
	public String prepareModifica(@PathVariable("id") Long id, Model model) {

		Tavolo tavolo = tavoloService.caricaTavoloConGiocatori(id);
		if (tavolo.hasPlayersIn()) {
			model.addAttribute("listaTavoliAttr", listAllTavoliUser());
			model.addAttribute("messaggioErrore", "non è possibile modificare un tavolo con giocatori attivi");
			return "/imieitavoli/result";
		}

		model.addAttribute("tavoloAttr", tavolo);
		return "/imieitavoli/modifica";
	}

	@RequestMapping(value = "/ExecuteModifica", method = RequestMethod.POST)
	public String executeModifica(@Valid @ModelAttribute("userDTOModifica") TavoloDTOModifica tavoloDTOModifica,
			BindingResult bindingResult, Model model) {

		List<FieldError> errors = ValidationErrorsUtils.sorted(bindingResult.getFieldErrors());

		if (!errors.isEmpty()) {
			model.addAttribute("tavoloAttr", tavoloDTOModifica);
			model.addAttribute("tavoloErrors", errors);
			return "/imieitavoli/modifica";
		}

		Tavolo tavoloModificato = TavoloDTOModifica.buildModelFromDTO(tavoloDTOModifica);
		boolean modificato = tavoloService.modificaTavoloSeVuoto(tavoloModificato);

		if (modificato == false) {
			model.addAttribute("listaTavoliAttr", listAllTavoliUser());
			model.addAttribute("messaggioErrore", "non è possibile modificare un tavolo con giocatori attivi");
			return "/imieitavoli/result";
		}

		model.addAttribute("messaggioConferma", "Tavolo Modificato con successo");
		model.addAttribute("listaTavoliAttr", listAllTavoliUser());
		return "/imieitavoli/result";
	}

	private User userInSessione() {
		return (User) session.getAttribute("userInfo");
	}

	private List<Tavolo> listAllTavoliUser() {
		Tavolo tavoloExample = new Tavolo();
		tavoloExample.setUserCreatore(userInSessione());
		return tavoloService.findByExample(tavoloExample);
	}

}
