import java.awt.*;
import java.awt.event.*;

public class Analyst extends Frame{
    public Analyst () {
        super("My Window");
        Frame frame = new Frame("My Window");
        frame.setSize(900,300);
        Label newLabel = new Label("My Label");
        frame.add (newLabel);

        Storage store = new Storage();


        Button b = new Button("Open File");
        b.addActionListener(new ActionListener() {
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

        frame.add(b);

        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                ((Frame)e.getSource()).dispose();
                // or
                System.exit(0);
            }
        });




    }
    public static void main (String args[]) {
        new Analyst();
    }
}