package com.vc.userservice.entity;

import com.vc.common.dto.UserStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name ="mobile", length = 10)
    private String mobile;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;
}
