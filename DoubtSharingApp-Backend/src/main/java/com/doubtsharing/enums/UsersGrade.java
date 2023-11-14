package com.doubtsharing.enums;

public enum UsersGrade {

    GRADE_1(0),
    GRADE_2(1),
    GRADE_3(2),
    GRADE_4(3),
    GRADE_5(4),
    GRADE_6(5),
    GRADE_7(6),
    GRADE_8(7),
    GRADE_9(8),
    GRADE_10(9),
    GRADE_11(10),
    GRADE_12(11);
	
	
    private Integer grade;

    UsersGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getGrade() {
        return grade;
    }

	
}
