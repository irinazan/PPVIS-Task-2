package database;

public interface Student extends Comparable<Student> {
    String getFirstName();

    String getSecondName();

    String getPatronymic();

    String getKnownAddress();

    boolean isStudying();

}
