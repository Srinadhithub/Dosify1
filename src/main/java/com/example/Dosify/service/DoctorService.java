package com.example.Dosify.service;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.dto.RequestDTO.DoctorRequestDto;
import com.example.Dosify.dto.ResponseDTO.DoctorResponseDto;
import com.example.Dosify.exception.CenterNotPresentException;
import com.example.Dosify.model.Doctor;

import java.util.List;

public interface DoctorService {

    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) throws CenterNotPresentException;

    List<DoctorResponseDto> getAllDoctorsAtleastAppointments(int count);

    List<DoctorResponseDto> getAllDoctorsbygenderandAge(Gender gender, int age);

    double getRatioOfMaletoFemale();

    DoctorResponseDto updateName(String name, String emailId);
}
