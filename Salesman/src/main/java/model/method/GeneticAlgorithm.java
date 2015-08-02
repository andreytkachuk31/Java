package model.method;

import java.util.*;

/**
 * Date: 14.05.13
 *
 * @author andrey.tkachuk
 */
public class GeneticAlgorithm {
    private static Random rnd = new Random();
    private int matrix[][] = new int[][]{
            {-1, 5, 1, 10, 29},
            {8, -1, 24, 2, 9},
            {12, 7, -1, 57, 3},
            {9, 3, 11, -1, 20},
            {9, 1, 11, 10, -1}
    };
    private int[] bestPath;
    private int generationsEvolved = 0;

    public static int[] getRandomPermutation(int n) {
        int[] res = new int[n - 1];
        for (int i = 1; i < n; i++) {
            res[i - 1] = i;
        }
        for (int i = res.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            swap(res, i, j);
        }
        return res;
    }

    private Map<Integer, Integer> groupPath(int[] result) {
        Map<Integer, Integer> path = new HashMap<Integer, Integer>();
        for (int i = 0; i < result.length - 1; i++) {
            path.put(result[i], result[i + 1]);
        }
        return path;
    }

    private double eval(int[] genom) {
        double sum = 0;
        Map<Integer, Integer> path = groupPath(addZero(genom));
        //System.out.println(Arrays.toString(addZero(genom)));
        for (Map.Entry<Integer, Integer> value : path.entrySet()) {
            sum += matrix[value.getKey()][value.getValue()];
        }
        return sum;
    }

    class Chromosome implements Comparable<Chromosome> {
        final int[] genom;
        private double cost = Double.NaN;

        public Chromosome(int[] genom) {
            this.genom = genom;
        }

        public double getCost() {
            if (Double.isNaN(cost)) {
                cost = eval(genom);
            }
            return cost;
        }

        @Override
        public int compareTo(Chromosome other) {
            return Double.compare(getCost(), other.getCost());
        }
    }

    static class Population {
        List<Chromosome> chromosomes = new ArrayList<Chromosome>();
        final int populationLimit;

        public Population(int populationLimit) {
            this.populationLimit = populationLimit;
        }

        public void nextGeneration() {
            Collections.sort(chromosomes);
            chromosomes = new ArrayList<Chromosome>(chromosomes.subList(0, (chromosomes.size() + 1) / 2));
        }
    }

    int[][] crossOver(int[] genom1, int[] genom2) {
        int n = genom1.length - 1;
        int i1 = rnd.nextInt(n);
        int i2 = (i1 + 1 + rnd.nextInt(n - 1)) % n;

        int[] n1 = genom1.clone();
        int[] n2 = genom2.clone();

        boolean[] used1 = new boolean[n + 2];
        boolean[] used2 = new boolean[n + 2];

        for (int i = i1; ; i = (i + 1) % n) {
            n1[i] = genom2[i];
            used1[n1[i]] = true;
            n2[i] = genom1[i];
            used2[n2[i]] = true;
            if (i == i2) {
                break;
            }
        }

        for (int i = (i2 + 1) % n; i != i1; i = (i + 1) % n) {
            if (used1[n1[i]]) {
                n1[i] = -1;
            } else {
                used1[n1[i]] = true;
            }
            if (used2[n2[i]]) {
                n2[i] = -1;
            } else {
                used2[n2[i]] = true;
            }
        }

        int pos1 = 0;
        int pos2 = 0;
        for (int i = 0; i < n; i++) {
            if (n1[i] == -1) {
                while (used1[pos1])
                    ++pos1;
                n1[i] = pos1++;
            }
            if (n2[i] == -1) {
                while (used2[pos2])
                    ++pos2;
                n2[i] = pos2++;
            }
        }
        return new int[][]{n1, n2};
    }

    void mutate(int[] p) {
        int n = p.length;
        int i = rnd.nextInt(n);
        int j = (i + 1 + rnd.nextInt(n - 1)) % n;
        if (rnd.nextBoolean()) {
            swap(p, i, j);
        } else {
            // reverse order from i to j
            int sign = i - j;
            while (sign * (i - j) > 0) {
                swap(p, i, j);
                i = (i + 1) % n;
                j = (j - 1 + n) % n;
            }
        }
    }

    public void geneticAlgorithm() {
        final int n = matrix.length;
        final int populationLimit = 100;
        final Population population = new Population(populationLimit);
        for (int i = 0; i < populationLimit; i++) {
            population.chromosomes.add(new Chromosome(getRandomPermutation(n)));
        }

        final double mutationRate = 0.3;
        final int generations = 1000;

        for (int i = 0; i < generations; i++) {
            while (population.chromosomes.size() < population.populationLimit) {
                int i1 = rnd.nextInt(population.chromosomes.size());
                int i2 = (i1 + 1 + rnd.nextInt(population.chromosomes.size() - 1)) % population.chromosomes.size();

                Chromosome ch1 = population.chromosomes.get(i1);
                Chromosome ch2 = population.chromosomes.get(i2);

                int[][] pair = crossOver(ch1.genom, ch2.genom);
                ch1 = new Chromosome(pair[0]);
                ch2 = new Chromosome(pair[1]);

                if (rnd.nextDouble() < mutationRate) {
                    mutate(ch1.genom);
                    mutate(ch2.genom);
                }

                population.chromosomes.add(ch1);
                if (population.chromosomes.size() < population.populationLimit) {
                    population.chromosomes.add(ch2);
                }
            }
            population.nextGeneration();
            if (isOnlyUniqueValues(population.chromosomes.get(0).genom)) {
                bestPath = population.chromosomes.get(0).genom;
            } else {
                break;
            }
            generationsEvolved = i + 1;
        }
    }

    private int[] addZero(int[] arr) {
        int result[] = new int[arr.length + 2];
        for (int i = 0; i < arr.length; i++) {
            result[i + 1] = arr[i];
        }
        return result;
    }

    private static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private boolean isOnlyUniqueValues(int arr[]) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (list.contains(arr[i])) {
                return false;
            }
            list.add(arr[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        geneticAlgorithm.geneticAlgorithm();
        System.out.println(geneticAlgorithm.groupPath(geneticAlgorithm.addZero(geneticAlgorithm.bestPath)));
    }
}
