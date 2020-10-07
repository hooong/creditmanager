package com.knu.creditmanager.grade;

public enum Grade {
    AP(4.5),
    A(4.0),
    BP(3.5),
    B(3.0),
    CP(2.5),
    C(2.0),
    DP(1.5),
    D(1.0);

    private double points;

    Grade(double points) {
        this.points = points;
    }

    double getPoints() {
        return points;
    }
}
