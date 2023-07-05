package AdmissionManagement;

public class Student {
    private String id; // Student's unique ID
    private String firstName; // Student's first name
    private String lastName; // Student's last name
    private String phoneNumber; // Student's contact phone number
    private String gender; // Student's gender
    private String major; // Student's major of study
    private double gpa; // Student's Grade Point Average (GPA)

    /**
     * Constructs a new Student object with default attributes.
     */
    public Student() {
        this.id = "";
        this.firstName = "";
        this.lastName = "";
        this.phoneNumber = "";
        this.gender = "";
        this.major = "";
        this.gpa = 0.0;
    }

    /**
     * Constructs a new Student object with the given attributes.
     *
     * @param firstName   The first name of the student.
     * @param lastName    The last name of the student.
     * @param phoneNumber The contact phone number of the student.
     * @param gender      The gender of the student.
     * @param major       The major of study of the student.
     * @param gpa         The Grade Point Average (GPA) of the student.
     */
    public Student(String firstName, String lastName, String phoneNumber, String gender, String major,
            double gpa) {
        this.firstName = capitalizeEachWord(firstName).trim();
        this.lastName = capitalizeEachWord(lastName).trim();
        this.phoneNumber = phoneNumber.trim();
        this.gender = capitalizeEachWord(gender).trim();
        this.major = major.toUpperCase().trim();
        this.gpa = gpa;
    }

    /**
     * Constructs a new Student object with the given attributes including the ID.
     *
     * @param id          The unique ID of the student.
     * @param firstName   The first name of the student.
     * @param lastName    The last name of the student.
     * @param phoneNumber The contact phone number of the student.
     * @param gender      The gender of the student.
     * @param major       The major of study of the student.
     * @param gpa         The Grade Point Average (GPA) of the student.
     */
    public Student(String id, String firstName, String lastName, String phoneNumber, String gender, String major,
            double gpa) {
        this.id = id.toUpperCase().trim();
        this.firstName = capitalizeEachWord(firstName).trim();
        this.lastName = capitalizeEachWord(lastName).trim();
        this.phoneNumber = phoneNumber.trim();
        this.gender = capitalizeEachWord(gender).trim();
        this.major = major.toUpperCase().trim();
        this.gpa = gpa;
    }

    /**
     * Retrieves the unique ID of the student.
     *
     * @return The ID of the student.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique ID of the student.
     *
     * @param id The ID of the student.
     */
    public void setId(String id) {
        this.id = id.toUpperCase().trim();
    }

    /**
     * Retrieves the first name of the student.
     *
     * @return The first name of the student.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the student.
     *
     * @param firstName The first name of the student.
     */
    public void setFirstName(String firstName) {
        this.firstName = capitalizeEachWord(firstName).trim();
    }

    /**
     * Retrieves the last name of the student.
     *
     * @return The last name of the student.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the student.
     *
     * @param lastName The last name of the student.
     */
    public void setLastName(String lastName) {
        this.lastName = capitalizeEachWord(lastName).trim();
    }

    /**
     * Retrieves the full name of the student.
     *
     * @return The full name of the student.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the student.
     *
     * @param phoneNumber The phone number of the student.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber.trim();
    }

     /**
     * Retrieves the gender of the student.
     *
     * @return The gender of the student.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender of the student.
     *
     * @param gender The gender to set.
     */
    public void setGender(String gender) {
        this.gender = capitalizeEachWord(gender).trim();
    }

    /**
     * Retrieves the major of study of the student.
     *
     * @return The major of study of the student.
     */
    public String getMajor() {
        return major;
    }

    /**
     * Sets the major of study of the student.
     *
     * @param major The major of study of the student.
     */
    public void setMajor(String major) {
        this.major = major.toUpperCase().trim();
    }

    /**
     * Retrieves the Grade Point Average (GPA) of the student.
     *
     * @return The Grade Point Average (GPA) of the student.
     */
    public double getGpa() {
        return gpa;
    }

    /**
     * Sets the Grade Point Average (GPA) of the student.
     *
     * @param gpa The Grade Point Average (GPA) of the student.
     */
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

     /**
     * Capitalizes the first letter of each word in a string.
     *
     * @param str The string to capitalize.
     * @return The capitalized string.
     */
    private String capitalizeEachWord(String str) {
        String[] words = str.split(" "); // Split the string into an array of words
        String capitalizedStr = ""; // Create an empty string to store the capitalized string
        for (String word : words) { // Loop through each word in the array
            String firstLetter = word.substring(0, 1); // Get the first letter of the word
            String restOfWord = word.substring(1); // Get the rest of the word
            capitalizedStr += firstLetter.toUpperCase() + restOfWord.toLowerCase() + " "; // Add the capitalized
                                                                                          // first letter and the rest
                                                                                          // of the word to the
                                                                                          // capitalized string
        }
        return capitalizedStr.trim(); // Return the capitalized string with no leading or trailing whitespace
    }

}