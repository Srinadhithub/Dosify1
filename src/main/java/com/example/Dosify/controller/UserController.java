package com.example.Dosify.controller;


import com.example.Dosify.dto.RequestDTO.UserRequestDto;
import com.example.Dosify.dto.ResponseDTO.CertificateResponseDto;
import com.example.Dosify.dto.ResponseDTO.UserResponseDto;
import com.example.Dosify.model.Certificate;
import com.example.Dosify.model.User;
import com.example.Dosify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody UserRequestDto userRequestDto){

        UserResponseDto userResponseDto = userService.addUser(userRequestDto);
        return new ResponseEntity(userResponseDto, HttpStatus.CREATED);
    }
    @GetMapping("/get/{emailId}")
    public ResponseEntity getUserByEmailId(@PathVariable String emailId){
        UserResponseDto userResponseDto=userService.getUserByEmailId(emailId);
        return new ResponseEntity(userResponseDto,HttpStatus.FOUND);
    }
    // update the name of the user mobno
    @PutMapping("/updatename")
    public ResponseEntity updateName(@RequestParam String name,@RequestParam String mobNo){
        UserResponseDto userResponseDto= userService.updateName(name,mobNo);
        return new ResponseEntity(userResponseDto,HttpStatus.ACCEPTED);
    }

    // all the users who have not taken even a single dose
    @GetMapping("/getnotevensingledose")
    public ResponseEntity getNotEvenSingleDose(){
        List<UserResponseDto> userList=userService.getNotEvenSingleDose();
        return new ResponseEntity(userList,HttpStatus.FOUND);
    }


    // all users who have taken does1 but not dose2
    @GetMapping("/getsingledose")
    public ResponseEntity getSingleDose(){
        List<UserResponseDto> userList=userService.getSingleDose();
        return new ResponseEntity(userList,HttpStatus.FOUND);
    }

    // all users who are fully vaccinated.
    @GetMapping("/gettwodoses")
    public ResponseEntity getTwoDosesVaccinated(){
        List<UserResponseDto> userList=userService.getTwoDoseVaccinated();
        return new ResponseEntity(userList,HttpStatus.FOUND);
    }
    // all male users who have not taken even a single vaccine
    @GetMapping("/getnotevensingledosebygender")
    public ResponseEntity getNotEvenSingleDoseByGender(@RequestParam String gender){
        List<UserResponseDto> userList=userService.getNotEvenSingleDoseByGender(gender);
        return new ResponseEntity(userList,HttpStatus.FOUND);
    }
    //single dose by gender
    @GetMapping("/getsingledosebygender")
    public ResponseEntity getSingleDoseByGender(@RequestParam String gender){
        List<UserResponseDto> userList=userService.getSingleDoseByGender(gender);
        return new ResponseEntity(userList,HttpStatus.FOUND);
    }
    // all female users who are fully vaccinated
    @GetMapping("/gettwodosesbygender")
    public ResponseEntity getTwoDosesVaccinatedByGender(@RequestParam String gender){
        List<UserResponseDto> userList=userService.getTwoDoseVaccinatedByGender(gender);
        return new ResponseEntity(userList,HttpStatus.FOUND);
    }
    @GetMapping("/getcertificate")
    public CertificateResponseDto getCertificate(@RequestParam int userId){
        CertificateResponseDto certificateResponseDto= userService.getCertificate(userId);
        return certificateResponseDto;
    }
}
