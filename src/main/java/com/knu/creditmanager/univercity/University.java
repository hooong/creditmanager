package com.knu.creditmanager.univercity;

import com.knu.creditmanager.department.Department;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class University{

    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="university_id")
    private List<Department> department;
}
