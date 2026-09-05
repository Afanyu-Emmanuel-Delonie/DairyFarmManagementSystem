package com.dairyfarm.dao;

import com.dairyfarm.model.MilkProduction;
import com.dairyfarm.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class MilkProductionDAO implements GenericDAO<MilkProduction, Long> {

    @Override
    public void save(MilkProduction milkProduction) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(milkProduction);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new IllegalStateException("Unable to save milk production", e);
        }
    }

    @Override
    public void update(MilkProduction milkProduction) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(milkProduction);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new IllegalStateException("Unable to update milk production", e);
        }
    }

    @Override
    public void delete(Long id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            MilkProduction record = session.get(MilkProduction.class, id);
            if (record != null) {
                session.remove(record);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new IllegalStateException("Unable to delete milk production", e);
        }
    }

    @Override
    public MilkProduction findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(MilkProduction.class, id);
        }
    }

    @Override
    public List<MilkProduction> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM MilkProduction m JOIN FETCH m.cattle", MilkProduction.class).list();
        }
    }
}
