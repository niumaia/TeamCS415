package librarysystem;

//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import librarysystem.Issue;
import librarysystem.IssueDA;

public class IssueGrid extends AbstractTableModel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static final int OBJECT_COL = -1;
    //private static final int NO_COL = 0;
    private static final int TITLE_COL = 0;
   
    private static final int CAT_COL = 1;
    private static final int OUT_COL = 2;
    private static final int DUE_COL = 3;
    private static final int STAT_COL = 4;

    private String[] columnNames = {"Title","Catalog", "Checked Out","Due Date", "Status"};
    private List<Issue> issue;
    private IssueDA issueDA;

//	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    public IssueGrid(List<Issue> i) {
        issue = i;
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return issue.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        // TODO Auto-generated method stub
        
        Issue temp = issue.get(row);
        switch (col) {
            case TITLE_COL:
                return temp.gettitle();          
            case CAT_COL:
                return temp.getcatalog();
            case OUT_COL:
                return temp.getDateOut();
            case DUE_COL:
                return temp.getDatedue();
            case STAT_COL:
                return temp.getStatus();
            case OBJECT_COL:
                return temp;
            default:
                return temp.gettitle();
        }
    }

}
