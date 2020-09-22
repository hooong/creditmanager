package com.knu.creditmanager.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    @Column(unique = true)
    private String studentId;

    @NonNull
    private String password;

    @NonNull
    private String semester;    // TODO: 학기 및 학년을 어떻게 저장할지?

    @NonNull
    @OneToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department major;       // 학과

    // 수업
    //    @Transient
    //    @JsonInclude(JsonInclude.Include.NON_NULL)
    //    private List<Course> courseList;

    @ColumnDefault("0")
    private Double grade;       // 학점

    private String admissionYear;

    @Setter
    @ColumnDefault("0")
    private boolean isDeleted;

    @ColumnDefault("0")
    private boolean isMulti;    // 복전

    @ColumnDefault("0")
    private boolean istransfer;    // 편입

    public void setAdmissionYear() {
        admissionYear = semester.substring(0,3);
    }
}
