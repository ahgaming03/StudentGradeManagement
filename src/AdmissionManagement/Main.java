package AdmissionManagement;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Font;
import java.awt.Point;
import java.awt.Color;
import java.awt.Component;
import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.JFormattedTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.RowFilter;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import javax.swing.filechooser.FileFilter;
import javax.swing.text.MaskFormatter;

public class Main extends JFrame {

	private JPanel contentPane;

	// Storage
	private StudentList studentList = new StudentList();
	private File selectedFile;

	// Table student
	private JTable tableStudent;
	private DefaultTableModel tableModelStudent;
	private TableRowSorter<DefaultTableModel> sorterStudent;

	// Add new student
	private JTextField textField_Add_FirstName;
	private JTextField textField_Add_LastName;
	private JFormattedTextField formatTextField_Add_PhoneNumber;
	private JComboBox comboBox_Add_Gender;
	private JComboBox comboBox_Add_Major;
	private JTextField textField_Add_GPA;

	// Information of a student (Update or remove)
	private JLabel label_Info_ID_Display;
	private JTextField textField_Info_FirstName;
	private JTextField textField_Info_LastName;
	private JFormattedTextField formatTextField_Info_PhoneNumber;
	private JComboBox comboBox_Info_Gender;
	private JComboBox comboBox_Info_Major;
	private JTextField textField_Info_GPA;
	private JButton button_Update;
	private JButton button_Remove;

	// Filter
	private JTextField textFieldSearch;
	private JCheckBox chckbxMale;
	private JCheckBox chckbxFemale;
	private JCheckBox chckbxIT;
	private JCheckBox chckbxBusiness;
	private JCheckBox chckbxDesign;
	private JPanel panelFilter;

	// Report
	private JLabel labelNoS_All_Count;
	private JLabel labelAccept_All_Count;
	private JLabel labelReject_All_Count;
	private JLabel labelNoS_IT_Count;
	private JLabel labelAccept_IT_Count;
	private JLabel labelReject_IT_Count;
	private JLabel labelNoS_Business_Count;
	private JLabel labelAccept_Business_Count;
	private JLabel labelReject_Business_Count;
	private JLabel labelNoS_Design_Count;
	private JLabel labelAccept_Design_Count;
	private JLabel labelReject_Design_Count;

	// Table report
	private JTable tableReport;
	private DefaultTableModel tableModelReport;
	private TableRowSorter<DefaultTableModel> sorterReport;

