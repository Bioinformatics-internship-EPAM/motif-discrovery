package ru.spbstu.pwmatrix;

import java.util.ArrayList;
import java.util.List;

public class PWData {

    private List<PWMatrix> data;

    public PWData() {
        this.data = new ArrayList<>();
    }

    public List<PWMatrix> getListMatrix() {
        return data;
    }

    public PWMatrix getMatrixByIndex(int index) {
        return data.get(index);
    }

    protected void addNewMatrix(PWMatrix pwmatrix) {
        data.add(pwmatrix);
    }
}
