package viber;

public class AgeSort {

    public void sortAges(int[] ages) {
        int[] newArray = new int[100];

        // O(N)
        for (int i = 0; i < ages.length; i++) {
            int age = ages[i];
            newArray[age]++;
        }

        // O(N)
        for (int i = 0; i < newArray.length; i++) {
            if (newArray[i] != 0) {
                for (int j = 0; j < newArray[i]; j++) {
                    System.out.print(i + " ");
                }
            }
        }
    }
}
