package com.vivas.jpa;

import com.google.common.collect.Lists;
import com.vivas.entity.AdminUser;
import com.vivas.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
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
            tx.commit();
            log.info("Save successfully");
        } catch (HibernateException e) {
            log.error("Save error cased: "+ e.toString());
            e.printStackTrace();
            tx.rollback();

        }
        HibernateUtils.closeConnection(session);
        System.exit(0);
    }

    public static void main(String[] args) {
        AdminUserJPA adminUserJPA = new AdminUserJPA();
//        adminUserJPA.save(new AdminUser("test1","test"));
        System.out.println(adminUserJPA.getAll().size());
        System.exit(0);
    }

}
