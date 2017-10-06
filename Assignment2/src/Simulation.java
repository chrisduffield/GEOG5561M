import java.awt.*;
import java.awt.image.*;

public class Simulation {
    Double [][] data = new Double [300][300];

    public Double  getValue(Point p) {
        Integer  x = (int)p.getX();
        Integer  y = (int)p.getY();
        return data[x][y];
    }

    public void incrementData ( Point p) {
        Integer  x = (int)p.getX();
        Integer  y = (int)p.getY();
        data[x][y] = getValue(p) + 1;
    }

    public Double  getMax() {
        Double  maxVal = 0.0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] > maxVal) {
                    maxVal = data[i][j];
                }
            }
        }
        return maxVal;
    }

    public Double  getMin(int iter) {
        Double  minVal = iter + 1.0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] < minVal) {
                    minVal = data[i][j];
                }
            }
        }
        return minVal;
    }

    public Double[][] runSim (Storage store, int iter, int height, double rise, double stay, double north, double south, double east) {

        for (int i = 0; i < iter; i++) {
            incrementData(store.moveBacteria(store.getBombSite(), height, rise, stay, north, south, east));

        }
        return data;
    }

    public Double[][] getReRangedData (Double  minVal, Double  MaxVal){

        Double  [][] tempArray = new Double [data.length][data[0].length];

        for (int i = 0; i < data.length; i++){
            for (int j = 0; j < data[0].length; j++){
                tempArray[i][j] = data[i][j];
                tempArray[i][j] = tempArray[i][j] - minVal;

                tempArray[i][j] = tempArray[i][j] / (MaxVal - minVal);
                tempArray[i][j] = tempArray[i][j] * 255.0;
            }
        }

        return tempArray;
    }

    public Double[] get1DArray (int iter){
        Double [][] reranged = getReRangedData(getMin(iter),getMax());
        Double [] tempArray = new Double[reranged.length * reranged[0].length];
        for (int i = 0; i < data.length; i++){
            for (int j = 0; j < data[0].length; j++){
                tempArray[(i * reranged[0].length) + j] = data[i][j];
            }
        }
        return tempArray;
    }

    public Image getDataAsImage(int iter) {
        Double [] tempArray = get1DArray(iter);
        int[] pixels = new int[tempArray.length];
        for(int i = 0; i < tempArray.length; i++){
            int x = tempArray[i].intValue();
            Color color = new Color(x,x,x);
            pixels[i] = color.getRGB();
        }
        MemoryImageSource memImage = new MemoryImageSource(data.length, data[0].length, pixels, 0 , 0);
        Panel panel = new Panel();
        Image image = panel.createImage(memImage);

        return image;
    }


}
