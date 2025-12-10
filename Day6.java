import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day6 {
    static String[][] problems;

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("Day6.txt");
        Scanner s = new Scanner(f);
        String line = s.nextLine();
        Scanner first = new Scanner(line);
        int length = 0;
        while (first.hasNext()) {
            first.next();
            length++;
        }
        problems = new String[5][length];
        s = new Scanner(f);
        for (int i = 0; i < problems.length; i++) {
            for (int j = 0; j < problems[i].length; j++) {
                problems[i][j] = s.next();
            }
        }
        long total = 0;
        for (int col = 0; col < problems[0].length; col++)
            total += getColumn(col);
        System.out.println(total);

        //Part 2
        int cols = getWidth(f);
        int rows = getLength(f);
        String[][] arr = new String[rows][cols];
        arr = trim(copy(arr, f));
        System.out.println(total(arr));
    }

    public static long total(String[][] arr) {
        long total = 0;
        int col = 0;
        while (col < arr[0].length) {
            if (!arr[arr.length - 1][col].equals(" ")) {
                total += subtotal(arr, col);
            }
            col++;
        }
        return total;
    }

    public static long subtotal(String[][] arr, int c) {
        String operation = arr[arr.length - 1][c];
        long subtotal = getColumn(arr, c);
        c++;
        int next = getColumn(arr, c);
        while (next != 0) {
            if (operation.equals("*")) subtotal *= next;
            else subtotal += next;
            c++;
            next = getColumn(arr, c);
        }
        return subtotal;
    }

    public static int getColumn(String[][] arr, int c) {
        String s = "";
        int row = 0;
        while (row < arr.length - 1 && c < arr[0].length) {
            String current = arr[row][c];
            if (!current.equals(" "))
                s += arr[row][c];
            row++;
        }
        if (s.length() == 0) return 0;
        return Integer.parseInt(s);
    }

    public static String[][] copy(String[][] arr, File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        for (int i = 0; i < arr.length; i++) {
            String row = s.nextLine();
            for (int j = 0; j < row.length(); j++)
                arr[i][j] = row.substring(j, j + 1);
        }
        return arr;
    }

    public static String[][] trim(String[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++)
                if (arr[r][c] == null) arr[r][c] = " ";
        }
        return arr;
    }

    public static int getLength(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        int length = 0;
        while (s.hasNextLine()) {
            s.nextLine();
            length++;
        }
        return length;
    }

    public static int getWidth(File file) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        int width = 0;
        while (s.hasNextLine()) {
            String line = s.nextLine();
            int length = line.length();
            if (length > width) width = length;
        }
        return width;
    }

    public static void print(Object[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++)
                System.out.print(arr[i][j]);
            System.out.println();
        }
    }


    public static long getColumn(int c) {
        String operation = problems[4][c];
        long[] nums = new long[4];
        for (int i = 0; i < 4; i++)
            nums[i] = Long.parseLong(problems[i][c]);
        long sum = 0;
        long product = 1;
        for (long l : nums) {
            product *= l;
            sum += l;
        }
        if (operation.equals("*")) return product;
        return sum;
    }
}
