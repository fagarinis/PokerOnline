package it.poker.PokerOnline.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.poker.PokerOnline.service.TavoloService;

@Controller()
@RequestMapping("/play")
public class PlayController {


	@RequestMapping(method = RequestMethod.GET)
	public String homePlay(Model model) {
		return "/play/homePlayManagement";
	}

	@RequestMapping(value = "/PrepareSearch", method = RequestMethod.GET)
	public String prepareSearch() {
		//TODO
		return "/play/search";
	}

	@RequestMapping(value = "/ExecuteSearch", method = RequestMethod.POST)
	public String executeSearch(HttpSession session, Model model, @RequestParam String denominazione,
			@RequestParam String dataCreazione, @RequestParam String cifraMinimaPerEntrare,
			@RequestParam Long idCreatore, @RequestParam Long idPartecipante) {

		//TODO
		return "/play/result";
	}
	
	
	@RequestMapping(value = "/PrepareCompraCredito", method = RequestMethod.GET)
	public String prepareCompraCredito() {
		//TODO
		return "/play/compraCredito";
	}
	
	@RequestMapping(value = "/ExecuteCompraCredito", method = RequestMethod.POST)
	public String executeCompraCredito() {
		//TODO
		return "/play/homePlayManagement";
	}
	
	@RequestMapping(value = "/ExecuteGoToLastGame", method = RequestMethod.GET)
	public String executeGoToLastGame(HttpSession session) {
		//TODO
		return "/play/gioca";
	}
	

}
