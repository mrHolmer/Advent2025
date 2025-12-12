import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day7 {
    static String[][] map;
    static File f;
    static Scanner s;
    static int splits;
    public static void main(String[] args) throws FileNotFoundException {
        f = new File("Day7.txt");
        int width = getWidth();
        int length = getLength();
        map = new String[length][width];
        copy();
        beam(0, getS());
        int row = 1;
        int splits = 0;
        while (row < map.length - 1) {
            splits += split(row);
            beam(row);
            row++;
        }
        print(map);
        System.out.println(splits);

        //Part 2
        map = new String[length][width];
        copy();
        beam(0, getS());
        for (int i = 3; i < map.length; i +=2) {
            paths(i);
        }
        print(map);
        System.out.println(getRow(map.length - 1));
    }
    public static long getRow (int row) {
        long total = 2;
        for (int i = 1; i < map[row].length - 1; i++) {
            total += Long.parseLong(map[row][i]);
        }
        return total;
    }
    public static int paths(int row)
    {
        int paths = 0;
        for (int i = 1; i < map[row].length - 1; i++)
            paths += paths(row, i);
        return paths;
    }

    public static long paths(int row, int col) {
        long paths = 0;
        if (map[row - 1][col + 1].equals("^")) {
            long aboveSplit = Long.parseLong(map[row - 2][col + 1]);
            paths += aboveSplit;
        }
        if (map[row - 1][col - 1].equals("^")) {
            long aboveSplit = Long.parseLong(map[row - 2][col - 1]);
            paths += aboveSplit;
        }
        String twoAbove = map[row - 2][col];
        if (!map[row - 1][col].equals("^") && !twoAbove.equals("."))
            paths += Long.parseLong(map[row - 2][col]);
        map[row][col] = paths + "";
        return paths;
    }

    public static void beam(int row) {
        for (int i = 0; i < map[row].length; i++) {
            String above = map[row - 1][i];
            String below = map[row + 1][i];
            if (above.equals("|") && !map[row][i].equals("^"))
                map[row][i] = "|";
        }
    }
    public static int split(int row) {
        int splits = 0;
        for (int i = 0; i < map[row].length; i++) {
            if (map[row][i].equals("^") && map [row - 1][i].equals("|")) {
                map[row][i - 1] = "|";
                map[row][i + 1] = "|";
                splits++;
            }
        }
        return splits;
    }
    public static void beam(int row, int col) {
        if (row < map.length - 1) map[row + 1][col] = "1";
    }
    public static void print(Object[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++)
                System.out.print(arr[i][j]);
            System.out.println();
        }
    }
    public static void copy() throws FileNotFoundException {
        int row = 0;
        s = new Scanner(f);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            for (int i = 0; i < line.length(); i++)
                map[row][i] = line.substring(i, i + 1);
            row++;
        }
    }
    public static int getS() {
        for (int i = 0; i < map[0].length; i++)
            if (map[0][i].equals("S")) return i;
        return -1;
    }
    public static int getLength() throws FileNotFoundException {
        int length = 0;
        s = new Scanner(f);
        while (s.hasNextLine()) {
            s.nextLine();
            length++;
        }
        return length;
    }
    public static int getWidth() throws FileNotFoundException{
        int width = 0;
        s = new Scanner(f);
        return s.nextLine().length();
    }
}
