package database;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class UniversityDatabase implements Database {
    private final List<CourseLinkedContainer> coursesList = new ArrayList<>();
    private final List<Student> dontStudy = new ArrayList<>();
    private final List<Student> allStudents = new ArrayList<>();

    @Override
    public synchronized boolean addStudent(Student student, Group toGroup) {
        Objects.requireNonNull(student);
        Objects.requireNonNull(toGroup);

        if (!isGroupExists(toGroup))
            throw new RuntimeException("This group doesn't exist in database: course number = "
                    + toGroup.getCourseNumber() + "; group name = " + toGroup.getGroupName());

        boolean result = ((MyGroup) toGroup).addStudent(student);
        allStudents.add(student);
        if (result)
            dontStudy.remove(student);
        return result;
    }

    @Override
    public List<Student> getStudents() {
        return Collections.unmodifiableList(allStudents);
    }

    @Override
    public synchronized boolean addStudent(Student student) {
        Objects.requireNonNull(student);
        return dontStudy.add(student) && allStudents.add(student);
    }

    @Override
    public synchronized boolean removeStudent(Student student, Group fromGroup) {
        Objects.requireNonNull(student);
        Objects.requireNonNull(fromGroup);
        if (!isGroupExists(fromGroup))
            throw new RuntimeException("This group doesn't exist in database: course number = "
                    + fromGroup.getCourseNumber() + "; group name = " + fromGroup.getGroupName());

        return ((MyGroup) fromGroup).removeStudent(student) && dontStudy.add(student);
    }

    @Override
    public synchronized boolean removeStudent(Student student) {
        Objects.requireNonNull(student);
        if (!allStudents.contains(student))
            throw new RuntimeException("This student doesn't exist in database: " + student);
        return dontStudy.remove(student) && allStudents.remove(student);
    }

    @Override
    public synchronized boolean createGroup(Group group) {
        Objects.requireNonNull(group);
        for (CourseLinkedContainer course : coursesList) {
            if (course.getCourseNumber() == group.getCourseNumber()) {
                return course.add(group);
            }
        }
        CourseLinkedContainer tempList = new CourseLinkedContainer(group.getCourseNumber());
        tempList.add(group);
        return coursesList.add(tempList);
    }

    @Override
    public Group getGroup(int courseNumber, String groupName) {
        if (groupName.isEmpty())
            throw new RuntimeException("Group with an empty name cannot exist");
        for (CourseLinkedContainer course : coursesList) {
            if (course.getCourseNumber() == courseNumber) {
                for (Group group : course) {
                    if (group.getGroupName().equals(groupName)) {
                        MyGroup resultGroup = new MyGroup(group.getGroupName(), group.getCourseNumber());
                        group.getStudents().forEach(resultGroup::addStudent);
                        return resultGroup;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<Group> getGroups() {
        List<Group> result = new ArrayList<>(coursesList.size() * 5);
        for (CourseLinkedContainer course : coursesList) {
            result.addAll(course);
        }
        return Collections.unmodifiableList(result);
    }

    public List<Group> getGroupsByCourse(int courseNumber) {
        for (CourseLinkedContainer course : coursesList) {
            if (course.getCourseNumber() == courseNumber) {
                List<Group> result = new ArrayList<>(course.size());
                result.addAll(course);
                return Collections.unmodifiableList(result);
            }
        }
        return List.of();
    }

    @Override
    public List<Student> getStudentsNotInGroups() {
        return Collections.unmodifiableList(dontStudy);
    }

    @Override
    public synchronized boolean removeGroup(Group group) {
        Objects.requireNonNull(group);
        for (CourseLinkedContainer course : coursesList) {
            if (course.getCourseNumber() == group.getCourseNumber()) {
                for (Group gr : course) {
                    if (gr.getGroupName().equals(group.getGroupName())) {
                        dontStudy.addAll(gr.getStudents());
                        ((MyGroup) gr).clear();
                        if (course.remove(gr)) {
                            if (course.isEmpty()) {
                                coursesList.remove(course);
                            }
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isGroupExists(Group group) {
        return getGroups().contains(group);
    }

}
