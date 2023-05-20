package com.example.Dosify.repository;

import com.example.Dosify.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    @Query(value = "select*from Doctor where gender=:gender and age>=:age",nativeQuery = true)
    List<Doctor> getAllDoctorsbygenderandAge(String gender, int age);

    List<Doctor> findByGender(String gender);

    Optional<Doctor> findByEmailId(String emailId);
}
