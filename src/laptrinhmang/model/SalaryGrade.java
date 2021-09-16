/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laptrinhmang.model;

import java.io.Serializable;

/**
 *
 * @author hoang
 */
public class SalaryGrade implements Serializable {

    private static final long serialVersionUID = 1L;

    public SalaryGrade(int grade, float highSalary, float lowSalary) {
        super();
        this.grade = grade;
        this.highSalary = highSalary;
        this.lowSalary = lowSalary;
    }

    private int grade;

    private float highSalary;

    private float lowSalary;

    public SalaryGrade() {
    }

    public int getGrade() {
        return this.grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public float getHighSalary() {
        return this.highSalary;
    }

    public void setHighSalary(float highSalary) {
        this.highSalary = highSalary;
    }

    public float getLowSalary() {
        return this.lowSalary;
    }

    public void setLowSalary(float lowSalary) {
        this.lowSalary = lowSalary;
    }
}
