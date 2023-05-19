package com.example.Dosify.repository;

import com.example.Dosify.model.Dose1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface Dose1Repository extends JpaRepository<Dose1,Integer> {
}
