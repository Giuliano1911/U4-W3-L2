package org.eventi.DAO;

import jakarta.persistence.EntityManager;
import org.eventi.EntityManagerUtil;
import org.eventi.Evento;

import java.util.List;

public class EventoDAOImpl implements EventoDAO {

    @Override
    public void save(Evento evento) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(evento);
            em.getTransaction().commit();
            System.out.println("Evento creato con ID: " + evento.getId());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Evento getById(Long id) {
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

    @Override
    public void deleteById(Long id) {
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

    @Override
    public List<Evento> findAll() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            List<Evento> lista = em.createQuery("SELECT e FROM Evento e", Evento.class).getResultList();
            em.getTransaction().commit();
            return lista;
        } finally {
            em.close();
        }
    }
}