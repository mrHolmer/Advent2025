import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Day5 {
    public static void main(String[] args) throws FileNotFoundException{
        File f = new File("Day5.txt");
        Scanner ranges = new Scanner(f);
        f = new File("Ingredients.txt");
        Scanner ingredients = new Scanner(f);
        int fresh = 0;
        while (ingredients.hasNext()) {
            String ingredient = ingredients.next();
            if (inRange(ingredient)) fresh++;
        }
        System.out.println(fresh);

        f = new File("Ranges.txt");
        ranges = new Scanner(f);
        ArrayList<String> freshRanges = new ArrayList<String>();
        while (ranges.hasNext()) {
            freshRanges.add(ranges.next());
        }
        for(int i = 0; i < freshRanges.size() - 1; i++) {
            String one = freshRanges.get(i);
            String two = freshRanges.get(i + 1);
            int dash1 = one.indexOf("-");
            long low1 = Long.parseLong(one.substring(0, dash1));
            long high1 = Long.parseLong(one.substring(dash1 + 1));
            int dash2 = two.indexOf("-");
            long low2 = Long.parseLong(two.substring(0, dash2));
            long high2 = Long.parseLong(two.substring(dash2 + 1));
            if (high1 >= low2 && high2 >= high1) {
                String newRange = low1 + "-" + high2;
                freshRanges.set(i, newRange);
                freshRanges.remove(i + 1);
                i--;
            }
            else if (high2 <= high1) {
                freshRanges.remove(i + 1);
                i--;
            }
        }
        System.out.println(freshRanges);
        long freshes = 0;
        for (String s : freshRanges) {
            freshes += range(s);
            System.out.println(freshes);
        }
        System.out.println(freshes);
    }
    public static long range(String range) {
        int dash = range.indexOf("-");
        long low = Long.parseLong(range.substring(0, dash));
        long high = Long.parseLong(range.substring(dash + 1));
        return high - low + 1;
    }
    public static boolean inRange(String ingredient) throws FileNotFoundException{
        File f = new File("Day5.txt");
        Scanner s = new Scanner(f);
        boolean inRange = false;
        while (s.hasNext()) {
            inRange = inRange(ingredient, s.next());
            if (inRange) return true;
        }
        return false;
    }
}