package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.User;


public class UserDB {
    public User get(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            User user = em.find(User.class, email);
            return user;
        } finally {
            em.close();
        }
    }
    
    public List<User> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<User> users = em.createNamedQuery("User.findAll", 
                User.class).getResultList();
            return users;
        } finally {
            em.close();
        }
    }

    
      public User getByUUID(String resetPasswordUUID) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            User user;
            List<User> users = getAll();
            user = users.get(2);
            return user;
        } finally {
            em.close();
        }
    }
      
     public boolean update(User user){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
    try{
        trans.begin();
        em.merge(user);
        trans.commit();
        return true;
    }catch(Exception ex){
        trans.rollback();
        return false;
    }
    finally{
        em.close();
    }    
     }
}
