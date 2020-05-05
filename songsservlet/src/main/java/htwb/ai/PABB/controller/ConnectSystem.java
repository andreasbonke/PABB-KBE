package htwb.ai.PABB.controller;

import javax.persistence.*;
import java.sql.SQLOutput;
import java.util.List;

public class ConnectSystem {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("songsservlet");

    public static void main(String[] args) {
        //addSong("tick","trick","track",4);
        //addSong("bla","bli","blub",9);
        getSong(1);
        System.out.println();
        System.out.println();
        getSongs();
        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addSong(String title, String artist, String label, int released) {

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;

        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Song song = new Song();
            song.setTitle(title);
            song.setArtist(artist);
            song.setLabel(label);
            song.setReleased(released);

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


    public static void getSong(int id) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT s FROM Song s WHERE s.id = :id";
        TypedQuery<Song> tq = entityManager.createQuery(query, Song.class);
        tq.setParameter("id", id);
        Song song = null;
        try {
            song = tq.getSingleResult();
            System.out.println(song.getTitle() + " by " + song.getArtist() + "  " + song.getLabel() + song.getReleased());
        } catch(NoResultException e) {
            e.printStackTrace();
        }
        finally {
            entityManager.close();
        }
    }


    public static void getSongs() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT s FROM Song s WHERE s.id IS NOT NULL";
        TypedQuery<Song> tq = entityManager.createQuery(query, Song.class);
        List<Song> songs;
        try {
            songs = tq.getResultList();
            songs.forEach(song -> System.out.println(song.getTitle() + " by " + song.getArtist() + "  " + song.getLabel() + song.getReleased()));

        }catch(NoResultException e) {
            e.printStackTrace();
        }
        finally {
            entityManager.close();
        }
    }
}