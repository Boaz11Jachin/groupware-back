package org.codenova.groupware.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="department")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    Integer id;

    private String name;
}
