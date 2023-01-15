package com.example.querydsl.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@ToString
@Entity
@Table(name = "todo")
public class Todo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id", nullable = false)
  private Long id;

  @Column(name="title")
  private String title;

  @Column(name="description")
  private String description;

  @Column(name="completed")
  private Boolean completed;
}