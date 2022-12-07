import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
class employee{
    String empid,firstname,lastname,DOB, Firstline, SecondLine,City,State,post,country,phnumber,personalemail,officialemail,department,DOJ,Education;
    String age;
    int i=0; 
    String pincode;
    PrintWriter pw;
    Scanner sc = new Scanner(System.in);
    Object obj;
    List n = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    boolean check_employee(String eid){
        try {
            JSONArray array = (JSONArray) new JSONParser().parse(new FileReader("jsonExampl.json"));
            JSONObject jo = new JSONObject();
            for(Object obj : array.stream().toArray()){
                JSONObject to  = (JSONObject) obj;
                String emid = (String) to.get("EmployeeId");
                if(eid.equals(emid)){
                    return (true);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return (false);
    }
    void add_employee(){
        try{  
            //pw = new PrintWriter("jsonExampl.json");
            boolean found = false;
            System.out.println("Enter employee id: - ");
            empid = sc.nextLine();
            JSONObject jo = new JSONObject();
            found = check_employee(empid);
            if(!found){
                jo.put("EmployeeId", empid);
                System.out.println("Enter employee First Name: - ");
                firstname = sc.nextLine();
                jo.put("FirstName", firstname);
                System.out.println("Enter employee Last Name: - ");
                lastname = sc.nextLine();
                jo.put("LastName", lastname);
                System.out.println("Enter employee DOB: - ");
                DOB = reader.readLine();
                jo.put("DOB", DOB);
                System.out.println("Enter Employee Age: - ");
                age = sc.nextLine();
                jo.put("Age", age);
                Map m = new LinkedHashMap(7);
                System.out.println("Enter employee Address Details : - ");
                System.out.println("FirstLine:- ");
                Firstline = reader.readLine();
                System.out.println("Second Line: - ");
                SecondLine = reader.readLine();
                System.out.println("City: - ");
                City = sc.nextLine();
                System.out.println("State: -");
                State = sc.nextLine();
                System.out.println("post: - ");
                post = reader.readLine();
                System.out.println("Pincode: - ");
                pincode = sc.nextLine();
                System.out.println("Country: -");
                country = sc.nextLine();
                m.put("FirstLine", Firstline);
                m.put("Second Line", SecondLine);
                m.put("City", City);
                m.put("State", State);
                m.put("Post", post);
                m.put("Pincode",pincode);
                m.put("Country", country);
                jo.put("Address", m);
                System.out.println("Enter employee Phone Number: - ");
                phnumber = sc.nextLine();
                jo.put("PhoneNumber", phnumber);
                System.out.println("Enter employee Personal Email: - ");
                personalemail = sc.nextLine();
                jo.put("PersonalMail", personalemail);
                System.out.println("Enter employee Official Mail: - ");
                officialemail = sc.nextLine();
                jo.put("OfficialMail", officialemail);
                System.out.println("Enter employee Department: - ");
                department = sc.nextLine();
                jo.put("Department", department);
                System.out.println("Enter employee DOJ: - ");
                DOJ = sc.nextLine();
                jo.put("DOJ", DOJ);
                System.out.println("Enter Employee Education: - ");
                Education = reader.readLine();
                jo.put("Education", Education);
                n.add(jo);
                try(PrintWriter out = new PrintWriter(new FileWriter("jsonExampl.json"))){
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(n);
                    out.write(jsonString);
                }
            }
            else{
                System.out.println("Employee Id Already Exist!!!!!");
            }   
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }   
    }
    void display_employee(){
        try {
            JSONArray array = (JSONArray) new JSONParser().parse(new FileReader("jsonExampl.json"));
            for(Object obj : array.stream().toArray()){
                System.out.println("========= Employee Detail =========");
                JSONObject jo = (JSONObject) obj;
                empid = (String) jo.get("EmployeeId");
                firstname = (String) jo.get("FirstName");
                lastname = (String) jo.get("LastName");
                DOB = (String) jo.get("DOB");
                String ae = (String) jo.get("Age");
                System.out.println("Employee Id:- "+ empid);
                System.out.println("First Name:- "+ firstname);
                System.out.println("Last Name:- "+ lastname);
                System.out.println("DOB:- "+ DOB);
                System.out.println("Age:- "+ ae);
                Map address = ((Map) jo.get("Address"));
                System.out.println("===Address Details===");
                Iterator<Map.Entry> itr1 = address.entrySet().iterator(); 
                while (itr1.hasNext()) { 
                    Map.Entry pair = itr1.next(); 
                    System.out.println(pair.getKey() + " : " + pair.getValue()); 
                }
                System.out.println("==== Contact and Qualification Detail =====");
                phnumber=(String) jo.get("PhoneNumber");
                personalemail = (String) jo.get("PersonalMail");
                officialemail = (String) jo.get("OfficialMail");
                department = (String) jo.get("Department");
                DOJ = (String) jo.get("DOJ");
                Education = (String) jo.get("Education");
                System.out.println("Employee Phone:- "+ phnumber);
                System.out.println("Personal:- "+ personalemail);
                System.out.println("Official:- "+ officialemail);
                System.out.println("DOJ:- "+ DOJ);
                System.out.println("Education:- "+ Education);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }
    void update_employee(){
        try {
            int cnt = 0;
            boolean found = false;
            JSONArray array = (JSONArray) new JSONParser().parse(new FileReader("jsonExampl.json"));
            JSONObject jo = new JSONObject();
            System.out.println("Enter Employee id for Updating Details");
            String eid = sc.nextLine();
            for(Object obj : array.stream().toArray()){
                JSONObject to  = (JSONObject) obj;
                String emid = (String) to.get("EmployeeId");
                if(eid.equals(emid)){
                    found = true;
                    break;
                }
                else{
                    cnt++;
                }
            }
            if(found){
                System.out.println("Enter Updated employee Firstname: - ");
                jo.put("EmployeeId", eid);
                firstname = sc.nextLine();
                jo.put("FirstName", firstname);
                System.out.println("Enter Updated employee Last Name: - ");
                lastname = sc.nextLine();
                jo.put("LastName", lastname);
                System.out.println("Enter Updated employee DOB: - ");
                DOB = reader.readLine();
                jo.put("DOB", DOB);
                System.out.println("Enter Updated Employee Age: - ");
                age = sc.nextLine();
                jo.put("Age", age);
                Map m = new LinkedHashMap(7);
                System.out.println("Enter Updated employee Address Details : - ");
                System.out.println("FirstLine:- ");
                Firstline = reader.readLine();
                System.out.println("Second Line: - ");
                SecondLine = reader.readLine();
                System.out.println("City: - ");
                City = sc.nextLine();
                System.out.println("State: -");
                State = sc.nextLine();
                System.out.println("post: - ");
                post = reader.readLine();
                System.out.println("Pincode: - ");
                pincode = sc.nextLine();
                System.out.println("Country: -");
                country = sc.nextLine();
                m.put("FirstLine", Firstline);
                m.put("Second Line", SecondLine);
                m.put("City", City);
                m.put("State", State);
                m.put("Post", post);
                m.put("Pincode",pincode);
                m.put("Country", country);
                jo.put("Address", m);
                System.out.println("Enter Updated employee Phone Number: - ");
                phnumber = sc.nextLine();
                jo.put("PhoneNumber", phnumber);
                System.out.println("Enter Updated employee Personal Email: - ");
                personalemail = sc.nextLine();
                jo.put("PersonalMail", personalemail);
                System.out.println("Enter Updated employee Official Mail: - ");
                officialemail = sc.nextLine();
                jo.put("OfficialMail", officialemail);
                System.out.println("Enter Updated employee Department: - ");
                department = sc.nextLine();
                jo.put("Department", department);
                System.out.println("Enter Updated employee DOJ: - ");
                DOJ = sc.nextLine();
                jo.put("DOJ", DOJ);
                System.out.println("Enter Updated Employee Education: - ");
                Education = reader.readLine();
                jo.put("Education", Education);
                n.set(cnt,jo);
                try(PrintWriter out = new PrintWriter(new FileWriter("jsonExampl.json"))){
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(n);
                    out.write(jsonString);
                    System.out.println("Employee Updated Successfully");
                }
            }
            else{
                System.out.println("Employee id not exist!!!");
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

    }
    void delete_employee(){
        try {
            int counter = 0;
            boolean found = false;
            JSONArray array = (JSONArray) new JSONParser().parse(new FileReader("jsonExampl.json"));
            //JSONObject jo = new JSONObject();
            System.out.println("Enter Employee id for removing");
            String eid = sc.nextLine();
            for(Object obj : array.stream().toArray()){
                JSONObject to  = (JSONObject) obj;
                String emid = (String) to.get("EmployeeId");
                if(eid.equals(emid)){
                    found = true;
                    break;
                }
                else{
                    counter++;
                }
            }
            if(found){
                n.remove(counter);
                try(PrintWriter out = new PrintWriter(new FileWriter("jsonExampl.json"))){
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(n);
                    out.write(jsonString);
                    System.out.println("Employee Removed Successfully");
                }
            }
            else{
                System.out.println("Employee id not Found");
            }
        }
        catch (Exception e) {
            // TODO: handle exception
        }
    }
}
public class jsoncrudEx {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        employee e = new employee();
        int choice;
        do {
            System.out.println("==============Talak Solution Pvt. Ltd.==============");
            System.out.println("1. Add Employee\n2. Update Employee\n3. Show Employee\n4. Delete Employee\n5. Exit");
            System.out.println("Enter your choice:- ");
            choice = Integer.parseInt(sc.nextLine());
            switch(choice){
                case 1:
                    e.add_employee();
                    break;
                case 2:
                    e.update_employee();
                    break;
                case 3:
                    e.display_employee();
                    break;
                case 4:
                    e.delete_employee();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Please Enter right choice!!!");
                    break;
            }
        } while (choice!=5);     
    } 
}