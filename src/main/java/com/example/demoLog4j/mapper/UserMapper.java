package com.example.demoLog4j.mapper;

import com.example.demoLog4j.dto.UserDto;
import com.example.demoLog4j.dto.response.UserResponse;
import com.example.demoLog4j.entity.RoleEntity;
import com.example.demoLog4j.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    private ModelMapper modelMapper;
    public UserEntity toUserEntity(UserDto userDto){
        if(userDto != null){
            UserEntity user = modelMapper.map(userDto, UserEntity.class);
            return user;
        }
        return null;
    }
    public UserDto toUserDto(UserEntity userEntity){
        if(userEntity != null){
            UserDto user = modelMapper.map(userEntity, UserDto.class);
            return user;
        }
        return null;
    }

    public UserResponse toUserResponse(UserEntity userEntity){
        if(userEntity != null){
            UserResponse user = modelMapper.map(userEntity, UserResponse.class);
            return user;
        }
        return null;
    }

    public UserResponse toUserReponse(UserDto userDto){
        if(userDto != null){
            UserResponse user = modelMapper.map(userDto, UserResponse.class);
            return user;
        }
        return null;
    }
}
