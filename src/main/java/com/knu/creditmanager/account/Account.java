package com.knu.creditmanager.account;

import com.knu.creditmanager.department.Department;
import com.knu.creditmanager.grade.GradeBySemester;
import com.knu.creditmanager.grade.Semester;
import com.knu.creditmanager.major.Major;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

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
    private String studentId;

    @NonNull
    private String password;

    @NonNull
    private Integer uniYear;

    @NonNull
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major major;

    // 수업
    //    @Transient
    //    @JsonInclude(JsonInclude.Include.NON_NULL)
    //    private List<Course> courseList;

//    private List<GradeBySemester> gradeBySemesterList;

    private Double avgGrade;       // 학점

    private String admissionYear;

    @Setter
    @ColumnDefault("0")
    private boolean isDeleted;

    @ColumnDefault("0")
    private boolean isMulti;    // 복전

    @ColumnDefault("0")
    private boolean istransfer;    // 편입

    public void setAdmissionYear() {
        admissionYear = studentId.substring(0,4);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studentId='" + studentId + '\'' +
                ", password='" + password + '\'' +
                ", uniYear=" + uniYear +
                ", semester=" + semester +
                ", major=" + major +
                ", avgGrade=" + avgGrade +
                ", admissionYear='" + admissionYear + '\'' +
                ", isDeleted=" + isDeleted +
                ", isMulti=" + isMulti +
                ", istransfer=" + istransfer +
                '}';
    }
}
