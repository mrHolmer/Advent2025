import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Day4 {
    static String[][] arr = new String[142][142];
    public static boolean accessible(int r, int c)
    {
        int rolls = 0;
        String roll = arr[r][c];
        if (roll.equals(".")) return false;
        if (arr[r - 1][c - 1].equals("@")) rolls++;
        if (arr[r - 1][c].equals("@")) rolls++;
        if (arr[r - 1][c + 1].equals("@")) rolls++;
        if (arr[r][c - 1].equals("@")) rolls++;
        if (arr[r][c + 1].equals("@")) rolls++;
        if (arr[r + 1][c - 1].equals("@")) rolls++;
        if (arr[r + 1][c].equals("@")) rolls++;
        if (arr[r + 1][c + 1].equals("@")) rolls++;
        return rolls < 4;
    }
    public static void main(String[] args) throws FileNotFoundException{
        File f = new File("Day4.txt");
        Scanner s = new Scanner(f);
        for (int i = 0; i < 142; i++) {
            arr[0][i] = ".";
            arr[141][i] = ".";
        }
        int row = 1;
        while(s.hasNext()) {
            String line = s.next();
            arr[row][0] = ".";
            for(int i = 0; i < line.length(); i++) {
                arr[row][i + 1] = line.substring(i, i + 1);
            }
            arr[row][141] = ".";
            row++;
        }

        int rolls = 0;
        for (int i = 1; i < arr.length - 1; i++)
            for (int j = 1; j < arr[i].length - 1; j++)
                if(accessible(i, j)) rolls++;
        System.out.println(rolls);

        int toRemove = remove();
        int thisTime = toRemove;
        while (thisTime > 0) {
            thisTime = remove();
            toRemove += thisTime;
        }
        System.out.println(toRemove);
    }
    public static int remove() {
        int rolls = 0;
        for (int i = 1; i < arr.length - 1; i++)
            for (int j = 1; j < arr[i].length - 1; j++)
                if(accessible(i, j)) {
                    rolls++;
                    remove(i, j);
                }
        return rolls;
    }
    public static void remove(int r, int c) {
        arr[r][c] = ".";
    }
}