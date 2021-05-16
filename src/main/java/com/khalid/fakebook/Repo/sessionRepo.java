package com.khalid.fakebook.Repo;

import com.khalid.fakebook.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface sessionRepo extends JpaRepository<Session, Long> {

}
