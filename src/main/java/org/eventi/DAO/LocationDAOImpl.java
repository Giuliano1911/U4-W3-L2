package org.eventi.DAO;

import jakarta.persistence.EntityManager;
import org.eventi.EntityManagerUtil;
import org.eventi.Location;

import java.util.List;

public class LocationDAOImpl implements LocationDAO{

    @Override
    public void save(Location location) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(location);
            em.getTransaction().commit();
            System.out.println("Location creata con ID: " + location.getId());
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
    public Location getById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        Location locationRecuperata = new Location();
        try {
            em.getTransaction().begin();
            locationRecuperata = em.find(Location.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            em.close();
        }
        return locationRecuperata;
    }

    @Override
    public void deleteById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Location locationDaEliminare = em.find(Location.class, id);
            if (locationDaEliminare != null) {
                em.remove(locationDaEliminare);
                System.out.println("Location eliminata");
            } else System.out.println("Location non trovata");
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
    public List<Location> findAll() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            List<Location> lista = em.createQuery("SELECT l FROM Location l", Location.class).getResultList();
            em.getTransaction().commit();
            return lista;
        } finally {
            em.close();
        }
    }
}
