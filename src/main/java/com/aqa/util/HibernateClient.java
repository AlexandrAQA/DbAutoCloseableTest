package com.aqa.util;

import com.aqa.dto.Student;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

@Log4j2
public class HibernateClient {
    private static SessionFactory sessionFactory;

    private HibernateClient() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Student.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception exception) {
                log.error(exception.getMessage());
            }
        }
        return sessionFactory;
    }


    public static Student findById(int id) {
        try (Session session = getSessionFactory().openSession()) {
            return session.get(Student.class, id);
        }
    }
}
