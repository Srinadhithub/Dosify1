package com.example.Dosify.service.impl;

import com.example.Dosify.dto.RequestDTO.UserRequestDto;
import com.example.Dosify.dto.ResponseDTO.AppointmentResponseDto;
import com.example.Dosify.dto.ResponseDTO.UserResponseDto;
import com.example.Dosify.model.Appointment;
import com.example.Dosify.model.Certificate;
import com.example.Dosify.model.User;
import com.example.Dosify.repository.AppointmentRepository;
import com.example.Dosify.repository.UserRepository;
import com.example.Dosify.service.UserService;
import com.example.Dosify.transformer.AppointmentTransformer;
import com.example.Dosify.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
@Autowired
    AppointmentRepository appointmentRepository;
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
        User user= userRepository.findByMobNo(mobNo).get();
        user.setName(name);
        userRepository.save(user);

        return UserResponseDto.builder().message("updated !!").build();
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
    public Certificate getCertificate(int userId) {
        User user=userRepository.findById(userId).get();
        List<Appointment> appointments=appointmentRepository.getByuserId(userId);
        List<AppointmentResponseDto> appointmentResponseDtos=new ArrayList<>();
        for(Appointment appointment:appointments) appointmentResponseDtos.add(AppointmentTransformer.appointmentToResponseDto(appointment));
        return Certificate.builder()
                .name(user.getName())
                .age(user.getAge())
                .emailId(user.getEmailId())
                .is_dose1_taken(appointments.size()>0)
                .isIs_dose2_taken(appointments.size()>1)
                .AppointemtList()
                .build();
        appointments.get(0).getDoctor().
    }
}
