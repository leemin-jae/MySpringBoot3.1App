package com.ktds.myspringboot.service;

import com.ktds.myspringboot.dto.UserReqDto;
import com.ktds.myspringboot.dto.UserResDto;
import com.ktds.myspringboot.entity.User;
import com.ktds.myspringboot.exception.BusinessException;
import com.ktds.myspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserResDto saveUser(UserReqDto userReqDto){
        User user = modelMapper.map(userReqDto, User.class);
        User savedUser = userRepository.save(user);
        // entity ==> resDto

        return modelMapper.map(savedUser, UserResDto.class);

    }

    @Transactional(readOnly = true)
    public UserResDto getUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(id + " User Not Found", HttpStatus.NOT_FOUND));
        return modelMapper.map(user, UserResDto.class);
    }

    @Transactional(readOnly = true)
    public List<UserResDto> getUsers(){
        List<User> userList = userRepository.findAll();
        List<UserResDto> userResDtoList = userList.stream() // stream<User>
                .map(user -> modelMapper.map(user, UserResDto.class))  // Stream<UserResDto
                .collect(toList());

        return userResDtoList;
    }

    public UserResDto updateUser(String email, UserReqDto userReqDto) {
        User existUser = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new BusinessException(email + " User Not Found", HttpStatus.NOT_FOUND));
        //setter method 호출
        existUser.setName(userReqDto.getName());
        return modelMapper.map(existUser, UserResDto.class);
    }

}
