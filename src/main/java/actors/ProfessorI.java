package actors;

import database.Database;
import database.Group;
import database.Student;

import java.util.List;

public interface ProfessorI {
    List<Student> getStudentsByGroup(int courseNumber, String groupName);

    List<Student> getStudentsByGroup(Group group);

    List<Map<Student, Group>> getStudentsBySecondName(String secondName);

    Database getDatabase();
}
