package project;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class classConference extends JFrame implements ActionListener {



 private static final long serialVersionUID = 1L;
 static JPanel jp1, jp2, jp3, jp4;
 static JFrame jf1, jf2, f3, f4;
 static JLabel jname, jlocation, jyear, jmonth, jdate, jtype, jproceedings, jnumber;
 static JTextField tname, tlocation, tyear, tmonth, tdate, ttype, tproceedings, tnumber;
 static JButton jb1, jb2, jb3;
 static JTextArea txta, txta2;
 static int ch, ch1;
 int serial = 0;
 Connection con;
 String databaseName = "conferencedatabase";
 String url = "jdbc:mysql://localhost:3306/" + databaseName;
 String username = "root";
 String password = "1234";

 @SuppressWarnings("resource")
 classConference() throws SQLException, ClassNotFoundException {
  Class.forName("com.mysql.jdbc.Driver");
  con = DriverManager.getConnection(url, username, password);
  String login = JOptionPane.showInputDialog("Enter Login ", "LOGIN");
  if (login.equals("Admin")) {
   String pwd = JOptionPane.showInputDialog("Enter password for : Admin", "PASSWORD");
   if (pwd.equals("Conference")) {


    int ch = firstOption();

    if (ch == 0) {
     f3 = new JFrame("Search");
     jp3 = new JPanel();
     txta2 = new JTextArea(50, 50);
     jp3.add(txta2);
     f3.add(jp3);
     f3.setVisible(true);
     f3.setSize(800, 800);
     String y = JOptionPane.showInputDialog(f3, "Enter year to search for");

     String quer = "SELECT * FROM conferencedatabase.contable WHERE Year=?;";
     PreparedStatement ps = con.prepareStatement(quer);
     ps.setString(1, y);
     ResultSet rs = ps.executeQuery();
     txta2.setText(" ");
     while (rs.next()) {
      txta2.append("\nConferenceID:\t" + rs.getString(1));
      txta2.append("\nName:\t" + rs.getString(2));
      txta2.append("\nLocation:\t" + rs.getString(3));
      txta2.append("\nYear:\t" + rs.getString(4));
      txta2.append("\nMonth:\t" + rs.getString(5));
      txta2.append("\nDate:\t" + rs.getString(6));
      txta2.append("\nType:\t" + rs.getString(7));
      txta2.append("\nProceedings:\t" + rs.getString(8));
      txta2.append("\nNumber:\t" + rs.getString(9));
      txta2.append("\n---------------------------------------------------\n");
      

     }


     String data = txta2.getText().trim(); //read contents of text area into 'data'
     if (data.equals("")) {
      txta2.append("No records matched your search!");


     }
     boolean bol = true;

     String[] choices = {
      "UPDATE",
      "DELETE"
     };
     String[] choice = {
      "YES",
      "NO"
     };
     ch1 = JOptionPane.showOptionDialog(null, "Do you wish to update/delete one of the searched records?", "Choice select", 0, JOptionPane.INFORMATION_MESSAGE, null, choices, choices[1]);
     if (ch1 == 0)

     {
      while (bol) {
       int id = Integer.parseInt(JOptionPane.showInputDialog("Which of the selected records would you like to update?", "*Enter the ConferenceID"));
       String attribute = JOptionPane.showInputDialog("Which attribute would you like to change?", "*Enter Name,Year..etc");
       switch (attribute) {
        case "Date":
         String dt = JOptionPane.showInputDialog("Enter the new date");
         ps = con.prepareStatement("UPDATE conferencedatabase.contable SET Date = ? WHERE ConferenceID = ?;");
         ps.setString(1, dt);
         ps.setInt(2, id);
         break;
        case "Month":
         String mn = JOptionPane.showInputDialog("Enter the new Month");
         ps = con.prepareStatement("UPDATE conferencedatabase.contable SET Month = ? WHERE ConferenceID = ?;");
         ps.setString(1, mn);
         ps.setInt(2, id);
         break;
        case "Year":
         String yr = JOptionPane.showInputDialog("Enter the new Year");
         ps = con.prepareStatement("UPDATE contable SET Year = ? WHERE ConferenceID = ?;");
         ps.setString(1, yr);
         ps.setInt(2, id);
         break;
        case "Name":
         String nm = JOptionPane.showInputDialog("Enter the new Name");
         ps = con.prepareStatement("UPDATE contable SET Name = ? WHERE ConferenceID = ?;");
         ps.setString(1, nm);
         ps.setInt(2, id);
         break;
        case "Location":
         String lc = JOptionPane.showInputDialog("Enter the new Location");
         ps = con.prepareStatement("UPDATE contable SET Location = ? WHERE ConferenceID = ?;");
         ps.setString(1, lc);
         ps.setInt(2, id);
         break;
        case "Type":
         String ty = JOptionPane.showInputDialog("Enter the new Type");
         ps = con.prepareStatement("UPDATE contable SET Type = ? WHERE ConferenceID = ?;");
         ps.setString(1, ty);
         ps.setInt(2, id);
         break;
        case "Proceedings":
         String pr = JOptionPane.showInputDialog("Enter the new Proceedings");
         ps = con.prepareStatement("UPDATE contable SET Proceedings = ? WHERE ConferenceID = ?;");
         ps.setString(1, pr);
         ps.setInt(2, id);
         break;
        case "Number":
         int n = Integer.parseInt(JOptionPane.showInputDialog("Enter the new Year"));
         ps = con.prepareStatement("UPDATE contable SET Number = ? WHERE ConferenceID = ?;");
         ps.setInt(1, n);
         ps.setInt(2, id);
         break;
        default:
         System.exit(0);

       }
       int i = ps.executeUpdate();
       if (i != 0) {
        int fc = JOptionPane.showOptionDialog(null, "UPDATE SUCCESSFUL!!\nDo you wish to update more of the searched records?", "Choice select", 0, JOptionPane.INFORMATION_MESSAGE, null, choice, choice[1]);
        if (fc != 0) {
         bol = false;
        } else {
         //Do Nothing
        }
       }

      }
     } else if (ch1 == 1) {

      while (bol) {
       int id = Integer.parseInt(JOptionPane.showInputDialog("Which of the selected records would you like to delete?", "*Enter the ConferenceID"));
       ps = con.prepareStatement("DELETE FROM contable WHERE ConferenceID = ?;");
       ps.setInt(1, id);
       int status = ps.executeUpdate();
       if (status != 0) {
        int ch3 = JOptionPane.showOptionDialog(null, "Do you wish to delete one more of the searched records?", "Choice select", 0, JOptionPane.INFORMATION_MESSAGE, null, choice, choice[1]);
        if (ch3 == 1) {
         bol = false;

        }
       }
      }
     }
    }



    if (ch == 1) {
     jf1 = new JFrame("Conference Details");
     jp1 = new JPanel();
     jp2 = new JPanel();
     jname = new JLabel("CONFERENCE NAME");
     jlocation = new JLabel("CONFERENCE LOCATION");
     jyear = new JLabel("YEAR");
     jmonth = new JLabel("MONTH");
     jdate = new JLabel("DATE");
     jtype = new JLabel("TYPE OF CONFERENCE");
     jproceedings = new JLabel("PROCEEDINGS");
     jnumber = new JLabel("NUMBER OF PAPERS PUBLISHED");
     jb1 = new JButton("ADD VALUES");
     tname = new JTextField(20);
     tlocation = new JTextField(30);
     tyear = new JTextField(10);
     tmonth = new JTextField(20);
     tdate = new JTextField(10);
     ttype = new JTextField(20);
     tproceedings = new JTextField(20);
     tnumber = new JTextField(20);
     jb2 = new JButton("DISPLAY ALL VALUES");
     jb3 = new JButton("CLEAR SCREEN");
     jp1.add(jname);
     jp1.add(tname);
     jp1.add(jlocation);
     jp1.add(tlocation);
     jp1.add(jyear);
     jp1.add(tyear);
     jp1.add(jmonth);
     jp1.add(tmonth);
     jp1.add(jdate);
     jp1.add(tdate);
     jp1.add(jtype);
     jp1.add(ttype);
     jp1.add(jproceedings);
     jp1.add(tproceedings);
     jp1.add(jnumber);
     jp1.add(tnumber);
     jp1.add(jb1);
     jp1.add(jb2);
     jp1.add(jb3);
     jb1.addActionListener(this);
     jp1.setVisible(true);

     jp1.setLayout(new GridLayout(20, 1));

     jb2.addActionListener(this);
     jb3.addActionListener(this);
     jb2.setEnabled(true);
     jb3.setEnabled(false);



     jf1.add(jp1);
     jf1.setVisible(true);
     jf1.setSize(800, 800);

    }
   } else {
    JOptionPane.showMessageDialog(null, "Invalid Password for user: " + login, "Error", JOptionPane.ERROR_MESSAGE);

   }
  } else {
   JOptionPane.showMessageDialog(null, "Invalid Username", "Error", JOptionPane.ERROR_MESSAGE);

  }

 }
 public static void main(String args[]) throws ClassNotFoundException, SQLException {
  classConference cf = new classConference();
 }



 public static int firstOption() {
  String[] choices = {
   "SEARCH",
   "INPUT"
  };
  ch = JOptionPane.showOptionDialog(null, "Choose what you want to do", "Choice select", 0, JOptionPane.INFORMATION_MESSAGE, null, choices, choices[1]);
  return ch;
 }

 public void actionPerformed(ActionEvent e) {
  if (e.getSource() == jb1) {


   String name = tname.getText();
   String year = tyear.getText();
   String location = tlocation.getText();
   String month = tmonth.getText();
   String type = ttype.getText();
   String date = tdate.getText();
   String proceedings = tproceedings.getText();
   int number = Integer.parseInt(tnumber.getText());
   try {
    PreparedStatement ps = con.prepareStatement("INSERT INTO contable (Name,Location,Year,Month,Date,Type,Proceedings,Number) VALUES (?,?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
    ps.setString(1, name);
    ps.setString(2, location);
    ps.setString(3, year);
    ps.setString(4, month);
    ps.setString(5, date);
    ps.setString(6, type);
    ps.setString(7, proceedings);
    ps.setInt(8, number);
    int i = ps.executeUpdate();
    ResultSet tableKeys = ps.getGeneratedKeys();
    tableKeys.next();
    int autoGeneratedID = tableKeys.getInt(1);
    Statement smt = con.createStatement();
    String query = "SELECT COUNT(*) FROM contable";
    ResultSet op = smt.executeQuery(query);
    op.next();
    int cn = op.getInt(1);
    if (cn > 5) {
     JOptionPane.showMessageDialog(null, "Exceeded 5 records : deleting oldest record");
     PreparedStatement delete = con.prepareStatement("DELETE FROM contable LIMIT 1;");
     int result = delete.executeUpdate();
     if (result == 1)
      JOptionPane.showMessageDialog(null, "Row 1 Deleted");

    }
    


    if (i != 0) {
     System.out.println("Database was connected\nRecord was inserted");
     PreparedStatement del = con.prepareStatement("DELETE FROM contable WHERE Year = '2015' ;");
     int res = del.executeUpdate();
     if(res == 1)
     JOptionPane.showMessageDialog(null, "The record with year 2015 and below has been deleted","Alert", JOptionPane.INFORMATION_MESSAGE, null);
    }
   } catch (SQLException e1) {
    // TODO Auto-generated catch block
    e1.printStackTrace();
   }


   jb2.setEnabled(true);
   jb3.setEnabled(true);

  }
  if (e.getSource() == jb2) {

   jf2 = new JFrame();
   jf2.setTitle("Result Display");
   JTable j;

   try {
    PreparedStatement ps = con.prepareStatement("SELECT * FROM contable;");
    ResultSet rs = ps.executeQuery();
    String[][] data = new String[6][9];
    String ColumnNames[] = {
     "ConferenceID",
     "Name",
     "Location",
     "Year",
     "Month",
     "Date",
     "Type",
     "Proceedings",
     "Number of Papers"
    };
    int i = 0;
    data[i][0] = "ConferenceID";
    data[i][1] = "Name";
    data[i][2] = "Location";
    data[i][3] = "Year";
    data[i][4] = "Month";
    data[i][5] = "Date";
    data[i][6] = "Type";
    data[i][7] = "Proceedings";
    data[i][8] = "Number of  papers published";
    i++;
    while (rs.next()) {
     data[i][0] = rs.getString(1);
     data[i][1] = rs.getString(2);
     data[i][2] = rs.getString(3);
     data[i][3] = rs.getString(4);
     data[i][4] = rs.getString(5);
     data[i][5] = rs.getString(6);
     data[i][6] = rs.getString(7);
     data[i][7] = rs.getString(8);
     data[i][8] = rs.getString(9);
     i++;
    }
    j = new JTable(data, ColumnNames);
    jf2.add(j);
    j.setBounds(200, 200, 200, 300);

   } catch (SQLException e1) {
    // TODO Auto-generated catch block
    e1.printStackTrace();
   }
   jf1.setVisible(false);

   jf2.setVisible(true);
   jf2.setSize(1000, 500);

  }
  if (e.getSource() == jb3) {
   tname.setText("");
   tlocation.setText("");
   tyear.setText("");
   tmonth.setText("");
   tdate.setText("");
   ttype.setText("");
   tproceedings.setText("");
   tnumber.setText("");

  }


 }

}