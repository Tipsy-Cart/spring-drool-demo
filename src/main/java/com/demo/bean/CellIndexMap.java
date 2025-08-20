package com.demo.bean;

public enum CellIndexMap {
    invType(2),supTin(11),buyerElectronicAddress(26);

    final int cellIndex;

    CellIndexMap(int cellIndex) {
        this.cellIndex = cellIndex;
    }

    public int getCellIndex() {return this.cellIndex;}
}
