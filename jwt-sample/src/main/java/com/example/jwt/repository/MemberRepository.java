package com.example.jwt.repository;

import com.example.jwt.domain.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

  @EntityGraph(attributePaths = "roles")
  Optional<Member> findByName(String Name);
}
