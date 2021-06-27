package actors;

import database.Student;
import database.Group;
import database.Database;

import java.util.List;
import java.util.Objects;

public class Dean implements DeanI {
    private final Database database;

    public Dean(Database database) {
        this.database = database;
    }

    @Override
    public boolean moveStudentToTheGroup(Student student, Group fromGroup, Group toGroup) {
        Objects.requireNonNull(student);
        if (fromGroup == null && toGroup == null)
            return database.removeStudent(student);

        if (fromGroup != null) {
            database.removeStudent(student, fromGroup);
        }
        if (toGroup != null) {
            return database.addStudent(student, toGroup);
        }
        return false;
    }

    @Override
    public List<Map<Student, Group>> getStudentsByAddress(String address) {
        List<Map<Student, Group>> result = Searcher.getPairIf(database, e -> e.getKnownAddress().equals(address));

        for (Student student : database.getStudentsNotInGroups()) {
            if (student.getKnownAddress().equals(address)) {
                result.add(new Map<>(student, null));
            }
        }
        return result;
    }

    @Override
    public Database getDatabase() {
        return database;
    }

}
