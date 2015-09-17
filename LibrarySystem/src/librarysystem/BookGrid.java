package librarysystem;

//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;
//import javax.swing.table.DefaultTableModel 
import librarysystem.Book;
import librarysystem.BookDA;

public class BookGrid extends AbstractTableModel  {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static final int OBJECT_COL = -1;
    //private static final int NO_COL = 0;
    private static final int TITLE_COL = 0;
    private static final int AUTHOR_COL = 1;
    private static final int PUB_COL = 2;
    private static final int EDN_COL = 3;
    private static final int ISBN_COL = 4;
    private static final int COP_COL = 5;

    private final String[] columnNames = {"Title", "Author", "Publisher","Edition", "ISBN", "Copies "};
    private final String[] columnStud = {"Title", "Author", "Publisher","Edition", "ISBN"};
    private final List<Book> books;
  //  private BookDA bookDA;

//	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    public BookGrid(List<Book> b) {
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
        
        Book temp = books.get(row);
        int copies = 0;
        BookDA bookDA;
       
      try {
            bookDA = new BookDA();
            copies = bookDA.getBookCopies(temp.getBookId());
             // System.out.println(copies);
        } catch (Exception e) {
             System.out.println(e.getMessage());
        }

        switch (col) {
            case TITLE_COL:
                return temp.gettitle();
            case AUTHOR_COL:
                return temp.getauthor();
            case PUB_COL:
                return temp.getpublisher();
            case ISBN_COL:
                return temp.getisbn();
            case EDN_COL:
                return temp.getedition();
            case COP_COL:
                return copies;
            case OBJECT_COL:
                return temp;
            default:
                return temp.gettitle();
        }
    }

}
