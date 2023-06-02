package com.redhat.layerdemo.repository;

import java.util.List;

import com.redhat.layerdemo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DuckRepository extends JpaRepository<Duck, Long> {


    @Query("SELECT d FROM Duck d WHERE d.name LIKE %?1%")
    List<Duck> findByNameLike(String name);

    List<Duck> findByNameContaining(String title);
    
}
