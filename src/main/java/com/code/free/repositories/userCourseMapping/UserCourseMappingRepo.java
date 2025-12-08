package com.code.free.repositories.userCourseMapping;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.code.free.entities.userCourseMapping.UserCourseMappingEntity;
import com.code.free.entities.userCourseMapping.UserCourseMappingId;

public interface UserCourseMappingRepo extends JpaRepository<UserCourseMappingEntity, UserCourseMappingId> {
    Optional<UserCourseMappingEntity> findByUserIdAndCourseId(Long userId, Long courseId);

    @Query(value = "SELECT COUNT(*) > 0 FROM user_course_mapping WHERE user_id = :userId AND course_id = :courseId AND is_active = true", nativeQuery = true)
    Boolean existsMapping(@Param("userId") Long userId, @Param("courseId") Long courseId);
}
