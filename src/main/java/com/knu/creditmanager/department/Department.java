package com.knu.creditmanager.department;

import com.knu.creditmanager.account.Account;
import com.knu.creditmanager.major.Major;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Department {

    @Id @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @Column(name = "university_id")
    private Long universityId;

    @Setter
    @OneToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="department_id")
    private List<Major> major;

}
