import java.io.File;
import java.io.*;

public class filehandling {
    public static void main(String[] args) {
        int i;
        try {
            File obj = new File("file1.txt");
            //obj.createNewFile();
            //System.out.println(obj.exists());
            if(obj.createNewFile()){
                FileWriter f = new FileWriter(obj);
                f.write("Hello world-----!!!");
                f.close();
            }
            else{
                FileWriter f = new FileWriter(obj,true);
                f.write("Munna bhai");
                f.write("Dostar");
                f.close();
            }
            FileReader fr = new FileReader(obj);
            while((i = fr.read())!=-1){
                System.out.println((char)i);
            }
            fr.close();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Eroor : "+e.getMessage());
        }
    }
}
