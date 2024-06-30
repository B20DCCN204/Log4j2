package com.example.demoLog4j.service.impl;

import com.example.demoLog4j.constants.ConstantSystem;
import com.example.demoLog4j.dto.UserDto;
import com.example.demoLog4j.dto.response.UserResponse;
import com.example.demoLog4j.entity.RoleEntity;
import com.example.demoLog4j.entity.UserEntity;
import com.example.demoLog4j.mapper.UserMapper;
import com.example.demoLog4j.repository.RoleRepository;
import com.example.demoLog4j.repository.UserRepository;
import com.example.demoLog4j.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserMapper userMapper;

    private Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Override
    @Transactional
    public UserResponse saveUser(UserDto userDto) {
        String username = userDto.getUsername();
        if(userRepository.existsByUsername(username)){
            logger.warn("Username " + username + " already exist");
            throw new DataIntegrityViolationException("Username already exist");
        }
        RoleEntity role = roleRepository.findRoleEntityByCode(ConstantSystem.USER);
        UserEntity user = userMapper.toUserEntity(userDto);
        user.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
        user.setRole(role);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserDto findByUsername(String Username) {
        UserEntity user = userRepository.findUserEntityByUsernameAndIsActive(Username, ConstantSystem.ACTIVE);
        return userMapper.toUserDto(user);
    }

    @Override
    public Boolean checkPassword(UserDto userDto, String rawPassword) {
        return BCrypt.checkpw(rawPassword, userDto.getPassword());
    }

    @Override
    public UserResponse getUserById(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseGet(
                () -> {
                    logger.error("User not found with id: {}", userId);
                    return null;
                }
        );
        return userMapper.toUserResponse(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        UserEntity user = userRepository.findById(id).get();
        if(user == null){
            logger.error("User not found with id: {}", id);
            throw new RuntimeException("User not found");
        }
        user.setIsActive(ConstantSystem.NOT_ACTIVE);
    }

}
