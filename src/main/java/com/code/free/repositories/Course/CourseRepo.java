package com.code.free.repositories.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.free.entities.cousre.CourseEntity;
@Repository
public interface CourseRepo extends JpaRepository<CourseEntity, Long>  {

}
