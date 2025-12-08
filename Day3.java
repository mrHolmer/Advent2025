import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Day3 {
    public static int largest = -1;
    public static int largestIndex = -1;
    public static int secondLargest = -1;
    public static int secondLargestIndex = -1;
    public static void main(String[] args) throws FileNotFoundException{
        long sum = 0;
        File f = new File("Day3.txt");
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String battery = s.next();
            largest(battery);
            secondLargest(battery);
            int joltage = getJoltage();
            sum += joltage;
        }
        System.out.println(sum);
        sum = 0;
        s = new Scanner(f);
        while (s.hasNext()) {
            sum += getBank(s.next());
        }
        System.out.println(sum);
    }
    public static long getBank(String s) {
        String battery = "";
        int index = -1;
        for (int i = 12; i > 0; i--) {
            index = maxIndex(s, i, index);
            battery += s.substring(index, index + 1);
        }
        return Long.parseLong(battery);
    }
    public static int maxIndex(String s, int start, int end)
    {
        int maxIndex = s.length() - start;
        int max = Integer.parseInt(s.substring(maxIndex, maxIndex + 1));
        int i = maxIndex;
        while (i > end) {
            int possible = (Integer.parseInt(s.substring(i, i + 1)));
            if (possible >= max) {
                max = possible;
                maxIndex = i;
            }
            i--;
        }
        return maxIndex;
    }
    public static int getJoltage() {
        if (largestIndex < secondLargestIndex) return largest * 10 + secondLargest;
        else return secondLargest * 10 + largest;
    }
    public static void largest(String s) {
        largestIndex = -1;
        largest = -1;
        for (int i = 0; i < s.length(); i++) {
            int possible = Integer.parseInt(s.substring(i, i + 1));
            if (possible > largest) {
                largest = possible;
                largestIndex = i;
            }
        }
    }
    public static void secondLargest(String s) {
        int possibleIndex = largestIndex + 1;
        if (possibleIndex == s.length()) possibleIndex = 0;
        secondLargest = -1;
        while(possibleIndex < s.length()) {
            int possible = Integer.parseInt(s.substring(possibleIndex, possibleIndex + 1));
            if (possible > secondLargest && possibleIndex != largestIndex) {
                secondLargest = possible;
                secondLargestIndex = possibleIndex;
            }
            possibleIndex++;
        }
    }
}