package com.tutorials.msuser.entity;

import java.util.List;
import java.util.UUID;
import javax.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "role")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    UUID roleId;
    String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    List<User> users;
}
