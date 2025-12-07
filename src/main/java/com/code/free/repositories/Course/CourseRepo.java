package com.code.free.repositories.course;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.code.free.entities.course.CourseEntity;

@Repository
public interface CourseRepo extends JpaRepository<CourseEntity, Long> {

    @Query(value = "SELECT * FROM public.course WHERE id = :courseId and is_active = true", nativeQuery = true)
    Optional<CourseEntity> findCourseById(@Param("courseId") Long courseId);
}
