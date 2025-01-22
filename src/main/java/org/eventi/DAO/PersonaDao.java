package org.eventi.DAO;

import org.eventi.Persona;

import java.util.List;

public interface PersonaDao {

        void save (Persona persona);

        Persona getById(Long id);

        void deleteById(Long id);

        List<Persona> findAll();
}
