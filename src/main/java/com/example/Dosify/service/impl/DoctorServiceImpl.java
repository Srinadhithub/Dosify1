package com.example.Dosify.service.impl;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.dto.RequestDTO.DoctorRequestDto;
import com.example.Dosify.dto.ResponseDTO.CenterResponseDto;
import com.example.Dosify.dto.ResponseDTO.DoctorResponseDto;
import com.example.Dosify.dto.ResponseDTO.UserResponseDto;
import com.example.Dosify.exception.CenterNotPresentException;
import com.example.Dosify.model.Doctor;
import com.example.Dosify.model.User;
import com.example.Dosify.model.VaccinationCenter;
import com.example.Dosify.repository.AppointmentRepository;
import com.example.Dosify.repository.CenterRepository;
import com.example.Dosify.repository.DoctorRepository;
import com.example.Dosify.service.DoctorService;
import com.example.Dosify.transformer.DoctorTransformer;
import com.example.Dosify.transformer.VaccinationCenterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    CenterRepository centerRepository;
@Autowired
    DoctorRepository doctorRepository;
@Autowired
    AppointmentRepository appointmentRepository;
    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) throws CenterNotPresentException {

       Optional<VaccinationCenter> optionalCenter = centerRepository.findById(doctorRequestDto.getCenterId());
       if(!optionalCenter.isPresent()){
           throw new CenterNotPresentException("Invalid center id!");
       }

       VaccinationCenter center = optionalCenter.get();

       // dto -> entity
        Doctor doctor = DoctorTransformer.DoctorRequestDtoToDoctor(doctorRequestDto);
        doctor.setVaccinationCenter(center);
        // add doctor to current list of doctors at that center
        center.getDoctors().add(doctor);

        VaccinationCenter savedCenter = centerRepository.save(center);  // saves both center and doctor

        // prepare response Dto
        return DoctorTransformer.DoctorToDoctorResponseDto(doctor);
    }

    @Override
    public List<DoctorResponseDto> getAllDoctorsAtleastAppointments(int count) {
        List<Integer> doctorIds=appointmentRepository.getAllDoctorsAtleastAppointments(count);
            List<DoctorResponseDto> filteredList=new ArrayList<>();
        for(int doctorId:doctorIds){
            {
                Doctor doctor=doctorRepository.findById(doctorId).get();
                filteredList.add(DoctorTransformer.DoctorToDoctorResponseDto(doctor));
            }
        }
        return filteredList;
    }

    @Override
    public List<DoctorResponseDto> getAllDoctorsbygenderandAge(Gender gender, int age) {
        List<Doctor> doctorList=doctorRepository.findByGenderAndAge(gender,age);
        List<DoctorResponseDto> filteredList=new ArrayList<>();
        for(Doctor doctor:doctorList){
                filteredList.add(DoctorTransformer.DoctorToDoctorResponseDto(doctor));

        }
        return filteredList;
    }

    @Override
    public double getRatioOfMaletoFemale() {
        List<Doctor> doctorMaleList=doctorRepository.findByGender(Gender.MALE);
        List<Doctor> doctorFeMaleList=doctorRepository.findByGender(Gender.FEMALE);
        return doctorMaleList.size()/doctorFeMaleList.size();
    }

    @Override
    public DoctorResponseDto updateName(String name, String emailId) {
        Doctor doctor= doctorRepository.findByEmailId(emailId).get();
        doctor.setName(name);
        doctorRepository.save(doctor);

        return DoctorResponseDto.builder().name(doctor.getName()).build();
    }
}
