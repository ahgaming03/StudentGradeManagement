import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.io.*;

public class FileManager {

    
    public static void saveToFile(String filePath, List<Student> studentList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Student student : studentList) {
                String studentData = String.join(",",
                        student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getPhoneNumber(),
                        student.getGender(),
                        student.getMajor(),
                        String.valueOf(student.getGpa()));
                bw.write(studentData);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFromFile(String filePath, StudentList studentList) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0].trim();
                String firstName = parts[1].trim();
                String lastName = parts[2].trim();
                String phoneNumber = parts[3].trim();
                String gender = parts[4].trim();
                String major = parts[5].trim();
                double gpa = Double.parseDouble(parts[6].trim());

                Student student = new Student(id, firstName, lastName, phoneNumber, gender, major, gpa);
                studentList.addStudent(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
