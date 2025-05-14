package com.vips.vipsconnect.repositories;

import com.vips.vipsconnect.models.Alumni;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumniRepository extends JpaRepository<Alumni, Long> {
    // No extra methods required for now
}
