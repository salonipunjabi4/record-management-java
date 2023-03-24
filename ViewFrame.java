import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class ViewFrame extends JFrame
{
Container c;
TextArea ta;
JButton btnBack;
ViewFrame()
{
c = getContentPane();
c.setLayout(new FlowLayout());
ta = new TextArea(3,25);
btnBack = new JButton("Back");
c.add(ta);
c.add(btnBack);
DbHandler db = new DbHandler();
String data = db.viewStudent();
ta.setText(data);
btnBack.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae)
{
MainFrame a = new MainFrame();
dispose();
}
});

setTitle("View Student ");
setSize(230,300);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);



}//end of constructor
}//end of class