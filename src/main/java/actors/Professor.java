package actors;

import database.Student;
import database.Group;
import database.Database;

import java.util.List;

public class Professor implements ProfessorI {
    private final Database database;

    public Professor(Database database) {
        this.database = database;
    }

    @Override
    public List<Student> getStudentsByGroup(int courseNumber, String groupName) {
        return database.getGroup(courseNumber, groupName).getStudents();
    }

    @Override
    public List<Student> getStudentsByGroup(Group group) {
        return group.getStudents();
    }

    @Override
    public List<Map<Student, Group>> getStudentsBySecondName(String secondName) {
        return Searcher.getPairIf(database, e -> e.getSecondName().equals(secondName));
    }

    @Override
    public Database getDatabase() {
        return database;
    }

}
