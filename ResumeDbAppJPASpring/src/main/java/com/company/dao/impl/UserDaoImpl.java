package com.company.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;




@Repository("userDao1")
public class UserDaoImpl implements UserDaoInter {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        String jpql="select u from User u where 1=1";
        
            if(name!=null && !name.trim().isEmpty()){
                jpql+= "and u.name=: name";
            }

            if(surname!=null && !surname.trim().isEmpty()){
                jpql+= "and u.surname=: surname";
            }
            if(nationalityId!=null ){
                jpql+= "and u.nationality.id=:nid";
            }
            
            Query query =em.createQuery(jpql, User.class);

            if(name!=null && !name.trim().isEmpty()){
                query.setParameter("name", name);
            }

            if(surname!=null && !surname.trim().isEmpty()){
                query.setParameter("surname", surname);
            }

            if(nationalityId!=null){
                query.setParameter("nid", nationalityId);
            }

        return query.getResultList();
    }


//    @Override
//    public User findByEmailAndPassword(String email, String password) {
//        Query q=em.createQuery("select u from User u where u.email=:e and u.password=:p", User.class);
//        q.setParameter("e", email);
//        q.setParameter("p", password);
//        
//        List<User> list = q.getResultList();
//        
//        if(list.size() ==1){
//            return list.get(0);
//        }
//        
//        return null;
//    }
    
        @Override
    public User findByEmailAndPassword(String email, String password) {
            CriteriaBuilder cb=em.getCriteriaBuilder();
            CriteriaQuery<User> q1=cb.createQuery(User.class);
            Root<User> postRoot = q1.from(User.class);
            CriteriaQuery<User> q2 =q1
                    .where(cb.equal(postRoot.get("email"), email), cb.equal(postRoot.get("password"),password));
        
        Query query = em.createQuery(q2);
                                                
        List<User> list = query.getResultList();
        if(list.size() ==1){
            return list.get(0);
        }
        return null;
    }

      //JPQL
//    @Override
//    public User findByEmail(String email) {
//        Query q=em.createQuery("select u from User u where u.email=:e", User.class);
//        q.setParameter("e", email);
//        
//        List<User> list = q.getResultList();
//        
//        if(list.size() ==1){
//            return list.get(0);
//        }
//        
//        return null;
//    }
    
    
      //CriteriaBuilder
//    @Override
//    public User findByEmail(String email) {
//            CriteriaBuilder cb=em.getCriteriaBuilder();
//            CriteriaQuery<User> q1=cb.createQuery(User.class);
//            Root<User> postRoot = q1.from(User.class);
//            CriteriaQuery<User> q2 =q1
//                    .where(cb.equal(postRoot.get("email"), email));
//        
//        Query query = em.createQuery(q2);
//                                                
//        List<User> list = query.getResultList();
//        if(list.size() ==1){
//            return list.get(0);
//        }
//        return null;
//    }
    
    
    //NamedQuery
//    @Override
//    public User findByEmail(String email) {
//        Query query =em.createNamedQuery("User.findByEmail", User.class);
//        query.setParameter("email", email);
//
//        List<User> list = query.getResultList();
//        if (list.size() == 1) {
//            return list.get(0);
//        }
//        return null;
//    }


    //NativeSQL
    @Override
    public User findByEmail(String email) {
        Query query =em.createNativeQuery("select * from user where email=?", User.class);
        query.setParameter(1, email);

        List<User> list = query.getResultList();
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    
    
    @Override
    public boolean updateUser(User u) {
        em.merge(u);
        return true;
    }
    
    @Override
    public boolean removeUser(int id) {
        User u = em.find(User.class, id);
        em.remove(u);
        return true;
    }

    @Override
    public User getById(int userId) {
        User u = em.find(User.class, userId);
        return u;
    }

    private static BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean addUser(User u) {
        u.setPassword(crypt.hashToString(4, u.getPassword().toCharArray()));
        em.persist(u);
        return true;
    }


}