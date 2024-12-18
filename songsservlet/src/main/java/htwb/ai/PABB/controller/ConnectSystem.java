package htwb.ai.PABB.controller;

import javax.persistence.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class ConnectSystem {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("songsservlet");

    public static void main(String[] args) {

    }

    public void addSong(Song song) {

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;

        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            entityManager.persist(song);
            entityTransaction.commit();
        } catch (Exception ex) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }


    public Song getSong(int id) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT s FROM Song s WHERE s.id = :id";
        TypedQuery<Song> tq = entityManager.createQuery(query, Song.class);
        tq.setParameter("id", id);
        Song song = null;
        try {
            song = tq.getSingleResult();
            System.out.println(song.getTitle() + " by " + song.getArtist() + "  " + song.getLabel() + song.getReleased());
            return song;
        } catch(NoResultException e) {
            e.printStackTrace();
        }
        finally {
            entityManager.close();
        }
        return song;
    }


    public List<Song> getSongs() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT s FROM Song s WHERE s.id IS NOT NULL";
        TypedQuery<Song> tq = entityManager.createQuery(query, Song.class);
        List<Song> songs = new ArrayList<>();
        try {
            songs = tq.getResultList();
            songs.forEach(song -> System.out.println(song.getTitle() + " by " + song.getArtist() + "  " + song.getLabel() + song.getReleased()));
            return songs;
        }catch(NoResultException e) {
            e.printStackTrace();
        }
        finally {
            entityManager.close();
        }
        return songs;
    }


}