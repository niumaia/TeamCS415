/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;


//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import librarysystem.BookCopy;
import librarysystem.Reserve;
import librarysystem.BookCopyDA;

public class ReserveGrid extends AbstractTableModel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static final int OBJECT_COL = -1;
    //private static final int NO_COL = 0;
    private static final int TITLE_COL = 0;
    private static final int AUTHOR_COL = 1;
    private static final int STUD_COL = 3;
    private static final int CAT_COL = 2;
    private static final int DATE_COL = 4;
    private static final int COLL_COL = 5;

    private final String[] columnNames = {"Title", "Author", "Catalog Number", "Student ID","Reserved Date","Collection Date"};
    private final List<Reserve> resBooks;

    public ReserveGrid(List<Reserve> b) {
        resBooks = b;
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return resBooks.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        // TODO Auto-generated method stub
        Reserve temp = resBooks.get(row);      

        switch (col) {
            case TITLE_COL:
                return temp.gettitle();
            case CAT_COL:
               return temp.getcatalogNo();
            case AUTHOR_COL:
                return temp.getauthor();
            case STUD_COL:
                return temp.getStudentID();
            case DATE_COL:
                return temp.getReserveDate();
            case COLL_COL:
                return temp.getPickupDate();
            case OBJECT_COL:
                return temp;
            default:
                return temp.getReserveId();
        }
    }

}

