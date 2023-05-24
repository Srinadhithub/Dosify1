package com.example.Dosify.controller;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.dto.RequestDTO.DoctorRequestDto;
import com.example.Dosify.dto.ResponseDTO.DoctorResponseDto;
import com.example.Dosify.exception.CenterNotPresentException;
import com.example.Dosify.model.Doctor;
import com.example.Dosify.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity addDoctor(@RequestBody DoctorRequestDto doctorRequestDto){

        try {
            DoctorResponseDto doctorResponseDto = doctorService.addDoctor(doctorRequestDto);
            return new ResponseEntity(doctorResponseDto,HttpStatus.CREATED);
        } catch (CenterNotPresentException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getalldoctorsatleastappointments/{count}")
    public ResponseEntity getAllDoctorsAtleastAppointments(@PathVariable int count){
        List<DoctorResponseDto> doctorList=doctorService.getAllDoctorsAtleastAppointments(count);
        return new ResponseEntity(doctorList,HttpStatus.FOUND);
    }


//     get all the male doctors whose age is above 40
    @GetMapping("/getalldoctorsbygiven_gender_and_age")
    public ResponseEntity getAllDoctorsbygenderandAge(@RequestParam Gender gender, @RequestParam int age){
        List<DoctorResponseDto> doctorList=doctorService.getAllDoctorsbygenderandAge(gender,age);
        return new ResponseEntity(doctorList,HttpStatus.FOUND);
    }
    // get the ratio of male to female doctors
  @GetMapping("/getratioofmaletofemale")
    public ResponseEntity getRatioOfMaletoFemale(){
        Double ratio=doctorService.getRatioOfMaletoFemale();
        return new ResponseEntity(ratio,HttpStatus.FOUND);
  }

    //update the details based on email id of the doctor
    @PutMapping("/updatename")
    public ResponseEntity upodateName(@RequestParam String name,@RequestParam String emailId){
        DoctorResponseDto doctorResponseDto= doctorService.updateName(name,emailId);
        return new ResponseEntity(doctorResponseDto,HttpStatus.OK);
    }
}
