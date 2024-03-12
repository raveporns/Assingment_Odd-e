import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FourKingsInstitution {
    static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class House {
        private List<Person> residents;

        public House() {
            this.residents = new ArrayList<>();
        }

        public void addResident(Person person) {
            residents.add(person);
        }

        public List<Person> getResidents() {
            return residents;
        }

        public int getNumberOfResidents() {
            return residents.size();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // รับจำนวนคน (N)
        System.out.print("Enter the number of people : ");
        int N = scanner.nextInt();

        // สร้างบ้าน
        Map<String, House> houses = new HashMap<>();

        // รับข้อมูลและจัดเก็บในบ้าน
        for (int i = 0; i < N; i++) {
            scanner.nextLine();  // รับ newline ที่เหลือจากคำสั่งก่อนหน้า
            System.out.print("Enter the name of person " + (i + 1) + ": ");
            String personName = scanner.nextLine();

            // เลือกบ้านให้คนนี้
            House house = selectHouse(houses);
            house.addResident(new Person(personName));
        }

        // แสดงรายชื่อและบ้านที่คนแต่ละคนอยู่
        System.out.println("\nPeople and their houses:");
        for (Map.Entry<String, House> entry : houses.entrySet()) {
            String houseName = entry.getKey();
            House house = entry.getValue();

            System.out.println("House: " + houseName);
            for (Person person : house.getResidents()) {
                System.out.println("- " + person.getName());
            }
        }

        // แสดงจำนวนคนเฉลี่ยในแต่ละบ้าน
        System.out.println("\nAverage number of residents in each house:");
        for (Map.Entry<String, House> entry : houses.entrySet()) {
            String houseName = entry.getKey();
            House house = entry.getValue();

            System.out.println("House: " + houseName + ", Average residents: " + house.getNumberOfResidents());
        }
    }

    private static House selectHouse(Map<String, House> houses) {
        // ถ้าไม่มีบ้านใน Map ให้สร้างบ้านใหม่
        if (houses.isEmpty()) {
            String houseName = "House1";
            House newHouse = new House();
            houses.put(houseName, newHouse);
            return newHouse;
        }

        // เลือกบ้านที่มีคนน้อยที่สุด
        int minResidents = Integer.MAX_VALUE;
        House selectedHouse = null;

        for (Map.Entry<String, House> entry : houses.entrySet()) {
            House house = entry.getValue();
            int numberOfResidents = house.getNumberOfResidents();

            if (numberOfResidents < minResidents) {
                minResidents = numberOfResidents;
                selectedHouse = house;
            }
        }

        return selectedHouse;
    }
}
