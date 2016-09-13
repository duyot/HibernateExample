package com.vivas.jpa;

import com.google.common.collect.Lists;
import com.vivas.entity.AdminUser;
import com.vivas.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by duyot on 8/25/2016.
 */
public class AdminUserJPA {
    SessionFactory sessionFactory;
    Logger log = LoggerFactory.getLogger(AdminUserJPA.class);

    public AdminUserJPA(){
        sessionFactory = HibernateUtils.getSessionFactory();
    }

    public AdminUser findById(long id){
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.getCurrentSession();
//            session.unwrap(Session.class).setDefaultReadOnly(true);
            transaction = session.getTransaction();
            transaction.begin();

            AdminUser admin =  session.get(AdminUser.class,id);
            admin.setUsername("newusername");
            //get second admin
            log.info("Before get 1");
            AdminUser admin1 =  session.get(AdminUser.class,2896377L);
            log.info("After get 1");
            admin1.setUsername("newusernameadmin11");
            admin.setUsername("newusernameadmin00");
            //
            transaction.commit();
            log.info("SUCCESS");
            return admin;
        } catch (HibernateException e) {
            log.error("Error caused: "+ e.toString());
            e.printStackTrace();
            transaction.rollback();
        }finally {
            HibernateUtils.closeConnection(session);
        }
        return new AdminUser();
    }

    public List<AdminUser> getAll(){
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();
            return session.createCriteria(AdminUser.class).list();
        } catch (HibernateException e) {
            log.error("Save error cased: "+ e.toString());
            e.printStackTrace();
        }finally {
            HibernateUtils.closeConnection(session);
        }
        return Lists.newArrayList();
    }

    public void save(AdminUser adminUser){
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.getCurrentSession();
            tx = session.getTransaction();
            tx.begin();
            session.save(adminUser);
//            session.persist(adminUser);
            long id = adminUser.getId();
            log.info("Saved id: "+ id);
            tx.commit();
            log.info("Save successfully");
        } catch (HibernateException e) {
            log.error("Save error cased: "+ e.toString());
            e.printStackTrace();
            tx.rollback();
        }finally {
            HibernateUtils.closeConnection(session);
        }
    }

    public static void main(String[] args) {
        AdminUserJPA adminUserJPA = new AdminUserJPA();
//        adminUserJPA.save(new AdminUser("test_persist","test"));
        System.out.println(adminUserJPA.findById(2896376L).getUsername());
        System.exit(0);
    }

}
