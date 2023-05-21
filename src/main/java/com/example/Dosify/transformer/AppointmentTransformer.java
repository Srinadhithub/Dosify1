package com.example.Dosify.transformer;

import com.example.Dosify.Enum.VaccineType;
import com.example.Dosify.dto.RequestDTO.AppointmentRequestDto;
import com.example.Dosify.dto.ResponseDTO.AppointmentResponseDto;
import com.example.Dosify.dto.ResponseDTO.CenterResponseDto;
import com.example.Dosify.dto.ResponseDTO.DoctorResponseDto;
import com.example.Dosify.model.Appointment;
import com.example.Dosify.model.Doctor;
import com.example.Dosify.model.User;

import java.util.UUID;

public class AppointmentTransformer {
    public static Appointment AppointmentRquestDtoToAppointment(AppointmentRequestDto appointmentRequestDto, Doctor doctor, User user) {
        return Appointment.builder()
                .appointmentNo(String.valueOf(UUID.randomUUID()))
                .doseNo(appointmentRequestDto.getDoseNo())
                .user(user)
                .doctor(doctor)
                .build();
    }

    public static AppointmentResponseDto appointmentToResponseDto(User user, Appointment savedAppointment, Doctor doctor, VaccineType vaccineType) {
       DoctorResponseDto doctorResponseDto= DoctorTransformer.DoctorToDoctorResponseDto(doctor);
        return AppointmentResponseDto.builder()
                .userName(user.getName())
                .appointmentNo(savedAppointment.getAppointmentNo())
                .dateOfAppointment(savedAppointment.getDateOfAppointment())
                .doseNo(savedAppointment.getDoseNo())
                .doctorResponseDto(doctorResponseDto)
                .vaccineType(vaccineType)
                .build();
    }
}
