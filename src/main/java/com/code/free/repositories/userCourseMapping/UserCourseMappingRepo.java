package com.code.free.repositories.userCourseMapping;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.free.entities.userCourseMapping.UserCourseMappingEntity;
import com.code.free.entities.userCourseMapping.UserCourseMappingId;

public interface UserCourseMappingRepo extends JpaRepository<UserCourseMappingEntity, UserCourseMappingId> {
    Optional<UserCourseMappingEntity> findByUserIdAndCourseId(Long userId, Long courseId);
}
