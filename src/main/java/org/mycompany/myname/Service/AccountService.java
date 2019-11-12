package org.mycompany.myname.Service;

import org.mycompany.myname.accounts.UserProfile;
import org.mycompany.myname.executor.Executor;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class AccountService {
    private static final Map<String, UserProfile> sessionIdToProfile = new HashMap<>();
    private Transaction transaction = null;

    public AccountService() {
    }

    public void addNewUser(UserProfile userProfile) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // save the objects
            session.save(userProfile);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public UserProfile getUserByLogin(String login) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            UserProfile userProfile = session.get(UserProfile.class, login);
            return userProfile;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public static void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public static void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
