package com.khalid.fakebook.Service;

import com.khalid.fakebook.Repo.SessionRepo;
import com.khalid.fakebook.model.Session;
import com.khalid.fakebook.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SessionService {
    private final SessionRepo sessionRepo;

    public Session saveSession(String sessionId, User userId, ) {
        Session session = new Session();
        session.setSession(sessionId);
        session.setUserId(userId.getUser_id());
        return sessionRepo.save(session);
    }
}
