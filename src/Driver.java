import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {	
	public static ArrayList<Courses> courses;
	public static ArrayList<TimeSlots> slots;
	public static ArrayList<Instructors> ins;
	public static void main(String[] args) {
		ins=new ArrayList<>();
		courses=readFile();
		int[] crom=new int[courses.size()];
		int[] crom2=new int[courses.size()];
		int[] sol=new int[courses.size()];
		slots=createSlots();
		sol=createSol();
		for(int counter=0;counter<10000;counter++) {
		crom=createSol();
		int randomcut=(int)(Math.random()*136);
		for(int counter2=0;counter2<randomcut;counter2++) {
			crom2[counter2]=sol[counter2];
		}
		for(int counter2=randomcut;counter2<136;counter2++) {
			crom2[counter2]=crom[counter2];
		}
		crom2=mutation(crom2);
		int cromFit=fitness(crom);
		int solFit=fitness(sol);
		int crom2Fit=fitness(crom2);
		System.out.println(solFit);
		if(crom2Fit<cromFit&&crom2Fit<solFit) {
			for(int counter2=0;counter2<crom2.length;counter2++) {
				sol[counter2]=crom2[counter2];
			}
		}
		else if(crom2Fit>cromFit&&cromFit<solFit) {
			for(int counter2=0;counter2<crom.length;counter2++) {
				sol[counter2]=crom[counter2];
			}
		}

		}
		for(int counter=0;counter<136;counter++) {
			System.out.println(courses.get(counter).getCoursecode()+"\t"+slots.get(sol[counter]).getDay1()+slots.get(sol[counter]).getDay2()+"\t"+slots.get(sol[counter]).getFrom()+"  "+slots.get(sol[counter]).getTo());
		}
	}

	
	public static ArrayList<Courses> readFile(){
		File file=new File("data.txt");
		ArrayList<Courses> array=new ArrayList<>();
		try {
			Scanner in=new Scanner(file);
			String line=new String();
			while(in.hasNext()) {
				line=in.nextLine();
				String[] line2=line.split(";");
				String[] line3=line2[2].split(",");
				String[] line4=line3[1].split("`");
				for(int counter=0;counter<line4.length;counter++) {
					array.add(new Courses(line2[1],line2[0],line4[counter]));
					ins.add(new Instructors(line4[counter]));
				}
				
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array;
	}
	public static ArrayList<TimeSlots> createSlots(){
		ArrayList<TimeSlots> arr=new ArrayList<>();
		arr.add(new TimeSlots(0,'S','M',8,9.5));
		arr.add(new TimeSlots(1,'S','W',8,9.5));
		arr.add(new TimeSlots(2,'S','M',9.5,11));
		arr.add(new TimeSlots(3,'S','W',9.5,11));
		arr.add(new TimeSlots(4,'S','M',11,12.5));
		arr.add(new TimeSlots(5,'S','W',11,12.5));
		arr.add(new TimeSlots(6,'S','M',12.5,2));
		arr.add(new TimeSlots(7,'S','W',12.5,2));
		arr.add(new TimeSlots(8,'S','M',2,3.5));
		arr.add(new TimeSlots(9,'S','W',2,3.5));
		arr.add(new TimeSlots(10,'S','M',3.5,5));
		arr.add(new TimeSlots(11,'S','W',3.5,5));

		arr.add(new TimeSlots(12,'M','W',8,9.5));
		arr.add(new TimeSlots(13,'M','W',9.5,11));
		arr.add(new TimeSlots(14,'M','W',11,12.5));
		arr.add(new TimeSlots(15,'M','W',12.5,2));
		arr.add(new TimeSlots(16,'M','W',2,3.5));
		arr.add(new TimeSlots(17,'M','W',3.5,5));
		
		arr.add(new TimeSlots(18,'T','R',8,9.5));
		arr.add(new TimeSlots(19,'T','R',9.5,11));
		arr.add(new TimeSlots(20,'T','R',11,12.5));
		arr.add(new TimeSlots(21,'T','R',12.5,2));
		arr.add(new TimeSlots(22,'T','R',2,3.5));
		arr.add(new TimeSlots(23,'T','R',3.5,5));
		
		arr.add(new TimeSlots(24,'S','Q',8,11));
		arr.add(new TimeSlots(25,'S','Q',9.5,12.5));
		arr.add(new TimeSlots(26,'S','Q',11,2));
		arr.add(new TimeSlots(27,'S','Q',12.5,3.5));
		arr.add(new TimeSlots(28,'S','Q',2,5));
		
		arr.add(new TimeSlots(29,'M','Q',8,11));
		arr.add(new TimeSlots(30,'M','Q',9.5,12.5));
		arr.add(new TimeSlots(31,'M','Q',11,2));
		arr.add(new TimeSlots(32,'M','Q',12.5,3.5));
		arr.add(new TimeSlots(33,'M','Q',2,5));
		
		arr.add(new TimeSlots(34,'T','Q',8,11));
		arr.add(new TimeSlots(35,'T','Q',9.5,12.5));
		arr.add(new TimeSlots(36,'T','Q',11,2));
		arr.add(new TimeSlots(37,'T','Q',12.5,3.5));
		arr.add(new TimeSlots(38,'T','Q',2,5));
		
		arr.add(new TimeSlots(39,'W','Q',8,11));
		arr.add(new TimeSlots(40,'W','Q',9.5,12.5));
		arr.add(new TimeSlots(41,'W','Q',11,2));
		arr.add(new TimeSlots(42,'W','Q',12.5,3.5));
		arr.add(new TimeSlots(43,'W','Q',2,5));
		
		arr.add(new TimeSlots(44,'R','Q',8,11));
		arr.add(new TimeSlots(45,'R','Q',9.5,12.5));
		arr.add(new TimeSlots(46,'R','Q',11,2));
		arr.add(new TimeSlots(47,'R','Q',12.5,3.5));
		arr.add(new TimeSlots(48,'R','Q',2,5));
		for(int counter=0;counter<arr.size();counter++) {
			arr.get(counter).contradictions(arr);
		}
		return arr;
	}
	public static int[] createSol() {
		int[] crom=new int[136];
		int state=0;
		while(true) {
			for(int counter=0;counter<ins.size();counter++) {
				ins.get(counter).refresh();
			}
			state=0;
		for(int counter=0;counter<crom.length;counter++) {
			if(courses.get(counter).getCoursecode().charAt(5)=='1') {
				int number;

				


						number=exist(courses.get(counter).getInstructor(),1);
						
						
				
				
				if(number==-1) {
					state=1;
					break;
				}
			
			
			crom[counter]=number;
			ins.get(indexOfInstructor(courses.get(counter).getInstructor())).remove(slots.get(number).getCont());
			
			}
			else {
				int number;
				number=exist(courses.get(counter).getInstructor(),0);
				if(number==-1) {
					state=1;
					break;
				}
				crom[counter]=number;
				ins.get(indexOfInstructor(courses.get(counter).getInstructor())).remove(slots.get(number).getCont());


			}
		}
		if(state==0)
			break;
		}
		return crom;
	}
	
	public static int indexOfInstructor(String instructor) {
		for(int counter=0;counter<ins.size();counter++) {
			if(instructor.equals(ins.get(counter).getInstructor())) {
				return counter;
			}
		}
		return -1;
	}
	
	public static int[] mutation(int[] crom) {
		for(int counter=0;counter<ins.size();counter++) {
			ins.get(counter).refresh();
		}
		for(int counter=0;counter<136;counter++) {
			ins.get(indexOfInstructor(courses.get(counter).getInstructor())).remove(slots.get(crom[counter]).getCont());
			for(int counter2=counter+1;counter2<136;counter2++) {
				if(courses.get(counter2).getInstructor().equals(courses.get(counter).getInstructor())) {
					if(check(slots.get(crom[counter]),slots.get(crom[counter2]))) {
						if(courses.get(counter).getCoursecode().charAt(5)=='1') {
							int number;
							
						while(true) {
						number=ins.get(indexOfInstructor(courses.get(counter).getInstructor())).getAllowed().get((int)(Math.random()*ins.get(indexOfInstructor(courses.get(counter).getInstructor())).getAllowed().size()));
						if(number>23)
							break;
						}
						
						crom[counter2]=number;
						ins.get(indexOfInstructor(courses.get(counter).getInstructor())).remove(slots.get(number).getCont());
						
						}
						else {
							int number;
							
							while(true) {
							number=ins.get(indexOfInstructor(courses.get(counter).getInstructor())).getAllowed().get((int)(Math.random()*ins.get(indexOfInstructor(courses.get(counter).getInstructor())).getAllowed().size()));
							if(number<24)
								break;
							}
							crom[counter2]=number;
							ins.get(indexOfInstructor(courses.get(counter).getInstructor())).remove(slots.get(number).getCont());


						}

					}
				}
			}
		}
		return crom;
	}
	public static boolean check(TimeSlots t1,TimeSlots t2) {
		if (t1.cont.contains(t2))
			return true;
		return false;
	}
	
	public static int fitness(int[] crom) {
		int commonDays=0;
		int commonHours=0;
		for(int counter=0;counter<136;counter++) {
			if(courses.get(counter).getCoursecode().charAt(4)=='1') {
				for(int counter2=counter+1;counter2<136;counter2++) {
					if(courses.get(counter2).getCoursecode().charAt(4)=='1') {
						if(slots.get(crom[counter]).getDay1()==slots.get(crom[counter2]).getDay1()&&slots.get(crom[counter]).getDay2()==slots.get(crom[counter2]).getDay2()) {
							commonDays+=2;
						}
						else if(slots.get(crom[counter]).getDay1()==slots.get(crom[counter2]).getDay1()||slots.get(crom[counter]).getDay1()==slots.get(crom[counter2]).getDay2()||slots.get(crom[counter]).getDay2()==slots.get(crom[counter2]).getDay1()||slots.get(crom[counter]).getDay2()==slots.get(crom[counter2]).getDay2()) {
							commonDays+=1;
						}
						if(slots.get(crom[counter]).cont.contains(slots.get(crom[counter2]))) {
							commonHours+=1;
						}
					}
				}
				
			}
			if(courses.get(counter).getCoursecode().charAt(4)=='2') {
				for(int counter2=counter+1;counter2<136;counter2++) {
					if(courses.get(counter2).getCoursecode().charAt(4)=='2') {
						if(slots.get(crom[counter]).getDay1()==slots.get(crom[counter2]).getDay1()&&slots.get(crom[counter]).getDay2()==slots.get(crom[counter2]).getDay2()) {
							commonDays+=2;
						}
						else if(slots.get(crom[counter]).getDay1()==slots.get(crom[counter2]).getDay1()||slots.get(crom[counter]).getDay1()==slots.get(crom[counter2]).getDay2()||slots.get(crom[counter]).getDay2()==slots.get(crom[counter2]).getDay1()||slots.get(crom[counter]).getDay2()==slots.get(crom[counter2]).getDay2()) {
							commonDays+=1;
						}
						if(slots.get(crom[counter]).cont.contains(slots.get(crom[counter2]))) {
							commonHours+=1;
						}
					}
				}
			}
			if(courses.get(counter).getCoursecode().charAt(4)=='3') {
				for(int counter2=counter+1;counter2<136;counter2++) {
					if(courses.get(counter2).getCoursecode().charAt(4)=='3') {
						if(slots.get(crom[counter]).getDay1()==slots.get(crom[counter2]).getDay1()&&slots.get(crom[counter]).getDay2()==slots.get(crom[counter2]).getDay2()) {
							commonDays+=2;
						}
						else if(slots.get(crom[counter]).getDay1()==slots.get(crom[counter2]).getDay1()||slots.get(crom[counter]).getDay1()==slots.get(crom[counter2]).getDay2()||slots.get(crom[counter]).getDay2()==slots.get(crom[counter2]).getDay1()||slots.get(crom[counter]).getDay2()==slots.get(crom[counter2]).getDay2()) {
							commonDays+=1;
						}
						if(slots.get(crom[counter]).cont.contains(slots.get(crom[counter2]))) {
							commonHours+=1;
						}
					}
				}
			}
			if(courses.get(counter).getCoursecode().charAt(4)=='4') {
				for(int counter2=counter+1;counter2<136;counter2++) {
					if(courses.get(counter2).getCoursecode().charAt(4)=='4') {
						if(slots.get(crom[counter]).getDay1()==slots.get(crom[counter2]).getDay1()&&slots.get(crom[counter]).getDay2()==slots.get(crom[counter2]).getDay2()) {
							commonDays+=2;
						}
						else if(slots.get(crom[counter]).getDay1()==slots.get(crom[counter2]).getDay1()||slots.get(crom[counter]).getDay1()==slots.get(crom[counter2]).getDay2()||slots.get(crom[counter]).getDay2()==slots.get(crom[counter2]).getDay1()||slots.get(crom[counter]).getDay2()==slots.get(crom[counter2]).getDay2()) {
							commonDays+=1;
						}
						if(slots.get(crom[counter]).cont.contains(slots.get(crom[counter2]))) {
							commonHours+=1;
						}
					}
				}
			}
			if(courses.get(counter).getCoursecode().charAt(4)=='5') {
				for(int counter2=counter+1;counter2<136;counter2++) {
					if(courses.get(counter2).getCoursecode().charAt(4)=='5') {
						if(slots.get(crom[counter]).getDay1()==slots.get(crom[counter2]).getDay1()&&slots.get(crom[counter]).getDay2()==slots.get(crom[counter2]).getDay2()) {
							commonDays+=2;
						}
						else if(slots.get(crom[counter]).getDay1()==slots.get(crom[counter2]).getDay1()||slots.get(crom[counter]).getDay1()==slots.get(crom[counter2]).getDay2()||slots.get(crom[counter]).getDay2()==slots.get(crom[counter2]).getDay1()||slots.get(crom[counter]).getDay2()==slots.get(crom[counter2]).getDay2()) {
							commonDays+=1;
						}
						if(slots.get(crom[counter]).cont.contains(slots.get(crom[counter2]))) {
							commonHours+=1;
						}
					}
				}
			}
		}
		return ((commonDays)+(commonHours*3));
	}
	public static int exist(String inst,int state) {
		ArrayList<Integer> arr=new ArrayList<>();
		if (state==0) {
			for(int counter=0;counter<ins.get(indexOfInstructor(inst)).getAllowed().size();counter++) {
				if(ins.get(indexOfInstructor(inst)).getAllowed().get(counter).intValue()<24)
					arr.add(ins.get(indexOfInstructor(inst)).getAllowed().get(counter).intValue());
			}
			
		}
		else if (state==1) {
			for(int counter=0;counter<ins.get(indexOfInstructor(inst)).getAllowed().size();counter++) {
				if(ins.get(indexOfInstructor(inst)).getAllowed().get(counter).intValue()>23)
					arr.add(ins.get(indexOfInstructor(inst)).getAllowed().get(counter).intValue());
			}

	}
		if(arr.size()==0) {
			return-1;
		}
		int number=arr.get((int)(Math.random()*arr.size())).intValue();
		return number;

	}
}
