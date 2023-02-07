package com.example.jwt.repository;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.jwt.domain.entity.Member;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

@Slf4j
@SpringBootTest
class MemberRepositoryTest {

  @Autowired
  MemberRepository memberRepository;

  @DisplayName("name 컬럼을 조건으로 member, role 테이블 조회")
  @Test
  void testFindByName() {

    // Given
    String name = "admin";

    // When
    Member member = memberRepository.findByName(name).orElseGet(Member::new);

    // Then
    log.debug("member:[{}]", member);
    assertAll(
        () -> assertNotNull(member.getId()),
        () -> assertNotNull(member.getRoles()),
        () -> assertEquals(name, member.getName())
    );
  }
}