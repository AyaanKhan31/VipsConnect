package com.vips.vipsconnect.repositories;

import com.vips.vipsconnect.models.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {}

//@Repository
//public interface EventRepository extends JpaRepository<Event, Long> {}
//
//@Repository
//public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long> {}

