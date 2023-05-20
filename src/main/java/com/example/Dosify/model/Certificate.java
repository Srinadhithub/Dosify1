package com.example.Dosify.model;

import com.example.Dosify.dto.ResponseDTO.AppointmentResponseDto;
import lombok.Builder;

import java.util.List;
@Builder
public class Certificate {
    String name;
    int age;
    String mobNo;
    String emailId;

    boolean is_dose1_taken;
    boolean isIs_dose2_taken;
    List<AppointmentResponseDto> AppointemtList;
}
