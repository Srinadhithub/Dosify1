package com.example.Dosify.model;

import com.example.Dosify.dto.ResponseDTO.AppointmentResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Builder;

import java.util.List;
@Builder
@Entity
public class Certificate {
    @Id
  String id;
    @OneToOne
    User user;
}
