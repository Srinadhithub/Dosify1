package com.example.Dosify.dto.ResponseDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CertificateResponseDto {
 String id;
 UserResponseDto userResponseDto;
 Dose1ResponseDto dose1ResponseDto;
 Dose2ResponseDto dose2ResponseDto;
}
