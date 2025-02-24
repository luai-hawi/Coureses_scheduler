import java.util.ArrayList;

public class TimeSlots {
	private int id;
	private char day1;
	private char day2;
	private double from;
	private double to;
	ArrayList<TimeSlots> cont;
	public TimeSlots(int id,char day1, char day2, double from, double to) {
		super();
		this.id=id;
		this.day1 = day1;
		this.day2 = day2;
		this.from = from;
		this.to = to;
	}
	public char getDay1() {
		return day1;
	}
	public void setDay1(char day1) {
		this.day1 = day1;
	}
	public char getDay2() {
		return day2;
	}
	public void setDay2(char day2) {
		this.day2 = day2;
	}
	public double getFrom() {
		return from;
	}
	public void setFrom(double from) {
		this.from = from;
	}
	public double getTo() {
		return to;
	}
	public void setTo(double to) {
		this.to = to;
	}
	public  void contradictions(ArrayList<TimeSlots> time) {
		ArrayList<TimeSlots> contradiction=new ArrayList<>();
		for(int counter=0;counter<time.size();counter++) {
			
			if(time.get(counter).day2=='Q'&&day2=='Q') {
				if((day1==time.get(counter).day1)&&(((time.get(counter).from==from))||((time.get(counter).to==to))||(((from<time.get(counter).to)&&(from>time.get(counter).from)))||(((to<time.get(counter).to)&&(to>time.get(counter).from))))) {
				contradiction.add(time.get(counter));
				}
				}
			else if(time.get(counter).day2=='Q') {
				if((day1==time.get(counter).day1||day2==time.get(counter).day1)&&(((time.get(counter).from==from))||((time.get(counter).to==to))||(((from<time.get(counter).to)&&(from>time.get(counter).from)))||(((to<time.get(counter).to)&&(to>time.get(counter).from))))) {
					contradiction.add(time.get(counter));

				}}
			else if(day2=='Q') {
				if((day1==time.get(counter).day1||day1==time.get(counter).day2)&&(((time.get(counter).from==from))||((time.get(counter).to==to))||(((from<time.get(counter).to)&&(from>time.get(counter).from)))||(((to<time.get(counter).to)&&(to>time.get(counter).from))))) {
					contradiction.add(time.get(counter));

				}}
			else if(((time.get(counter).from==from)&&(day1==time.get(counter).day1||day1==time.get(counter).day2||day2==time.get(counter).day1||day2==time.get(counter).day2))||((time.get(counter).to==to)&&(day1==time.get(counter).day1||day1==time.get(counter).day2||day2==time.get(counter).day1||day2==time.get(counter).day2))||(((from<time.get(counter).to)&&(from>time.get(counter).from))&&(day1==time.get(counter).day1||day1==time.get(counter).day2||day2==time.get(counter).day1||day2==time.get(counter).day2))||(((to<time.get(counter).to)&&(to>time.get(counter).from))&&(day1==time.get(counter).day1))) {
				contradiction.add(time.get(counter));
			}
		}
		cont=contradiction;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<TimeSlots> getCont() {
		return cont;
	}
	public void setCont(ArrayList<TimeSlots> cont) {
		this.cont = cont;
	}

}
