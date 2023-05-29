
package bank;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Deposit extends JFrame implements ActionListener{
    JButton deposit,back;
    JTextField amount;
    String pinnumber;
    
    Deposit(String pinnumber){
        
         this.pinnumber = pinnumber;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);
        
        JLabel text = new JLabel("Enter the deposit amount" );
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(220, 200, 400, 20);
        image.add(text);
        
         amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(170, 250, 320, 30 );
        image.add(amount);
        
         deposit = new JButton("Deposit");
        deposit.setBounds(355, 380, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);
        
         back = new JButton("Back");
        back.setBounds(355, 415, 150, 30);
        back.addActionListener(this);
        image.add(back);
        
        setSize(900, 900);
        setLocation(300, 0);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==deposit){
            String number = amount.getText();
            Date date = new Date();
            if (number.equals("")){
                JOptionPane.showMessageDialog(null,"Please Enter the Amount");
            }else{
                try{
                Conn conn = new Conn();
                String query = "Insert into bank values('"+pinnumber+"', '"+date+"','Deposit','"+number+"')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Rs" +number+" Deposited Successfully");
                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
                } catch(Exception e){
                    System.out.println(e);
                }
            }
        }else if(ae.getSource()==back){
            
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }
    public static void main(String args[]){
        new Deposit("");
    }
}
