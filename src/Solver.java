/**
 * @author Loshan Sundaramoorthy
 * @version 31/10-2022
 */
public class Solver {
    private int noOfQueens;
    private int[] queens;
    private int noOfSolutions;
    private boolean showSolutions;

    public void findAllSolutions(int noOfQueens) {
        System.out.println("********************************************************************");
        System.out.println("Solutions for " + noOfQueens + " queens:");
        System.out.println("");

        this.showSolutions = true;
        long result= findTime(noOfQueens);

        System.out.println("");
        System.out.println("A total of " + noOfSolutions + " solutions was found in " + result + " ms");
        System.out.println("********************************************************************");
    }

    public void findNoOfSolutions(int min, int max) {
        System.out.println("********************************************************************");
        System.out.println("  Queens      Solutions      Time(ms)      Solutions/ms             ");
        this.showSolutions = false;
        for(int i = min; i <= max; i++) {
            long result = Math.max(1, findTime(i));
            System.out.format(" %6d %,12d %,10d %,12d %n", i, noOfSolutions, result, noOfSolutions / result);
        }
        System.out.println("********************************************************************");
    }

    public static void testSolver() {
        Solver test = new Solver();

        test.findAllSolutions(1);
        test.findAllSolutions(2);
        test.findAllSolutions(6);

        test.findNoOfSolutions(1, 12);
    }

    private long findTime(int noOfQueens) {
        this.noOfQueens = noOfQueens;
        this.queens = new int[noOfQueens];
        this.noOfSolutions = 0;
        long result = System.currentTimeMillis();
        positionQueens(0);
        return System.currentTimeMillis() - result;
    }

    private void positionQueens(int row) {
        if(row == noOfQueens) {
            noOfSolutions++;
            if(showSolutions) {
                printSolution();
            }
            return;
        }
        for(int i = 0; i < noOfQueens; i++) {
            if(legal(row, i)) {
                queens[row] = i;
                positionQueens(row + 1);
            }
        }
    }

    private boolean legal(int row, int col) {
        for(int i = 0; i < row; i++) {
            if(i == row) {
                continue;
            }
            int abs = Math.abs(row - i);
            int j = queens[i];
            if(j == col || col == (j + abs) || col == (j - abs)) {
                return false;
            }
        }
        return true;
    }

    private void printSolution() {
        String result = new String();
        for(int i = 0; i < noOfQueens; i++){
            result += " " + convert(i, queens[i]);
        }
        System.out.println(result);
    }

    private String convert(int row, int col) {
        char ch = (char) ('a' + col);
        return new String("" + ch + col);
    }
}
