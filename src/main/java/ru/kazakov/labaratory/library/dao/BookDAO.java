package ru.kazakov.labaratory.library.dao;

import org.hibernate.Session;

public class BookDAO {
    private Session session;

    public BookDAO(Session session) {
        this.session = session;
    }

}
