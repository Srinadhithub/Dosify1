package com.example.Dosify.service;


import com.example.Dosify.Enum.CenterType;
import com.example.Dosify.dto.RequestDTO.CenterRequestDto;
import com.example.Dosify.dto.ResponseDTO.CenterResponseDto;
import com.example.Dosify.dto.ResponseDTO.DoctorResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VaccinationCenterService {

    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto);

    List<DoctorResponseDto> getDoctorsFromCenterId(int id);

    List<CenterResponseDto> getCentersByCenterType(CenterType centerType);

    List<DoctorResponseDto> getAllDoctorsbygenderandAgeCenter( String gender, int age, int id);

    List<DoctorResponseDto> getAllDoctorsbyGender(String gender, int id);

    void deleteById(int id);
}
