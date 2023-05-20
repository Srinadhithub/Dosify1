package com.example.Dosify.repository;

import com.example.Dosify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByMobNo(String mobNo);

    @Query(value = "select * from User where is_dose1_taken=false and is_dose2_taken=false",nativeQuery = true)
    public List<User> NotEvenSingleDose();
    @Query(value = "select * from User where is_dose1_taken=true and is_dose2_taken=false",nativeQuery = true)
    List<User> getSingleDose();
    @Query(value = "select * from User where is_dose1_taken=true and is_dose2_taken=true",nativeQuery = true)
    List<User> getTwoDoseVaccinated();
    @Query(value = "select * from User where is_dose1_taken=false and is_dose2_taken=false and gender =:gender",nativeQuery = true)
    List<User> getNotEvenSingleDoseByGender(String gender);
    @Query(value = "select * from User where is_dose1_taken=true and is_dose2_taken=true and gender =:gender",nativeQuery = true)
    List<User> getTwoDoseVaccinatedByGender(String gender);
    @Query(value = "select * from User where is_dose1_taken=true and is_dose2_taken=false and gender =:gender",nativeQuery = true)
    List<User> getSingleDoseByGender(String gender);
      Optional<User>  findByEmailId(String emailId);
}
