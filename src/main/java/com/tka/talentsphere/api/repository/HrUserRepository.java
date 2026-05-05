package com.tka.talentsphere.api.repository;

import com.tka.talentsphere.api.entity.HrUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface HrUserRepository extends JpaRepository<HrUser, Long> {
    Optional<HrUser> findByEmail(String email);
    boolean existsByEmail(String email);
}
