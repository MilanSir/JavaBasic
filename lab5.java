import java.io.*;
import java.util.*;
class Student{
    File sdata;
    RandomAccessFile ra;
    String sname,reg,email,phone,wholename,r;
    int index,endindex;
    boolean found;
    Scanner scan = new Scanner(System.in);
    Student(){
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
    void add_student(){
        try {
            System.out.println("Enter Registration No for insert new Record");
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
                System.out.println("New Student Added");
                ra.close();
            }
            else{
                ra.close();
                System.out.println("Registration number Already Exist");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    void update_student(){
        try {
            System.out.println("Please Enter the Roll No for Updation: - ");
            r =  scan.nextLine();
            found = check_student(r);
            ra = new RandomAccessFile(sdata, "rw");
            if(found){
                File tmpFile = new File("temp.txt");
                RandomAccessFile tmpra = new RandomAccessFile(tmpFile, "rw");
                ra.seek(0);
                while(ra.getFilePointer()<ra.length()){
                    wholename = ra.readLine();
                    index = wholename.indexOf(',');
                    endindex = wholename.indexOf(',',index+1);
                    reg = wholename.substring(index+1, endindex);
                    if(reg.equals(r)){
                        System.out.println("Enter the Name: -");
                        sname = scan.nextLine();
                        System.out.println("Enter the email: - ");
                        email = scan.nextLine();
                        System.out.println("Enter the Phone No: - ");
                        phone = scan.nextLine();
                        wholename = sname+","+r+","+email+","+phone;
                    }
                    tmpra.writeBytes(wholename);
                    tmpra.writeBytes(System.lineSeparator());     
                }
                ra.seek(0);
                tmpra.seek(0);
                while(tmpra.getFilePointer()< tmpra.length())
                {
                    ra.writeBytes(tmpra.readLine());
                    ra.writeBytes(System.lineSeparator());
                }
                ra.setLength(tmpra.length());
                tmpra.close();
                ra.close();
                tmpFile.delete();
                System.out.println("Student Detail Updated!!!");
            }
            else{
                ra.close();
                System.out.println(r+"not exist!!!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    void delete_student(){
        try {
            System.out.println("Please Enter the Roll No for Updation: - ");
            r =  scan.nextLine();
            found = check_student(r);
            ra = new RandomAccessFile(sdata, "rw");
            if(found){
                File tmpFile = new File("temp.txt");
                RandomAccessFile tmpra = new RandomAccessFile(tmpFile, "rw");
                ra.seek(0);
                while(ra.getFilePointer()<ra.length()){
                    wholename = ra.readLine();
                    index = wholename.indexOf(',');
                    endindex = wholename.indexOf(',',index+1);
                    reg = wholename.substring(index+1, endindex);
                    if(reg.equals(r)){
                        continue;
                    }
                    tmpra.writeBytes(wholename);
                    tmpra.writeBytes(System.lineSeparator());
                }
                ra.seek(0);
                tmpra.seek(0);
                while(tmpra.getFilePointer()< tmpra.length())
                {
                    ra.writeBytes(tmpra.readLine());
                    ra.writeBytes(System.lineSeparator());
                }
                ra.setLength(tmpra.length());
                tmpra.close();
                ra.close();
                tmpFile.delete();
                System.out.println("Student Delete Successfully!!!");
            }
            else{
                ra.close();
                System.out.println("Student Not Exist.......");
            }   
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    void display_student(){
        try {
            ra = new RandomAccessFile(sdata, "rw");
            while(ra.getFilePointer() < ra.length()){
                wholename = ra.readLine();
                String[] linesplit = wholename.split(",");
                sname = linesplit[0];
                reg = linesplit[1];
                email = linesplit[2];
                phone = linesplit[3];
                System.out.println("Name:-"+sname+"\nRoll No:- "+reg+"\nEmail: -"+email+"\nphone: - "+phone+"\n");
            }
            ra.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

public class lab5{
    public static void main(String[] args) {
        Student st = new Student();
        Scanner sc = new Scanner(System.in);
        int choice;
        do{
            System.out.println("============= Dee University ================");
            System.out.println("1.Add New Student\n2.Update/edit & Save Student Details\n3.View All the Student Details\n4.Delete Student\n5.Exit");
            System.out.println("Enter your choice:- ");
            choice = Integer.parseInt(sc.next());
            switch(choice){
                case 1:
                    //System.out.println("Hello Creation");
                    st.add_student();
                    break;
                case 2:
                    st.update_student();
                    break;
                case 3:
                    st.display_student();
                    break;
                case 4:
                    st.delete_student();
                    break;  
                case 5:
                    System.out.println("Visit Again!!!!");
                    break;
                default:
                    System.out.println("Enter the right choice!!!!");      
            }
        }while(choice!=5);      
    }
}