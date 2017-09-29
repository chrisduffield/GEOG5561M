import java.awt.Point;


public class Storage {
    double[][] data = new double[300][300];

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
                System.out.println();
            }

        }
        return p;
    }

    public Point moveBacteria(Point startPosition, int numIter, double north, double south, double east){

        int x = (int)startPosition.getX();
        int y = (int)startPosition.getY();

        for (int i = 0 ; i < numIter; i++){
            double ran = Math.random();
            if(ran < north) {y = y+1;}
            else if (ran < north + south){y = y-1;}
            else if (ran < north + south + east){x = x+1;}
            else {x = x-1;}
        }

        Point finalPosition = new Point(x,y);
        return finalPosition;
    }

}
