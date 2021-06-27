package actors;

import database.Database;
import database.Group;
import database.Student;

import java.util.List;

public interface DeanI {
    boolean moveStudentToTheGroup(Student student, Group fromGroup, Group toGroup);

    List<Map<Student, Group>> getStudentsByAddress(String address);

    Database getDatabase();
}
