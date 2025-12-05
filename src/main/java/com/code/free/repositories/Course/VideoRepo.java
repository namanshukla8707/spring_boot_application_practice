package com.code.free.repositories.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.free.entities.video.VideoEntity;
@Repository
public interface VideoRepo extends JpaRepository<VideoEntity, Long>  {

}
