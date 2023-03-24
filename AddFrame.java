import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class AddFrame extends JFrame
{
Container c;
JLabel lblRno, lblName;
JTextField txtRno, txtName;
JButton btnSave, btnBack;
AddFrame()
{
c = getContentPane();
c.setLayout(new FlowLayout());

lblRno = new JLabel("Enter rno: ");
lblName = new JLabel("Enter name: ");
txtRno = new JTextField(10);
txtName = new JTextField(10);
btnSave = new JButton("Save");
btnBack = new JButton("Back");

c.add(lblRno);
c.add(txtRno);
c.add(lblName);
c.add(txtName);
c.add(btnSave);
c.add(btnBack);
btnSave.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae)
{
try
{
String rno = txtRno.getText();
if(rno.length() == 0)
{
JOptionPane.showMessageDialog(new JDialog(),"Roll number cannot be empty!");
txtRno.setText("");
txtRno.requestFocus();
}
else if((Integer.parseInt(rno) < 0))
{
JOptionPane.showMessageDialog(new JDialog(),"Roll number cannot be negative!");
txtRno.setText("");
txtRno.requestFocus();
}

else
{
String name = txtName.getText();
if(name.length() == 0)
{
JOptionPane.showMessageDialog(new JDialog(),"Name cannot be empty!");
txtName.setText("");
txtName.requestFocus();
}
else if((!name.matches("^[\\sa-zA-Z\\s]*$")) || (name.matches("^[\\s]*$")))
{
JOptionPane.showMessageDialog(new JDialog(),"Name can contain only alphabets!");
txtName.setText("");
txtName.requestFocus();
}

else
{
DbHandler db = new DbHandler();
db.addStudent(Integer.parseInt(rno), name);
txtRno.setText("");
txtName.setText("");
}
}


}
catch(NumberFormatException ne)
{
JOptionPane.showMessageDialog(new JDialog(),"Roll number cannot contain alphabets!");
txtRno.setText("");
txtRno.requestFocus();
}
}
});

btnBack.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae)
{
MainFrame a = new MainFrame();
dispose();
}
});
setTitle("Add Student");
setSize(250,300);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);

}
}//end of class