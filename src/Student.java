
public class Student {
    private String id; // Student's unique ID
    private String firstName; // Student's first name
    private String lastName; // Student's last name
    private String phoneNumber; // Student's contact phone number
    private String gender; // Student's gender
    private String major; // Student's major of study
    private double gpa; // Student's Grade Point Average (GPA)

    // Constructor
    public Student(String firstName, String lastName, String phoneNumber, String gender, String major,
            double gpa) {
        this.firstName = capitalizeEachWord(firstName).trim();
        this.lastName = capitalizeEachWord(lastName).trim();
        this.phoneNumber = phoneNumber.trim();
        this.gender = capitalizeEachWord(gender).trim();
        this.major = major.toUpperCase().trim();
        this.gpa = gpa;
    }

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
    
    // Getters and setters for each attribute...

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id.toUpperCase().trim();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = capitalizeEachWord(firstName).trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = capitalizeEachWord(lastName).trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = capitalizeEachWord(gender).trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major.toUpperCase().trim();
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    // method capitalizes the first letter of each word in a string
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