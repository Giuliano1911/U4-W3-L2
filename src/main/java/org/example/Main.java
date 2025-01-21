package org.example;

import jakarta.persistence.EntityManager;
import org.eventi.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Evento evento1 = new Evento("GigiSabbani", LocalDate.of(2025, 02, 27), "Spettacolo", TipoEvento.PUBBLICO, 20000);
        System.out.println(getById(5L));
    }

    public static void save(Evento evento) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(evento);
            em.getTransaction().commit();
            System.out.println("Persona creata con ID: " + evento.getId());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public static Evento getById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        Evento eventoRecuperato = new Evento();
        try {
            em.getTransaction().begin();
            eventoRecuperato = em.find(Evento.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            em.close();
        }
        return eventoRecuperato;
    }

    public static void delete(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Evento eventoDaEliminare = em.find(Evento.class, id);
            if (eventoDaEliminare != null) {
                em.remove(eventoDaEliminare);
                System.out.println("Evento eliminato");
            } else System.out.println("Evento non trovato");
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}