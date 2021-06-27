package database;

import java.util.List;

public interface Database {
    boolean addStudent(Student student);

    boolean addStudent(Student student, Group group);

    boolean removeStudent(Student student, Group group);

    boolean removeStudent(Student student);

    boolean createGroup(Group group);

    Group getGroup(int courseNumber, String groupNumber);

    boolean removeGroup(Group group);

    List<Group> getGroups();

    List<Group> getGroupsByCourse(int courseNumber);

    List<Student> getStudentsNotInGroups();

    List<Student> getStudents();

}
