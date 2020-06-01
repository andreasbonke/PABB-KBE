package htwb.ai.PABB.dao;

import htwb.ai.PABB.model.User;

import javax.persistence.*;


public class DBUserDAO implements IUserDAO{

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("songsWS");

    private String persistenceUnit2;

    public void setPersistenceUnit2(String pUnit) {
        System.out.println("I'm instanciated: " + pUnit);
        this.persistenceUnit2 = pUnit;
    }

    @Override
    public User getUserByUserId(String userid) {

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT s FROM User s WHERE s.userid = :userid";
        TypedQuery<User> tq = entityManager.createQuery(query, User.class);
        tq.setParameter("userid", userid);
        User user = null;
        try {
            user = tq.getSingleResult();
            return user;
        } catch (NoResultException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return user;
    }
}
