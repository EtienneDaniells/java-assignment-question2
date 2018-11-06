import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

//Etienne Daniells
public class registerClass {


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        login log = new login();
        String[] validate = log.myLogin();
        System.out.println(validate[0]);
        System.out.println(validate[1]);
        int loop = -1;
        while (loop <0) {
                if (validate_login(validate[0], validate[1])) {
                    JOptionPane.showMessageDialog(null, "Login Succesfull");
                    studentPresent();
                    loop++;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login, try again");
                    log.myLogin();
                }
        }
    }

    static private boolean validate_login(String username, String password){
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance", "root", "");
            PreparedStatement state = connect.prepareStatement("select * from login Where username = ? and password = ?");
            state.setString(1, username);
            state.setString(2, password);
            ResultSet result = state.executeQuery();
            return result.next();
        }catch (Exception dberror){
            dberror.printStackTrace();
        }
        return false;
    }

    static void studentPresent(){
        int students = Integer.parseInt(JOptionPane.showInputDialog("How many students are in the class?"));
        insertDetails(students);
    }

    static void insertDetails(int students){
        String name= "";
        String username= "";
        String subject = "";
        int studentNo = 0;
        Student student = new Student(name, username, studentNo, subject);
        int i = 0;
        while (i<students){
            JPanel panel = new JPanel(new BorderLayout(10, 5));
            //Creating and placing labels
            JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
            label.add(new JLabel("Name", SwingConstants.RIGHT));
            label.add(new JLabel("Surname", SwingConstants.RIGHT));
            label.add(new JLabel("Student No.", SwingConstants.RIGHT));
            label.add(new JLabel("Subject", SwingConstants.RIGHT));
            panel.add(label, BorderLayout.WEST);

            //Creating and placing textfields
            JPanel inputs = new JPanel(new GridLayout(0, 1, 2, 2));
            JTextField stuName = new JTextField();
            inputs.add(stuName);
            JTextField stuLName = new JTextField();
            inputs.add(stuLName);
            JTextField stuNo = new JTextField();
            inputs.add(stuNo);
            JTextField subj = new JTextField();
            inputs.add(subj);
            panel.add(inputs, BorderLayout.CENTER);

            int result = JOptionPane.showConfirmDialog(null, panel, "login", JOptionPane.OK_CANCEL_OPTION);
            if(result == JOptionPane.OK_OPTION){
                student.setName(stuName.getText());
                student.setSurName(stuLName.getText());
                student.setStudentNo(Integer.parseInt(stuNo.getText()));
                student.setSubject(subj.getText());
            } else{
                System.exit(0);
            }

            try {
                java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance", "root", "");
                PreparedStatement state = connect.prepareStatement("INSERT INTO `class-register`(`name`, `surName`, `StudentNo`, `subject`, `date`) VALUES (?, ?, ?, ?, ?);");
                state.setString(1, student.getName());
                state.setString(2, student.getSurName());
                state.setInt(3, student.getStudentNo());
                state.setString(4, student.getSubject());
                state.setTimestamp(5, date);

                int success = state.executeUpdate();
                if(success>0)
                {
                    System.out.println("Data inserted");
                    i++;
                }
            }catch (Exception dberror){
                dberror.printStackTrace();
            }
        }
        System.out.println(getAllDetails());
    }

    public static ArrayList<Student> getAllDetails(){
        ArrayList<Student> studentList = new ArrayList<>();
        try{
        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance", "root", "");
        PreparedStatement state = connect.prepareStatement ("Select * From `class-register`");
        ResultSet rst;
        rst = state.executeQuery();
        while (rst.next()) {
            String name= "";
            String username= "";
            String subject = "";
            int studentNo = 0;
            Student student = new Student(name, username, studentNo, subject);
            student.setName(rst.getString("name"));
            student.setSurName(rst.getString("surName"));
            student.setStudentNo(rst.getInt("StudentNo"));
            student.setSubject(rst.getString("subject"));
            studentList.add(student);
        }
        }catch(Exception err){
            err.printStackTrace();
        }
        return studentList;
    }
}
