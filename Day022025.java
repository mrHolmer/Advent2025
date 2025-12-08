import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Day022025 {
    public static void main(String[] args) throws FileNotFoundException{
        File f = new File("202502.txt");
        Scanner s = new Scanner(f);
        long invalids = 0;
        String str = s.next();
        String[] tokens = str.split(",");
        for (String range : tokens) {
            int dash = range.indexOf("-");
            String low = range.substring(0, dash);
            String high = range.substring(dash + 1);
            long start = Long.parseLong(low);
            long end = Long.parseLong(high);
            while (start <= end) {
                invalids += checkAll(start + "");
                start++;
            }
        }
        System.out.println(invalids);
    }

    public static long isInvalid(String s) {
        if (s.length() % 2 != 0) return 0;
        int half = s.length() / 2;
        String front = s.substring(0, half);
        String back = s.substring(half);
        if (front.equals(back)) return Long.parseLong(s);
        return 0;
    }
    public static long isInvalid(String s, int length) {
        if (s.length() % length != 0) return 0;
        String block = s.substring(0, length);
        for(int i = length; i <= s.length() - length; i += length) {
            String potential = s.substring(i, i + length);
            if (!block.equals(potential)) return 0;
        }
        return Long.parseLong(s);
    }
    public static long checkAll(String s)
    {
        for (int i = 1; i <= s.length() / 2; i++) {
            long toAdd = isInvalid(s, i);
            if (toAdd > 0) return toAdd;
        }
     
        return 0;
    }

}
