import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.*;
import java.io.*;
class studentdatabase{
    File sdata;
    RandomAccessFile ra;
    String sname,reg,email,phone,wholename,r;
    int index,endindex;
    boolean found;
    Scanner scan = new Scanner(System.in);
    studentdatabase(){
        this.sdata = new File("stud.csv");
    }
    boolean check_student(String sr){
        try {
            ra = new RandomAccessFile(sdata, "rw");
            while(ra.getFilePointer() < ra.length()){
                wholename = ra.readLine();
                String[] ln = wholename.split(",");
                reg = ln[1];
                if(reg.matches(sr)){
                    ra.close();
                    return (true);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return (false);
    }
    public void add_student(){
        try {
            System.out.println("Enter Registration No for insert new Record:- ");
            r = scan.nextLine();
            found = check_student(r);
            ra = new RandomAccessFile(sdata, "rw");
            if(!found){
                System.out.println("Enter the Name: -");
                sname = scan.nextLine();
                System.out.println("Enter the email: - ");
                email = scan.nextLine();
                System.out.println("Enter the Phone No: - ");
                phone = scan.nextLine();
                wholename = sname+","+r+","+email+","+phone;
                ra.seek(ra.length());
                ra.writeBytes(wholename);
                ra.writeBytes(System.lineSeparator());
                ra.close();
                Connection conn = null;
                conn = DriverManager.getConnection("jdbc:mysql://localhost/art", "root", "");
                String sql = "insert into student(roll_no, name, email, mobile)" + " values (?,?,?,?)";
                PreparedStatement prstmt = conn.prepareStatement(sql);
                int rege = Integer.parseInt(r);
                prstmt.setInt(1, rege);
                prstmt.setString(2, sname);
                prstmt.setString(3, email);
                prstmt.setString(4, phone);
                prstmt.execute();
                conn.close();
                System.out.println("Student Add Successfully");
            }
            else{
                ra.close();
                System.out.println("Registration number Already Exist");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void display_students(){
        try {
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost/art", "root", "");
            Statement stmt=conn.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from student"); 
            System.out.println("RollNo\tName\tEmail\t\tMobile"); 
            while(rs.next())  
            System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+ "\t"+rs.getString(3)+"\t"+rs.getString(4));
            conn.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            e.printStackTrace();
        }   
    }     
}

public class lab7 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        studentdatabase st = new studentdatabase();
        int choice,count=0;
        do{
            System.out.println("===============The Student Database Hub =============");
            System.out.println("1.Add from CSV\n2.Insert Student\n3.View Student\n4.Exit");
            System.out.println("Enter your choice :- ");
            choice = Integer.parseInt(s.next());
            switch(choice){
                case 1:
                    if(count == 0){
                        try {
                            RandomAccessFile ra = new RandomAccessFile("stud.csv", "rw");
                            Connection conn = null;
                            conn = DriverManager.getConnection("jdbc:mysql://localhost/art", "root", "");
                            while(ra.getFilePointer() < ra.length()){
                                String wholename = ra.readLine();
                                String[] linesplit = wholename.split(",");
                                String sname = linesplit[0];
                                String reg = linesplit[1];
                                String email = linesplit[2];
                                String phone = linesplit[3];
                                String sql = "insert into student(roll_no, name, email, mobile)" + " values (?,?,?,?)";
                                PreparedStatement prstmt = conn.prepareStatement(sql);
                                int rege = Integer.parseInt(reg);
                                prstmt.setInt(1, rege);
                                prstmt.setString(2, sname);
                                prstmt.setString(3, email);
                                prstmt.setString(4, phone);
                                prstmt.execute();    
                            }
                            conn.close();
                            ra.close();
                            System.out.println("Successfully Imported");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    else{
                        System.out.println("You already insert all the data");
                    }
                    break;
                case 2:
                    st.add_student();
                    break;
                case 3:
                    st.display_students();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Enter Right Choice");
            }
        }while(choice!=4);  
        System.out.println("Thank you");        
    }    
}
