/**
 * @author Loshan Sundaramoorthy og Søren Fals Vind
 * @version 1.0
 */

public class Solver {


    private int noOfQueens;
    private int[] queens;
    private int noOfSolutions;
    private boolean showSolutions = true;


    public static void main(String[] args) {
        testSolver();
    }

    public static void testSolver() {
        Solver test = new Solver();

        test.findAllSolutions(1);
        test.findAllSolutions(2);
        test.findAllSolutions(6);

        test.findNoOfSolutions(1, 12);
    }

    public Solver() {

    }

    /**
     * Indsætter N antal dronninger, og printer alle løsningerne ud, samt runtime.
     * @param noOfQueens
     */

    public void findAllSolutions(int noOfQueens) {
        noOfSolutions = 0;
        this.noOfQueens = noOfQueens;
        this.queens = new int[noOfQueens];
        if(showSolutions) {
            System.out.println("********************************************************************");
            System.out.println("Solutions for " + noOfQueens + " queens:");
            System.out.println("");
        }

        long before = System.currentTimeMillis();
        positionQueens(0);
        long result = System.currentTimeMillis() - before;

        if(showSolutions) {
            System.out.println("");
            System.out.println("A total of " + noOfSolutions + " solutions was found in " + result + " ms");
            System.out.println("********************************************************************");
        }
    }

    /**
     *
     * Bruger findAllSolution med et specificeret antal dronninger og konstruere en tabel med løsningerne.
     *
     * @param min Min antal dronninger.
     * @param max Max antal dronninger.
     */

    public void findNoOfSolutions(int min, int max) {
        showSolutions = false;

        System.out.println("********************************************************************");
        System.out.println("  Queens      Solutions      Time(ms)      Solutions/ms             ");
        for(int i = min; i <= max; i++) {

            long before = System.currentTimeMillis();
            findAllSolutions(i);
            long result = System.currentTimeMillis() - before;

            System.out.format(" %6d %,12d %,10d %,12d %n", i, noOfSolutions, result+1, noOfSolutions / (result+1));
        }
        System.out.println("********************************************************************");
        System.out.println();

        showSolutions = true;
    }

    /**
     * Placere en dronning i den specificerede række.
     *
     * @param row rækken dronningen placeres i.
     */

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

    /**
     * Tjekker om den nuværende position af dronningen er lovlig.
     *
     * @param row Rækken der tjekkes
     * @param col Kolonnen der tjekkes
     * @return booelean.
     */

    private boolean legal(int row, int col) {
        for(int i = 0; i < row; i++) {
            int abs = Math.abs(row - i);
            int j = queens[i];
            if(j == col || col == (j + abs) || col == (j - abs)) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * Printer en løsning.
     *
     */

    private void printSolution() {
        String result = new String();
        for(int i = 0; i < noOfQueens; i++){
            result += " " + convert(i, queens[i]);
        }
        System.out.println(result);
    }

    /**
     * Metoden bruges til at konvertere row og col til den almen skak notation
     *
     * @param row nuværende række
     * @param col Nuværende kolonne
     * @return Returner en string.
     */

    private String convert(int row, int col) {
        char ch = (char) ('a' + col);
        return new String("" + ch + col);
    }
}
