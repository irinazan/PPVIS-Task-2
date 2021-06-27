package actors;

import database.MyGroup;
import database.Student;
import database.Group;
import database.Database;

import java.util.List;
import java.util.function.Predicate;

public class Secretary implements SecretaryI {
    private final Database database;

    public Secretary(Database database) {
        this.database = database;
    }

    @Override
    public Group creteGroup(int courseNumber, String groupName) {
        Group group = new MyGroup(groupName, courseNumber);
        database.createGroup(group);
        return group;

    }

    @Override
    public Group creteGroup(Group group) {
        database.createGroup(group);
        return group;
    }

    @Override
    public boolean removeGroup(int courseNumber, String groupName) {
        Group result = database.getGroup(courseNumber, groupName);
        if (result != null) {
            return database.removeGroup(result);
        }
        return false;
    }

    @Override
    public boolean removeGroup(Group group) {
        return database.removeGroup(group);
    }

    @Override
    public boolean addStudentToTheGroup(Student student, Group group) {
        return database.addStudent(student, group);
    }

    @Override
    public boolean addStudentToTheGroup(Student student, int courseNumber, String groupName) {
        Group result = database.getGroup(courseNumber, groupName);
        if (result != null) {
            return database.addStudent(student, result);
        } else
            return false;
    }

    @Override
    public List<Student> getStudentsByGroup(int courseNumber, String groupName) {
        return database.getGroup(courseNumber, groupName).getStudents();
    }

    @Override
    public List<Student> getStudentsByGroupName(Group group) {
        return group.getStudents();
    }

    @Override
    public List<Student> getStudentsByCourse(int courseNumber) {
        return getStudentIf(e -> e.getCourseNumber() == courseNumber);
    }

    private List<Student> getStudentIf(Predicate<Group> condition) {
        return Searcher.getStudentIf(database, condition);
    }

    @Override
    public Database getDatabase() {
        return database;
    }

}
