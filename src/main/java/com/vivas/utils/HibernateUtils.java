package com.vivas.utils;

import com.vivas.entity.AdminUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by duyot on 8/25/2016.
 */
public class HibernateUtils {
    public static final SessionFactory sessionFactory = getSessionFactory();

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(AdminUser.class);
        configuration.configure("hibernate.cfg.xml");
        configuration.configure();

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        // Tạo đối tượng SessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

    public static void closeConnection(Session session){
        if(session != null && session.isOpen()){
            session.close();
        }
    }
    public static void rollback(Transaction tx){
        tx.rollback();
    }
}
