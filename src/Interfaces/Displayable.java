package Interfaces;
import CourseManagement.Course;
import UserManagement.User;

import java.util.ArrayList;

public interface Displayable {
    void displayUsers(ArrayList<User> usersList);
    void displayCourses();
    void displayModules(Course course);
}
