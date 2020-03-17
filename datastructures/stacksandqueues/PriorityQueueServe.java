package datastructures.stacksandqueues;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;

/*
 * Create the Student and Priorities classes here.
 */
 class Student{
    private int id;
    private String name;
    private double cgpa;

    Student(int id,String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }
    public int getId() { return this.id;}
    public String getName() {return this.name;}
    public double getCgpa() {return this.cgpa;}

    public String toString() {
        return this.name;
    }
 }

class StudentComparator implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        if(s1.getCgpa() != s2.getCgpa()) {
            return s1.getCgpa() < s2.getCgpa() ? 1 : -1;
        } else if(!s1.getName().equals(s2.getName())) {
            return s1.getName().compareTo(s2.getName());
        } else {
            return s1.getId() > s2.getId() ? 1 : -1;
        }
    }
}
class Priorities {

    public List<Student> getStudents(List<String> events) {
        PriorityQueue<Student> pq = new PriorityQueue<Student>(events.size(), new StudentComparator());
        for(String event: events) {
            String[] words = event.split(" ");
            if("ENTER".equals(words[0])) {
                Student s = new Student(Integer.parseInt(words[3]),words[1],Double.parseDouble(words[2]));
                pq.add(s);
            } else {
                pq.poll();
            }
        }
        List<Student> students = new ArrayList<Student>();
        while(!pq.isEmpty()) {
            students.add(pq.poll());
        }
        return students;
    }
}

public class PriorityQueueServe {
    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();
    
    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());    
        List<String> events = new ArrayList<>();
        
        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }
        
        List<Student> students = priorities.getStudents(events);
        
        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st: students) {
                System.out.println(st.getName());
            }
        }
    }
}