import java.awt.*;
import java.awt.event.*;

public class Analyst extends Frame{
    public Analyst () {

        Storage store = new Storage();

        Frame frame = new Frame("My Window");
        frame.setSize(900,900);

        BorderLayout myLayout = new BorderLayout();
        frame.setLayout(myLayout);

        Panel panelOne = new Panel();
        panelOne.setBackground(Color.white);
        panelOne.setBounds(5,5,890,290);

        Panel panelTwo = new Panel();
        panelTwo.setBackground(Color.lightGray);
        panelTwo.setBounds(5,305,890,290);

        Panel panelThree = new Panel();
        panelThree.setBackground(Color.white);
        panelThree.setBounds(5,605,890,290);

        Panel panelFour = new Panel();
        panelFour.setBackground(Color.white);
        panelFour.setBounds(5,605,890,290);

        // Set panelOne
        Label lblLoad = new Label("Load data from file: ");

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

        //Set panelTwo

        Label lblIter = new Label("The number of particles: ");
        Label lblHeight = new Label("The height of the building (m): ");
        Label lblRise = new Label("The chance of the particle rising while above the building height: ");
        Label lblStay = new Label("The chance of the particle staying at the same height while above the building height: ");
        Label lblNorth = new Label("The chance of the particle travelling North: ");
        Label lblSouth = new Label("The chance of the particle travelling South: ");
        Label lblEast = new Label("LThe chance of the particle travelling East: ");
        Label lblWest = new Label("The chance of the particle travelling West: ");


        //Set panelThree
        Button btnRun = new Button("Run simulation");
        btnRun.setBounds(30,30,30,30);
        btnRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                store.moveBacteria(store.getBombSite(),75, 0.2, 0.1, 0.1, 0.1, 0.75);

                //System.out.println("Running");
            }
        });


        frame.addWindowListener(new WindowAdapter(){
                                    public void windowClosing(WindowEvent e){
                                        ((Frame)e.getSource()).dispose();
                                        // or
                                        System.exit(0);
                                    }
                                });




        frame.add(panelOne, BorderLayout.PAGE_START);
        frame.add(panelTwo, BorderLayout.LINE_START);
        frame.add(panelThree, BorderLayout.LINE_END);
        frame.add(panelFour, BorderLayout.PAGE_END);

        panelOne.add(lblLoad);
        panelOne.add(btnLoadFile);


        panelFour.add(btnRun);

        frame.setVisible(true);

    }
    public static void main (String args[]) {
        new Analyst();
    }
}