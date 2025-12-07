package com.code.free.repositories.video;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.code.free.entities.video.VideoEntity;
import com.code.free.responses.VideoResponses.VideoResponseDto;

@Repository
public interface VideoRepo extends JpaRepository<VideoEntity, Long> {

    @Query(value = "Select title,description,position from public.video where course_id = :courseId and is_active = true order by position asc", nativeQuery = true)
    List<VideoResponseDto> findAllWithoutUrl(@Param("courseId") Long courseId);

}
