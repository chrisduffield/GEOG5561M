import java.util.Arrays;

public class Analyst {

    public static void main(String[] args) {

        Storage store = new Storage();
    // Loop to initialise array with a random number between 0 and 1000
        for (int i = 0; i < store.data.length; i++ ){
            for (int j = 0; j < store.data[i].length; j++) {
                double var = Math.random() * 1000;
                store.data[i][j] = var ;
            }
        }
    /*
    Create a variable to hold the maximum value and then loop checking if the current value is higher
    and replacing it if it is.
    */
        double maximum = -1.0; // this is lower than the potential values in the array.

        for (int i = 0; i < store.data.length; i++ ){
            for (int j = 0; j < store.data[i].length; j++) {

                if (store.data[i][j] > maximum) {maximum = store.data[i][j];}
            }
        }
    /*
    Create a variable to hold the minimum value and then loop checking if the current value is lower
    and replacing it if it is.
    */
        double minimum = 1001.0; // this is lower than the potential values in the array.

        for (int i = 0; i < store.data.length; i++ ){
            for (int j = 0; j < store.data[i].length; j++) {

                if (store.data[i][j] < minimum) {minimum = store.data[i][j];}
            }
        }

        System.out.println("The maximum value is: " + maximum);
        System.out.println("The minimum value is: " + minimum);
    }
}
