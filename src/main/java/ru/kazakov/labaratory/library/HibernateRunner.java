package ru.kazakov.labaratory.library;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.kazakov.labaratory.library.entity.Reader;

import java.sql.SQLException;
import java.util.UUID;

public class HibernateRunner {
    public static void main(String[] args) throws SQLException {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Reader.class);
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            System.out.println("OK");

            session.beginTransaction();

            Reader reader = Reader.builder()
                    .id(UUID.randomUUID())
                    .name("John")
                    .surname("Sparrow")
                    .build();

            session.save(reader);

            session.getTransaction().commit();
        }
    }
}
