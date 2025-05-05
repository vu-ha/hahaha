package Model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


class Course {
    private final String courseName;
    private final String courseID;
    private final int creditNumber;
    private final String institute;
    private final float finalExamWeight;
    private final String typeID;
    private final boolean isMandatory;
    private final List<Course> prereqCourses;

    public Course(String courseName, String courseID, int creditNumber, String institute, 
                  float finalExamWeight, String typeID, boolean isMandatory, List<Course> prereqCourses) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.creditNumber = creditNumber;
        this.institute = institute;
        this.finalExamWeight = finalExamWeight;
        this.typeID = typeID;
        this.isMandatory = isMandatory;
        this.prereqCourses = prereqCourses != null ? new ArrayList<>(prereqCourses) : new ArrayList<>();
    }

    public String getCourseName() { return courseName; }
    public String getCourseID() { return courseID; }
    public int getCreditNumber() { return creditNumber; }
    public String getInstitute() { return institute; }
    public float getFinalExamWeight() { return finalExamWeight; }
    public String getTypeID() { return typeID; }
    public boolean isMandatory() { return isMandatory; }
    public List<Course> getPrereqCourses() { return new ArrayList<>(prereqCourses); }
}