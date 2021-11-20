public class Test {
    static String intArrayToString(int[] array, int leftBound, int rightBound) {
        StringBuilder builder = new StringBuilder();
        builder.append("[").append(array[leftBound]);
        for (int i = leftBound + 1; i <= rightBound; i++) {
            builder.append(", ").append(array[i]);
        }
        builder.append("]");
        return builder.toString();
    }
    static void merge(int[] array, int leftBound, int middle, int rightBound) {
        if (array[middle] > array[middle + 1]) {
            int[] leftHalf = new int[middle - leftBound + 1];
            int[] rightHalf = new int[rightBound - middle];
            System.arraycopy(array, leftBound, leftHalf, 0, middle - leftBound + 1);
            System.arraycopy(array, middle + 1, rightHalf, 0, rightBound - middle);
            int leftIndex = 0;
            int rightIndex = 0;
            int arrayIndex = leftBound;
            while (leftIndex < leftHalf.length && rightIndex < rightHalf.length) {
                if (leftHalf[leftIndex] < rightHalf[rightIndex]) {
                    array[arrayIndex] = leftHalf[leftIndex];
                    leftIndex++;
                } else {
                    array[arrayIndex] = rightHalf[rightIndex];
                    rightIndex++;
                }
                arrayIndex++;
            }
            System.arraycopy(leftHalf, leftIndex, array, arrayIndex, leftHalf.length - leftIndex);
            System.arraycopy(rightHalf, rightIndex, array, arrayIndex, rightHalf.length - rightIndex);
        }
    }
    static void mergeInPlace(int[] array, int leftBound, int middle, int rightBound) {
        System.out.println("leftBound=" + leftBound + " middle=" + middle + " rightBound=" + rightBound + " Merging: " + intArrayToString(array, leftBound, rightBound));
        if (array[middle] > array[middle + 1]) {
            int leftIndex = leftBound;
            int rightIndex = middle + 1;
            int arrayIndex = leftBound;
            if (rightBound - leftBound == 1) {
                int temp = array[leftIndex];
                array[leftIndex] = array[rightIndex];
                array[rightIndex] = temp;
            } else {
                while (leftIndex <= rightBound && rightIndex <= rightBound) {
                    if (array[leftIndex] >= array[rightIndex]) {
                        int temp = array[rightIndex];
                        System.arraycopy(array, arrayIndex, array, arrayIndex + 1, rightIndex - arrayIndex);
                        array[arrayIndex] = temp;
                        rightIndex++;
                    }
                    leftIndex++;
                    arrayIndex++;
                }
            }
        }
    }
    static void sort(int[] array, int leftBound, int rightBound, boolean inPlace) {
        if (leftBound < rightBound) {
            int middle = (leftBound + rightBound) / 2;
            sort(array, leftBound, middle, inPlace);
            sort(array, middle + 1, rightBound, inPlace);
            if (inPlace) {
                mergeInPlace(array, leftBound, middle, rightBound);
            } else {
                merge(array, leftBound, middle, rightBound);
            }
        }
    }
    public static void main(String[] args) {
        int[] array = new int[] { 5, 2, 4, 6, 1, 3, 2, 6 };
        sort(array, 0, array.length - 1, true);
        System.out.println("Sorted in place: " + intArrayToString(array, 0, array.length - 1));
        array = new int[] { 5, 2, 4, 6, 1, 3, 2, 6 };
        sort(array, 0, array.length - 1, false);
        System.out.println("Sorted: " + intArrayToString(array, 0, array.length - 1));
    }
}
