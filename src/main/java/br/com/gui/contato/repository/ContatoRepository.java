package br.com.gui.contato.repository;

import br.com.gui.contato.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ContatoRepository extends JpaRepository<Contato, Integer> {
    
    public Contato findByNome(String nome);
    
    public Iterable<Contato> findAllByOrderByIdAsc();
    
    public Iterable<Contato> findAllByOrderByNomeAsc();
    
    public Iterable<Contato> findAllByOrderByEmailAsc();
    
    //JPQL
    @Query("select c from Contato c where c.nome like %?1% or c.email like %?1%")
    Iterable<Contato> findByNomeOrEmail(String filtro);
    
    @Query(value = "SELECT * FROM contato", nativeQuery = true)
    Iterable<Contato> findAllNativeQuery();

}
