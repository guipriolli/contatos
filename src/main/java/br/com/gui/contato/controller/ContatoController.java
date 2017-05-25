package br.com.gui.contato.controller;

import br.com.gui.contato.model.Contato;
import br.com.gui.contato.service.ContatoService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContatoController {
    
    @Autowired
    private final ContatoService service;
    
    public ContatoController(ContatoService service) {
        this.service = service;
    }
    
    @RequestMapping(path = "/contatos", method = RequestMethod.GET)
    public String list(@RequestParam(name = "order", required = false) String order, Map<String, Object> model) {
        Iterable<Contato> contatos = service.findAllOrder(order);
        model.put("contatos", contatos);
        return "index";
    }
    
    @RequestMapping(path = "/filtro", method = RequestMethod.POST)
    public String search(String filtro, Map<String, Object> model) {
        Iterable<Contato> contatos = service.findByNomeOrEmail(filtro);
        model.put("contatos", contatos);
        return "index";
    }
    
    @RequestMapping(path = "/contato/novo", method = RequestMethod.GET)
    public String newContato() {
        return "novo-contato";
    }
    
    @RequestMapping(path = "/contato", method = RequestMethod.POST)
    public String create(String nome, String email) {
        Contato contato = new Contato(nome, email);
        service.create(contato);
        return "redirect:/contatos";
    }
    
    @RequestMapping(path = "/contato/editar/{id}", method = RequestMethod.GET)
    public String editContato(@PathVariable Integer id, Map<String, Object> model) {
        Contato contato = service.findOne(id);
        model.put("contato", contato);
        return "editar-contato";
    }
    
    @RequestMapping(path = "/contato/editar/{id}", method = RequestMethod.POST)
    public String upate(@PathVariable Integer id, String nome, String email) {
        Contato contato = service.findOne(id);
        contato.setNome(nome);
        contato.setEmail(email);
        service.update(contato);
        return "redirect:/contatos";
    }
    
    @RequestMapping(path = "/contato/deletar/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer id) {
        Contato contato = service.findOne(id);
        service.delete(contato);
        return "redirect:/contatos";
    }
    
//    @RequestMapping(path = "/contatos/filtro/{tipo}", method = RequestMethod.GET)
//    public String list(@PathVariable String filtro, Map<String, Object> model) {
//        
//        Iterable<Contato> contatos = null;
//        
//        if (filtro.equals("nome")) {
//            
//        } else if (filtro.equals("email")) {
//            
//        } else {
//            contatos = service.findAllByOrderByIdAsc();
//        }
//        
//        
//        model.put("contatos", contatos);
//        return "index";
//    }
}
