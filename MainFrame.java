import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
// import oracle.sql.*; 
// import oracle.jdbc.driver.*;
class MainFrame extends JFrame
{
Container c;
JButton btnAdd, btnView, btnUpdate, btnDelete;

MainFrame()
{
c = getContentPane();
c.setLayout(new FlowLayout());
btnAdd = new JButton("Add");
btnView = new JButton("View");
btnUpdate = new JButton("Update");
btnDelete = new JButton("Delete");
c.add(btnAdd);
c.add(btnView);
c.add(btnUpdate);
c.add(btnDelete);

btnAdd.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae)
{
AddFrame a = new AddFrame();
dispose();
}
});
btnView.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae)
{
ViewFrame a = new ViewFrame();
dispose();
}
});
btnUpdate.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae)
{
UpdateFrame a = new UpdateFrame();
dispose();
}
});
btnDelete.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae)
{
DeleteFrame a = new DeleteFrame();
dispose();
}
});

setTitle("S. M. S.  ");
setSize(300,300);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);


}//end of constructor

public static void main(String args[])
{
MainFrame m = new MainFrame();
}//end of main
}//end of mainframe class


class DbHandler
{
public void addStudent(int rno, String name)
{
try
{
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##scott", "tiger");
String sql = "insert into student values(?,?)";
PreparedStatement pst = con.prepareStatement(sql);
pst.setInt(1, rno);
pst.setString(2, name);
int r1 = pst.executeUpdate();
JOptionPane.showMessageDialog(new JDialog(), r1 + " records inserted!");
con.close();
}//end of try
catch(SQLException se)
{
JOptionPane.showMessageDialog(new JDialog(), " issue " + se);
}
}//end of addStudent
public String viewStudent() 
{
StringBuffer sb = new StringBuffer();
try
{
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "abc123");
String sql = "select * from student";
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(sql);
while(rs.next())
{
int rno = rs.getInt(1);
String name = rs.getString(2);
sb.append("rno " + rno + " name " + name + " \n");
}//end of while
rs.close();
con.close();
}//end of try
catch(SQLException se)
{
JOptionPane.showMessageDialog(new JDialog(), " issue " + se);

}//end of catch
return sb.toString();
}//end of viewStudent
public void updateStudent(int rno, String name)
{
try
{
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "abc123");
String sql= "select name from student where rno=?";
PreparedStatement pst=con.prepareStatement(sql);
pst.setInt(1,rno);
ResultSet rs=pst.executeQuery();
int count=0;      
while(rs.next()) {
count++;
String sql1="update student set name=? where rno=? ";
PreparedStatement pst1=con.prepareStatement(sql1);
pst1.setString(1,name);
pst1.setInt(2,rno);
int r2=pst1.executeUpdate();
JOptionPane.showMessageDialog(new JDialog(),r2+"  record updated ");
}
if(count ==0)
{
JOptionPane.showMessageDialog(new JDialog(),"  Record does not exists ");
}
rs.close();
con.close(); 

}//end of try
catch(SQLException se)
{
JOptionPane.showMessageDialog(new JDialog(), " issue " + se);
}
}//end of updateStudent


public void deleteStudent(int rno)
{
try
{
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "abc123");
String sql= "select name from student where rno=?";
PreparedStatement pst=con.prepareStatement(sql);
pst.setInt(1,rno);
ResultSet rs=pst.executeQuery();
int count=0;      
while(rs.next()) {
count++;
String sql1="delete from student where rno=? ";
PreparedStatement pst1=con.prepareStatement(sql1);
pst1.setInt(1,rno);
int r2=pst1.executeUpdate();
JOptionPane.showMessageDialog(new JDialog(),r2+"  record deleted ");
}
if(count ==0)
{
JOptionPane.showMessageDialog(new JDialog(),"  record does not exists ");
}
rs.close();
con.close(); 

}//end of try
catch(SQLException se)
{
JOptionPane.showMessageDialog(new JDialog(), " issue " + se);
}
}//end of deleteStudent

}//end of DbHandler
