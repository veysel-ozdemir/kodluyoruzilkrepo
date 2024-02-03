package HackerRankChallenges;

import java.util.*;

public class HackerRankPriorityQueue {
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
            for (Student st : students) {
                System.out.println(st.getName());
            }
        }
    }
}

class Student {
    private final int id;
    private final String name;
    private final double cgpa;

    public Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCgpa() {
        return cgpa;
    }
}

class Priorities {
    List<Student> getStudents(List<String> events) {
        /*
         * Comparator<Student> comparator =
         * Comparator.comparingDouble(Student::getCgpa).reversed().thenComparing(Student
         * ::getName).thenComparingInt(Student::getId);
         * PriorityQueue<Student> queue = new PriorityQueue<>(comparator);
         */
        PriorityQueue<Student> queue = new PriorityQueue<>(
                new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        if (o1.getCgpa() != o2.getCgpa()) {
                            return o1.getCgpa() - o2.getCgpa() < 0.0 ? 1 : -1;
                        } else if (!o1.getName().equals(o2.getName())) {
                            return o1.getName().compareTo(o2.getName());
                        } else {
                            return o1.getId() < o2.getId() ? 1 : -1;
                        }
                    }
                });

        for (String event : events) {
            if (event.startsWith("ENTER")) {
                String[] credentials = event.split(" "); // ["ENTER", "name", "cgpa", "id"]
                queue.add(new Student(Integer.parseInt(credentials[3]), // id
                        credentials[1], // name
                        Double.parseDouble(credentials[2]) // cgpa
                ));
            } else if (event.startsWith("SERVED")) {
                queue.poll();
            }
        }

        List<Student> students = new ArrayList<>();
        while (queue.iterator().hasNext()) {
            students.add(queue.poll());
        }
        return students; // return queue.stream().toList();
    }
}

// --INPUT--
// 12
// ENTER John 3.75 50
// ENTER Mark 3.8 24
// ENTER Shafaet 3.7 35
// SERVED
// SERVED
// ENTER Samiha 3.85 36
// SERVED
// ENTER Ashley 3.9 42
// ENTER Maria 3.6 46
// ENTER Anik 3.95 49
// ENTER Dan 3.95 50
// SERVED
// --EXPECTED OUTPUT--
// Dan
// Ashley
// Shafaet
// Maria