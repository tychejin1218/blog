package com.example.jwt.service;

import com.example.jwt.domain.entity.Member;
import com.example.jwt.repository.MemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Component("memberDetailService")
public class CustomMemberDetailService implements UserDetailsService {

  private final MemberRepository memberRepository;

  @Transactional
  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    return memberRepository.findByName(name)
        .map(member -> createMember(name, member))
        .orElseThrow(() -> new UsernameNotFoundException(name + " -> 데이터베이스에서 찾을 수 없습니다."));
  }

  private User createMember(String name, Member member) {

    if (!member.isEnabled()) {
      throw new RuntimeException(name + " -> 활성화되어 있지 않습니다.");
    }

    List<GrantedAuthority> grantedAuthorities = member.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList());

    return new User(member.getName(), member.getPassword(), grantedAuthorities);
  }
}