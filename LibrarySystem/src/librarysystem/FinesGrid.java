package librarysystem;

//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import librarysystem.*;

public class FinesGrid extends AbstractTableModel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static final int OBJECT_COL = -1;
    private static final int ID_COL = 0;
    private static final int CAT_COL = 1;
    private static final int TITLE_COL = 2;
    private static final int AUTHOR_COL = 3;
     private static final int DUE_COL = 4;
    private static final int DAYS_COL = 5;
    private static final int FINE_COL = 6;

    private final String[] columnNames = {"Transaction ID","Catalog", "Title", "Author","Due Date", "Overdue Days","Fine Remaining"};
    private final List<LineItem> items;

    public FinesGrid(List<LineItem> i) {
        items = i;
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return items.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        // TODO Auto-generated method stub
        LineItem temp = items.get(row);      

        switch (col) {
            case ID_COL:
                return temp.getTransactionId();
           case CAT_COL:
                return temp.getCatalog();
            case TITLE_COL:
                return temp.getTitle();
            case AUTHOR_COL:
                return temp.getAuthor();
            case DUE_COL:
                return temp.getDueDate();
            case DAYS_COL:
                return temp.getDays();
            case FINE_COL:
                return temp.getAmount();                
            case OBJECT_COL:
                return temp;
            default:
                return temp.getItemId();
        }
    }

}
