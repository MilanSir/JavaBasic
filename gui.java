import javax.swing.*;
import java.awt.event.*;

public class gui {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JButton jb = new JButton("Click");
        JLabel jl = new JLabel("Dada");

        jl.setBounds(130,100,100,50);
        jb.setBounds(130,150,100,40);

        frame.add(jl);
        frame.add(jb);

        frame.setSize(400,500);
        frame.setLayout(null);
        frame.setVisible(true);

        jb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jl.setText("Hello world");
            }
        });
    }
    
}
