package com.example.Dosify.repository;

import com.example.Dosify.model.Appointment;
import com.example.Dosify.model.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
     @Query(value ="select doctor_id from appointment group by doctor_id having count(*) >=:count",nativeQuery = true)
    List<Integer> getAllDoctorsAtleastAppointments(int count);
    @Query(value = "select*from appointment where user_id :=userId",nativeQuery = true)
    List<Appointment> getByuserId(int userId);
}
