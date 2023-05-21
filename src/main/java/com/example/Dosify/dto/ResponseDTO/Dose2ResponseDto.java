package com.example.Dosify.dto.ResponseDTO;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@Data
@Builder
public class Dose2ResponseDto {
    String doseId;
    AppointmentResponseDto appointmentResponseDto;
}
