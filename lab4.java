import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.util.Scanner;
class Restaurant{
    String name,GSTIN,FSSAI,Location,Contact;
    Restaurant(){
        this.name = "Abra ka Dabra Resturant";
        this.GSTIN = "GSTIN289012";
        this.FSSAI = "FSSAI2021CHRIST";
        this.Location = "Lavasa";
        this.Contact = "Milan Baba(+91 85308 00148)";
    }
    void print_rest(){
        System.out.println("Restaurant Name:- "+this.name);
        System.out.println("Restaurant GSTIN:- "+this.GSTIN);
        System.out.println("Restaurant FSSAI: - "+this.FSSAI);
        System.out.println("Restaurant Location: - "+this.Location);
        System.out.println("Restaurant Contact: -"+this.Contact);
    }   
}
class Menu extends Restaurant{
    String item_name;
    int item_price;
    Menu(){
        System.out.println();
    }
    Menu(String name, int price){
        this.item_name = name;
        this.item_price = price;
    }
    void Restaurant_detailedMenu(Menu m[],int counter){
        super.print_rest();
        for(int i=0;i<counter;i++){
            int cnt = i+1;
            System.out.println("Item no: - "+cnt+" Item Name: - "+m[i].item_name+" Price: - "+m[i].item_price);
        }   
    }
    void Restaurant_detailedMenu(){
        //super.print_rest();
        System.out.println("Item Name: -"+this.item_name);
        System.out.println("Price: - "+this.item_price);
    }
}
class customer{
    String Name, Number, tbl_no;
    customer(){
        System.out.println();
    }
    customer(String n,String ph, String tb){
        this.Name = n;
        this.Number = ph;
        this.tbl_no = tb;
    }
    void print_customer(){
        System.out.println(this.Name);
        System.out.println(this.Number);
        System.out.println(this.tbl_no);
    }
    public boolean search_table(String t){
        if(tbl_no.equals(t)){
            return (true);
        }
        return (false);
    }
}
public class lab4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menu m[] = new Menu[50];
        Menu c = new Menu();
        customer cu[] = new customer[50];
        int counter=0,cust_counter=0,ite = 0,choice;
        File obj = new File("Bill.txt");
        do{
            System.out.println("============ Data Science ChaiWala ==================");
            System.out.println("1. Enter the Menu details \n2. Add Customer \n3. Book Order \n4. Show All the Bill \n5. Exit");
            System.out.println("Hello Admin!!! What you want!!!");
            choice = Integer.parseInt(sc.next());
            switch(choice){
                case 1:
                    if(ite!=0){
                        System.out.println("You Already Assign the Menu you can not Add anything Now");
                    }
                    else{
                        System.out.println("How Many items you want to add in Menu?");
                        int itemtotal = Integer.parseInt(sc.next());
                        if(itemtotal<=0){
                            System.out.println("Please Enter Right Choice");
                        }
                        else{
                            for(int i=0;i<itemtotal;i++){
                                System.out.println("Enter Item Name:- ");
                                String itemn = sc.next();
                                System.out.println("Enter Item Price:- ");
                                int price = Integer.parseInt(sc.next());
                                m[i] = new Menu(itemn, price);
                                counter++;
                            }
                            ite = 1;
                        }
                    }
                    break;
                case 2:
                    System.out.println("********Add Customer********");
                    System.out.println("Enter the Customer Name: -");
                    String cu_name = sc.next();
                    System.out.println("Enter phone number: - ");
                    String cu_phone = sc.next();
                    System.out.println("Enter the table no:- ");
                    String tb_no = sc.next();
                    cu[cust_counter] = new customer(cu_name, cu_phone, tb_no);
                    cust_counter++;
                    break;
                case 3:
                    if(ite == 0){
                        System.out.println("Please Assign the Menu!!!!");
                    }
                    else{
                        if(cust_counter == 0){
                            System.out.println("There is no customer in Resturant!!!");
                        }
                        else{
                            System.out.println("Enter the table no for order:- ");
                            String st_no = sc.next();
                            boolean found = false;
                            HashMap<String,List<Integer>> order_list = new HashMap<>();
                            for(int i=0;i<cust_counter;i++){
                                found = cu[i].search_table(st_no);
                                if(found){
                                    while(true){
                                        System.out.println("What you want to order");
                                        c.Restaurant_detailedMenu(m, counter);
                                        System.out.println("Enter the Item Number: - ");
                                        int cnt = Integer.parseInt(sc.next());
                                        if(cnt>counter){
                                            System.out.println("Please Enter the right item no");
                                        } 
                                        else{
                                            System.out.println("Enter the Quntity:- ");
                                            int qty = Integer.parseInt(sc.next());
                                            List<Integer> ln = new ArrayList<>();
                                            ln.add(m[cnt-1].item_price);
                                            ln.add(qty);
                                            order_list.put(m[cnt-1].item_name,ln);
                                            System.out.println("Do you want add any item(y/n): - ");
                                            String ck = sc.next();
                                            if(ck.toLowerCase().equals("y")){
                                                continue;
                                            }
                                            else if(ck.toLowerCase().equals("n")){
                                                break;
                                            }
                                            else{
                                                System.out.println("Please Enter the right choice----");
                                            }
                                        }   
                                    }
                                    System.out.println("======== Your Order Details =========");
                                    for (Map.Entry<String, List<Integer>> entry : order_list.entrySet()) {
                                        String key = entry.getKey();
                                        List<Integer> values = entry.getValue();
                                        Integer s = values.get(0);
                                        Integer t = values.get(1);
                                        System.out.println("Item Name = " + key);
                                        System.out.println("Price = " + s + "\n");
                                        System.out.println("Quantity = "+t);
                                    }
                                    while(true){
                                        System.out.println("Do you want to modified order?(y/n):- ");
                                        String pp = sc.next();
                                        if(pp.toLowerCase().equals("y")){
                                            System.out.println("Press 1 for Delete the item\nPress 2 for add item");
                                            String jj = sc.next();
                                            if(jj.toLowerCase().equals("1")){
                                                if(order_list.isEmpty())
                                                {
                                                    System.out.println("You Didn't Order Anything");
                                                    break;
                                                }
                                                else{
                                                    System.out.println("Enter the item Name:- ");
                                                    String itemname = sc.next();
                                                    if(order_list.containsKey(itemname.trim())){
                                                        order_list.remove(itemname.trim());
                                                        System.out.println("Suceessfully remove "+itemname);
                                                    }
                                                    else{
                                                        System.out.println("You didn't order "+itemname+" in your order");
                                                    }
                                                }
                                            }
                                            else if(jj.toLowerCase().equals("2")){
                                                System.out.println("What you want to order");
                                                c.Restaurant_detailedMenu(m, counter);
                                                System.out.println("Enter the Item Number: - ");
                                                int cnt = Integer.parseInt(sc.next());
                                                if(cnt>counter){
                                                    System.out.println("Please Enter the right item no");
                                                } 
                                                else{
                                                    if(order_list.containsKey(m[cnt-1].item_name)){
                                                        System.out.println("Enter the Quntity:- ");
                                                        int qty = Integer.parseInt(sc.next());
                                                        int l = order_list.get(m[cnt-1].item_name).get(1);
                                                        order_list.remove(m[cnt-1].item_name);
                                                        List<Integer> ln = new ArrayList<>();
                                                        ln.add(m[cnt-1].item_price);
                                                        ln.add(qty+l);
                                                        order_list.put(m[cnt-1].item_name,ln);                                                
                                                    }
                                                    else{
                                                        System.out.println("Enter the Quntity:- ");
                                                        int qty = Integer.parseInt(sc.next());
                                                        List<Integer> ln = new ArrayList<>();
                                                        ln.add(m[cnt-1].item_price);
                                                        ln.add(qty);
                                                        order_list.put(m[cnt-1].item_name,ln);
                                                    }
                                                }      
                                            }
                                            else{
                                                System.out.println("Enter the Right choice");
                                            }
                                        }
                                        else if(pp.toLowerCase().equals("n")){
                                            while(true){
                                                if(!order_list.isEmpty()){
                                                    System.out.println("Press 1 for print the bill\nPress 2 for cancel the order");
                                                    String ql = sc.next();
                                                    if(ql.equals("1")){
                                                        System.out.println("======================Your Bill=============================");
                                                        System.out.println("Customer Name:- "+ cu[i].Name);
                                                        System.out.println("Phone Number:- "+ cu[i].Number);
                                                        System.out.println("===================== Items ================================");
                                                        System.out.println("Item No\t\tItem Name\tPrice\t\tQuantity\ttotal");
                                                        int fj = 1,sum=0;
                                                        for (Map.Entry<String, List<Integer>> entry : order_list.entrySet()) {
                                                            String key = entry.getKey();
                                                            List<Integer> values = entry.getValue();
                                                            Integer s = values.get(0);
                                                            Integer t = values.get(1);
                                                            int tt = s * t;
                                                            sum = sum + tt;
                                                            System.out.println(fj+"\t\t"+key+"\t\t"+s+"\t\t"+t+"\t\t"+tt);
                                                            fj=fj+1;
                                                        }
                                                        System.out.println("You have to pay :- "+sum);
                                                        try {
                                                            if(obj.createNewFile()){
                                                                FileWriter f = new FileWriter(obj);
                                                                f.write("===========New Bill===========\n");
                                                                f.write("Customer Name:- "+ cu[i].Name+"\n");
                                                                f.write("Phone Number:- "+ cu[i].Number+"\n");
                                                                f.write("Table No: - "+st_no+"\n");
                                                                f.write("Item No\t\tItem Name\tPrice\t\tQuantity\ttotal\n");
                                                                int tj = 1,bill=0;
                                                                for (Map.Entry<String, List<Integer>> entry : order_list.entrySet()) {
                                                                    String key = entry.getKey();
                                                                    List<Integer> values = entry.getValue();
                                                                    Integer s = values.get(0);
                                                                    Integer t = values.get(1);
                                                                    int tt = s * t;
                                                                    bill = bill + tt;
                                                                    f.write(tj+"\t\t"+key+"\t\t"+s+"\t\t"+t+"\t\t"+tt+"\n");
                                                                    tj=tj+1;
                                                                }
                                                                f.write("Total Bill:- "+bill);
                                                                f.close();
                                                            }
                                                            else{
                                                                FileWriter f = new FileWriter(obj,true);
                                                                f.write("===========New Bill===========\n");
                                                                f.write("Customer Name:- "+ cu[i].Name+"\n");
                                                                f.write("Phone Number:- "+ cu[i].Number+"\n");
                                                                f.write("Table No: - "+st_no+"\n");
                                                                f.write("Item No\t\tItem Name\tPrice\tQuantity\ttotal\n");
                                                                int tj = 1,bill=0;
                                                                for (Map.Entry<String, List<Integer>> entry : order_list.entrySet()) {
                                                                    String key = entry.getKey();
                                                                    List<Integer> values = entry.getValue();
                                                                    Integer s = values.get(0);
                                                                    Integer t = values.get(1);
                                                                    int tt = s * t;
                                                                    bill = bill + tt;
                                                                    f.write(tj+"\t\t"+key+"\t"+s+"\t"+t+"\t"+tt+"\n");
                                                                    tj=tj+1;
                                                                }
                                                                f.write("Total Bill:- "+bill);
                                                                f.close();
                                                            }    
                                                        } catch (Exception e) {
                                                            // TODO: handle exception
                                                            System.out.println(e.getMessage());
                                                        }
                                                        break;
                                                    }
                                                    else if(ql.equals("2")){
                                                        System.out.println("Your Order Cancelled Succesfully");
                                                        break;
                                                    }
                                                    else{
                                                        System.out.println("Please enter right choice");
                                                    }
                                                }
                                                else{
                                                    System.out.println("You Didn't Order Anything");
                                                    break;
                                                }
                                            }
                                            break;
                                        }
                                        else{
                                            System.out.println("Enter the Right Choice");
                                        }
                                    }
                                }
                            }
                            if(!found){
                                System.out.println("Table is Empty!!!!!!");
                            }
                        }
                    }
                    break;
                case 4:
                    try {
                        BufferedReader in = new BufferedReader(new FileReader(obj));
                        String myString;
                        while((myString = in.readLine())!= null)
                        {
                            System.out.println(myString);
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Thank you!! Visit Again!!");
                    break;
                default:
                    System.out.println("");
            }
        }while(choice!=5);
    }    
}
