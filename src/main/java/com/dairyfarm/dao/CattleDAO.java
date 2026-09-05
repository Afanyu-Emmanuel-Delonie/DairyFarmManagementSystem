package com.dairyfarm.dao;

import com.dairyfarm.model.Cattle;
import com.dairyfarm.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class CattleDAO implements GenericDAO<Cattle, Long> {

    @Override
    public void save(Cattle cattle) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(cattle);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new IllegalStateException("Unable to save cattle", e);
        }
    }

    @Override
    public void update(Cattle cattle) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(cattle);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new IllegalStateException("Unable to update cattle", e);
        }
    }

    @Override
    public void delete(Long id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Cattle cattle = session.get(Cattle.class, id);
            if (cattle != null) {
                session.remove(cattle);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new IllegalStateException("Unable to delete cattle", e);
        }
    }

    @Override
    public Cattle findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Cattle.class, id);
        }
    }

    @Override
    public List<Cattle> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Cattle", Cattle.class).list();
        }
    }
}
