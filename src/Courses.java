
public class Courses {
private String coursename;
private String coursecode;
private int courseyear;
private int coursehours;
private String instructor;
private int timeslot;
public int getTimeslot() {
	return timeslot;
}
public void setTimeslot(int timeslot) {
	this.timeslot = timeslot;
}
public Courses(String coursename, String coursecode,String instructor) {
	super();
	this.coursename = coursename;
	this.coursecode = coursecode;
	this.instructor=instructor;
	setCourseyear();
	setCoursehours();
}

public String getCoursename() {
	return coursename;
}
public void setCoursename(String coursename) {
	this.coursename = coursename;
}
public String getCoursecode() {
	return coursecode;
}
public void setCoursecode(String coursecode) {
	this.coursecode = coursecode;
}
public int getCourseyear() {
	return courseyear;
}
public void setCourseyear() {
	
	courseyear =(int)( coursecode.charAt(4));
}
public int getCoursehours() {
	return coursehours;
}
public void setCoursehours() {
	coursehours = (int)(coursecode.charAt(5));
}
public String getInstructor() {
	return instructor;
}
public void setInstructor(String instructor) {
	this.instructor = instructor;
}

}
