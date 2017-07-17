/**
 * Created by ChrisDuffield on 09/07/2017.
 */
public class Storage {
    double[][] data = new double[10][10];

    void  setRandomData() {
        // Loop to initialise array with a random number between 0 and 1000
        for (int i = 0; i < data.length; i++ ){
            for (int j = 0; j < data[i].length; j++) {
                double var = Math.random() * 1000;
                data[i][j] = var ;
            }
        }
     }

    void printArray() {
        for (int i = 0; i < data.length; i++ ){
            for (int j = 0; j < data[i].length; j++) {

                System.out.print(data[i][j]+ " ");
            }
            System.out.println();
        }

    }

    double getMaximum() {
        /*
        Create a variable to hold the maximum value and then loop checking if the current value is higher
        and replacing it if it is.
        */
        double maximum = -1.0; // this is lower than the potential values in the array.

        for (int i = 0; i < data.length; i++ ){
            for (int j = 0; j < data[i].length; j++) {

                if (data[i][j] > maximum) {maximum = data[i][j];}
            }
        }
        return maximum;
    }

    double getMinimum() {
        /*
        Create a variable to hold the minimum value and then loop checking if the current value is lower
        and replacing it if it is.
        */
        double minimum = 1001.0; // this is lower than the potential values in the array.

        for (int i = 0; i < data.length; i++ ){
            for (int j = 0; j < data[i].length; j++) {

                if (data[i][j] < minimum) {minimum = data[i][j];}
            }
        }

        return minimum;
    }

}
