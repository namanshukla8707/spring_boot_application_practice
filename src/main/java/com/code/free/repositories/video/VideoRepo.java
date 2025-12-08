package com.code.free.repositories.video;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.code.free.entities.video.VideoEntity;
import com.code.free.responses.VideoResponses.VideoResponseInterfaceDto;

@Repository
public interface VideoRepo extends JpaRepository<VideoEntity, Long> {

    @Query(value = "Select title,description,position,link as url from public.video where course_id = :courseId and is_active = true order by position asc", nativeQuery = true)
    List<VideoResponseInterfaceDto> findAllWithoutUrl(@Param("courseId") Long courseId);

    @Query(value = "Select coalesce(max(position),0) from public.video where course_id = :courseId and is_active = true", nativeQuery = true)
    Integer findLastVideoPositionByCourseId(@Param("courseId") Long courseId);
    
}
