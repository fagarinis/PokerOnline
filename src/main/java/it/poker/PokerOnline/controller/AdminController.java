package it.poker.PokerOnline.controller;

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

import it.poker.PokerOnline.dto.user.UserDTOInsert;
import it.poker.PokerOnline.dto.user.UserDTOModifica;
import it.poker.PokerOnline.model.Role;
import it.poker.PokerOnline.model.User;
import it.poker.PokerOnline.model.enm.StatoUser;
import it.poker.PokerOnline.model.utils.ValidationErrorsUtils;
import it.poker.PokerOnline.service.RoleService;
import it.poker.PokerOnline.service.UserService;

@Controller()
@RequestMapping("/gestioneAmministrazione")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	private void addListaStati(Model model) {
		model.addAttribute("statiListAttr", StatoUser.values());
	}

	private void addListaRuoli(Model model) {
		model.addAttribute("ruoliListAttr", roleService.listAll());
	}

	@RequestMapping(method = RequestMethod.GET)
	public String prepareSearchUser(Model model) {
		addListaStati(model);
		return "/gestioneAmministrazione/search";
	}

	@RequestMapping(value = "/ExecuteSearch", method = RequestMethod.POST)
	public String executeSearch(Model model, @ModelAttribute User userExample) {

		model.addAttribute("listaUtentiAttr", userService.findByExample(userExample));
		return "/gestioneAmministrazione/result";
	}

	@RequestMapping(value = "/{id}/ExecuteDettaglio", method = RequestMethod.GET)
	public String dettaglio(@PathVariable("id") Long id, Model model) {
		User utente = userService.caricaSingolo(id);
		model.addAttribute("utenteAttr", utente);
		return "/gestioneAmministrazione/dettaglio";
	}

	@RequestMapping(value = "/{id}/PrepareModifica", method = RequestMethod.GET)
	public String prepareModifica(@PathVariable("id") Long id, Model model) {
		User utente = userService.caricaSingolo(id);

		addListaStati(model);
		addListaRuoli(model);
		model.addAttribute("utenteAttr", utente);
		return "/gestioneAmministrazione/modifica";
	}

	@RequestMapping(value = "/ExecuteModifica", method = RequestMethod.POST)
	public String prepareModifica(HttpSession session, @Valid @ModelAttribute("userDTOModifica") UserDTOModifica userDTOModifica,
			BindingResult bindingResult, Model model, @RequestParam(required = false) String[] ruolo) {

		userDTOModifica.setRuoli(Role.buildRoleListFromIdRoleArray(ruolo));

		List<FieldError> errors = ValidationErrorsUtils.sorted(bindingResult.getFieldErrors());

		if (userService.usernameOccupatoDaDiversoId(userDTOModifica.getId(), userDTOModifica.getUsername())) {
			FieldError usernameNonDisponibileError = new FieldError("username", "username", "Username non disponibile");
			errors.add(usernameNonDisponibileError);
		}

		if (!errors.isEmpty()) {
			addListaStati(model);
			addListaRuoli(model);
			model.addAttribute("utenteAttr", userDTOModifica);
			model.addAttribute("utenteErrors", errors);
			return "/gestioneAmministrazione/modifica";
		}
		User userUpdate = UserDTOModifica.buildModelFromDTO(userDTOModifica);

		userService.aggiornaConRuoli(userUpdate);
		//Aggiorna utente in sessione se viene modificato l'account dell'utente in sessione
		if(userUpdate.getId() == ((User) session.getAttribute("userInfo")).getId()) {
			session.setAttribute("userInfo", userService.caricaSingoloConRuoli(userUpdate.getId()));
		}

		model.addAttribute("messaggioConferma", "Modifica avvenuta con successo");
		model.addAttribute("listaUtentiAttr", userService.listAll());
		return "/gestioneAmministrazione/result";
	}

	@RequestMapping(value = "/PrepareInsert", method = RequestMethod.GET)
	public String prepareInsert(Model model) {

		addListaStati(model);
		addListaRuoli(model);
		return "/gestioneAmministrazione/insert";
	}

	@RequestMapping(value = "/ExecuteInsert", method = RequestMethod.POST)
	public String executeInsert(@Valid @ModelAttribute("userDTOInsert") UserDTOInsert userDTOInsert,
			BindingResult bindingResult, Model model, @RequestParam(required = false) String[] ruolo) {

		userDTOInsert.setRuoli(Role.buildRoleListFromIdRoleArray(ruolo));
		List<FieldError> errors = ValidationErrorsUtils.sorted(bindingResult.getFieldErrors());

		if (!userService.usernameDisponibile(userDTOInsert.getUsername())) {
			FieldError usernameNonDisponibileError = new FieldError("username", "username", "Username non disponibile");
			errors.add(usernameNonDisponibileError);
		}

		if (!errors.isEmpty()) {
			addListaRuoli(model);
			model.addAttribute("utenteAttr", userDTOInsert);
			model.addAttribute("utenteErrors", errors);
			return "/gestioneAmministrazione/insert";
		}
		
		User userInsert = UserDTOInsert.buildModelFromDTO(userDTOInsert);
		userService.inserisciNuovoUser(userInsert, StatoUser.CREATO);

		model.addAttribute("messaggioConferma", "Inserimento avvenuto con successo");
		model.addAttribute("listaUtentiAttr", userService.listAll());
		return "/gestioneAmministrazione/result";
	}

}
