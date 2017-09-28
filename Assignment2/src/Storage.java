public class Storage {
    double[][] data = new double[300][300];

    void  setData(double[][] inpurData) {
        // Loop to initialise array with a random number between 0 and 1000
        for (int i = 0; i < data.length; i++ ){
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = inpurData[i][j] ;
            }
        }
    }
}
