package org.codenova.groupware.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
// @Table(name="serial")        // 엔티티명과 테이블명 일치하면 생략가능
public class Serial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")      // 컬럼명과 필드명 일치하면 생략가능.
    private Integer id;

    private String ref;

    // 생략시엔 카멜 - 언더스코어 변환되어 연결
    private Long lastNumber;
}
