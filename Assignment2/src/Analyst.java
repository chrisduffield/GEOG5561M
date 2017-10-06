import java.awt.*;
import java.awt.event.*;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Analyst extends Frame{
    public Analyst () {


        Storage store = new Storage();

        Frame frame = new Frame("My Window");
        frame.setSize(1900,900);

        BorderLayout myLayout = new BorderLayout();
        frame.setLayout(myLayout);

        Panel panelOne = new Panel();
        //panelOne.setLayout(myLayout);
        panelOne.setBackground(Color.white);
        panelOne.setPreferredSize(new Dimension(1890, 290));

        Panel panelTwo = new Panel();
        panelTwo.setLayout(new GridLayout(8,2));
        panelTwo.setBackground(Color.lightGray);
        panelTwo.setPreferredSize(new Dimension(945, 290));

        Panel panelThree = new Panel();
        //panelThree.setLayout(myLayout);
        panelThree.setBackground(Color.white);
        panelThree.setPreferredSize(new Dimension(945, 290));

        Panel panelFour = new Panel();
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        panelFour.setBackground(Color.white);
        panelFour.setPreferredSize(new Dimension(1890, 290));



        //Set panelTwo

        Label lblIter = new Label("The number of particles: ");
        Label lblHeight = new Label("The height of the building (m): ");
        Label lblRise = new Label("The chance of the particle rising while above the building height: ");
        Label lblStay = new Label("The chance of the particle staying at the same height while above the building height: ");
        Label lblNorth = new Label("The chance of the particle travelling North: ");
        Label lblSouth = new Label("The chance of the particle travelling South: ");
        Label lblEast = new Label("The chance of the particle travelling East: ");
        Label lblWest = new Label("The chance of the particle travelling West: ");

        TextField txtIter = new TextField("5000");
        TextField txtHeight = new TextField("75");
        TextField txtRise = new TextField("0.2");
        TextField txtStay = new TextField("0.1");
        TextField txtNorth = new TextField("0.1");
        TextField txtSouth = new TextField("0.1");
        TextField txtEast = new TextField("0.75");
        TextField txtWest = new TextField("0.05");

        //Set panelFour
        Button btnRun = new Button("Run simulation");
        btnRun.setPreferredSize(new Dimension(300,100));
        btnRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Integer intIter = Integer.valueOf(txtIter.getText());
                Integer intHeight = Integer.valueOf(txtHeight.getText());
                Double dblRise = Double.parseDouble(txtRise.getText());
                Double dblStay = Double.parseDouble(txtStay.getText());
                Double dblNorth = Double.parseDouble(txtNorth.getText());
                Double dblSouth = Double.parseDouble(txtSouth.getText());
                Double dblEast = Double.parseDouble(txtEast.getText());
                Double dblWest = Double.parseDouble(txtWest.getText());

                if ((dblRise + dblStay > 1) || (dblNorth + dblSouth + dblEast + dblWest) > 1) {
                    Dialog msg = new Dialog(frame, "Parameters sum to more tha 100%", FALSE);
                    msg.setPreferredSize(new Dimension(450,300));
                    Button btnDismiss = new Button("OK");
                    btnDismiss.setPreferredSize(new Dimension(300,100));
                    btnDismiss.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            msg.dispose();
                        }
                    });
                    msg.add(btnDismiss);
                    msg.setVisible(TRUE);
                } else {

                    Simulation sim = new Simulation();
                    sim.runSim(store,intIter,intHeight, dblRise, dblStay, dblNorth, dblSouth, dblEast);
                    sim.getDataAsImage(intIter);
                }



                //System.out.println("Running");
            }
        });

        // Set panelOne
        Label lblLoad = new Label("Load data from file: ");

        Button btnLoadFile = new Button("Open File");
        btnLoadFile.setPreferredSize(new Dimension(300,100));
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
                    panelFour.add(btnRun);
                //System.out.println("Running");
            }
        });

        //set panelThree
        /*
        public void paint(Graphics g) {
            Image image = sim.getDataAsImage(); // or equivalent
            g.drawImage(image, getInsets().left, getInsets().top, this);
        }

        */



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

        panelOne.add(lblLoad, BorderLayout.LINE_START);
        panelOne.add(btnLoadFile, BorderLayout.LINE_END);

        panelTwo.add(lblIter, BorderLayout.LINE_START);
        panelTwo.add(txtIter, BorderLayout.LINE_END);
        panelTwo.add(lblHeight, BorderLayout.LINE_START);
        panelTwo.add(txtHeight, BorderLayout.LINE_END);
        panelTwo.add(lblRise, BorderLayout.LINE_START);
        panelTwo.add(txtRise, BorderLayout.LINE_END);
        panelTwo.add(lblStay, BorderLayout.LINE_START);
        panelTwo.add(txtStay, BorderLayout.LINE_END);
        panelTwo.add(lblNorth, BorderLayout.LINE_START);
        panelTwo.add(txtNorth, BorderLayout.LINE_END);
        panelTwo.add(lblSouth, BorderLayout.LINE_START);
        panelTwo.add(txtSouth, BorderLayout.LINE_END);
        panelTwo.add(lblEast, BorderLayout.LINE_START);
        panelTwo.add(txtEast, BorderLayout.LINE_END);
        panelTwo.add(lblWest, BorderLayout.LINE_START);
        panelTwo.add(txtWest, BorderLayout.LINE_END);

        //panelTwo.add(btnGetVals);


        //panelFour.add(btnRun, BorderLayout.CENTER);

        frame.setVisible(true);

    }
    public static void main (String args[]) {
        new Analyst();
    }
}