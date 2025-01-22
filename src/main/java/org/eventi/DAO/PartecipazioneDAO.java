package org.eventi.DAO;

import org.eventi.Partecipazione;

import java.util.List;

public interface PartecipazioneDAO {
    void save (Partecipazione partecipazone);

    Partecipazione getById(Long id);

    void deleteById(Long id);

    List<Partecipazione> findAll();
}
