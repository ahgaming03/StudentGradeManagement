package AdmissionManagement;

import java.util.List;
import java.io.*;

public class FileManager {

    /**
     * Saves the student data to a file.
     * Usage:
     * Provide the file path and the list of students to be saved.
     * The method writes the student data to the specified file in a comma-separated
     * format.
     * Each line in the file represents a student's information, with each field
     * separated by a comma.
     * The fields include the student's ID, first name, last name, phone number,
     * gender, major, and GPA.
     * 
     * @param filePath    The path of the file to save the student data to.
     * @param studentList The list of students to be saved.
     */
    public static void saveToFile(String filePath, List<Student> studentList) {
        if (!filePath.endsWith(".txt") && !filePath.endsWith(".csv")) {
        filePath += ".csv"; // Default to .csv if no extension is provided
    }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Student student : studentList) {
                // Convert student data to a comma-separated string
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

    /**
     * The loadFromFile method reads a text file containing student information,
     * creates Student objects for each line, validates the data and adds them
     * to a temporary StudentList. If all the student data are valid, it sets the
     * students of the provided StudentList to the students in the temporary list.
     *
     * @param filePath    The path to the text file.
     * @param studentList The StudentList object whose students will be set.
     * @return true if all student data are valid and the StudentList is updated,
     *         false otherwise.
     */
    public static boolean loadFromFile(String filePath, StudentList studentList) {
        StudentList tempStudentList = new StudentList();
        boolean valid = true;

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Split the line into parts using the comma as the delimiter
                String[] parts = line.split(",");
                String id = parts[0].trim();
                String firstName = parts[1].trim();
                String lastName = parts[2].trim();
                String phoneNumber = parts[3].trim();
                String gender = parts[4].trim();
                String major = parts[5].trim();
                double gpa = Double.parseDouble(parts[6].trim());

                Student student = new Student();
                if (tempStudentList.isValidId(id)
                        && tempStudentList.isValidName(firstName)
                        && tempStudentList.isValidName(lastName)
                        && tempStudentList.isValidPhoneNumber(phoneNumber)
                        && tempStudentList.isValidGender(gender)
                        && tempStudentList.isValidMajor(major)
                        && tempStudentList.isValidGpa(gpa)) {
                    student = new Student(id, firstName, lastName, phoneNumber, gender, major, gpa);
                } else {
                    valid = false;
                    break;
                }
                // Create a new Student object with the parsed information
                tempStudentList.addStudent(student);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (valid) {
            studentList.setStudents(tempStudentList.getStudents());
            return valid;
        }
        return valid;

    }
}
