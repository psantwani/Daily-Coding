package com.piyush.oodesign.StudentLevel;

/**
 * Description
 * Implement a class Student, including the following attributes and methods:
 *
 * Two public attributes name(string) and score(int).
 * A constructor expect a name as a parameter.
 * A method getLevel to get the level(char) of the student.
 * score – level table:
 *
 * A: score >= 90
 * B: score >= 80 and < 90
 * C: score >= 60 and < 80
 * D: score < 60
 */

public class StudentLevelMain {
  public static void main(String ... args){
    Student student = new Student("Mark");
    student.score = 10;
    System.out.println(student.getLevel()); // should be 'D'
    student.score = 60;
    System.out.println(student.getLevel()); // should be 'C'
  }
}

class Student{

  public String name;
  public int score;

  public Student(String name){
    this.name = name;
  }

  public char getLevel(){
    if(this.score >= 90) return 'A';
    else if(this.score >= 80) return 'B';
    else if(this.score >= 60) return 'C';
    else return 'D';
  }
}