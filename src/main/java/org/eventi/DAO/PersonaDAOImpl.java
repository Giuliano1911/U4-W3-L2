package org.eventi.DAO;

import jakarta.persistence.EntityManager;
import org.eventi.EntityManagerUtil;
import org.eventi.Persona;

import java.util.List;

public class PersonaDAOImpl implements PersonaDao{

    @Override
    public void save(Persona persona) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(persona);
            em.getTransaction().commit();
            System.out.println("Persona creata con ID: " + persona.getId());
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
    public Persona getById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        Persona personaRecuperata = new Persona();
        try {
            em.getTransaction().begin();
            personaRecuperata = em.find(Persona.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            em.close();
        }
        return personaRecuperata;
    }

    @Override
    public void deleteById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Persona personaDaEliminare = em.find(Persona.class, id);
            if (personaDaEliminare != null) {
                em.remove(personaDaEliminare);
                System.out.println("Persona eliminata");
            } else System.out.println("Persona non trovata");
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
    public List<Persona> findAll() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            List<Persona> lista = em.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();
            em.getTransaction().commit();
            return lista;
        } finally {
            em.close();
        }
    }
}
