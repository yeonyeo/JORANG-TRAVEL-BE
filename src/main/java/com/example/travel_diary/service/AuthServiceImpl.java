package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.FindLoginIdEmailSender;
import com.example.travel_diary.global.domain.entity.FindPasswordEmailSender;
import com.example.travel_diary.global.domain.entity.PasswordGenerator;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.repository.UserRepository;
import com.example.travel_diary.global.exception.LoginFailedException;
import com.example.travel_diary.global.exception.UserNotFoundException;
import com.example.travel_diary.global.request.FindLoginIdRequestDto;
import com.example.travel_diary.global.request.FindPasswordRequestDto;
import com.example.travel_diary.global.request.SignInRequestDto;
import com.example.travel_diary.global.request.SignUpRequestDto;
import com.example.travel_diary.global.response.GetUserByIdResponseDto;
import com.example.travel_diary.global.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements com.example.travel_diary.service.AuthService, UserDetailsService {
    private final UserRepository userRepository;
    private final FindPasswordEmailSender findPasswordEmailSender;
    private final FindLoginIdEmailSender findLoginIdEmailSender;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        return userRepository.findByLoginId(loginId).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    @Transactional
    public UUID signUp(SignUpRequestDto signUpRequestDto){
        Optional<User> byLoginId = userRepository.findByLoginId(signUpRequestDto.loginId());
        if (byLoginId.isPresent()) throw new LoginIdAlreadyExistsException();
        Optional<User> byEmail = userRepository.findByEmail(signUpRequestDto.email());
        if (byEmail.isPresent()) throw new EmailAlreadyExistsException();
        String encodedPassword = passwordEncoder.encode(signUpRequestDto.password());
        User entity = signUpRequestDto.toEntity(encodedPassword);
        userRepository.save(entity);
        return entity.getId();
    }

    @Override
    @Transactional
    public String signIn(SignInRequestDto signInRequestDto) throws Exception {
        User user = userRepository.findByLoginId(signInRequestDto.loginId()).orElseThrow(UserNotFoundException::new);
        if(!passwordEncoder.matches(signInRequestDto.password(), user.getPassword())) {
            throw new LoginFailedException();
        }
        return jwtUtil.createToken(signInRequestDto.loginId());
    }

    @Override
    @Transactional
    public GetUserByIdResponseDto getUserById(UUID id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) throw new UserNotFoundException();
        User user = byId.get();
        return new GetUserByIdResponseDto(user.getNickname(), user.getPassword(), user.getEmail());
    }

    @Override
    @Transactional
    public void updateUserNickname(UUID id, String nickname){
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) throw new UserNotFoundException();
        User user = byId.get();
        user.setNickname(nickname);
    }

    @Override
    @Transactional
    public void updateUserPassword(UUID id, String password){
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) throw new UserNotFoundException();
        User user = byId.get();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
    }

    @Override
    @Transactional
    public void deleteUserById(UUID id){
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) throw new UserNotFoundException();
        User user = byId.get();
        userRepository.deleteById(user.getId());
    }

    @Override
    @Transactional
    public void findLoginId(FindLoginIdRequestDto req){
        User user = userRepository.findByEmail(req.email()).orElseThrow(UserNotFoundException::new);
        if(!user.getName().equals(req.name())) throw new UserNotFoundException();
        try {
            findLoginIdEmailSender.emailSender(user.getNickname(), user.getEmail(), user.getLoginId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void findPassword(FindPasswordRequestDto req){
        Optional<User> byId = userRepository.findByLoginId(req.loginId());
        if (byId.isEmpty()) throw new UserNotFoundException();
        User user = byId.get();
        String passwordGenerator = PasswordGenerator.generateRandomPassword(13);
        String encodedPassword = passwordEncoder.encode(passwordGenerator);
        user.setPassword(encodedPassword);
        try {
            findPasswordEmailSender.emailSender(user.getNickname(), user.getEmail(), passwordGenerator);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
