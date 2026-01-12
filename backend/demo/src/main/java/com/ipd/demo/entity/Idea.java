package com.ipd.demo.entity;

import com.ipd.demo.enums.IdeaStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ideas")
public class Idea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idea_title", nullable = false)
    private String title;

    @Column(name = "idea_description", length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IdeaStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users createdBy;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}