package com.example.Supermart.repo;

import com.example.Supermart.model.Supermart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupermartRepo extends JpaRepository<Supermart,Long> {

}
