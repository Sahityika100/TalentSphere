package com.tka.talentsphere.api.service;

import com.tka.talentsphere.api.entity.HrUser;
import com.tka.talentsphere.api.model.AuthDTO;
import com.tka.talentsphere.api.repository.HrUserRepository;
import com.tka.talentsphere.api.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private HrUserRepository hrUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthDTO.AuthResponse signup(AuthDTO.SignupRequest req) {
        if (hrUserRepository.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        HrUser user = new HrUser();
        user.setEmail(req.getEmail());
        user.setFullName(req.getFullName());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        HrUser saved = hrUserRepository.save(user);

        String token = jwtUtil.generateToken(saved.getEmail(), saved.getId());
        return new AuthDTO.AuthResponse(token, saved.getId(), saved.getFullName(), saved.getEmail(), saved.getRole());
    }

    public AuthDTO.AuthResponse login(AuthDTO.LoginRequest req) {
        HrUser user = hrUserRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getId());
        return new AuthDTO.AuthResponse(token, user.getId(), user.getFullName(), user.getEmail(), user.getRole());
    }
}
