package com.example.jwt.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "member")
@Entity
public class Member {

  @JsonIgnore
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonIgnore
  @Column(name = "password", length = 100)
  private String password;

  @Column(name = "name", length = 50)
  private String name;

  @JsonIgnore
  @Column(name = "enabled")
  private boolean enabled;

  @ToString.Exclude
  @ManyToMany
  @JoinTable(
      name = "member_role",
      joinColumns = {
          @JoinColumn(name = "member_id", referencedColumnName = "id")
      },
      inverseJoinColumns = {
          @JoinColumn(name = "role_id", referencedColumnName = "id")
      }
  )
  private List<Role> roles;
}