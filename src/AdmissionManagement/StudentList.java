package AdmissionManagement;

import java.util.ArrayList;
import java.util.List;

/**
 * The StudentList class is responsible for storing a list of Student objects
 * and providing methods for manipulating this list.
 */
public class StudentList {
    // The list of students in the collection
    private List<Student> students;
    private int counter = 1;

    /**
     * The StudentList class is responsible for storing a list of Student objects
     * and providing methods for manipulating this list.
     */
    public StudentList() {
        this.students = new ArrayList<>();
    }

    /**
     * The addStudent method generates a unique ID for the student and then adds
     * them to the list.
     *
     * @param student The Student object to be added.
     */
    public void addStudent(Student student) {
        do {
            student.setId(String.format("ST%05d", counter++));
        } while (isDuplicatedId(student.getId()));
        students.add(student);
    }

    /**
     * 
     * Adds a student to the student list.
     * 
     * @param student The student to be added.
     */
    public void loadStudent(Student student) {
        students.add(student);
    }

    /**
     * The updateStudent method replaces the student with the given ID in the
     * list with the updated student.
     *
     * @param id             The ID of the student to be replaced.
     * @param updatedStudent The updated Student object.
     */
    public void updateStudent(String id, Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) { // Iterate over the list
            if (students.get(i).getId().equalsIgnoreCase(id)) { // If the current student's ID matches the given ID
                students.set(i, updatedStudent); // Replace the current student with the updated student
                return; // Exit the method
            }
        }
    }

    /**
     * The removeStudent method removes the student with the given ID from the
     * list.
     *
     * @param id The ID of the student to be removed.
     */
    public void removeStudent(String id) {
        students.removeIf(student -> student.getId().equalsIgnoreCase(id)); // Remove any students from the list that
                                                                            // match the given ID
    }

    /**
     * The isValidId method checks if the student's ID is valid.
     *
     * @param id The ID to be checked.
     * @return true if the ID is valid, false otherwise.
     */
    public boolean isValidId(String id) {
        String ID = id.trim();
        if (ID.matches("^[sS][tT]\\d{5}$"))
            return true;
        return false;
    }

    /**
     * The isValidName method checks if the student's name is valid.
     *
     * @param name The name to be checked.
     * @return true if the name is valid, false otherwise.
     */
    public boolean isValidName(String name) {
        if (name.length() >= 2) {
            if (name.matches("^[a-zA-Z]+(([', -][a-zA-Z ])?[a-zA-Z]*)*$")) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * The isValidPhoneNumber method checks if the student's phone number is valid.
     *
     * @param phoneNumber The phone number to be checked.
     * @return true if the phone number is valid, false otherwise.
     */
    public boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("^[0-9]{10}$"))
            return true;
        return false;
    }

    /**
     * The isValidGpa method checks if the student's GPA is valid.
     *
     * @param gpa The GPA to be checked.
     * @return true if the GPA is valid, false otherwise.
     */
    public boolean isValidGpa(double gpa) {
        if (gpa >= 0 && gpa <= 100) {
            return true;
        }
        return false;
    }

    public boolean isValidGender(String gender) {
        if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female")) {
            return true;
        }
        return false;
    }

    public boolean isValidMajor(String major) {
        if (major.equalsIgnoreCase("IT") || major.equalsIgnoreCase("BUSINESS") || major.equalsIgnoreCase("DESIGN")) {
            return true;
        }
        return false;
    }

    /**
     * The isDuplicatedId method checks if the given ID is already in the list of
     * students.
     *
     * @param ID The ID to be checked.
     * @return true if the ID is duplicated, false otherwise.
     */
    public boolean isDuplicatedId(String ID) {
        ID = ID.trim().toUpperCase();
        if (getStudentById(ID) != null)
            return true;
        return false;
    }

    /**
     * The isDuplicatedPhoneNumber method checks if the given phone number is
     * already in the list of students.
     *
     * @param phoneNumber The phone number to be checked.
     * @return true if the phone number is duplicated, false otherwise.
     */
    public boolean isDuplicatedPhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.trim();

        if (getStudentByPhoneNumber(phoneNumber) != null)
            return true;
        return false;
    }

    /**
     * The getStudentById method finds a student in the list by their ID.
     *
     * @param id The ID of the student to be found.
     * @return The Student object if found, null otherwise.
     */
    public Student getStudentById(String id) {
        return students.stream() // Convert the list to a stream
                .filter(student -> student.getId().equalsIgnoreCase(id)) // Filter the stream to only students with the
                                                                         // matching ID
                .findFirst() // Get the first student from the filtered stream
                .orElse(null); // If there is no matching student, return null
    }

    /**
     * The getStudentByPhoneNumber method finds a student in the list by their phone
     * number.
     *
     * @param phoneNumber The phone number of the student to be found.
     * @return The Student object if found, null otherwise.
     */
    public Student getStudentByPhoneNumber(String phoneNumber) {
        return students.stream() // Convert the list to a stream
                .filter(student -> student.getPhoneNumber().equalsIgnoreCase(phoneNumber)) // Filter the stream to only
                                                                                           // students with the matching
                                                                                           // phone number
                .findFirst() // Get the first student from the filtered stream
                .orElse(null); // If there is no matching student, return null
    }

    /**
     * The getStudentByMajor method finds a student in the list by their major.
     *
     * @param major The major of the student to be found.
     * @return The Student object if found, null otherwise.
     */
    public Student getStudentByMajor(String major) {
        return students.stream() // Convert the list to a stream
                .filter(student -> student.getMajor().equalsIgnoreCase(major)) // Filter the stream to only students
                                                                               // with the matching major
                .findFirst() // Get the first student from the filtered stream
                .orElse(null); // If there is no matching student, return null
    }

    /**
     * The getStudents method returns a new list containing all the students in
     * the StudentList.
     *
     * @return A new ArrayList containing all Student objects.
     */
    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    /**
     * The setStudents method sets the students in the StudentList.
     *
     * @param students The new list of Student objects.
     */
    public void setStudents(List<Student> students) {
        this.students = new ArrayList<>(students);
    }

}
