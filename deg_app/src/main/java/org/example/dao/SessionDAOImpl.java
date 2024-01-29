package org.example.dao;
import org.example.model.Session;
import java.util.ArrayList;
import java.util.List;
public class SessionDAOImpl implements SessionDAO {
    private List<Session> sessions = new ArrayList<>();

    @Override
    public void addSession(Session session) {
        sessions.add(session);
    }

    @Override
    public List<Session> getAllSessions() {
        return new ArrayList<>(sessions);
    }
}