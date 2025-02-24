import java.util.ArrayList;

public class Instructors {
private String instructor;
private ArrayList<Integer> allowed;
public Instructors(String instructor) {
	super();
	this.instructor = instructor;
	refresh();
}
public String getInstructor() {
	return instructor;
}
public void setInstructor(String instructor) {
	this.instructor = instructor;
}
public ArrayList<Integer> getAllowed() {
	return allowed;
}
public void setAllowed(ArrayList<Integer> allowed) {
	this.allowed = allowed;
}
public void refresh() {
	ArrayList<Integer> arr=new ArrayList<>();
	for(int counter=0;counter<49;counter++) {
		arr.add(counter);
	}
	allowed=arr;
}
public void remove(ArrayList<TimeSlots> arr) {
	for(int counter=0;counter<arr.size();counter++) {
		for(int counter2=0;counter2<allowed.size();counter2++) {
			if(arr.get(counter).getId()==allowed.get(counter2).intValue()) {
				allowed.remove(counter2);
				break;
			}
		}
	}
}
}
