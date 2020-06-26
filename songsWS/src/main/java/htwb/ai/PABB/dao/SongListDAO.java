package htwb.ai.PABB.dao;


import javax.persistence.*;

import htwb.ai.PABB.model.Song;
import htwb.ai.PABB.model.SongList;

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
    public SongList getSongList(String ownerid) {
        return null;
    }

    @Override
    public boolean deleteSong(int id) {
        return false;
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
