package it.poker.PokerOnline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller()
@RequestMapping("/utente")
public class UtenteController {

//    @Autowired
//    private UtenteService utenteService;
//
//    @RequestMapping(value = "/lista", method = RequestMethod.GET)
//    public String lista(Model model) {
//        List<Utente> listaUtenti = utenteService.lista();
//        model.addAttribute("utentiList", listaUtenti);
//        return "utente/lista";
//    }
//
//    @RequestMapping(value = "/crea", method = RequestMethod.GET)
//    public String crea() {
//        return "utente/crea";
//    }
//
//    @RequestMapping(value = "/salva", method = RequestMethod.POST)
//    public String salva(@ModelAttribute UtenteDTO utenteDTO, BindingResult bindingResult) {
//
//        bindingResult.hasErrors();
//        List<ObjectError> errors = bindingResult.getAllErrors();
//        errors.get(0).getDefaultMessage();
//
//        Utente utente = new Utente();
//        utente.setNome(utenteDTO.getNome());
//        utente.setCognome(utenteDTO.getCognome());
//        utenteService.salva(utente);
//        return "redirect:/utente/lista";
//    }
//
//    @RequestMapping(value = "/{id}/dettaglio", method = RequestMethod.GET)
//    public String dettaglio(@PathVariable("id") Long id, Model model) {
//        Utente utente = utenteService.dettaglio(id);
//        model.addAttribute("utente", utente);
//        return "utente/dettaglio";
//    }
//
//    @RequestMapping(value = "/{id}/modifica", method = RequestMethod.GET)
//    public String modifica(@PathVariable("id") Long id, Model model) {
//        Utente utente = utenteService.dettaglio(id);
//        model.addAttribute("utente", utente);
//        return "utente/modifica";
//    }
//
//    @RequestMapping(value = "/aggiorna", method = RequestMethod.POST)
//    public String aggiorna(@ModelAttribute UtenteDTO utenteDTO) {
//        Utente utente = utenteService.dettaglio(utenteDTO.getId());
//        utente.setNome(utenteDTO.getNome());
//        utente.setCognome(utenteDTO.getCognome());
//        utenteService.salva(utente);
//        return "redirect:/utente/lista";
//    }
//
//    @RequestMapping(value = "/{id}/elimina", method = RequestMethod.GET)
//    public String elimina(@PathVariable("id") Long id) {
//        Utente utente = utenteService.dettaglio(id);
//        utenteService.elimina(utente);
//        return "redirect:/utente/lista";
//    }

}
