import java.util.Scanner;

public class array_pract {
    public static void main(String[] args) {
        int arr[] = new int[10];
        int counter = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the limit");
        counter = Integer.parseInt(sc.nextLine());

        System.out.println("Enter the values");

        for(int i=0;i<counter;i++){
            arr[i] = Integer.parseInt(sc.nextLine());
        }

        System.out.println("Values are...");
        for(int i=0;i<counter;i++){
            System.out.println(arr[i]);
        }
    }
}
