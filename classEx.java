class student{
    public String name, rollno, email;

    String StudentString(){
        return name + " " + rollno+ " " + email;
    }
}
public class classEx {
    public static void main(String[] args) {
        student s = new student();
        s.name = "Milan";
        s.rollno = "21122026";
        s.email = "milanjasani@gmail.com";
        System.out.println(s.StudentString());     
        
    }
}
