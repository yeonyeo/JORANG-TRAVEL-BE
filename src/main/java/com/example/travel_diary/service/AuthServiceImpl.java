package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.FindLoginIdEmailSender;
import com.example.travel_diary.global.domain.entity.FindPasswordEmailSender;
import com.example.travel_diary.global.domain.entity.PasswordGenerator;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.repository.UserRepository;
import com.example.travel_diary.global.request.FindLoginIdRequestDto;
import com.example.travel_diary.global.request.FindPasswordRequestDto;
import com.example.travel_diary.global.request.SignInRequestDto;
import com.example.travel_diary.global.request.SignUpRequestDto;
import com.example.travel_diary.global.response.GetUserByIdResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements com.example.travel_diary.service.AuthService {
    private final UserRepository userRepository;
    private final FindPasswordEmailSender findPasswordEmailSender;
    private final FindLoginIdEmailSender findLoginIdEmailSender;

    @Override
    @Transactional
    public UUID signUp(SignUpRequestDto signUpRequestDto) throws Exception {
        Optional<User> byEmail = userRepository.findByEmail(signUpRequestDto.email());
        if (byEmail.isPresent()) throw new Exception("이메일 있음");
        User entity = signUpRequestDto.toEntity();
        userRepository.save(entity);
        return entity.getId();
    }

    @Override
    @Transactional
    public void signIn(SignInRequestDto signInRequestDto) throws Exception {
        Optional<User> byLoginId = userRepository.findByLoginId(signInRequestDto.loginId());
        if (!byLoginId.get().getPassword().equals(signInRequestDto.password()))
            throw new Exception("로그인 실패");
    }

    @Override
    @Transactional
    public GetUserByIdResponseDto getUserById(UUID id) throws Exception {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) throw new Exception("uuid 없음");
        User user = byId.get();
        return new GetUserByIdResponseDto(user.getNickname(), user.getPassword(), user.getEmail());
    }

    @Override
    @Transactional
    public void updateUserNickname(UUID id, String nickname) throws Exception {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) throw new Exception("uuid 없음");
        User user = byId.get();
        user.setNickname(nickname);
    }

    @Override
    @Transactional
    public void updateUserPassword(UUID id, String password) throws Exception {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) throw new Exception("uuid 없음");
        User user = byId.get();
        user.setPassword(password);
    }

    @Override
    @Transactional
    public void deleteUserById(UUID id) throws Exception {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) throw new Exception("uuid 없음");
        User user = byId.get();
        userRepository.deleteById(user.getId());
    }

    @Override
    @Transactional
    public void findLoginId(FindLoginIdRequestDto req) throws Exception {
        User user = userRepository.findByEmail(req.email()).orElseThrow(Exception::new);
        if(!user.getName().equals(req.name())) throw new Exception("로그인되어있지 않음");
        try {
            findLoginIdEmailSender.emailSender(user.getNickname(), user.getEmail(), user.getLoginId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void findPassword(FindPasswordRequestDto req) throws Exception {
        Optional<User> byId = userRepository.findByLoginId(req.loginId());
        if (byId.isEmpty()) throw new Exception("loginId 없음");
        User user = byId.get();
        String passwordGenerator = PasswordGenerator.generateRandomPassword(13);

        user.setPassword(passwordGenerator);
        try {
            findPasswordEmailSender.emailSender(user.getNickname(), user.getEmail(), passwordGenerator);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
