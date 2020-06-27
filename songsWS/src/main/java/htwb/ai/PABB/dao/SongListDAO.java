package htwb.ai.PABB.dao;


import javax.persistence.*;

import htwb.ai.PABB.model.Song;
import htwb.ai.PABB.model.SongList;

import java.util.ArrayList;
import java.util.List;

public class SongListDAO implements ISongListDAO {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("songsWS");

    private String persistenceUnit3;

    public void setPersistenceUnit3(String pUnit) {
        System.out.println("I'm instanciated: " + pUnit);
        this.persistenceUnit3 = pUnit;
    }

    @Override
    public void addSongList(SongList songList) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;

        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            entityManager.persist(songList);
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
    public SongList getSongList(int id) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT s FROM SongList s WHERE s.id = :id";
        TypedQuery<SongList> tq = entityManager.createQuery(query, SongList.class);
        tq.setParameter("id", id);
        SongList songList = null;
        try {
            songList = tq.getSingleResult();
            return songList;
        } catch (NoResultException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return songList;
    }

    @Override
    public List<SongList> getSongList(String ownerid) {
        //EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        //String query = "SELECT s FROM SongList s WHERE s.user.id = :ownerid";
        //TypedQuery<SongList> tq = entityManager.createQuery(query, SongList.class);
        List<SongList> songs = new ArrayList<>();
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        Query q = entityManager.createQuery("SELECT s FROM SongList s WHERE s.user.id = :ownerid")
                .setParameter("ownerid", ownerid);
        //return q.getResultList();
        try {
            songs = q.getResultList();
            //songs.forEach(song -> System.out.println(song.getTitle() + " by " + song.getArtist() + "  " + song.getLabel() + song.getReleased()));
            return songs;
        } catch (NoResultException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return songs;
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

    @Override
    public Song findSongById(Integer id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        Song entity = null;
        try {
            entity = em.find(Song.class, id);
        } finally {
            em.close();
        }
        return entity;
    }

}
