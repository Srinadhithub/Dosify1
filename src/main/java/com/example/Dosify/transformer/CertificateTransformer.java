package com.example.Dosify.transformer;

import com.example.Dosify.dto.ResponseDTO.*;
import com.example.Dosify.model.User;
import com.example.Dosify.repository.UserRepository;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;


public class CertificateTransformer {
    @Autowired
    UserRepository userRepository;
    public static CertificateResponseDto getCertificate(User user){


        Dose1ResponseDto dose1ResponseDto=null;
        Dose2ResponseDto dose2ResponseDto=null;
        if(user.isDose1Taken()==true) {
            AppointmentResponseDto appointmentResponseDto= AppointmentTransformer.appointmentToResponseDto(user,user.getAppointments().get(0),user.getAppointments().get(0).getDoctor(),user.getDose1().getVaccineType());
            dose1ResponseDto = Dose1ResponseDto.builder()
                    .doseId(user.getDose1().getDoseId())
                    .appointmentResponseDto(appointmentResponseDto)
                    .build();
        }
        if(user.isDose2Taken()==true) {
            AppointmentResponseDto appointmentResponseDto= AppointmentTransformer.appointmentToResponseDto(user,user.getAppointments().get(1),user.getAppointments().get(1).getDoctor(),user.getDose2().getVaccineType());
            dose2ResponseDto = Dose2ResponseDto.builder()
                    .doseId(user.getDose2().getDoseId())
                    .appointmentResponseDto(appointmentResponseDto)
                    .build();
        }

        UserResponseDto userResponseDto= UserTransformer.userToResponseDto(user,2);
        return CertificateResponseDto.builder()
                .id(String.valueOf((long) Math.floor(Math.random() * 9_000_000_00L) + 1_000_000_00L))
                .dose1ResponseDto(dose1ResponseDto)
                .dose2ResponseDto(dose2ResponseDto)
                .userResponseDto(userResponseDto)
                .build();
    }

}
