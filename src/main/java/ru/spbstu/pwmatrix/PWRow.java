package ru.spbstu.pwmatrix;

/**
 * PWRow represents data of frequency for one nucleotide at every position
 */
public class PWRow {
    private double[] pwrow;

    PWRow(int size) {
        this.pwrow = new double[size];
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

    public double getByIndex(int index) {
        return pwrow[index];
    }

    public void setByIndex(int index, double number) {
        pwrow[index] = number;
    }

    public double[] getRow() {
        return pwrow;
    }
}
