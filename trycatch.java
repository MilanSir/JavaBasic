import java.util.Scanner;

public class trycatch {
    public static void main(String[] args) {
        int a = 8, b = 0;
        Scanner sc = new Scanner(System.in);
        int arr[] = new int[3];
         try {
            //System.out.println(a / b);
            //System.out.println(arr[100]);
            int age = sc.nextInt();
            if(age<20){
                throw new ArithmeticException("You are not mature");
            }
        }

        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        catch(ArithmeticException e){
            System.out.println(e.getMessage());
        }
        finally{
            System.out.println("Program in finally");
        }
    }

}
