package org.example;

import org.eventi.*;
import org.eventi.DAO.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Evento evento1 = new Evento("Concerto",LocalDate.of(2025,2,2),"bello",TipoEvento.PRIVATO,5);
        Location location1 = new Location("ciao","Catania");

        evento1.setLocation(location1);

        EventoDAO eventoDAO = new EventoDAOImpl();

        LocationDAO locationDAO = new LocationDAOImpl();

        System.out.println(eventoDAO.findAll());

    }
}