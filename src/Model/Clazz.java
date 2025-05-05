package Model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Clazz {
    private final Course course;
    private final String credit;
    private final String time;
    private final String startTime;
    private final String endTime;
    private final String dayOfWeek;
    private final String weeks;
    private final String room;
    private final int maxCapacity;
    private int registeredCount;
    private Teacher teacher;
    private List<Student> students;
    
	public Clazz(Course course, String credit, String time, String startTime, String endTime, String dayOfWeek,
			String weeks, String room, int maxCapacity, int registeredCount, Teacher teacher, List<Student> students) {
		this.course = course;
		this.credit = credit;
		this.time = time;
		this.startTime = startTime;
		this.endTime = endTime;
		this.dayOfWeek = dayOfWeek;
		this.weeks = weeks;
		this.room = room;
		this.maxCapacity = maxCapacity;
		this.registeredCount = registeredCount;
		this.teacher = teacher;
		this.students = students;
	}

	public int getRegisteredCount() { return registeredCount; }
	public Course getCourse() { return course; }
	public String getCredit() { return credit; }
	public String getTime() { return time; }
	public String getStartTime() { return startTime; }
	public String getEndTime() { return endTime; }
	public String getDayOfWeek() { return dayOfWeek; }
	public String getWeeks() { return weeks; }
	public String getRoom() { return room; }
	public int getMaxCapacity() { return maxCapacity; }
	public Teacher getTeacher() { return teacher; }
	public List<Student> getStudents() { return students; }
    
}