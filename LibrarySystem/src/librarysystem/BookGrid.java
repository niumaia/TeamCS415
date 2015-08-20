package librarysystem;

//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import librarysystem.BookCopy;



public class BookGrid extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int OBJECT_COL = -1;
	private static final int NO_COL = 0;
	private static final int TITLE_COL = 1;
	private static final int AUTHOR_COL = 2;	
	private static final int CAT_COL = 3;
	private static final int ISBN_COL = 4;
	private static final int AVL_COL =5;
	
	private String[] columnNames = { "Copy No","Title", "Author", "Cat Num", "ISBN", "Avalibility " };
	private List<BookCopy> books; 

//	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	public BookGrid(List<BookCopy> b) {
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
		case TITLE_COL:
			return temp.gettitle();
		case AUTHOR_COL:
			return temp.getauthor();
		case CAT_COL:
			return temp.getcat();
		case NO_COL:
			return temp.getcopyNum();
		case ISBN_COL:
			return temp.getisbn();
		case AVL_COL:
			return temp.getdesc();
		case OBJECT_COL:
			return temp;
		default:
			return temp.gettitle();
		}
	}
	


}
