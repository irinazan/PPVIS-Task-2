package database;

import java.util.LinkedHashSet;

class CourseLinkedContainer extends LinkedHashSet<Group> {
    private final int courseNumber;

    public CourseLinkedContainer(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public int getCourseNumber() {
        return courseNumber;
    }
}
