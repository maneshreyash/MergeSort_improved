/** Sample starter code for SP9.
 *  @author
 */

package ssm170730;
import java.util.Random;

public class SP9 {
    public static Random random = new Random();
    public static int numTrials = 100;
    private static int T = 77;
    public static void main(String[] args) {
        int n = 10;  int choice = 1 + random.nextInt(4);
        if(args.length > 0) { n = Integer.parseInt(args[0]); }
        if(args.length > 1) { choice = Integer.parseInt(args[1]); }
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = i;
        }
        Timer timer = new Timer();
        switch(choice) {
            case 1:
                Shuffle.shuffle(arr);
                numTrials = 1;
                insertionSort(arr);
                break;
            case 2:
                for(int i=0; i<numTrials; i++) {
                    Shuffle.shuffle(arr);
                    mergeSort1(arr);
                }
                break;
            case 3:
                for(int i=0; i<numTrials; i++) {
                    Shuffle.shuffle(arr);
                    mergeSort2(arr);
                }
                break;
            case 4:
                for(int i=0; i<numTrials; i++) {
                    Shuffle.shuffle(arr);
                    mergeSort3(arr);
                }
                break;
        }
        timer.end();
        timer.scale(numTrials);
        System.out.println("Choice: " + choice + "\n" + timer);
    }


    /**
     *
     * @param arr: Array to be sorted.
     */
    public static void insertionSort(int[] arr) {
        insertionSort(arr, 0, (arr.length-1));
    }

    /**
     *
     * @param arr The array that needs sorting.
     * @param p from what index to sort
     * @param r to what index sorting should be done.
     */
    private static void insertionSort(int[] arr, int p, int r){
        int temp, j;
        for (int i = p + 1; i <= r; i++) {
            temp = arr[i];
            j = i - 1;
            while(j >= p && temp < arr[j]){
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = temp;
        }
    }



    /**
     * Take 1 on merge sort. Divide and conquer recursion performed till the end.
     * @param arr Array to be sorted.
     */
    public static void mergeSort1(int[] arr) {
        mergeSort1(arr, 0, (arr.length-1));
    }


    /**
     *
     * @param arr Input array for sorting.
     * @param p index at which sorting has to begin.
     * @param r index at which sorting has to end.
     */
    private static void mergeSort1(int[] arr, int p, int r){
        if(p < r){
            int q = (p + r) / 2;
            mergeSort1(arr, p , q);
            mergeSort1(arr, (q + 1), r);
            merge1(arr, p, q, r);
        }
    }

    /**
     *
     * @param arr Input array for sorting.
     * @param p index at which sorting has to begin.
     * @param q mid of arr.
     * @param r index at which sorting has to end.
     */
    private static void merge1(int[] arr, int p, int q, int r){
        int[] larr = new int[q - p + 1];
        int[] rarr = new int[r - q];
        System.arraycopy(arr, p, larr, 0, q - p + 1);
        System.arraycopy(arr, q + 1, rarr, 0, r - q);
        int i = 0;
        int j = 0;
        for(int k = p; k <= r; k++){
            if(j >= rarr.length || (i < larr.length && larr[i] <= rarr[j])){
                arr[k] = larr[i++];
            }else {
                arr[k] = rarr[j++];
            }
        }
    }

    /**
     * Take 2 on merge sort. Divide and conquer recursion performed till the end.
     * @param arr Array to be sorted.
     */

    public static void mergeSort2(int[] arr) {
        int[] brr = new int[arr.length];
        System.arraycopy(arr, 0, brr, 0, arr.length);
        mergeSort2(arr, brr, 0, arr.length);
    }

    /**
     *
     * @param arr first half of array to be sorted.
     * @param brr other half of array to be sorted.
     * @param left from where sorting has to start.
     * @param n number of elements starting from left.
     */
    private static void mergeSort2(int[] arr, int[] brr, int left, int n){
        if(n < T){
            insertionSort(arr, left, left + n - 1);
        }
        else{
            int ln = n / 2;
            mergeSort2(arr, brr, left, ln);
            mergeSort2(arr, brr, left + ln, n - ln);
            merge2(arr, brr, left, left + ln - 1, left + n - 1);
        }
    }

    /**
     *
     * @param arr Input half array for sorting.
     * @param p index at which sorting has to begin.
     * @param q mid point of arr
     * @param r index at which sorting has to end.
     * @param brr other half array to be sorted.
     */
    private static void merge2(int[] arr, int[] brr, int p, int q, int r){
        System.arraycopy(arr, p, brr, p, r - p + 1);
        int i = p;
        int j = q + 1;
        for(int k = p; k <= r; k++){
            if(j > r || (i <= q && brr[i] <= brr[j])){
                arr[k] = brr[i++];
            }else{
                arr[k] = brr[j++];
            }
        }
    }


    /**
     * Take 3 on merge sort. Divide and conquer recursion performed till the end.
     * @param arr Array to be sorted.
     */

    public static void mergeSort3(int[] arr) {
        int[] brr = new int[arr.length];
        System.arraycopy(arr, 0, brr, 0, arr.length);
        mergeSort3(arr, brr, 0, arr.length);
    }

    /**
     *
     * @param arr first half of array to be sorted.
     * @param brr other half of array to be sorted.
     * @param left from where sorting has to start.
     * @param n number of elements starting from left.
     */
    private static void mergeSort3(int[] arr, int[] brr, int left, int n){
        if(n < T){
            insertionSort(arr, left, left + n - 1);
        }
        else{
            int ln = n / 2;
            mergeSort2(brr, arr, left, ln);
            mergeSort2(brr, arr, left + ln, n - ln);
            merge3(arr, brr, left, left + ln - 1, left + n - 1);
        }
    }

    /**
     *
     * @param arr Input half array for sorting.
     * @param p index at which sorting has to begin.
     * @param q mid point of arr
     * @param r index at which sorting has to end.
     * @param brr other half array to be sorted.
     */
    private static void merge3(int[] arr, int[] brr, int p, int q, int r){
        int i = p;
        int j = q + 1;
        int k = p;
        while(i <= q && j <= r){
            if(brr[i] <= brr[j]){
                arr[k++] = brr[i++];
            }else{
                arr[k++] = brr[j++];
            }
        }
        while(i <= q){
            arr[k++] = brr[i++];
        }
        while(j <= r){
            arr[k++] = brr[j++];
        }
    }

    /** Timer class for roughly calculating running time of programs
     *  @author rbk
     *  Usage:  Timer timer = new Timer();
     *          timer.start();
     *          timer.end();
     *          System.out.println(timer);  // output statistics
     */

    public static class Timer {
        long startTime, endTime, elapsedTime, memAvailable, memUsed;
        boolean ready;

        public Timer() {
            startTime = System.currentTimeMillis();
            ready = false;
        }

        public void start() {
            startTime = System.currentTimeMillis();
            ready = false;
        }

        public Timer end() {
            endTime = System.currentTimeMillis();
            elapsedTime = endTime-startTime;
            memAvailable = Runtime.getRuntime().totalMemory();
            memUsed = memAvailable - Runtime.getRuntime().freeMemory();
            ready = true;
            return this;
        }

        public long duration() { if(!ready) { end(); }  return elapsedTime; }

        public long memory()   { if(!ready) { end(); }  return memUsed; }

        public void scale(int num) { elapsedTime /= num; }

        public String toString() {
            if(!ready) { end(); }
            return "Time: " + elapsedTime + " msec.\n" + "Memory: " + (memUsed/1048576) + " MB / " + (memAvailable/1048576) + " MB.";
        }
    }

    /** @author rbk : based on algorithm described in a book
     */


    /* Shuffle the elements of an array arr[from..to] randomly */
    public static class Shuffle {

        public static void shuffle(int[] arr) {
            shuffle(arr, 0, arr.length-1);
        }

        public static<T> void shuffle(T[] arr) {
            shuffle(arr, 0, arr.length-1);
        }

        public static void shuffle(int[] arr, int from, int to) {
            int n = to - from  + 1;
            for(int i=1; i<n; i++) {
                int j = random.nextInt(i);
                swap(arr, i+from, j+from);
            }
        }

        public static<T> void shuffle(T[] arr, int from, int to) {
            int n = to - from  + 1;
            Random random = new Random();
            for(int i=1; i<n; i++) {
                int j = random.nextInt(i);
                swap(arr, i+from, j+from);
            }
        }

        static void swap(int[] arr, int x, int y) {
            int tmp = arr[x];
            arr[x] = arr[y];
            arr[y] = tmp;
        }

        static<T> void swap(T[] arr, int x, int y) {
            T tmp = arr[x];
            arr[x] = arr[y];
            arr[y] = tmp;
        }

        public static<T> void printArray(T[] arr, String message) {
            printArray(arr, 0, arr.length-1, message);
        }

        public static<T> void printArray(T[] arr, int from, int to, String message) {
            System.out.print(message);
            for(int i=from; i<=to; i++) {
                System.out.print(" " + arr[i]);
            }
            System.out.println();
        }
    }
}

