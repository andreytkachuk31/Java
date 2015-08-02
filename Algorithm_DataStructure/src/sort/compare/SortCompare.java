package sort.compare;

import sort.*;

/**
 * Date: 21.05.13
 *
 * @author: andrey.tkachuk31
 */
public class SortCompare {

    public static double time(SortAlgorithm sortAlgorithm, Double[] data) {
        double start = System.currentTimeMillis();
        switch (sortAlgorithm) {
            case STUPID:
                StupidSort.sort(data);
                break;
            case SELECTION:
                SelectionSort.sort(data);
                break;
            case INSERTION:
                InsertionSort.sort(data);
                break;
            case SHELL:
                ShellSort.sort(data);
            case MERGE:
                MergeSort.sort(data);
        }
        double end = System.currentTimeMillis();
        return end - start;
    }

    public static double timeRandomInput(SortAlgorithm sortAlgorithm, int N, int T) {
        double total = 0;
        Double[] a = new Double[N];
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < N; j++) {
                a[j] = Math.random();
            }
            total += time(sortAlgorithm, a);
        }
        return total;
    }

}
