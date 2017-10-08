/**
 * Analyst.java 1.0 8 October 2017
 *
 * Copyright (c) School of Geography.
 * University of Leeds, Leeds, West Yorkshire, UK. LS2 9JT
 * All rights reserved.
 *
 * This code is provided under the Academic Academic Free License v. 3.0.
 * For details, please see the http://www.opensource.org/licenses/AFL-3.0.
 */

/**
 * This class processes an array of geographical data. It takes an initial
 * array with the site of the bomb and works through the process until it
 * generates an output file containing the spread of the bacteria.
 *
 * @author Student number 201088763
 * @version 1.0 8 October 2017
 *
 */

import java.awt.*;
import java.awt.image.*;
import java.awt.Point;

public class Storage {
    double[][] data = new double[300][300];
    double[][] map = new double[300][300];

    /**
     * This method reads in an array for processing.
      * @param dataIn The array returned by the IO.readData method
     */

    public void setData(double[][] dataIn) {
        data = dataIn;    }

    /**
     * This method loads the bomb site for the simulation.
      * @return A point representing the bomb site
     */
    public Point getBombSite() {
        Point p = new Point();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {

                if (data[i][j] == 255) {
                    p.setLocation(i, j);
                }
            }
        }
        return p;
    }

    /**
     * This method runs the simulation for each bacteria cell
     * @param startPosition The site of the bomb
     * @param height The initial height of the bomb
     * @param rise The chance that the cell will rise if it is above the height of the building
     * @param stay The chance that the cell will remain at the same level if it is above the height of the building
     * @param north The chance the cell will be blown North
     * @param south The chance the cell will be blown South
     * @param east The chance the cell will be blown East
     * @return The point representing the final position of the bacteria
     */

    public Point moveBacteria(Point startPosition, int height, double rise, double stay, double north, double south, double east){

        int x = (int)startPosition.getX();
        int y = (int)startPosition.getY();
        int z = height;

        while (z > 0){
            double ranh = Math.random();
            double randir = Math.random();

            if(z >= height) {
                if(ranh < rise) {z = z + 1;}
                else if(ranh < rise + stay) {}
                else {z = z - 1;}
            } else {z = z - 1;}

            if(randir < north) {y = y+1;}
            else if (randir < north + south){y = y-1;}
            else if (randir < north + south + east){x = x+1;}
            else {x = x-1;}
        }

        Point finalPosition = new Point(x, y);
        return finalPosition;


    }

    /**
     * This method returns the value of map at a particular point
     * @param p The point for which a value should be returned.
     * @return The value of the map array at point p
     */
    public double  getValue(Point p) {

        int  x = (int)p.getX();
        int  y = (int)p.getY();
        System.out.println(map[x][y]);
        return map[x][y];
    }

    /**
     * This method increments the map array by one each time a
     * cell lands at the point indicated by the moveBacteria method
     * @param p the point at which a cell lands
     */
    public void incrementData ( Point p) {
        int  x = (int)p.getX();
        int  y = (int)p.getY();
        if((x >= 0) && (x < 300) && (y >= 0) && (y < 300)) {
            map[x][y] = map[x][y] + 1.0;
        }
    }

    /**
     * This method gets the maximum value of the map array so it can be used to
     * rebase the array for the image.
     * @return The maximum value in map
     */
    public double  getMax() {
        double  maxVal = 0.0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] > maxVal) {
                    maxVal = map[i][j];
                }
            }
        }
        System.out.println("Maximum value " + maxVal);
        return maxVal;
    }

    /**
     * This method gets the minimum value of the map array so it can be used to
     * rebase the array for the image.
     * @param iter The number of iterations in the simulation, this is used to ensure the
     *             correct initial value is set.
     * @return The minimum value in map
     */
    public double  getMin(int iter) {
        double  minVal = iter + 1.0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] < minVal) {
                    minVal = map[i][j];
                }
            }
        }
        System.out.println("Minimum value " + minVal);
        return minVal;
    }

    /**
     * This method runs the simulation by looping through the helper methods
     * @param iter The number of iterations the simulation undertakes
     * @param height The initial height of the bomb
     * @param rise The chance that the cell will rise if it is above the height of the building
     * @param stay The chance that the cell will remain at the same level if it is above the height of the building
     * @param north The chance the cell will be blown North
     * @param south The chance the cell will be blown South
     * @param east The chance the cell will be blown East
     * @return The map array
     */
    public double[][] runSim (int iter, int height, double rise, double stay, double north, double south, double east) {

        for (int i = 0; i < iter; i++) {
            incrementData(moveBacteria(getBombSite(), height, rise, stay, north, south, east));
        }
        return map;
    }

    /**
     * This function re-calculates the array values so that they are between 0 - 255 so that they
     * can be used to generate RGB colours
     * @param minVal The minimum value in the map array
     * @param maxVal The maximum value in the map array
     * @return The map array re-ranged
     */
    public double[][] getReRangedData (double  minVal, double  maxVal){

        double  [][] tempArray = new double [map.length][map[0].length];

        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                tempArray[i][j] = map[i][j];
                tempArray[i][j] = tempArray[i][j] - minVal;

                tempArray[i][j] = tempArray[i][j] / (maxVal - minVal);
                tempArray[i][j] = tempArray[i][j] * 255.0;
            }
        }

        return tempArray;
    }

    /**
     * This method re-shapes the array to a one dimensional array suitable
     * for processing into an image
     * @param iter The number of iterations the simulation undertakes
     * @return The one dimensional representation of the re-ranged map
     */
    public double[] get1DArray (int iter){
        double [][] reranged = getReRangedData(getMin(iter),getMax());
        double [] tempArray = new double[reranged.length * reranged[0].length];
        for (int i = 0; i < reranged.length; i++){
            for (int j = 0; j < reranged[0].length; j++){
                tempArray[(i * reranged[0].length) + j] = reranged[i][j];
            }
        }
        return tempArray;
    }

    /**
     * This method changes the data from a one dimensional array to an image,
     * draws it into the panel and saves the file.
     * @param iter The number of iterations the simulation undertakes
     * @param panel The panel to display the calculated image on
     */
    public void getDataAsImage(int iter, Panel panel) {
        double [] tempArray = get1DArray(iter);
        int[] pixels = new int[tempArray.length];
        int maxXval = 0;
        int minXval = 256;
        int sumXval = 0;
        for(int i = 0; i < tempArray.length; i++){
            int x = (int)tempArray[i];
            Color color = new Color(x,0, (255-x));
            if (x > maxXval) {maxXval = x;}
            if (x < minXval) {minXval = x;}
            sumXval = sumXval + x;
            pixels[i] = color.getRGB();
        }
        System.out.println("Minimum X value " + minXval);
        System.out.println("Maximum X value " + maxXval);
        System.out.println("Sum X value " + sumXval);
        System.out.println("Mean X value " + (double)sumXval/(double)iter);

        MemoryImageSource memImage = new MemoryImageSource(300, 300, pixels, 0 , 16);

        Graphics g = panel.getGraphics();
        Image img = panel.createImage(memImage);
        g.drawImage(img,300,10, null);
        panel.setVisible(true);
        String pathname = System.getProperty("user.dir");
        IO.writeImage (img, pathname + "/output.png");

    }
}
