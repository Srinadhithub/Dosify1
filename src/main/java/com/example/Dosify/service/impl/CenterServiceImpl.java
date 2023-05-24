package com.example.Dosify.service.impl;

import com.example.Dosify.Enum.CenterType;
import com.example.Dosify.dto.RequestDTO.CenterRequestDto;
import com.example.Dosify.dto.ResponseDTO.CenterResponseDto;
import com.example.Dosify.dto.ResponseDTO.DoctorResponseDto;
import com.example.Dosify.model.Doctor;
import com.example.Dosify.model.VaccinationCenter;
import com.example.Dosify.repository.CenterRepository;
import com.example.Dosify.repository.DoctorRepository;
import com.example.Dosify.service.VaccinationCenterService;
import com.example.Dosify.transformer.DoctorTransformer;
import com.example.Dosify.transformer.VaccinationCenterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@Service
public class CenterServiceImpl implements VaccinationCenterService {

    @Autowired
    CenterRepository centerRepository;
 @Autowired
 DoctorRepository doctorRepository;
    @Override
    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto) {
        // dto -> entity
        VaccinationCenter vaccinationCenter = VaccinationCenterTransformer.CenterRequestDtoToCenter(centerRequestDto);
        // save to your db
        VaccinationCenter savedCenter = centerRepository.save(vaccinationCenter);
        // entity -> response dto
        return VaccinationCenterTransformer.CenterToCenterResponseDto(savedCenter);
    }

    @Override
    public List<DoctorResponseDto> getDoctorsFromCenterId(int id) {
        List<Doctor> doctorList= doctorRepository.findByCenterId(id);
        List<DoctorResponseDto> doctorResponseDtoList= new ArrayList<>();
        for (Doctor doctor:doctorList){
            doctorResponseDtoList.add(DoctorTransformer.DoctorToDoctorResponseDto(doctor));
        }
        return doctorResponseDtoList;
    }

    @Override
    public List<CenterResponseDto> getCentersByCenterType(CenterType centerType) {
        List<VaccinationCenter> centerList=centerRepository.findByCenterType(centerType);
        List<CenterResponseDto> centerResponseDtoList= new ArrayList<>();
        for (VaccinationCenter center:centerList){
           centerResponseDtoList.add(VaccinationCenterTransformer.CenterToCenterResponseDto(center));
             }
        return centerResponseDtoList;
    }

    @Override
    public List<DoctorResponseDto> getAllDoctorsbygenderandAgeCenter(String gender, int age, int id) {
        List<Doctor> doctorList=doctorRepository.findByGenderAndAgeCenter(gender,age,id);
        List<DoctorResponseDto> filteredList=new ArrayList<>();
        for(Doctor doctor:doctorList){
            filteredList.add(DoctorTransformer.DoctorToDoctorResponseDto(doctor));
        }
        return filteredList;
    }
}
