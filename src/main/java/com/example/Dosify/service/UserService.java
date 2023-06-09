package com.example.Dosify.service;

import com.example.Dosify.dto.RequestDTO.UserRequestDto;
import com.example.Dosify.dto.ResponseDTO.CertificateResponseDto;
import com.example.Dosify.dto.ResponseDTO.UserResponseDto;

import java.util.List;

public interface UserService {

    public UserResponseDto addUser(UserRequestDto userRequestDto);

    public UserResponseDto getUserByEmailId(String emailId);

    UserResponseDto updateName(String name,String mobNo);
    List<UserResponseDto> getNotEvenSingleDose();

    List<UserResponseDto> getSingleDose();

    List<UserResponseDto> getTwoDoseVaccinated();

    List<UserResponseDto> getNotEvenSingleDoseByGender(String gender);

    List<UserResponseDto> getTwoDoseVaccinatedByGender(String gender);

    List<UserResponseDto> getSingleDoseByGender(String gender);

    CertificateResponseDto getCertificate(int userId);
}
