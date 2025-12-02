import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day12025 {
    public static int loc = 50;
    public static int zeros = 0;
    public static int line = 1;
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("src/202501.txt");
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            //rotate(s.next());
            part2(s.next());
        }
        System.out.println(zeros);
    }
    public static void rotate(String s) {
        System.out.print(line + ": " + loc + " -> ");
        if (s.substring(0, 1).equals ("R")) {
            loc += Integer.parseInt(s.substring(1));
            loc %= 100;
        }
        else {
            loc -= Integer.parseInt(s.substring(1));
            if (loc < 0) loc = (100 + (loc % 100)) % 100;
        }
        System.out.println(loc);
        line++;
        if (loc == 0) zeros++;
    }
    public static void part2(String s) {
        int rotation = Integer.parseInt(s.substring(1));
        if (s.substring(0, 1).equals("R")) {
            while (rotation > 0) {
                loc++;
                loc %= 100;
                if (loc == 0) zeros++;
                rotation--;
            }
        }
        else {
            while (rotation > 0) {
                loc--;
                if (loc == 0) zeros++;
                if (loc == -1) loc = 99;
                rotation--;
            }
        }
    }
}
