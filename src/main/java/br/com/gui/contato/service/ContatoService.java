package br.com.gui.contato.service;

import br.com.gui.contato.model.Contato;

public interface ContatoService {
    Iterable<Contato> findAll();
    Iterable<Contato> findAllOrder(String order);
    Contato create(Contato contato);
    Contato update(Contato contato);
    Contato findOne(Integer id);
    void delete(Contato task);
    public Iterable<Contato> findByNomeOrEmail(String filtro);
}
