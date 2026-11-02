package com.vc.showservice.entity;

import com.vc.common.dto.ShowStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "shows")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movie_name",nullable = false)
    private String movieName;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private ShowStatus status;

    @Column(name = "show_time",nullable = false)
    private LocalDateTime showTime;

    @Column(name = "total_seats",nullable = false)
    private Integer totalSeats;

    @Column(name = "available_seats",nullable = false)
    private Integer availableSeats;
}
