package com.brainswords.clubin.auth.service;

import com.brainswords.clubin.auth.dto.SignupRequest;
import com.brainswords.clubin.auth.dto.SignupResponse;
import com.brainswords.clubin.member.domain.Member;
import com.brainswords.clubin.member.domain.Role;
import com.brainswords.clubin.member.exception.DuplicateLoginIdException;
import com.brainswords.clubin.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public SignupResponse signup(SignupRequest request) {
        if (memberRepository.existsByLoginId(request.getLoginId())) {
            throw new DuplicateLoginIdException();
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Member member = new Member(request.getLoginId(), encodedPassword, Role.MEMBER);
        Member savedMember = memberRepository.save(member);

        return new SignupResponse(savedMember.getId(), savedMember.getLoginId());
    }
}
