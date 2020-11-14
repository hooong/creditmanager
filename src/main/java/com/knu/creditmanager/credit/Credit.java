package com.knu.creditmanager.credit;

import com.knu.creditmanager.course.Course;
import com.knu.creditmanager.curriculum.Curriculum;
import com.knu.creditmanager.mycourse.MyCourse;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Credit {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String studentId;

    private Long curriculumId;

    // 기초교양
    private Integer foundation;

    // 균형
    private Integer balance;

    // 특화
    private Integer specialization;

    // 대학별
    private Integer byUni;

    // 교양 총점 +10학점까지
    private Integer sumCulture;

    // 전필
    private Integer majorNecessary;

    // 전선
    private Integer majorSelection;

    // 전공 심화
    private Integer majorDeepening;

    // 전공 총점 = 넘어가면 총점
    private Integer majorSum;

    // 자선
    private Integer freeChoice;

    // 전체 총점 130
    private Integer allSum;

    public Credit(String studentId, Long curriculumId, Integer foundation, Integer balance,
                  Integer specialization, Integer byUni, Integer sumCulture,
                  Integer majorNecessary, Integer majorSelection, Integer majorDeepening,
                  Integer majorSum, Integer freeChoice, Integer allSum) {
        this.studentId = studentId;
        this.curriculumId = curriculumId;
        this.foundation = foundation;
        this.balance = balance;
        this.specialization = specialization;
        this.byUni = byUni;
        this.sumCulture = sumCulture;
        this.majorNecessary = majorNecessary;
        this.majorSelection = majorSelection;
        this.majorDeepening = majorDeepening;
        this.majorSum = majorSum;
        this.freeChoice = freeChoice;
        this.allSum = allSum;
    }

    public void cutCredit(MyCourse course) {
        String type = course.getType();
        Integer credit = course.getCredit();
        int amount = 0;

        switch (type) {
            case "기초교양":
            case "균형교양":
                // 분야별 이수 했는지?
            case "특화교양":
                amount = calcCulture(type, credit);
            case "대학별교양":
                // 대교가 전공이랑 같은 경우 아닌 경우를 나누어야함.
                break;
            case "교직":
                break;
            case "자유선택":
                break;
            case "전공필수":
                break;
            case "전공선택":
                break;
        }

        this.allSum -= amount;
    }

    private Integer calcCulture(String type, Integer credit) {
        int targetCredit = 0;
        int amount = 0;

        switch (type) {
            case "기초교양":
                targetCredit = this.foundation;
                break;
            case "균형교양":
                targetCredit = this.balance;
                break;
            case "특화교양":
                targetCredit = this.specialization;
                break;
        }

        if (targetCredit >= credit) {
            amount = credit;
            targetCredit -= amount;
            this.sumCulture -= amount;
        }
        else if (targetCredit > 0) {
            amount = targetCredit;
            int exceedAmount = credit - amount;
            targetCredit = 0;
            amount += calcSumCulture(exceedAmount);
        }

        switch (type) {
            case "기초교양":
                this.foundation = targetCredit;
                break;
            case "균형교양":
                this.balance = targetCredit;
                break;
            case "특화교양":
                this.specialization = targetCredit;
                break;
        }

        return amount;
    }

    private Integer calcSumCulture(int exceedAmount) {
        int amount = 0;

        if (this.sumCulture >= exceedAmount) {
            amount = exceedAmount;
            this.sumCulture -= exceedAmount;
        }
        else if (this.sumCulture > 0) {
            amount = this.sumCulture;
            this.sumCulture = 0;
        }

        return amount;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", curriculumId=" + curriculumId +
                ", foundation=" + foundation +
                ", balance=" + balance +
                ", specialization=" + specialization +
                ", byUni=" + byUni +
                ", sumCulture=" + sumCulture +
                ", majorNecessary=" + majorNecessary +
                ", majorSelection=" + majorSelection +
                ", majorDeepening=" + majorDeepening +
                ", majorSum=" + majorSum +
                ", freeChoice=" + freeChoice +
                ", allSum=" + allSum +
                '}';
    }
}
