import java.awt.*;
import java.awt.image.*;
import java.awt.Point;
import java.util.Arrays;

public class Storage {
    double[][] data = new double[300][300];
    double[][] map = new double[300][300];

    public void setData(double[][] dataIn) {
        data = dataIn;    }

    public double[][] getData () {
        return data;
    }

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

    public double  getValue(Point p) {

        int  x = (int)p.getX();
        int  y = (int)p.getY();
        System.out.println(map[x][y]);
        return map[x][y];
    }

    public void incrementData ( Point p) {
        int  x = (int)p.getX();
        int  y = (int)p.getY();
        if((x >= 0) && (x < 300) && (y >= 0) && (y < 300)) {
            map[x][y] = map[x][y] + 1.0;
        }
    }

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

    public double[][] runSim (int iter, int height, double rise, double stay, double north, double south, double east) {

        for (int i = 0; i < iter; i++) {
            incrementData(moveBacteria(getBombSite(), height, rise, stay, north, south, east));
        }
        return map;
    }

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

    public void getDataAsImage(int iter, Panel panel) {
        double [] tempArray = get1DArray(iter);
        int[] pixels = new int[tempArray.length];
        for(int i = 0; i < tempArray.length; i++){
            int x = (int)tempArray[i];
            Color color = new Color(x,0, (255-x));
            pixels[i] = color.getRGB();
        }
        MemoryImageSource memImage = new MemoryImageSource(300, 300, pixels, 0 , 16);

        Graphics g = panel.getGraphics();
        Image img = panel.createImage(memImage);
        g.drawImage(img,300,10, null);
        panel.setVisible(true);
    }

}
