//Etienne Daniells
import javax.swing.*;
import java.awt.*;

//Etienne Daniells
public class login{



     String[] myLogin(){
         String uName;
         String pWord;
         //Creating overall layout
        JPanel panel = new JPanel(new BorderLayout(10, 5));
        //Creating and placing labels
        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("Username", SwingConstants.RIGHT));
        label.add(new JLabel("Password", SwingConstants.RIGHT));
        panel.add(label, BorderLayout.WEST);

        //Creating and placing textfields
        JPanel inputs = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField username = new JTextField();
        inputs.add(username);
        JPasswordField password = new JPasswordField();
        inputs.add(password);
        panel.add(inputs, BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(null, panel, "login", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
           uName = username.getText();
           pWord = new String(password.getPassword());
            System.out.println("1" +uName);
            System.out.println("1" +pWord);
           return new String[]{uName, pWord};
        } else{
            System.exit(0);
        }
        return null;
    }
}



