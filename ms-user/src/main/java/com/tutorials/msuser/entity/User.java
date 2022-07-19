package com.tutorials.msuser.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "app_user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "user_id")
    UUID userId;

    @Column(name = "email")
    String email;

    @ToString.Exclude
    @Column(name = "password")
    String password;

    @Column(name = "full_name")
    String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    UserStatus status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    List<Role> roles;

    @PrePersist
    private void setPreValues() {
        if (status == null)
            status = UserStatus.ACTIVE;
    }
}
