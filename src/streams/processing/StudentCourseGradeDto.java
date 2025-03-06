package streams.processing;

public class StudentCourseGradeDto {
    private String name;
    private String surname;
    private Integer age;
    private String course;
    private String finalGrade;
    private Boolean approved;

    public StudentCourseGradeDto(String name, String surname, Integer age, String course, String finalGrade, Boolean approved) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.course = course;
        this.finalGrade = finalGrade;
        this.approved = approved;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(String finalGrade) {
        this.finalGrade = finalGrade;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
