import java.awt.*;
import java.awt.event.*;

public class Analyst extends Frame{
    public Analyst () {


        Frame frame = new Frame("My Window");
        frame.setSize(900,300);
        Label newLabel = new Label("My Label");
        frame.add (newLabel);
        FlowLayout myLayout = new FlowLayout();
        frame.setLayout(myLayout);

        Storage store = new Storage();


        Button btnLoadFile = new Button("Open File");
        btnLoadFile.setBounds(130,130,150,100);
        btnLoadFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog fd = new FileDialog(frame, "Choose a file", FileDialog.LOAD);
                fd.setDirectory("C:\\");
                fd.setFile("*.txt");
                fd.setVisible(true);
                String filename = fd.getFile();
                if (filename == null)
                    System.out.println("You cancelled the choice");
                else
                    System.out.println("You chose " + filename);
                    store.setData(IO.readData(filename));
                //System.out.println("Running");
            }
        });

        Button btnRun = new Button("Run simulation");
        btnRun.setBounds(30,30,30,30);
        btnRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog fd = new FileDialog(frame, "Choose a file", FileDialog.LOAD);
                fd.setDirectory("C:\\");
                fd.setFile("*.txt");
                fd.setVisible(true);
                String filename = fd.getFile();
                if (filename == null)
                    System.out.println("You cancelled the choice");
                else
                    System.out.println("You chose " + filename);
                store.setData(IO.readData(filename));
                //System.out.println("Running");
            }
        });
        frame.add(btnLoadFile);
        frame.add(btnRun);

        frame.addWindowListener(new WindowAdapter(){
                                    public void windowClosing(WindowEvent e){
                                        ((Frame)e.getSource()).dispose();
                                        // or
                                        System.exit(0);
                                    }
                                });

        frame.setVisible(true);

    }
    public static void main (String args[]) {
        new Analyst();
    }
}