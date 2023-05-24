package com.example.Dosify.controller;

import com.example.Dosify.Enum.CenterType;
import com.example.Dosify.dto.RequestDTO.CenterRequestDto;
import com.example.Dosify.dto.ResponseDTO.CenterResponseDto;
import com.example.Dosify.dto.ResponseDTO.DoctorResponseDto;
import com.example.Dosify.model.VaccinationCenter;
import com.example.Dosify.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/center")
public class VaccinationCenterController {

    @Autowired
    VaccinationCenterService vaccinationCenterService;

    @PostMapping("/add")
    public ResponseEntity addVaccinationCenter(@RequestBody CenterRequestDto centerRequestDto){

        CenterResponseDto centerResponseDto = vaccinationCenterService.addCenter(centerRequestDto);
        return new ResponseEntity(centerResponseDto, HttpStatus.CREATED);
    }

    // give the list of all doctors at a particular center(centerId)

    @GetMapping("/getdoctorsfromcenterid/{id}")
    public ResponseEntity getDoctorsFromCenterId(@PathVariable int id){
        List<DoctorResponseDto> doctorResponseDtoList=vaccinationCenterService.getDoctorsFromCenterId(id);
        return new ResponseEntity(doctorResponseDtoList,HttpStatus.FOUND);
    }
    // give the list of all male doctors at a particular center(centerId)

    // give the list of all females doctors at a particular center(centerId)

    // give the list of all male doctors above age 40 at a particular center(centerId)
    @GetMapping("/getalldoctorsbygiven_gender_and_age/center/{id}")
    public ResponseEntity getAllDoctorsbygenderandAgeCenter(@RequestParam String gender,@RequestParam int age,@PathVariable int id ){
        List<DoctorResponseDto> doctorList=vaccinationCenterService.getAllDoctorsbygenderandAgeCenter(gender,age,id);
        return new ResponseEntity(doctorList,HttpStatus.FOUND);
    }
    // give all centers of a particular centerType
      @GetMapping("/getcentersbycentertype/{centerType}")
    public ResponseEntity getCentersByCenterType(@PathVariable CenterType centerType){
        return new ResponseEntity(vaccinationCenterService.getCentersByCenterType(centerType),HttpStatus.FOUND);
      }

}
