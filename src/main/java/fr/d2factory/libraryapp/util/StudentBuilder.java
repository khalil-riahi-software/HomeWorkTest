package fr.d2factory.libraryapp.util;

import fr.d2factory.libraryapp.member.Student;

/**
 * this class StudentBuilder can create easy and build Student Object
 */
public class StudentBuilder {

    private float wallet;
    private boolean late;
    private int levelStudy;

    public StudentBuilder() {
    }

    public StudentBuilder withWallet(float wallet){
        this.wallet=wallet;
        return this;
    }
    public StudentBuilder withLevelStudy(int levelStudy){
        this.levelStudy=levelStudy;
        return this;
    }

    public StudentBuilder islate(boolean late){
        this.late=late;
        return this;
    }
    public Student build(){
        return new Student(levelStudy,wallet,late);
    }

}
