import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.StringTokenizer;

public class IO {

    /**
     * This method reads a file into a two dimensional array
     * @param myFile The name of the file to read
     * @return A two dimensional array of doubles
     */
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


        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print (data[i][j] + ", ");
                data[i][j]++;
            }
            System.out.println("");
        }

        return data;
    }

    /**
     * This method saves the array to a file.
     * @param dataOut The array to be saved
     */
    public static void writeData(Double[][] dataOut) {
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter (new FileWriter(new File("c:/out.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String tempStr = "";

        try{
            for (int i = 0; i < dataOut.length; i++) {
                for (int j = 0; j < dataOut[i].length; j++) {

                    tempStr = String.valueOf(dataOut[i][j]);
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

    /**
     * THis method saves the image to a file
     * @param img The image to be saved
     * @param pathname The directory to same output.png to
     */
    public static void writeImage (Image img, String pathname) {
        try {

            BufferedImage bufImage = new BufferedImage(300, 300, BufferedImage.TYPE_BYTE_BINARY);
            bufImage.getGraphics().drawImage(img, 0, 0 , null);
            File outputFile = new File(pathname);
            outputFile.createNewFile();
            ImageIO.write(bufImage, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
