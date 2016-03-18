package model.method;

import java.util.*;

/**
 * Date: 11.05.13
 *
 * @author andrey.tkachuk
 */
public class BruteForce {

    private List<Map<Integer, Integer>> listPaths = new ArrayList<Map<Integer, Integer>>();

    public Map<Integer, Integer> calculateBestPathByBruteForce(int[][] matrix) {
        long start = System.currentTimeMillis();
        int length = matrix.length - 1;
        listPaths = generatePermutations(new int[length], 1);
        Map<Integer, Map<Integer, Integer>> paths = getPaths(matrix, listPaths);
        Map<Integer, Integer> bestPath = paths.get(Collections.min(paths.keySet()));
        long time = System.currentTimeMillis() - start;
        System.out.println("Time calculate best path: " + time + " ms");
        return bestPath;
    }

    private Map<Integer, Integer> groupPath(int[] result) {
        Map<Integer, Integer> path = new HashMap<Integer, Integer>();
        for (int i = 0; i < result.length - 1; i++) {
            path.put(result[i], result[i + 1]);
        }
        return path;
    }

    private int[] plusOne(int[] arr) {
        int result[] = new int[arr.length + 2];
        for (int i = 0; i < arr.length; i++) {
            result[i + 1] = arr[i] + 1;
        }
        return result;
    }

    private Map<Integer, Map<Integer, Integer>> getPaths(int[][] matrix, List<Map<Integer, Integer>> listPaths) {
        Map<Integer, Map<Integer, Integer>> paths = new TreeMap<Integer, Map<Integer, Integer>>();
        for (Map<Integer, Integer> path : listPaths) {
            int sum = 0;
            for (Map.Entry<Integer, Integer> value : path.entrySet()) {
                sum += matrix[value.getKey()][value.getValue()];
            }
            paths.put(sum, path);
        }
        return paths;
    }

    private List<Map<Integer, Integer>> generatePermutations(int[] arr, int depth) {
        int n = arr.length;
        if (depth == n) {
            listPaths.add(groupPath(plusOne(arr)));
            return listPaths;
        }
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                arr[i] = depth;
                generatePermutations(arr, depth + 1);
                arr[i] = 0;
            }
        }
        return listPaths;
    }
}
