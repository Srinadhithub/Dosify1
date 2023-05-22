package com.example.Dosify.service.impl;

import com.example.Dosify.dto.RequestDTO.UserRequestDto;
import com.example.Dosify.dto.ResponseDTO.*;
import com.example.Dosify.model.User;
import com.example.Dosify.repository.AppointmentRepository;
import com.example.Dosify.repository.UserRepository;
import com.example.Dosify.service.UserService;
import com.example.Dosify.transformer.AppointmentTransformer;
import com.example.Dosify.transformer.CertificateTransformer;
import com.example.Dosify.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
@Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    private JavaMailSender emailSender;
    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {

        // User object using builder
        User user = UserTransformer.UserRequestDtoToUser(userRequestDto);

        // save the object in db
        User savedUser = userRepository.save(user);

        UserResponseDto userResponseDto = UserTransformer.userToResponseDto(savedUser,1);

        return userResponseDto;

    }
    @Override
    public UserResponseDto getUserByEmailId(String emailId) {

        User user=userRepository.findByEmailId(emailId).get();
       return UserTransformer.userToResponseDto(user,2);
    }

    @Override
    public UserResponseDto updateName(String name, String mobNo) {

        User user = userRepository.findByMobNo(mobNo).get();

        user.setName(name);
        userRepository.save(user);

        UserResponseDto userResponseDto = UserTransformer.userToResponseDto(user, 2);
        userResponseDto.setMessage("updated");
        return userResponseDto;
    }

    @Override
    public List<UserResponseDto> getNotEvenSingleDose() {
        List<User> userList=userRepository.NotEvenSingleDose();
        List<UserResponseDto> users=new ArrayList<>();
        for (User user:userList) users.add(UserTransformer.userToResponseDto(user,2));
        return users;
    }

    @Override
    public  List<UserResponseDto> getSingleDose() {
        List<User> userList=userRepository.getSingleDose();
        List<UserResponseDto> users=new ArrayList<>();
        for (User user:userList) users.add(UserTransformer.userToResponseDto(user,2));
        return users;
    }

    @Override
    public  List<UserResponseDto> getTwoDoseVaccinated() {
        List<User> userList=userRepository.getTwoDoseVaccinated();
        List<UserResponseDto> users=new ArrayList<>();
        for (User user:userList) users.add(UserTransformer.userToResponseDto(user,2));
        return users;
    }

    @Override
    public  List<UserResponseDto> getNotEvenSingleDoseByGender(String gender) {
        List<User> userList=userRepository.getNotEvenSingleDoseByGender(gender);
        List<UserResponseDto> users=new ArrayList<>();
        for (User user:userList) users.add(UserTransformer.userToResponseDto(user,2));
        return users;
    }

    @Override
    public  List<UserResponseDto> getTwoDoseVaccinatedByGender(String gender) {
        List<User> userList=userRepository.getTwoDoseVaccinatedByGender(gender);
        List<UserResponseDto> users=new ArrayList<>();
        for (User user:userList) users.add(UserTransformer.userToResponseDto(user,2));
        return users;
    }

    @Override
    public  List<UserResponseDto> getSingleDoseByGender(String gender) {
        List<User> userList=userRepository.getSingleDoseByGender(gender);
        List<UserResponseDto> users=new ArrayList<>();
        for (User user:userList) users.add(UserTransformer.userToResponseDto(user,2));
        return users;
    }

    @Override
    public CertificateResponseDto getCertificate(int userId) {
        User user=userRepository.findById(userId).get();
        CertificateResponseDto certificateResponseDto=CertificateTransformer.getCertificate(user);
        String textUser = certificateResponseDto.toString();
        SimpleMailMessage messageUser = new SimpleMailMessage();
        messageUser.setFrom("backendmaydosify@gmail.com");
        messageUser.setTo(user.getEmailId());
        messageUser.setSubject("Certificate from DOSIFY !!!");
        messageUser.setText(textUser);
        emailSender.send(messageUser);
        return certificateResponseDto;
    }
}
