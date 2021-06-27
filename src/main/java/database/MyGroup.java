package database;

import java.util.*;

public class MyGroup implements Group {
    private final String groupName;
    private final int courseNumber;
    private final Set<Student> studentsList = new TreeSet<>();

    public MyGroup(String groupName, int courseNumber) {
        if (groupName.isEmpty())
            throw new RuntimeException("The group name cannot be empty");
        else if (courseNumber < 0)
            throw new RuntimeException("The course number cannot be less then 0. Error value: " + courseNumber);
        this.groupName = groupName;
        this.courseNumber = courseNumber;
    }

    @Override
    public String getGroupName() {
        return groupName;
    }

    @Override
    public int getCourseNumber() {
        return courseNumber;
    }

    public synchronized boolean addStudent(Student student) {
        Objects.requireNonNull(student);
        ((MyStudent) student).setStudying(true);
        return studentsList.add(student);
    }

    public synchronized boolean removeStudent(Student student) {
        Objects.requireNonNull(student);
        ((MyStudent) student).setStudying(false);
        return studentsList.remove(student);
    }

    public synchronized void clear() {
        for (Student student : studentsList) {
            ((MyStudent) student).setStudying(false);
        }
        studentsList.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MyGroup group = (MyGroup) o;
        return groupName.equals(group.groupName) &&
                courseNumber == group.courseNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, courseNumber, studentsList);
    }

    @Override
    public List<Student> getStudents() {
        List<Student> result = new ArrayList<>(studentsList.size());
        result.addAll(studentsList);
        return Collections.unmodifiableList(result);
    }

    @Override
    public String toString() {
        return "Group: " +
                "groupName = " + groupName +
                "; courseNumber = " + courseNumber;
    }
}
