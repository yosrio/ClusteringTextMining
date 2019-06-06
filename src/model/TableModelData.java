/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.SearchingResult;

/**
 *
 * @author Yos Rio
 * 165314041
 */
public class TableModelData extends AbstractTableModel {

    private List<Document> doc = new ArrayList<Document>();

    public TableModelData(List<Document> doc) {
        this.doc = doc;
    }

    @Override
    public int getRowCount() {
        return doc.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Document b = doc.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return b.getId();
            case 1:
                return b.getAuthor() + " - " + b.getTitle();
            default:
                return "";
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id Document";
            case 1:
                return "Title Song";
            default:
                return "";
        }
    }
}
