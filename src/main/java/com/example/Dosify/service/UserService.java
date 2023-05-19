package com.example.Dosify.service;

import com.example.Dosify.dto.RequestDTO.UserRequestDto;
import com.example.Dosify.dto.ResponseDTO.UserResponseDto;
import com.example.Dosify.model.User;

public interface UserService {

    public UserResponseDto addUser(UserRequestDto userRequestDto);
}
