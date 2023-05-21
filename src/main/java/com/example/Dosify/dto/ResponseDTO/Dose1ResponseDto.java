package com.example.Dosify.dto.ResponseDTO;

import com.example.Dosify.Enum.VaccineType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@Data
@Builder
public class Dose1ResponseDto {
    String doseId;

    AppointmentResponseDto appointmentResponseDto;
}
