
import java.util.Scanner;

class student{
    public String name="Bhuvnesh", rollno="21122057", email="bhuvnesh@gmail.com";

    String StudentString(){
        return name + " " + rollno+ " " + email;
    }
}
public class menu_driven {
    public static void main(String[] args) {
        student s = new student();
        Scanner sc = new Scanner(System.in);
        int cnt = 0;
        do{
            System.out.println("1.Enter roll no\n2.Enter Name\n3.Enter Email\n4.fill full bye bye");
            cnt = sc.nextInt();
            if(cnt == 2){
                s.name = sc.next();
            }
            else if(cnt == 3){
                s.email = sc.next();
            }
            else if(cnt == 1){
                s.rollno = sc.next();
            }
            else if(cnt == 4){
                System.out.println("Visit Again!!");
            }
            else{
                System.out.println("Please enter right choice");
            }
        }while(cnt!=4);
        System.out.println(s.StudentString());

    }
    
}
