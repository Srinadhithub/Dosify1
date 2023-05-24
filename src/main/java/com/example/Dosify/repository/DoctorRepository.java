package com.example.Dosify.repository;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
         @Query(value = "select *from doctor where vaccination_center_id=:id",nativeQuery = true)
     List<Doctor> findByCenterId(int id) ;


  List<Doctor> findByGenderAndAge(Gender gender, int age);
       @Query(value = "select*from Doctor where gender=:gender and age>=:age and vaccination_center_id=:id",nativeQuery = true)
    List<Doctor> findByGenderAndAgeCenter(String gender, int age,int id) ;

    List<Doctor> findByGender(Gender gender);

    Optional<Doctor> findByEmailId(String emailId);
}
