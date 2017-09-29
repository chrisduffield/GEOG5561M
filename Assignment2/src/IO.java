import java.awt.*;
import java.io.*;
import java.util.StringTokenizer;

public class IO {

    public static double[][] readData(String myFile) {
        double[][] data = null;

        BufferedReader br = null;
        File f = new File(myFile);

// Count the number of lines.

        try {
            br = new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int lines = -1;
        String textIn = " ";
        String[] file = null;

        try {
            while (textIn != null) {
                textIn = br.readLine();
                lines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }

// Reopen the file at the top and read the
// file into a String array of the right size.

        try {
            br = new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        file = new String[lines];

        try {
            for (int i = 0; i < lines; i++) {
                file[i] = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }

// Convert each line into an array of double data.

        data = new double [lines][];

        for (int i = 0; i < lines; i++) {
            StringTokenizer st = new StringTokenizer(file[i],", ");
            data[i] = new double[st.countTokens()];
            int j = 0;
            while (st.hasMoreTokens()) {
                data[i][j] = Double.parseDouble(st.nextToken());
                j++;
            }
        }

// Check data.

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print (data[i][j] + ", ");
                data[i][j]++;
            }
            System.out.println("");
        }

        return data;
    }
    public static void writeData(double[][] dataIn) {
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter (new FileWriter(new File("c:/out.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String tempStr = "";

        try{
            for (int i = 0; i < dataIn.length; i++) {
                for (int j = 0; j < dataIn[i].length; j++) {

                    tempStr = String.valueOf(dataIn[i][j]);
                    System.out.print(tempStr + ", ");
                    bw.write(tempStr + ", ");

                }
                System.out.println("");
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
