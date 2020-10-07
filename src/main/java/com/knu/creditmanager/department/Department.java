package com.knu.creditmanager.department;

import com.knu.creditmanager.account.Account;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Department {

    public Department(String name, String link) {
        this.name = name;
        this.link = link;
    }

    @Id @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    private String link;

}
