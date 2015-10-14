package librarysystem;

//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import librarysystem.*;

public class JournalGrid extends AbstractTableModel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static final int OBJECT_COL = -1;
    //private static final int NO_COL = 0;
    private static final int TIT_COL = 0;  // 
    private static final int AUT_COL = 1;
    private static final int YR_COL = 2;
    private static final int PUB_COL = 3;
     private static final int ISBN_COL = 4;

    private final String[] columnNames = {"Title","Authors", "Year", "Publication", "ISBN"};
    private final List<Journal> journals;

    public JournalGrid(List<Journal> b) {
        journals = b;
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return journals.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        // TODO Auto-generated method stub
        Journal temp = journals.get(row);      

        switch (col) {
            case TIT_COL:
                return temp.getTitle();
            case ISBN_COL:
                return temp.getisbn();
            case YR_COL:
                return temp.getYear();
            case PUB_COL:
                return temp.getPublication();
            case AUT_COL:
                return temp.getauthors();
            case OBJECT_COL:
                return temp;
            default:
                return temp.gettitle();
        }
    }

}
