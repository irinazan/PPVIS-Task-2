package actors;

import database.Student;
import database.Group;
import database.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Searcher {
    public Searcher() {
        throw new AssertionError();
    }

    public static List<Student> getStudentIf(Database database, Predicate<Group> condition) {
        Objects.requireNonNull(database);
        Objects.requireNonNull(condition);
        List<Student> result = new ArrayList<>();
        List<Group> groups = database.getGroups();
        for (Group group : groups) {
            if (condition.test(group)) {
                result.addAll(group.getStudents());
            }
        }
        return result;
    }

    public static List<Map<Student, Group>> getPairIf(Database database, Predicate<Student> condition) {
        Objects.requireNonNull(database);
        Objects.requireNonNull(condition);
        List<Map<Student, Group>> result = new ArrayList<>();
        for (Group group : database.getGroups()) {
            for (Student student : group.getStudents()) {
                if (condition.test(student)) {
                    result.add(new Map<>(student, group));
                }
            }
        }
        return result;

    }
}
