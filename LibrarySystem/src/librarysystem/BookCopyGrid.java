package librarysystem;

//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import librarysystem.BookCopy;
import librarysystem.BookCopyDA;

public class BookCopyGrid extends AbstractTableModel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static final int OBJECT_COL = -1;
    //private static final int NO_COL = 0;
    private static final int COPY_COL = 0;
  //  private static final int EDN_COL = 1;
    private static final int YEAR_COL = 1;
    private static final int CAT_COL = 2;
    private static final int STAT_COL = 3;

    private final String[] columnNames = {"Copy Number", "Publish Year", "Catalog Number", "Status "};
    private final List<BookCopy> books;

    public BookCopyGrid(List<BookCopy> b) {
        books = b;
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return books.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        // TODO Auto-generated method stub
        BookCopy temp = books.get(row);      

        switch (col) {
            case COPY_COL:
                return temp.getcopyNum();
          //  case EDN_COL:
          //      return temp.getedition();
            case YEAR_COL:
                return temp.getYear();
            case CAT_COL:
                return temp.getcatalogNo();
            case STAT_COL:
                return temp.getdesc();
            case OBJECT_COL:
                return temp;
            default:
                return temp.gettitle();
        }
    }

}
