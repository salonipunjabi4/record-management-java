import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class DeleteFrame extends JFrame
{
Container c;
JLabel lblRno;
JTextField txtRno;
JButton btnSave, btnBack;
DeleteFrame()
{
c = getContentPane();
c.setLayout(new FlowLayout());

lblRno = new JLabel("Enter rno: ");
txtRno = new JTextField(10);
btnSave = new JButton("Save");
btnBack = new JButton("Back");

c.add(lblRno);
c.add(txtRno);
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
DbHandler db=new DbHandler();
db.deleteStudent(Integer.parseInt(rno));
txtRno.setText("");
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
setTitle("Delete Student");
setSize(250,300);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);

}
}//end of class