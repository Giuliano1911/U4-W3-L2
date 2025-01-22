package org.eventi.DAO;

import org.eventi.Evento;

import java.util.List;

public interface EventoDAO {

    void save (Evento evento);

    Evento getById(Long id);

    void deleteById(Long id);

    List<Evento> findAll();
}
