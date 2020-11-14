package com.knu.creditmanager.major;

import com.knu.creditmanager.department.Department;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Major {

    @Id @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    private String link;

    @Column(name = "department_id")
    private Long departmentId;


}
