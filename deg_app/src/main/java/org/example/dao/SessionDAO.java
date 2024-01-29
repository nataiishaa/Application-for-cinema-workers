package org.example.dao;
import org.example.model.Session;
import java.util.List;
public interface SessionDAO {
    void addSession(Session session);
    List<Session> getAllSessions();
}
