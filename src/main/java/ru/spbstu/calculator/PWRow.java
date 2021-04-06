package ru.spbstu.calculator;

/**
 * PWRow represents data of frequency for one nucleotide at every position
 */
public class PWRow {

    private final double[] pwrow;

    PWRow(int size) {
        this.pwrow = new double[size];
    }

    public double getByIndex(int index) {
        return pwrow[index];
    }

    public double[] getRow() {
        return pwrow;
    }

    protected void increaseByOne(int index) {
        pwrow[index] += 1;
    }

    protected void divideByNumber(int index, int number) throws ArithmeticException {
        pwrow[index] /= number;
    }

    protected void calculateLogFreq(int index, double frequency) {
        pwrow[index] = Math.log(pwrow[index] / frequency);
    }


}