	// Report filter
	private JCheckBox chckbxMale_Report;
	private JCheckBox chckbxFemale_Report;
	private JCheckBox chckbxIT_Report;
	private JCheckBox chckbxBusiness_Report;
	private JCheckBox chckbxDesign_Report;
	private JCheckBox chckbxAccepted_Report;
	private JCheckBox chckbxRejected_Report;
	private JTextField textFieldSearch_Report;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					centreWindow(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			private void centreWindow(Main frame) {
				// Get centre point of screen
				Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();

				// Get width and height of screen
				int windowWidth = frame.getWidth();
				int windowHeight = frame.getHeight();

				// Set frame new position to centre of screen
				frame.setBounds(center.x - windowWidth / 2, center.y - windowHeight / 2, windowWidth, windowHeight);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		// Setting properties for the JFrame
		setResizable(false);
		setTitle("Admission Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);

		/**
		 * Creating a menu bar for the JFrame
		 */
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);

		/**
		 * Creates a "Open" menu item and assigns an ActionListener to it
		 * The ActionListener opens a JFileChooser when the menu item is clicked
		 */
		JMenuItem itemOpen = new JMenuItem("Open");
		itemOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Create a file chooser
				JFileChooser fileChooser = new JFileChooser();

				// Setting the current directory of the file chooser to the user's home
				// directory
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

				// Setting a file filter to only allow .txt files to be selected
				fileChooser.setFileFilter(new FileFilter() {
					@Override
					public String getDescription() {
						return "TXT file (*.txt)";
					}

					@Override
					public boolean accept(File f) {
						if (f.isDirectory()) {
							return true;
						} else {
							return f.getName().toLowerCase().endsWith(".txt");
						}
					}
				});

				// Another file filter, this one for .csv files
				fileChooser.setFileFilter(new FileFilter() {
					@Override
					public String getDescription() {
						return "CSV file (*.csv)";
					}

					@Override
					public boolean accept(File f) {
						if (f.isDirectory()) {
							return true;
						} else {
							return f.getName().toLowerCase().endsWith(".csv");
						}
					}
				});

				// Display the file chooser dialog and get the user's response
				int result = fileChooser.showOpenDialog(null);

				// If the user selected a file
				if (result == JFileChooser.APPROVE_OPTION) {
					// Get the selected file
					selectedFile = fileChooser.getSelectedFile();

					// Load the students from the selected file
					studentList = new StudentList();
					if (FileManager.loadFromFile(selectedFile.getAbsolutePath(), studentList) == false) {
						JOptionPane.showMessageDialog(null, "Invalid file format", "Error", JOptionPane.ERROR_MESSAGE);
					}

					// Display the students in the table
					loadTable();
				}

			}
		});
		// Set an accelerator key for the menu item (Ctrl+O)
		itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		menuFile.add(itemOpen);

		/**
		 * Creates a "Save" menu item and assigns an ActionListener to it
		 * The ActionListener saves the student list to the previously selected file
		 * when the menu item is clicked.
		 * If no file was previously selected, it prompts the user to choose a file.
		 */
		JMenuItem itemSave = new JMenuItem("Save"); // Creating a new JMenuItem titled "Save"
		itemSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Called when "Save" is clicked
				// If a file has been previously selected
				if (selectedFile != null) {
					// Save the student list to the previously selected file
					FileManager.saveToFile(selectedFile.getAbsolutePath(), studentList.getStudents());
				} else {
					// Create a file chooser
					JFileChooser fileChooser = new JFileChooser();

					// Set the current directory to the user's home directory
					fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

					// Set a file filter to only accept .csv files
					fileChooser.setFileFilter(new FileFilter() {
						@Override
						public String getDescription() {
							return "CSV file (*.csv)";
						}

						@Override
						public boolean accept(File f) {
							if (f.isDirectory()) {
								return true;
							} else {
								return f.getName().toLowerCase().endsWith(".csv");
							}
						}
					});

					// Show the file chooser dialog and get the result
					int result = fileChooser.showSaveDialog(null);

					// If a file was chosen
					if (result == JFileChooser.APPROVE_OPTION) {
						// Get the selected file
						selectedFile = fileChooser.getSelectedFile();

						// Save the students to the selected file
						FileManager.saveToFile(selectedFile.getAbsolutePath(), studentList.getStudents());
					}
				}
				try {
					// Show a message dialog indicating the file has been saved
					JOptionPane.showMessageDialog(null, "Saved file: " + selectedFile.getName(), "Save file",
							JOptionPane.DEFAULT_OPTION);
				} catch (NullPointerException e1) {
					// Do nothing if no file is selected
					return;
				}
			}
		});
		// Set an accelerator key for the menu item (Ctrl+S)
		itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		menuFile.add(itemSave);

		/**
		 * Creates a "Save as..." menu item and assigns an ActionListener to it
		 * The ActionListener saves the student list to a new file when the menu item
		 * is clicked.
		 */
		JMenuItem itemSaveAs = new JMenuItem("Save as...");
		itemSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Create a file chooser
				JFileChooser fileChooser = new JFileChooser();

				// Set the current directory to the user's home directory
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

				// Set a file filter to only accept .csv files
				fileChooser.setFileFilter(new FileFilter() {
					@Override
					public String getDescription() {
						return "CSV file (*.csv)";
					}

					@Override
					public boolean accept(File f) {
						if (f.isDirectory()) {
							return true;
						} else {
							return f.getName().toLowerCase().endsWith(".csv");
						}
					}
				});

				// Show the file chooser dialog
				int result = fileChooser.showSaveDialog(null);

				// If the user selected a file
				if (result == JFileChooser.APPROVE_OPTION) {
					// Get the selected file
					selectedFile = fileChooser.getSelectedFile();

					// Save the students to the selected file
					FileManager.saveToFile(selectedFile.getAbsolutePath() + ".csv", studentList.getStudents());
					try {
						// Show a message dialog indicating the file has been saved
						JOptionPane.showMessageDialog(null, "Saved file: " + selectedFile.getName(), "Save file",
								JOptionPane.DEFAULT_OPTION);
					} catch (NullPointerException e1) {
						// Do nothing if no file is selected
						return;
					}
				}
			}
		});
		menuFile.add(itemSaveAs);

		JSeparator separator = new JSeparator();
		menuFile.add(separator);

		/**
		 * Creates an "Exit" menu item and assigns an ActionListener to it
		 * The ActionListener exits the program when the menu item is clicked
		 */
		JMenuItem itemExit = new JMenuItem("Exit");
		itemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuFile.add(itemExit);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(new BorderLayout(0, 0));
		JTabbedPane tabbedPaneStudent = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPaneStudent);

		/**
		 * Tab student management
		 */
		JPanel panelStudentManagement = new JPanel();
		tabbedPaneStudent.addTab("Student Management", null, panelStudentManagement, null);
		panelStudentManagement.setLayout(null);

		/**
		 * Logo
		 */
		ImageIcon imageIcon = new ImageIcon("./img/Logo.jpg");
		JLabel labelLogo = new JLabel();
		labelLogo.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelLogo.setBounds(33, 22, 160, 160);
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(160, 160, Image.SCALE_SMOOTH);
		labelLogo.setIcon(new ImageIcon(newimg));
		panelStudentManagement.add(labelLogo);

		/**
		 * Table student
		 */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 328, 960, 282);
		panelStudentManagement.add(scrollPane);

		tableModelStudent = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Disable editing for all cells
			}
		};

		// Add columns to the table model.
		tableModelStudent.addColumn("ID");
		tableModelStudent.addColumn("First Name");
		tableModelStudent.addColumn("Last Name");
		tableModelStudent.addColumn("Phone number");
		tableModelStudent.addColumn("Gender");
		tableModelStudent.addColumn("Major");
		tableModelStudent.addColumn("GPA");

		tableStudent = new JTable();
		tableStudent.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableStudent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Add a mouse listener to handle double-click events.
		tableStudent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					// Enable the update and remove buttons
					button_Update.setEnabled(true);
					button_Remove.setEnabled(true);

					// Get the selected row and its data
					int row = tableStudent.getSelectedRow();
					String id = (String) tableStudent.getValueAt(row, 0);
					String firstName = (String) tableStudent.getValueAt(row, 1);
					String lastName = (String) tableStudent.getValueAt(row, 2);
					String phoneNumber = (String) tableStudent.getValueAt(row, 3);
					String gender = (String) tableStudent.getValueAt(row, 4);
					String major = (String) tableStudent.getValueAt(row, 5);
					double gpa = (double) tableStudent.getValueAt(row, 6);

					// Display the data of the selected row
					label_Info_ID_Display.setText(id);
					textField_Info_FirstName.setText(firstName);
					textField_Info_LastName.setText(lastName);
					formatTextField_Info_PhoneNumber.setText(phoneNumber);
					comboBox_Info_Gender.setSelectedItem(gender);
					comboBox_Info_Major.setSelectedItem(major);
					textField_Info_GPA.setText(String.valueOf(gpa));

				}
			}
		});

		tableStudent.setModel(tableModelStudent);
		// Set the min and max width for each column.
		tableStudent.getColumnModel().getColumn(0).setMinWidth(75);
		tableStudent.getColumnModel().getColumn(0).setMaxWidth(75);
		tableStudent.getColumnModel().getColumn(1).setMinWidth(100);
		tableStudent.getColumnModel().getColumn(1).setMaxWidth(200);
		tableStudent.getColumnModel().getColumn(2).setMinWidth(100);
		tableStudent.getColumnModel().getColumn(2).setMaxWidth(200);
		tableStudent.getColumnModel().getColumn(3).setMinWidth(100);
		tableStudent.getColumnModel().getColumn(3).setMaxWidth(100);
		tableStudent.getColumnModel().getColumn(4).setMinWidth(75);
		tableStudent.getColumnModel().getColumn(4).setMaxWidth(75);
		tableStudent.getColumnModel().getColumn(5).setMinWidth(75);
		tableStudent.getColumnModel().getColumn(6).setMinWidth(50);
		tableStudent.getColumnModel().getColumn(6).setMaxWidth(75);
		scrollPane.setViewportView(tableStudent);

		/**
		 * Add new student
		 */
		JPanel panelAddNewStudent = new JPanel();
		panelAddNewStudent.setBounds(571, 22, 280, 216);
		panelStudentManagement.add(panelAddNewStudent);
		panelAddNewStudent.setBorder(
				new TitledBorder(null, "Add new student", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAddNewStudent.setLayout(null);

		// First name label and text field
		JLabel label_Add_FirstName = new JLabel("First name:");
		label_Add_FirstName.setBounds(10, 21, 93, 14);
		panelAddNewStudent.add(label_Add_FirstName);

		textField_Add_FirstName = new JTextField();
		textField_Add_FirstName.setBounds(113, 18, 150, 20);
		panelAddNewStudent.add(textField_Add_FirstName);
		textField_Add_FirstName.setColumns(10);

		// Last name label and text field
		JLabel label_Add_LastName = new JLabel("Last name:");
		label_Add_LastName.setBounds(10, 46, 93, 14);
		panelAddNewStudent.add(label_Add_LastName);

		textField_Add_LastName = new JTextField();
		textField_Add_LastName.setColumns(10);
		textField_Add_LastName.setBounds(113, 43, 150, 20);
		panelAddNewStudent.add(textField_Add_LastName);

		// Phone number label and formatted text field
		JLabel label_Add_PhoneNumber = new JLabel("Phone number:");
		label_Add_PhoneNumber.setBounds(10, 71, 93, 14);
		panelAddNewStudent.add(label_Add_PhoneNumber);

		// We use a formatted text field to enforce the input format.
		try {
			MaskFormatter formatter = new MaskFormatter("###-###-####");
			formatter.setPlaceholderCharacter('_');
			formatTextField_Add_PhoneNumber = new JFormattedTextField(formatter);
			formatTextField_Add_PhoneNumber.setFocusLostBehavior(JFormattedTextField.COMMIT);

			formatTextField_Add_PhoneNumber.setColumns(10);
			formatTextField_Add_PhoneNumber.setBounds(113, 68, 150, 20);
			panelAddNewStudent.add(formatTextField_Add_PhoneNumber);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		// Gender label and combo box
		JLabel label_Add_Gender = new JLabel("Gender:");
		label_Add_Gender.setBounds(10, 96, 93, 14);
		panelAddNewStudent.add(label_Add_Gender);

		comboBox_Add_Gender = new JComboBox();
		comboBox_Add_Gender.setModel(new DefaultComboBoxModel(new String[] { "Male", "Female" }));
		comboBox_Add_Gender.setBounds(113, 92, 150, 22);
		panelAddNewStudent.add(comboBox_Add_Gender);

		// Major label and combo box
		JLabel label_Add_Major = new JLabel("Major:");
		label_Add_Major.setBounds(10, 121, 93, 14);
		panelAddNewStudent.add(label_Add_Major);

		comboBox_Add_Major = new JComboBox();
		comboBox_Add_Major.setModel(new DefaultComboBoxModel(new String[] { "IT", "BUSINESS", "DESIGN" }));
		comboBox_Add_Major.setBounds(113, 117, 150, 22);
		panelAddNewStudent.add(comboBox_Add_Major);

		// GPA label and text field
		JLabel label_Add_GPA = new JLabel("GPA:");
		label_Add_GPA.setBounds(10, 146, 93, 14);
		panelAddNewStudent.add(label_Add_GPA);

		textField_Add_GPA = new JTextField();
		textField_Add_GPA.setColumns(10);
		textField_Add_GPA.setBounds(113, 143, 150, 20);
		panelAddNewStudent.add(textField_Add_GPA);

		// Add button
		JButton button_Add = new JButton("Add");
		// Add an action listener to the button.
		button_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String firstName = textField_Add_FirstName.getText();
				String lastName = textField_Add_LastName.getText();
				String phoneNumber = formatTextField_Add_PhoneNumber.getText().replaceAll("-", "");
				String gender = (String) comboBox_Add_Gender.getSelectedItem();
				String major = (String) comboBox_Add_Major.getSelectedItem();
				String gpa = textField_Add_GPA.getText();

				boolean valid = true;
				String msg = "";

				if (studentList.isValidName(firstName) == false) {
					valid = false;
					msg += "First name is invalid.\n";
				}
				if (studentList.isValidName(lastName) == false) {
					valid = false;
					msg += "Last name is invalid.\n";
				}
				if (studentList.isValidPhoneNumber(phoneNumber) == false) {
					valid = false;
					msg += "Phone number is invalid.\n";
				} else if (studentList.isDuplicatedPhoneNumber(phoneNumber)) {
					valid = false;
					msg += "Phone number is duplicated.\n";
				}
				try {
					if (studentList.isValidGpa(Double.parseDouble(gpa)) == false) {
						valid = false;
						msg += "GPA is invalid.\n";
					}
				} catch (Exception e1) {
					valid = false;
					msg += "GPA is invalid.\n";
				}

				if (valid == true) {
					Student student = new Student(firstName, lastName, phoneNumber, gender, major,
							Double.parseDouble(gpa));
					studentList.addStudent(student);

					// Load student table
					loadTable();

					JOptionPane.showMessageDialog(null, "Add student successfully.\nStudent id: " + student.getId());

					textField_Add_FirstName.setText("");
					textField_Add_LastName.setText("");
					formatTextField_Add_PhoneNumber.setText("");
					comboBox_Add_Gender.setSelectedIndex(0);
					comboBox_Add_Major.setSelectedIndex(0);
					textField_Add_GPA.setText("");
				} else {
					JOptionPane.showMessageDialog(null, msg);
				}

			}
		});
		button_Add.setBounds(174, 182, 89, 23);
		panelAddNewStudent.add(button_Add);

		/**
		 * Information of a student
		 */
		JPanel panelStudentInfo = new JPanel();
		panelStudentInfo.setBounds(226, 22, 312, 250);
		panelStudentManagement.add(panelStudentInfo);
		panelStudentInfo.setBorder(
				new TitledBorder(null, "Student information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelStudentInfo.setLayout(null);

		// Label and tex field for displaying ID
		JLabel label_Info_ID = new JLabel("ID:");
		label_Info_ID.setBounds(10, 24, 93, 14);
		panelStudentInfo.add(label_Info_ID);

		label_Info_ID_Display = new JLabel();
		label_Info_ID_Display.setBackground(new Color(240, 240, 240));
		label_Info_ID_Display.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_Info_ID_Display.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_Info_ID_Display.setBounds(113, 21, 150, 20);
		panelStudentInfo.add(label_Info_ID_Display);

		// Label and text field for first name
		JLabel label_Info_FirstName = new JLabel("First name:");
		label_Info_FirstName.setBounds(10, 49, 70, 14);
		panelStudentInfo.add(label_Info_FirstName);

		textField_Info_FirstName = new JTextField();
		textField_Info_FirstName.setBounds(113, 46, 150, 20);
		panelStudentInfo.add(textField_Info_FirstName);
		textField_Info_FirstName.setColumns(10);

		// Label and text field for last name
		JLabel label_Info_LastName = new JLabel("Last name:");
		label_Info_LastName.setBounds(10, 74, 70, 14);
		panelStudentInfo.add(label_Info_LastName);

		textField_Info_LastName = new JTextField();
		textField_Info_LastName.setBounds(113, 71, 150, 20);
		panelStudentInfo.add(textField_Info_LastName);
		textField_Info_LastName.setColumns(10);

		// Label and formatted text field for phone number
		JLabel label_Info_PhoneNumber = new JLabel("Phone number:");
		label_Info_PhoneNumber.setBounds(10, 99, 93, 14);
		panelStudentInfo.add(label_Info_PhoneNumber);

		// We use a formatted text field to enforce the input format.
		try {
			MaskFormatter formatter = new MaskFormatter("###-###-####");
			formatter.setPlaceholderCharacter('_');
			formatTextField_Info_PhoneNumber = new JFormattedTextField(formatter);
			formatTextField_Info_PhoneNumber.setFocusLostBehavior(JFormattedTextField.COMMIT);

			formatTextField_Info_PhoneNumber.setColumns(10);
			formatTextField_Info_PhoneNumber.setBounds(113, 96, 150, 20);
			panelStudentInfo.add(formatTextField_Info_PhoneNumber);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		// Label and Combo box for Gender
		JLabel label_Info_Gender = new JLabel("Gender:");
		label_Info_Gender.setBounds(10, 124, 93, 14);
		panelStudentInfo.add(label_Info_Gender);

		comboBox_Info_Gender = new JComboBox();
		comboBox_Info_Gender.setModel(new DefaultComboBoxModel(new String[] { "Male", "Female" }));
		comboBox_Info_Gender.setBounds(113, 120, 150, 22);
		panelStudentInfo.add(comboBox_Info_Gender);

		// Label and Combo box for Major
		JLabel label_Info_Major = new JLabel("Major:");
		label_Info_Major.setBounds(10, 149, 70, 14);
		panelStudentInfo.add(label_Info_Major);

		comboBox_Info_Major = new JComboBox();
		comboBox_Info_Major.setBounds(113, 145, 150, 22);
		panelStudentInfo.add(comboBox_Info_Major);
		comboBox_Info_Major.setModel(new DefaultComboBoxModel(new String[] { "IT", "BUSINESS", "DESIGN" }));

		// Label and text field for GPA
		JLabel label_Info_GPA = new JLabel("GPA:");
		label_Info_GPA.setBounds(10, 174, 70, 14);
		panelStudentInfo.add(label_Info_GPA);

		textField_Info_GPA = new JTextField();
		textField_Info_GPA.setBounds(113, 171, 150, 20);
		panelStudentInfo.add(textField_Info_GPA);
		textField_Info_GPA.setColumns(10);

		// Update buttons
		button_Update = new JButton("Update");
		button_Update.setEnabled(false);
		// Add an action listener to the button.
		button_Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = label_Info_ID_Display.getText();
				String firstName = textField_Info_FirstName.getText();
				String lastName = textField_Info_LastName.getText();
				String phoneNumber = formatTextField_Info_PhoneNumber.getText().replaceAll("-", "");
				String gender = (String) comboBox_Info_Gender.getSelectedItem();
				String major = (String) comboBox_Info_Major.getSelectedItem();
				String gpa = textField_Info_GPA.getText();

				boolean valid = true;
				String msg = "";

				if (studentList.isValidName(firstName) == false) {
					valid = false;
					msg += "First name is invalid.\n";
				}
				if (studentList.isValidName(lastName) == false) {
					valid = false;
					msg += "Last name is invalid.\n";
				}
				if (studentList.isValidPhoneNumber(phoneNumber) == false) {
					valid = false;
					msg += "Phone number is invalid.\n";
				} else if (studentList.isDuplicatedPhoneNumber(phoneNumber)
						&& id != studentList.getStudentByPhoneNumber(phoneNumber).getId()) {
					valid = false;
					msg += "Phone number is duplicated.\n";
				}
				try {
					if (studentList.isValidGpa(Double.parseDouble(gpa)) == false) {
						valid = false;
						msg += "GPA is invalid.\n";
					}
				} catch (Exception e1) {
					valid = false;
					msg += "GPA is invalid.\n";
				}

				if (valid == true) {
					Student student = new Student(id, firstName, lastName, phoneNumber, gender, major,
							Double.parseDouble(gpa));
					studentList.updateStudent(id, student);
					loadTable();

					label_Info_ID_Display.setText("");
					textField_Info_FirstName.setText("");
					textField_Info_LastName.setText("");
					formatTextField_Info_PhoneNumber.setText("");
					comboBox_Info_Gender.setSelectedIndex(0);
					comboBox_Info_Major.setSelectedIndex(0);
					textField_Info_GPA.setText("");
					button_Update.setEnabled(false);
					button_Remove.setEnabled(false);
					JOptionPane.showMessageDialog(null, "Student " + id + " updated successfully!");

				} else {
					JOptionPane.showMessageDialog(null, msg);
				}
			}
		});
		button_Update.setBounds(10, 215, 89, 23);
		panelStudentInfo.add(button_Update);

		// Remove button
		button_Remove = new JButton("Remove");
		button_Remove.setEnabled(false);
		// Add an action listener to the button.
		button_Remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String id = label_Info_ID_Display.getText();
				studentList.removeStudent(id);
				loadTable();
				label_Info_ID_Display.setText("");
				textField_Info_FirstName.setText("");
				textField_Info_LastName.setText("");
				formatTextField_Info_PhoneNumber.setText("");
				comboBox_Info_Gender.setSelectedIndex(0);
				comboBox_Info_Major.setSelectedIndex(0);
				textField_Info_GPA.setText("");
				button_Remove.setEnabled(false);
				button_Update.setEnabled(false);
				JOptionPane.showMessageDialog(null, "Student " + id + " removed successfully!");

			}
		});
		button_Remove.setBounds(113, 215, 89, 23);
		panelStudentInfo.add(button_Remove);

		// Cancel button
		JButton button_Cancel = new JButton("Cancel");
		// Add an action listener to the button.
		button_Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				label_Info_ID_Display.setText("");
				textField_Info_FirstName.setText("");
				textField_Info_LastName.setText("");
				formatTextField_Info_PhoneNumber.setText("");
				comboBox_Info_Gender.setSelectedIndex(0);
				comboBox_Info_Major.setSelectedIndex(0);
				textField_Info_GPA.setText("");
				button_Update.setEnabled(false);
				button_Remove.setEnabled(false);
			}
		});
		button_Cancel.setBounds(212, 215, 89, 23);
		panelStudentInfo.add(button_Cancel);

		// Search
		textFieldSearch = new JTextField();
		textFieldSearch.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textFieldSearch.setBounds(844, 275, 160, 40);
		panelStudentManagement.add(textFieldSearch);
		// Add a document listener to the search text field
		textFieldSearch.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				filterTable();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				filterTable();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				filterTable();
			}
		});
		textFieldSearch.setColumns(10);

		/**
		 * Filter
		 */
		panelFilter = new JPanel();
		panelFilter.setBorder(new TitledBorder(null, "Filter", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFilter.setBounds(884, 22, 130, 216);
		panelStudentManagement.add(panelFilter);
		panelFilter.setLayout(null);

		// Label for gender filter
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(10, 22, 46, 14);
		panelFilter.add(lblGender);

		// Check box for Male gender
		chckbxMale = new JCheckBox("Male");
		chckbxMale.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				filterTable();
			}
		});
		chckbxMale.setBounds(10, 43, 97, 23);
		panelFilter.add(chckbxMale);

		// Check box for Female gender
		chckbxFemale = new JCheckBox("Female");
		chckbxFemale.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				filterTable();
			}
		});
		chckbxFemale.setBounds(10, 69, 97, 23);
		panelFilter.add(chckbxFemale);

		// Label for major filter
		JLabel lblMajor = new JLabel("Major");
		lblMajor.setBounds(10, 99, 46, 14);
		panelFilter.add(lblMajor);

		// Check box for IT major
		chckbxIT = new JCheckBox("IT");
		chckbxIT.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				filterTable();
			}
		});
		chckbxIT.setBounds(10, 120, 97, 23);
		panelFilter.add(chckbxIT);

		// Check box for BUSINESS major
		chckbxBusiness = new JCheckBox("BUSINESS");
		chckbxBusiness.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				filterTable();
			}
		});
		chckbxBusiness.setBounds(10, 146, 97, 23);
		panelFilter.add(chckbxBusiness);

		// Check box for DESIGN major
		chckbxDesign = new JCheckBox("DESIGN");
		chckbxDesign.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				filterTable();
			}
		});
		chckbxDesign.setBounds(10, 172, 97, 23);
		panelFilter.add(chckbxDesign);

		/**
		 * Tab report
		 */
		JPanel panelReport = new JPanel();
		tabbedPaneStudent.addTab("Report", null, panelReport, null);
		panelReport.setLayout(null);

		// Create a scroll pane for the report table
		JScrollPane scrollPaneReport = new JScrollPane();
		scrollPaneReport.setBounds(25, 195, 998, 415);
		panelReport.add(scrollPaneReport);

		// Create the table model for the report table
		tableModelReport = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Disable editing for all cells
			}
		};

		// Add columns to the table model.
		tableModelReport.addColumn("ID");
		tableModelReport.addColumn("First Name");
		tableModelReport.addColumn("Last Name");
		tableModelReport.addColumn("Phone number");
		tableModelReport.addColumn("Gender");
		tableModelReport.addColumn("Major");
		tableModelReport.addColumn("GPA");
		tableModelReport.addColumn("Status");

		// Create the report table
		tableReport = new JTable() {
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				Object value = getValueAt(row, 7);
				if (column == 7) {
					if ("Accepted".equals(value)) {
						c.setForeground(Color.GREEN);
						c.setFont(c.getFont().deriveFont(Font.BOLD));
					} else if ("Rejected".equals(value)) {
						c.setForeground(Color.RED);
						c.setFont(c.getFont().deriveFont(Font.BOLD));
					} else {
						c.setForeground(Color.BLACK);
						c.setFont(c.getFont().deriveFont(Font.PLAIN));
					}
				} else {
					c.setForeground(Color.BLACK);
					c.setFont(c.getFont().deriveFont(Font.PLAIN));
				}
				return c;
			}
		};

		tableModelReport.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				// Create the table model for the report table
				chckbxMale_Report.setSelected(false);
				chckbxFemale_Report.setSelected(false);
				chckbxIT_Report.setSelected(false);
				chckbxBusiness_Report.setSelected(false);
				chckbxDesign_Report.setSelected(false);
				chckbxAccepted_Report.setSelected(false);
				chckbxRejected_Report.setSelected(false);
				textFieldSearch_Report.setText("");

				// Update statistics for all majors
				int rowCount = tableReport.getRowCount();
				labelNoS_All_Count.setText(String.valueOf(rowCount));
				int acceptCount = 0;
				int rejectCount = 0;
				for (int i = 0; i < rowCount; i++) {
					String status = (String) tableReport.getValueAt(i, 7);
					if (status.equals("Accepted")) {
						acceptCount++;
					} else if (status.equals("Rejected")) {
						rejectCount++;
					}
				}
				labelAccept_All_Count.setText(String.valueOf(acceptCount));
				labelReject_All_Count.setText(String.valueOf(rejectCount));

				// Update statistics for IT major
				int rowCountIT = tableReport.getRowCount();
				labelNoS_IT_Count.setText(String.valueOf(rowCountIT));
				int acceptCountIT = 0;
				int rejectCountIT = 0;
				for (int i = 0; i < rowCountIT; i++) {
					String status = (String) tableReport.getValueAt(i, 7);
					if (status.equals("Accepted")) {
						acceptCountIT++;
					} else if (status.equals("Rejected")) {
						rejectCountIT++;
					}
				}
				labelAccept_IT_Count.setText(String.valueOf(acceptCountIT));
				labelReject_IT_Count.setText(String.valueOf(rejectCountIT));

				// Update statistics for BUSINESS major
				int rowCountBUSINESS = tableReport.getRowCount();
				labelNoS_Business_Count.setText(String.valueOf(rowCountBUSINESS));
				int acceptCountBusiness = 0;
				int rejectCountBusiness = 0;
				for (int i = 0; i < rowCountBUSINESS; i++) {
					String status = (String) tableReport.getValueAt(i, 7);
					if (status.equals("Accepted")) {
						acceptCountBusiness++;
					} else if (status.equals("Rejected")) {
						rejectCountBusiness++;
					}
				}
				labelAccept_Business_Count.setText(String.valueOf(acceptCountBusiness));
				labelReject_Business_Count.setText(String.valueOf(rejectCountBusiness));

				// Update statistics for DESIGN major
				int rowCountDESIGN = tableReport.getRowCount();
				labelNoS_Design_Count.setText(String.valueOf(rowCountDESIGN));
				int acceptCountDesign = 0;
				int rejectCountDesign = 0;
				for (int i = 0; i < rowCountDESIGN; i++) {
					String status = (String) tableReport.getValueAt(i, 7);
					if (status.equals("Accepted")) {
						acceptCountDesign++;
					} else if (status.equals("Rejected")) {
						rejectCountDesign++;
					}
				}
				labelAccept_Design_Count.setText(String.valueOf(acceptCountDesign));
				labelReject_Design_Count.setText(String.valueOf(rejectCountDesign));
			}
		});

		tableReport.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableReport.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableReport.setModel(tableModelReport);
		tableReport.getColumnModel().getColumn(0).setMinWidth(75);
		tableReport.getColumnModel().getColumn(0).setMaxWidth(75);
		tableReport.getColumnModel().getColumn(1).setMinWidth(100);
		tableReport.getColumnModel().getColumn(1).setMaxWidth(200);
		tableReport.getColumnModel().getColumn(2).setMinWidth(100);
		tableReport.getColumnModel().getColumn(2).setMaxWidth(200);
		tableReport.getColumnModel().getColumn(3).setMinWidth(100);
		tableReport.getColumnModel().getColumn(3).setMaxWidth(100);
		tableReport.getColumnModel().getColumn(4).setMinWidth(75);
		tableReport.getColumnModel().getColumn(4).setMaxWidth(75);
		tableReport.getColumnModel().getColumn(5).setMinWidth(75);
		tableReport.getColumnModel().getColumn(6).setMinWidth(50);
		tableReport.getColumnModel().getColumn(6).setMaxWidth(75);
		tableReport.getColumnModel().getColumn(7).setMinWidth(75);
		tableReport.getColumnModel().getColumn(7).setMaxWidth(100);
		scrollPaneReport.setViewportView(tableReport);

		// Create a JTabbedPane for the major selection
		JTabbedPane tabbedPaneMajor = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneMajor.setBounds(184, 25, 228, 122);
		panelReport.add(tabbedPaneMajor);
		// Add a change listener to the tabbedPaneMajor to perform actions based on the
		// selected tab index
		tabbedPaneMajor.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// Get the currently selected tab index
				int selectedIndex = tabbedPaneMajor.getSelectedIndex();

				// Perform actions based on the selected tab index
				switch (selectedIndex) {
					case 0:
						loadTableReport();
						break;
					case 1:
						loadTabTableReportByMajor("IT");
						break;
					case 2:
						loadTabTableReportByMajor("BUSINESS");
						break;
					case 3:
						loadTabTableReportByMajor("DESIGN");
						break;
				}
			}
		});

		// Create panels for each major tab and set their layouts
		JPanel panelAllMajor = new JPanel();
		panelAllMajor.setLocation(2, 25);
		tabbedPaneMajor.addTab("All", null, panelAllMajor, null);
		panelAllMajor.setLayout(null);

		JPanel panelIT = new JPanel();
		tabbedPaneMajor.addTab("IT", null, panelIT, null);
		panelIT.setLayout(null);

		JPanel panelBusiness = new JPanel();
		tabbedPaneMajor.addTab("BUSINESS", null, panelBusiness, null);
		panelBusiness.setLayout(null);

		JPanel panelDesign = new JPanel();
		tabbedPaneMajor.addTab("DESIGN", null, panelDesign, null);
		panelDesign.setLayout(null);

		// Add labels and count labels for each major tab
		// Panel All Major
		JLabel labelNoS_All = new JLabel("Number of Students:");
		labelNoS_All.setBounds(10, 11, 125, 14);
		panelAllMajor.add(labelNoS_All);

		JLabel labelAccept_All = new JLabel("Accepted:");
		labelAccept_All.setBounds(10, 36, 107, 14);
		panelAllMajor.add(labelAccept_All);

		JLabel labelReject_All = new JLabel("Rejected:");
		labelReject_All.setBounds(10, 61, 107, 14);
		panelAllMajor.add(labelReject_All);

		labelNoS_All_Count = new JLabel("0");
		labelNoS_All_Count.setBounds(145, 11, 46, 14);
		panelAllMajor.add(labelNoS_All_Count);

		labelAccept_All_Count = new JLabel("0");
		labelAccept_All_Count.setBounds(145, 36, 46, 14);
		panelAllMajor.add(labelAccept_All_Count);

		labelReject_All_Count = new JLabel("0");
		labelReject_All_Count.setBounds(145, 61, 46, 14);
		panelAllMajor.add(labelReject_All_Count);

		// Panel IT
		JLabel labelNoS_IT = new JLabel("Number of Students:");
		labelNoS_IT.setBounds(10, 11, 125, 14);
		panelIT.add(labelNoS_IT);

		JLabel labelAccept_IT = new JLabel("Accepted:");
		labelAccept_IT.setBounds(10, 36, 107, 14);
		panelIT.add(labelAccept_IT);

		JLabel labelReject_IT = new JLabel("Rejected:");
		labelReject_IT.setBounds(10, 61, 107, 14);
		panelIT.add(labelReject_IT);

		labelNoS_IT_Count = new JLabel("0");
		labelNoS_IT_Count.setBounds(145, 11, 46, 14);
		panelIT.add(labelNoS_IT_Count);

		labelAccept_IT_Count = new JLabel("0");
		labelAccept_IT_Count.setBounds(145, 36, 46, 14);
		panelIT.add(labelAccept_IT_Count);

		labelReject_IT_Count = new JLabel("0");
		labelReject_IT_Count.setBounds(145, 61, 46, 14);
		panelIT.add(labelReject_IT_Count);

		// Panel BUSINESS
		JLabel labelNoS_Business = new JLabel("Number of Students:");
		labelNoS_Business.setBounds(10, 11, 125, 14);
		panelBusiness.add(labelNoS_Business);

		JLabel labelAccept_Business = new JLabel("Accepted:");
		labelAccept_Business.setBounds(10, 36, 107, 14);
		panelBusiness.add(labelAccept_Business);

		JLabel labelReject_Business = new JLabel("Rejected:");
		labelReject_Business.setBounds(10, 61, 107, 14);
		panelBusiness.add(labelReject_Business);

		labelNoS_Business_Count = new JLabel("0");
		labelNoS_Business_Count.setBounds(145, 11, 46, 14);
		panelBusiness.add(labelNoS_Business_Count);

		labelAccept_Business_Count = new JLabel("0");
		labelAccept_Business_Count.setBounds(145, 36, 46, 14);
		panelBusiness.add(labelAccept_Business_Count);

		labelReject_Business_Count = new JLabel("0");
		labelReject_Business_Count.setBounds(145, 61, 46, 14);
		panelBusiness.add(labelReject_Business_Count);

		// Panel DESIGN
		JLabel labelNoS_Design = new JLabel("Number of Students:");
		labelNoS_Design.setBounds(10, 11, 125, 14);
		panelDesign.add(labelNoS_Design);

		JLabel labelAccept_Design = new JLabel("Accepted:");
		labelAccept_Design.setBounds(10, 36, 107, 14);
		panelDesign.add(labelAccept_Design);

		JLabel labelReject_Design = new JLabel("Rejected:");
		labelReject_Design.setBounds(10, 61, 107, 14);
		panelDesign.add(labelReject_Design);

		labelNoS_Design_Count = new JLabel("0");
		labelNoS_Design_Count.setBounds(145, 11, 46, 14);
		panelDesign.add(labelNoS_Design_Count);

		labelAccept_Design_Count = new JLabel("0");
		labelAccept_Design_Count.setBounds(145, 36, 46, 14);
		panelDesign.add(labelAccept_Design_Count);

		labelReject_Design_Count = new JLabel("0");
		labelReject_Design_Count.setBounds(145, 61, 46, 14);
		panelDesign.add(labelReject_Design_Count);

		// Filter report
		JPanel panelFilter_1 = new JPanel();
		panelFilter_1.setLayout(null);
		panelFilter_1.setBorder(new TitledBorder(null, "Filter", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFilter_1.setBounds(443, 25, 350, 122);
		panelReport.add(panelFilter_1);

		// Label gender and checkboxes (Male, Female)
		JLabel lblGender_Report = new JLabel("Gender");
		lblGender_Report.setBounds(124, 20, 46, 14);
		panelFilter_1.add(lblGender_Report);

		chckbxMale_Report = new JCheckBox("Male");
		chckbxMale_Report.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				filterTableReport();
			}
		});
		chckbxMale_Report.setBounds(124, 41, 70, 23);
		panelFilter_1.add(chckbxMale_Report);

		chckbxFemale_Report = new JCheckBox("Female");
		chckbxFemale_Report.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				filterTableReport();
			}
		});
		chckbxFemale_Report.setBounds(124, 67, 70, 23);
		panelFilter_1.add(chckbxFemale_Report);

		// Label major and checkboxes (IT, BUSINESS, DESIGN)
		JLabel lblMajor_Report = new JLabel("Major");
		lblMajor_Report.setBounds(24, 20, 46, 14);
		panelFilter_1.add(lblMajor_Report);

		chckbxIT_Report = new JCheckBox("IT");
		chckbxIT_Report.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				filterTableReport();
			}
		});
		chckbxIT_Report.setBounds(24, 41, 95, 23);
		panelFilter_1.add(chckbxIT_Report);

		chckbxBusiness_Report = new JCheckBox("BUSINESS");
		chckbxBusiness_Report.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				filterTableReport();
			}
		});
		chckbxBusiness_Report.setBounds(24, 67, 95, 23);
		panelFilter_1.add(chckbxBusiness_Report);

		chckbxDesign_Report = new JCheckBox("DESIGN");
		chckbxDesign_Report.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				filterTableReport();
			}
		});
		chckbxDesign_Report.setBounds(24, 93, 95, 23);
		panelFilter_1.add(chckbxDesign_Report);

		// Label status and checkboxes (Accepted, Rejected)
		JLabel labelStatus_Report = new JLabel("Status");
		labelStatus_Report.setBounds(224, 20, 46, 14);
		panelFilter_1.add(labelStatus_Report);

		chckbxAccepted_Report = new JCheckBox("Accepted");
		chckbxAccepted_Report.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				filterTableReport();
			}
		});
		chckbxAccepted_Report.setBounds(224, 41, 95, 23);
		panelFilter_1.add(chckbxAccepted_Report);

		chckbxRejected_Report = new JCheckBox("Rejected");
		chckbxRejected_Report.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				filterTableReport();
			}
		});
		chckbxRejected_Report.setBounds(224, 67, 95, 23);
		panelFilter_1.add(chckbxRejected_Report);

		// Search
		textFieldSearch_Report = new JTextField();
		textFieldSearch_Report.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				filterTableReport();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				filterTableReport();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				filterTableReport();
			}
		});
		textFieldSearch_Report.setColumns(10);
		textFieldSearch_Report.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textFieldSearch_Report.setBounds(824, 107, 192, 40);
		panelReport.add(textFieldSearch_Report);

		// Logo
		JLabel labelLogo_1 = new JLabel();
		labelLogo_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelLogo_1.setBounds(31, 25, 122, 122);
		Image image2 = imageIcon.getImage();
		Image newimg2 = image2.getScaledInstance(labelLogo_1.getWidth(), labelLogo_1.getHeight(), Image.SCALE_SMOOTH);
		labelLogo_1.setIcon(new ImageIcon(newimg2));
		panelReport.add(labelLogo_1);
	}

	/**
	 * Loads the student data into the tableStudent and triggers the loading of the
	 * tableReport.
	 * 
	 * Usage:
	 * Call this method to populate the tableStudent with student data and create a
	 * TableRowSorter for sorting.
	 * Additionally, it triggers the loading of the tableReport by invoking the
	 * loadTableReport() method.
	 */
	private void loadTable() {

		DefaultTableModel tableModel = (DefaultTableModel) tableStudent.getModel();
		// Clear existing rows in the table
		tableModel.setRowCount(0);
		// Populate the table with student data
		for (Student student : studentList.getStudents()) {
			tableModel.addRow(new Object[] {
					student.getId(),
					student.getFirstName(),
					student.getLastName(),
					student.getPhoneNumber(),
					student.getGender(),
					student.getMajor(),
					student.getGpa()
			});
		}

		// Create a TableRowSorter for the tableStudent
		sorterStudent = new TableRowSorter<>(tableModel);
		tableStudent.setRowSorter(sorterStudent);
		// Trigger the loading of the tableReport
		loadTableReport();
	}

	/**
	 * Loads the student data into the tableReport.
	 * 
	 * Usage:
	 * Call this method to populate the tableReport with student data and determine
	 * the status based on their GPA.
	 */
	private void loadTableReport() {
		DefaultTableModel tableModel = (DefaultTableModel) tableReport.getModel();
		// Clear existing rows in the table
		tableModel.setRowCount(0);
		// Populate the table with student data and determine the status
		for (Student student : studentList.getStudents()) {
			String status = "";
			// Set status based on the student's GPA
			if (student.getGpa() > 80) {
				status = "Accepted";
			} else {
				status = "Rejected";
			}

			tableModel.addRow(new Object[] {
					student.getId(),
					student.getFirstName(),
					student.getLastName(),
					student.getPhoneNumber(),
					student.getGender(),
					student.getMajor(),
					student.getGpa(),
					status
			});
		}

		// Create a TableRowSorter for the tableReport
		sorterReport = new TableRowSorter<>(tableModel);
		tableReport.setRowSorter(sorterReport);
	}

	/**
	 * Loads the data into the tableReport filtered by the specified major.
	 * 
	 * Usage:
	 * Call this method to populate the tableReport with student data filtered by
	 * the specified major.
	 * 
	 * @param major The major by which the student data should be filtered.
	 */
	private void loadTabTableReportByMajor(String major) {
		DefaultTableModel tableModel = (DefaultTableModel) tableReport.getModel();
		// Clear existing rows in the table
		tableModel.setRowCount(0);
		// Populate the table with student data and determine the status
		for (Student student : studentList.getStudents()) {
			String status = "";
			// Set status based on the student's GPA
			if (student.getGpa() > 80) {
				status = "Accepted";
			} else {
				status = "Rejected";
			}

			// Add student to the table if their major matches the selected major
			if (student.getMajor().equals(major)) {
				tableModel.addRow(new Object[] {
						student.getId(),
						student.getFirstName(),
						student.getLastName(),
						student.getPhoneNumber(),
						student.getGender(),
						student.getMajor(),
						student.getGpa(),
						status
				});
			}
		}

		// Create a TableRowSorter for the tableReport
		sorterReport = new TableRowSorter<>(tableModel);
		tableReport.setRowSorter(sorterReport);
	}

	/**
	 * Filters the data in the tableStudent based on the selected filter criteria.
	 * 
	 * Usage:
	 * Call this method to apply filters to the tableStudent based on the selected
	 * options.
	 */
	private void filterTable() {
		RowFilter<Object, Object> rf = null;
		// If current expression doesn't parse, don't update.
		try {
			List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>();
			List<RowFilter<Object, Object>> filtersGender = new ArrayList<RowFilter<Object, Object>>();
			List<RowFilter<Object, Object>> filtersMajor = new ArrayList<RowFilter<Object, Object>>();

			if (textFieldSearch.getText().trim().length() != 0) {
				filters.add(RowFilter.regexFilter("(?i)" + textFieldSearch.getText()));
			}
			if (chckbxMale.isSelected()) {
				filtersGender.add(RowFilter.regexFilter("Male", 4));
			}
			if (chckbxFemale.isSelected()) {
				filtersGender.add(RowFilter.regexFilter("Female", 4));
			}
			if (chckbxIT.isSelected()) {
				filtersMajor.add(RowFilter.regexFilter("IT", 5));
			}
			if (chckbxBusiness.isSelected()) {
				filtersMajor.add(RowFilter.regexFilter("BUSINESS", 5));
			}
			if (chckbxDesign.isSelected()) {
				filtersMajor.add(RowFilter.regexFilter("DESIGN", 5));
			}

			// Combine filters
			if (filtersGender.size() != 0) {
				filters.add(RowFilter.orFilter(filtersGender));
			}
			if (filtersMajor.size() != 0) {
				filters.add(RowFilter.orFilter(filtersMajor));
			}

			// Apply the combined filters
			rf = RowFilter.andFilter(filters);
			sorterStudent.setRowFilter(rf);
		} catch (PatternSyntaxException | NullPointerException e) {
			// Handle any exceptions that may occur during the filtering process
			return;
		}
	}

	/**
	 * Filters the data in the tableReport based on the selected filter criteria.
	 * 
	 * Usage:
	 * Call this method to apply filters to the tableReport based on the selected
	 * options.
	 */
	private void filterTableReport() {
		RowFilter<Object, Object> rf = null;
		// If current expression doesn't parse, don't update.
		try {
			List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>();
			List<RowFilter<Object, Object>> filtersGender = new ArrayList<RowFilter<Object, Object>>();
			List<RowFilter<Object, Object>> filtersMajor = new ArrayList<RowFilter<Object, Object>>();
			List<RowFilter<Object, Object>> filtersStatus = new ArrayList<RowFilter<Object, Object>>();

			if (textFieldSearch_Report.getText().trim().length() != 0) {
				filters.add(RowFilter.regexFilter("(?i)" + textFieldSearch_Report.getText()));
			}
			if (chckbxMale_Report.isSelected()) {
				filtersGender.add(RowFilter.regexFilter("Male", 4));
			}
			if (chckbxFemale_Report.isSelected()) {
				filtersGender.add(RowFilter.regexFilter("Female", 4));
			}
			if (chckbxIT_Report.isSelected()) {
				filtersMajor.add(RowFilter.regexFilter("IT", 5));
			}
			if (chckbxBusiness_Report.isSelected()) {
				filtersMajor.add(RowFilter.regexFilter("BUSINESS", 5));
			}
			if (chckbxDesign_Report.isSelected()) {
				filtersMajor.add(RowFilter.regexFilter("DESIGN", 5));
			}
			if (chckbxAccepted_Report.isSelected()) {
				filtersStatus.add(RowFilter.regexFilter("Accepted", 7));
			}
			if (chckbxRejected_Report.isSelected()) {
				filtersStatus.add(RowFilter.regexFilter("Rejected", 7));
			}

			if (filtersGender.size() != 0) {
				filters.add(RowFilter.orFilter(filtersGender));
			}
			if (filtersMajor.size() != 0) {
				filters.add(RowFilter.orFilter(filtersMajor));
			}
			if (filtersStatus.size() != 0) {
				filters.add(RowFilter.orFilter(filtersStatus));
			}

			rf = RowFilter.andFilter(filters);
			sorterReport.setRowFilter(rf);
		} catch (PatternSyntaxException | NullPointerException e) {
			return;
		}
	}

}
