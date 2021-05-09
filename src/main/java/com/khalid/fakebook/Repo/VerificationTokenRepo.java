package com.khalid.fakebook.Repo;

import com.khalid.fakebook.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepo extends JpaRepository<VerificationToken, Long> {
}
