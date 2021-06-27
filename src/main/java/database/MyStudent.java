package database;

import java.util.Objects;

public class MyStudent implements Student {
    private final String firstName;
    private final String secondName;
    private final String patronymic;
    private final String knownAddress;
    private boolean isStudying;

    public MyStudent(String firstName, String secondName, String patronymic, String knownAddress) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.knownAddress = knownAddress;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getSecondName() {
        return secondName;
    }

    @Override
    public String getPatronymic() {
        return patronymic;
    }

    @Override
    public String getKnownAddress() {
        return knownAddress;
    }

    @Override
    public boolean isStudying() {
        return isStudying;
    }

    synchronized void setStudying(boolean study) {
        isStudying = study;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MyStudent student = (MyStudent) o;
        return Objects.equals(firstName, student.firstName)
                && Objects.equals(secondName, student.secondName)
                && Objects.equals(patronymic, student.patronymic)
                && Objects.equals(knownAddress, student.knownAddress)
                && isStudying == student.isStudying;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, patronymic, knownAddress);
    }

    @Override
    public int compareTo(Student o) {
        return this.secondName.compareTo(o.getSecondName());
    }

    @Override
    public String toString() {
        return "" + "Student: first name = " + firstName +
                "; second name = " + secondName +
                "; patronymic = " + patronymic +
                "; address = " + knownAddress +
                "; isStudy = " + (isStudying ? "yes" : "no");
    }
}
