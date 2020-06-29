package htwb.ai.PABB.dao;

import htwb.ai.PABB.model.Song;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DBSongDAO implements ISongDAO {

    //has to be reworked!!!
    //important!! DI in constructor
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("songsWS");

    private String persistenceUnit1;

    public void setPersistenceUnit1(String pUnit) {
        System.out.println("I'm instanciated: " + pUnit);
        this.persistenceUnit1 = pUnit;
    }

    @Override
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

    @Override
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
        } catch (NoResultException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return song;
    }

    @Override
    public List<Song> getSongs() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT s FROM Song s WHERE s.id IS NOT NULL";
        TypedQuery<Song> tq = entityManager.createQuery(query, Song.class);
        List<Song> songs = new ArrayList<>();
        try {
            songs = tq.getResultList();
            songs.forEach(song -> System.out.println(song.getTitle() + " by " + song.getArtist() + "  " + song.getLabel() + song.getReleased()));
            return songs;
        } catch (NoResultException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return songs;
    }

    @Override
    public boolean updateSong(Song replaceSong, int id) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;

        Song actSong = null;

        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            actSong = entityManager.find(Song.class, id);
            actSong.setTitle(replaceSong.getTitle());
            actSong.setArtist(replaceSong.getArtist());
            actSong.setLabel(replaceSong.getLabel());
            actSong.setReleased(replaceSong.getReleased());

            entityManager.persist(actSong);
            entityTransaction.commit();
            entityManager.close();
            return true;
        } catch (Exception ex) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            ex.printStackTrace();
            entityManager.close();
            return false;
        }
    }

    @Override
    public boolean deleteSong(int id) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        Song song = null;

        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            song = entityManager.find(Song.class, id);
            entityManager.remove(song);
            entityTransaction.commit();
            entityManager.close();
            return true;
        } catch (Exception ex) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            ex.printStackTrace();
            entityManager.close();
            return false;
        }
    }

}
