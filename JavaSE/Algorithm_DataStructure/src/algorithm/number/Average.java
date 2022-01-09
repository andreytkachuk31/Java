package algorithm.number;

/**
 * Average sum. Average is the sum of array elements divided by the number of elements
 * array[] = {1, 2, 3, 4, 5} = 3
 */
public class Average {

    public double average(int[] array) {
        int average = 0;

        for (int i = 0; i < array.length; i++) {
            average += array[i];
        }

        return average / array.length;
    }

    public double averageWithoutOverflow(int[] array) {
        double average = 0;

        for (int i = 0; i < array.length; i++) {
            average += (array[i] - average) / (i + 1);
        }

        return average / array.length;
    }
}
