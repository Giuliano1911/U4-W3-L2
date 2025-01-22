package org.eventi.DAO;

import jakarta.persistence.EntityManager;
import org.eventi.EntityManagerUtil;
import org.eventi.Partecipazione;

import java.util.List;

public class PartecipazioneDAOImpl implements PartecipazioneDAO{

    @Override
    public void save(Partecipazione partecipazione) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(partecipazione);
            em.getTransaction().commit();
            System.out.println("Partecipazione creata con ID: " + partecipazione.getId());
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
    public Partecipazione getById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        Partecipazione partecipazioneRecuperata = new Partecipazione();
        try {
            em.getTransaction().begin();
            partecipazioneRecuperata = em.find(Partecipazione.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            em.close();
        }
        return partecipazioneRecuperata;
    }

    @Override
    public void deleteById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Partecipazione partecipazioneDaEliminare = em.find(Partecipazione.class, id);
            if (partecipazioneDaEliminare != null) {
                em.remove(partecipazioneDaEliminare);
                System.out.println("Partecipazione eliminata");
            } else System.out.println("Partecipazione non trovata");
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
    public List<Partecipazione> findAll() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            List<Partecipazione> lista = em.createQuery("SELECT p FROM Partecipazione p", Partecipazione.class).getResultList();
            em.getTransaction().commit();
            return lista;
        } finally {
            em.close();
        }
    }
}
