//Etienne Daniells
public class Student {
    private String name;
    private String surName;
    private int StudentNo;
    private String subject;

    @Override
    public String toString(){
        return String.format(".%nStudent Name: " + name + "  Student Surname: " + surName+ " Student No: " + StudentNo + " Subject: " +subject +".%n");
    }

    public Student(String name, String surName, int studentNo, String subject) {
        this.name = name;
        this.surName = surName;
        this.StudentNo = studentNo;
        this.subject = subject;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getStudentNo() {
        return StudentNo;
    }

    public void setStudentNo(int studentNo) {
        StudentNo = studentNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}

