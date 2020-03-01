package it.poker.PokerOnline.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.poker.PokerOnline.dto.user.UserDTORegistrazione;
import it.poker.PokerOnline.model.User;
import it.poker.PokerOnline.model.utils.ValidationErrorsUtils;
import it.poker.PokerOnline.service.UserService;

@Controller()
@RequestMapping("/")
public class HomeController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String login(HttpSession session) {
		if(session.getAttribute("userInfo") != null) {
			return "/home";
		}
		
		return "/login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "/login";
	}

	@RequestMapping(value = "/eseguiAccesso", method = RequestMethod.POST)
	public String eseguiAccesso(HttpSession session, @RequestParam("inputUsername") String username,
			@RequestParam("inputPassword") String password, Model model) {

		User userCheAccede = userService.eseguiAccesso(username, password);

		if (userCheAccede == null) {
			model.addAttribute("messaggioErrore", "Login Errato");
			return "/login";
		}
		// metto l'utente in sessione
		session.setAttribute("userInfo", userCheAccede);

		// System.out.println(session.getAttribute("userInfo"));

		return "/home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
		return "/home";
	}

	@RequestMapping(value = "/registrati", method = RequestMethod.GET)
	public String registrazione() {
		return "/registrati";
	}

	@RequestMapping(value = "/eseguiRegistrazione", method = RequestMethod.POST)
	public String eseguiRegistrazione(
			@Valid @ModelAttribute("userDTOregistrazione") UserDTORegistrazione userDTOregistrazione,
			BindingResult bindingResult, Model model) {

		List<FieldError> errors = ValidationErrorsUtils.sorted(bindingResult.getFieldErrors());
		
		if(!userService.usernameDisponibile(userDTOregistrazione.getUsername())) {
			FieldError usernameNonDisponibileError = new FieldError("username", "username", "Username non disponibile");
			errors.add(usernameNonDisponibileError);
		}

		if (!errors.isEmpty()) {
			model.addAttribute("utenteAttr", userDTOregistrazione);
			model.addAttribute("utenteErrors", errors);
			return "/registrati";
		}

		User userInRegistrazione = UserDTORegistrazione.buildModelFromDTO(userDTOregistrazione);
		userService.registraUser(userInRegistrazione);
		

		model.addAttribute("messaggioConferma",
				"Registrazione effettuata con successo, contatta un Admin per attivare l'account");

		return "/login";
	}

}
