import java.util.Arrays;

public class Analyst {

    public Analyst() {

        Storage store = new Storage();

        store.setRandomData();
        store.printArray();
        System.out.println("Maximum value: " + store.getMaximum());
        System.out.println("Maximum value: " + store.getMinimum());

    }

    public static void main(String[] args) {
        new Analyst();



    }

}
