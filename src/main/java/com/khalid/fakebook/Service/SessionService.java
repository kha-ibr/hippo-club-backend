package com.khalid.fakebook.Service;

import com.khalid.fakebook.Repo.SessionRepo;
import com.khalid.fakebook.model.Session;
import com.khalid.fakebook.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class SessionService {
    private final SessionRepo sessionRepo;

    @Transactional
    public Session saveSession(String sessionId, User user) {
        Session session = new Session();

        session.setSession(sessionId);
        session.setUser(user);
        return sessionRepo.save(session);
    }

    @Transactional
    public Session findBySession(String session) {
        return sessionRepo.findBySession(session);
    }

    @Transactional
    public void deletePost(Session session) {
        sessionRepo.delete(session);
    }
}
