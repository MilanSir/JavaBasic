import java.util.Scanner;  
class BankDetails {  
    String accno,name,acc_type;
    long balance;  
    Scanner sc = new Scanner(System.in);   
    void Create_Account() {  
        System.out.print("Enter Account No: ");  
        accno = sc.next();  
        System.out.print("Enter Account type: ");  
        acc_type = sc.next();  
        System.out.print("Enter Name: ");  
        name = sc.next();  
        System.out.print("Enter Balance: ");  
        balance = sc.nextLong();  
    }  
    void Display_Account() {  
        System.out.println("Name of account holder: " + name);  
        System.out.println("Account no.: " + accno);  
        System.out.println("Account type: " + acc_type);  
        System.out.println("Balance: " + balance);  
    }
    void money_deposit() {  
        long amt;  
        System.out.println("Enter the amount you want to deposit: ");  
        amt = sc.nextLong();  
        balance = balance + amt;  
    }
    void money_withdrawal() {  
        long amt;  
        System.out.println("Enter the amount you want to withdraw: ");  
        amt = sc.nextLong();  
        if (balance >= amt) {  
            balance = balance - amt;  
            System.out.println("Balance after withdrawal: " + balance);  
        } else {  
            System.out.println("Your balance is less than " + amt + "\tTransaction failed...!!" );  
        }  
    }   
    public boolean search_AC(String ac_no) {  
        if (accno.equals(ac_no)) {  
            Display_Account();  
            return (true);  
        }  
        return (false);  
    }  
}  
public class first_lab{  
    public static void main(String arg[]) {  
        Scanner sc = new Scanner(System.in);    
        System.out.print("How many number of customers do you want to insert?: -  ");  
        int n = sc.nextInt();  
        BankDetails bank[] = new BankDetails[n];  
        for (int i = 0; i < bank.length; i++) {  
            bank[i] = new BankDetails();  
            bank[i].Create_Account();  
        }  
        int ch;  
        do {  
            System.out.println("*** Christ University Banking ***");  
            System.out.println("1. Display all account details \n 2. Find Account by Account number\n 3. Deposit the Money \n 4. Withdraw the Money \n 5.Exit ");  
            System.out.println("Enter your choice: ");  
            ch = sc.nextInt();  
                switch (ch) {  
                    case 1:  
                        for (int i = 0; i < bank.length; i++) {  
                            bank[i].Display_Account();  
                        }  
                        break;  
                    case 2:  
                        System.out.print("Enter account no. you want to Find: ");  
                        String ac_no = sc.next();  
                        boolean found = false;  
                        for (int i = 0; i < bank.length; i++) {  
                            found = bank[i].search_AC(ac_no);  
                            if (found) {  
                                break;  
                            }  
                        }  
                        if (!found) {  
                            System.out.println("Account doesn't exist! Please Check Account Number");  
                        }  
                        break;  
                    case 3:  
                        System.out.print("Enter Account no. : ");  
                        ac_no = sc.next();  
                        found = false;  
                        for (int i = 0; i < bank.length; i++) {  
                            found = bank[i].search_AC(ac_no);  
                            if (found) {  
                                bank[i].money_deposit();  
                                break;  
                            }  
                        }  
                        if (!found) {  
                            System.out.println("Account doesn't exist! please Check Account Number");  
                        }  
                        break;  
                    case 4:  
                        System.out.print("Enter Account No : ");  
                        ac_no = sc.next();  
                        found = false;  
                        for (int i = 0; i < bank.length; i++) {  
                            found = bank[i].search_AC(ac_no);  
                            if (found) {  
                                bank[i].money_withdrawal();  
                                break;  
                            }  
                        }  
                        if (!found) {  
                            System.out.println("Account doesn't exist! Please Check Account Number ");  
                        }  
                        break;  
                    case 5:  
                        System.out.println("Welcome Again!!");  
                        break; 
                    default:
                        System.out.println("Enter Right choice");
                        System.out.println(bank[0].accno);
                }  
            }  
            while (ch != 5);  
        }  
    }  