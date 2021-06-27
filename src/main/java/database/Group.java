package database;

import java.util.List;

public interface Group {

    String getGroupName();

    int getCourseNumber();

    List<Student> getStudents();


}
