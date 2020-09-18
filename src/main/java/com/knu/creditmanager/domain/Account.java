package com.knu.creditmanager.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String studentId;

    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id", nullable = false)
    private Department major;       // 학과

    // 수업
    //    @Transient
    //    @JsonInclude(JsonInclude.Include.NON_NULL)
    //    private List<Course> courseList;

    private String grade;       // 학점

    private LocalDate admissionDate;

    @Setter
    @ColumnDefault("0")
    private boolean isDeleted;

    @ColumnDefault("0")
    private boolean isMulti;    // 복전

    @ColumnDefault("0")
    private boolean istransfer;    // 편입
}
