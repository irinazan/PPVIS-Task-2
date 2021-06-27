package actors;

import database.Database;
import database.Group;
import database.Student;

import java.util.List;

public interface SecretaryI {
    Group creteGroup(int courseNumber, String groupName);

    Group creteGroup(Group group);

    boolean removeGroup(int courseNumber, String groupName);

    boolean removeGroup(Group group);

    boolean addStudentToTheGroup(Student student, Group group);

    boolean addStudentToTheGroup(Student student, int courseNumber, String groupName);

    List<Student> getStudentsByGroup(int courseNumber, String groupName);

    List<Student> getStudentsByGroupName(Group group);

    List<Student> getStudentsByCourse(int courseNumber);

    Database getDatabase();
}
