package com.example.Dosify.transformer;

import com.example.Dosify.dto.RequestDTO.UserRequestDto;
import com.example.Dosify.dto.ResponseDTO.UserResponseDto;
import com.example.Dosify.model.User;
import lombok.experimental.UtilityClass;

public class UserTransformer {

    public static User UserRequestDtoToUser(UserRequestDto userRequestDto){

        return User.builder()
                .name(userRequestDto.getName())
                .age(userRequestDto.getAge())
                .emailId(userRequestDto.getEmailId())
                .gender(userRequestDto.getGender())
                .mobNo(userRequestDto.getMobNo())
                .build();
    }

    public static UserResponseDto userToResponseDto(User savedUser, int i) {
        if(i==1) {
            return UserResponseDto.builder()
                    .name(savedUser.getName())
                    .message("You registered for Dosify!")
                    .build();
        }
        return UserResponseDto.builder()
                .name(savedUser.getName())
                .gender(savedUser.getGender().toString())
                .age(savedUser.getAge())
                .build();
    }

}
