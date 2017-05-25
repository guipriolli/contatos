package br.com.gui.contato.service;

import br.com.gui.contato.model.Contato;
import br.com.gui.contato.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContatoServiceImpl implements ContatoService {

    @Autowired
    private final ContatoRepository repository;
    
    public ContatoServiceImpl(ContatoRepository repository) {
        this.repository = repository;
    }
    
    @Transactional
    public Iterable<Contato> findAll() {
        return repository.findAll();
    }
    
    @Transactional
    public Iterable<Contato> findAllOrder(String order) {
        
        Iterable<Contato> contatos = null;
        
        if (order == null || order.isEmpty() || order.equals("id")) {
            contatos = repository.findAllByOrderByIdAsc();
        } else if (order.equals("nome")) {
            contatos = repository.findAllByOrderByNomeAsc();
        } else if (order.equals("email")) {
            contatos = repository.findAllByOrderByEmailAsc();
        }
        
        return contatos;
    }
    
    @Transactional
    public Contato create(Contato contato) {
        return repository.save(contato);
    }

    @Transactional
    public Contato findOne(Integer id) {
        return repository.findOne(id);
    }

    @Transactional
    public Contato update(Contato contato) {
        return repository.save(contato);
    }

    @Transactional
    public void delete(Contato contato) {
        repository.delete(contato);
    }

    @Transactional
    public Iterable<Contato> findByNomeOrEmail(String filtro) {
        return repository.findByNomeOrEmail(filtro);
    }

}
