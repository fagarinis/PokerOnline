package it.poker.PokerOnline.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.poker.PokerOnline.dto.tavolo.TavoloDTOModifica;
import it.poker.PokerOnline.model.Tavolo;
import it.poker.PokerOnline.model.User;
import it.poker.PokerOnline.service.TavoloService;

@Controller()
@RequestMapping("/imieitavoli")
public class TavoloController {

	// l'utente NON può modificare/cancellare i propri tavoli
	// se ci sono giocatori nel tavolo

	@Autowired
	private TavoloService tavoloService;

	@RequestMapping(method = RequestMethod.GET)
	public String homeTavolo(Model model) {
		return "/imieitavoli/homeGestioneTavolo";
	}

	@RequestMapping(value = "/PrepareInsert", method = RequestMethod.GET)
	public String prepareInsert() {
		return "/imieitavoli/insert";
	}
	
	@RequestMapping(value = "/ExecuteInsert", method = RequestMethod.POST)
	public String executeInsert() {
		//TODO esegui inserimento
		return "/imieitavoli/result";
	}

	@RequestMapping(value = "/PrepareSearch", method = RequestMethod.GET)
	public String prepareSearch() {

		return "/imieitavoli/search";
	}

	@RequestMapping(value = "/ExecuteSearch", method = RequestMethod.POST)
	public String executeSearch(HttpSession session, Model model, @RequestParam String denominazione, @RequestParam String dataCreazione,
			@RequestParam String cifraMinimaPerEntrare) {

//		System.out.println("denominazione: " + denominazione);
//		System.out.println("data creazione: " + dataCreazione);
//		System.out.println("cifra minima : " + cifraMinimaPerEntrare);

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
		
		User userInSessione = (User) session.getAttribute("userInfo");
		tavoloExample.setUserCreatore(userInSessione);

		model.addAttribute("listaTavoliAttr", tavoloService.findByExample(tavoloExample));

//		System.out.println(tavoloExample);

		return "/imieitavoli/result";
	}
	
	@RequestMapping(value = "/{id}/ExecuteDettaglio", method = RequestMethod.GET)
	public String executeDettaglio(@PathVariable("id") Long id) {

		return "/imieitavoli/dettaglio";
	}
	
	@RequestMapping(value = "/{id}/PrepareDelete", method = RequestMethod.GET)
	public String prepareDelete(@PathVariable("id") Long id) {

		return "/imieitavoli/delete";
	}
	
	@RequestMapping(value = "/ExecuteDelete", method = RequestMethod.POST)
	public String executeDelete() {
		//TODO esegui cancella
		
		return "/imieitavoli/result";
	}
	
	@RequestMapping(value = "/{id}/PrepareModifica", method = RequestMethod.GET)
	public String prepareModifica(@PathVariable("id") Long id) {

		return "/imieitavoli/modifica";
	}
	
	@RequestMapping(value = "/ExecuteModifica", method = RequestMethod.POST)
	public String executeModifica(@Valid @ModelAttribute("userDTOModifica") TavoloDTOModifica tavoloDTOModifica) {
		//TODO esegui modifica
		
		return "/imieitavoli/result";
	}

}
