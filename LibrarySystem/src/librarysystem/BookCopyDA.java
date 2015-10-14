package librarysystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import com.mysql.jdbc.Connection;

public class BookCopyDA {

    private dbConnect dbConn;
    Connection db;

    public BookCopyDA() throws Exception {
        dbConn = new dbConnect("Staff");
        db = (Connection) dbConn.getStaffConnector();
    }
    
    public void addBookCopy(BookCopy bc) throws Exception {
        PreparedStatement myStmt = null;
        String sql = "insert into bookcopy (bk_id,bkCopyEd,bkCopyNum,bkCopyPublishYear,bkCatNum,copyStat_id) values (?,?,?,?,?,?)";        
        java.sql.Date year = null;
        
        try {
            // prepare statement
            myStmt = db.prepareStatement(sql);

            if (bc.getYear() != null) {
                year = new java.sql.Date(bc.getYear().getTime());
            }
            // set params
            myStmt.setInt(1, bc.getbookid());
             myStmt.setString(2,"");
            myStmt.setInt(3, bc.getcopyNum());           
            myStmt.setDate(4,year );
            myStmt.setString(5, bc.getcatalogNo());
            myStmt.setInt(6, bc.getstatus());
			// execute SQL
            myStmt.executeUpdate();

        } finally {
            db.close();
        }
    }

    public List<BookCopy> findBooks(String title, String author, String pub, String isbn) throws Exception {
        List<BookCopy> blist = new ArrayList<BookCopy>();
        PreparedStatement myStmt = null;
        String sql = "select c.bkCopyID,c.bkCopyNum,b.bk_id,b.bk_title,b.bk_author,c.bk_publisher,c.ISBN,s.copyStat_desc from books b ,bookcopy c,copy_status s"
                + " where c.bk_id = b.bk_id and s.copyStat_id = c.copyStat_id "
                + " and b.bk_title like ? and b.bk_author like ? and b.bk_publisher like ? and c.ISBN like ? ";

        ResultSet myRs = null;

        try {
            myStmt = db.prepareStatement(sql);
            myStmt.setString(1, "%" + title + "%");
            myStmt.setString(2, "%" + author + "%");
            myStmt.setString(3, "%" + pub + "%");
            myStmt.setString(4, "%" + isbn + "%");
            myRs = myStmt.executeQuery();

            while (myRs.next()) {

                int bcid = myRs.getInt("bkCopyID");
                int copy = myRs.getInt("bkCopyNum");
                int bookid = myRs.getInt("bk_id");
                String btitle = myRs.getString("bk_title");
                String bauthor = myRs.getString("bk_author");
                String bcat = myRs.getString("bkCatNum");
                String bedn = myRs.getString("bkCopyEd");
                String bstat = myRs.getString("copyStat_desc");

                BookCopy tempBk = new BookCopy(bookid,bcid, copy, btitle, bauthor, bcat, bedn, bstat);
                blist.add(tempBk);
            }

            return blist;
        } finally {
            db.close();
        }
    }
    
     public List<Reserve> getReservedBooks() throws Exception {
        List<Reserve> blist = new ArrayList<Reserve>();
        PreparedStatement myStmt = null;
        String sql = "select r.reserveID,c.bkCopyID. b.bk_title,b.bk_author,c.bkCatNum ,r.StudentID,r.reserveDate,r.pickupDate from books b,bookcopy c,reserve r "
                +    " where b.bk_id = c.bk_id and c.bkCopyID = r.bookCopyId and c.copyStat_id =3";

        ResultSet myRs = null;

        try {
            myStmt = db.prepareStatement(sql);
           
          //  myStmt.setString(4, "%" + isbn + "%");
            myRs = myStmt.executeQuery();

            while (myRs.next()) {

                int rid = myRs.getInt("reserveID");
              //  int copy = myRs.getInt("bkCopyNum");
                int bkCopyID = myRs.getInt("bkCopyID");
                String btitle = myRs.getString("bk_title");
                String bauthor = myRs.getString("bk_author");
                String bcat = myRs.getString("bkCatNum");
                String bstud = myRs.getString("StudentID");
                java.sql.Date rDate = myRs.getDate("reserveDate"); 
                java.sql.Date pDate = myRs.getDate("pickupDate"); 

                Reserve r = new Reserve(rid,bkCopyID, btitle, bauthor, bcat, bstud, rDate,pDate);
                blist.add(r);
            }

            return blist;
        } finally {
            db.close();
        }
    }
     
        public List<Reserve> getStudentReservedBooks(String studentId) throws Exception {
        List<Reserve> blist = new ArrayList<Reserve>();
        PreparedStatement myStmt = null;
        String sql = "select r.reserveID,c.bkCopyID,b.bk_title,b.bk_author,c.bkCatNum ,r.StudentID,r.reserveDate,r.pickupDate from books b,bookcopy c,reserve r" 
                     +   " where b.bk_id = c.bk_id and c.bkCopyID = r.bookCopyId and r.StudentID=?";

        ResultSet myRs = null;

        try {
            myStmt = db.prepareStatement(sql);
           
            myStmt.setString(1,studentId);
            myRs = myStmt.executeQuery();

            while (myRs.next()) {

                int rid = myRs.getInt("reserveID");
              //  int copy = myRs.getInt("bkCopyNum");
               int bookid = myRs.getInt("bkCopyID");
                String btitle = myRs.getString("bk_title");
                String bauthor = myRs.getString("bk_author");
                String bcat = myRs.getString("bkCatNum");
                String bstud = myRs.getString("StudentID");
                java.sql.Date rDate = myRs.getDate("reserveDate"); 
                java.sql.Date pDate = myRs.getDate("pickupDate"); 

                Reserve r = new Reserve(rid,bookid, btitle, bauthor, bcat, bstud, rDate,pDate);
                blist.add(r);
            }

            return blist;
        } finally {
            db.close();
        }
    }
     
    public void reserveBookCopy(Book bc,String studentid,Date rDate) throws Exception {
        PreparedStatement myStmt = null;
        String sql = "insert into reserve (bookCopyId ,StudentID, reserveDate) values (?,?,?)";        
        java.sql.Date date = null;
        
        try {
            // prepare statement
            myStmt = db.prepareStatement(sql);

            if (rDate != null) {
                 date = new java.sql.Date(rDate.getTime());
            }
            // set params
            myStmt.setInt(1, bc.getBookId());
          //  myStmt.setString(2, bc.getedition());
            myStmt.setString(2,studentid);
            myStmt.setDate(3,date);
          
			// execute SQL
            myStmt.executeUpdate();

        } finally {
            db.close();
        }
    }
     
    
    
        public void updateBookCopy(BookCopy bc) throws Exception {
        PreparedStatement myStmt = null;
        String sql = "update bookcopy set bkCopyNum=?,bkCopyPublishYear=?,bkCatNum=? where bkCopyID=?";        
        java.sql.Date year = null;
        
        try {
            // prepare statement
            myStmt = db.prepareStatement(sql);

            if (bc.getYear() != null) {
                year = new java.sql.Date(bc.getYear().getTime());
            }
            // set params
            myStmt.setInt(1, bc.getcopyNum());
          //  myStmt.setString(2, bc.getedition());
            myStmt.setDate(2,year);
            myStmt.setString(3, bc.getcatalogNo());
            myStmt.setInt(4, bc.getId());
			// execute SQL
            myStmt.executeUpdate();

        } finally {
            db.close();
        }
    }
    
    
    public void updateBookStatus(BookCopy bc, int status) throws Exception {
        PreparedStatement myStmt = null;
        String sql = "update bookcopy set copyStat_id=? where bkCopyID=?";

        try {
            // prepare statement
            myStmt = db.prepareStatement(sql);

            // set params
            myStmt.setInt(1, status);
            myStmt.setInt(2, bc.getId());
			// execute SQL
            myStmt.executeUpdate();

        } finally {
            db.close();
        }
    }
    
        public List<BookCopy> getAllCopies(Book b) throws Exception {
        List<BookCopy> blist = new ArrayList<BookCopy>();        
        PreparedStatement myStmt = null;
        java.sql.Date year = null;
        String sql = "select c.bkCopyID,c.bkCopyNum,c.bk_id,c.bkCatNum,c.bkCopyPublishYear,c.copyStat_id,s.copyStat_desc from bookcopy c,copy_status s"
                + " where s.copyStat_id = c.copyStat_id and c.bk_id = ? order by c.bkCopyNum";

        try {
            myStmt = db.prepareStatement(sql);
            myStmt.setInt(1, b.getBookId());
            ResultSet myRs = myStmt.executeQuery();

            while (myRs.next()) {
            int bookId = myRs.getInt("bk_id");
            int copyId = myRs.getInt("bkCopyID");
            int copyNum = myRs.getInt("bkCopyNum");
            int stat = myRs.getInt("copyStat_id");
            java.sql.Date byear = myRs.getDate("bkCopyPublishYear");     
            String bcat = myRs.getString("bkCatNum");
           // String bedn = myRs.getString("bkCopyEd");
            String bstat = myRs.getString("copyStat_desc");

            //temp = new BookCopy(copyId,bookId,bedn ,byear,copyNum,, btitle, bcat, ,bstat, bdesc);
            BookCopy tempBk = new BookCopy(copyId,bookId,copyNum,"", byear, bcat, stat,bstat);
            blist.add(tempBk);
             
            }
            return blist;
        } finally {
            db.close();
        }
    }
    
    
    public BookCopy getBookCopy(String catalogNum) throws Exception {

        PreparedStatement myStmt = null;
        String sql = "select c.bkCopyID,c.bkCopyNum,b.bk_id,b.bk_title,b.bk_author,c.bkCatNum, s.copyStat_desc from books b ,bookcopy c,copy_status s"
                + " where c.bk_id = b.bk_id and s.copyStat_id = c.copyStat_id and bkCatNum = ?";
        BookCopy temp = null;        
        ResultSet myRs = null;

        try {
            myStmt = db.prepareStatement(sql);
            myStmt.setString(1, catalogNum);
            myRs = myStmt.executeQuery();

            while (myRs.next()) {
            int bookId = myRs.getInt("bk_id");
            int copyId = myRs.getInt("bkCopyID");
            int copyNum = myRs.getInt("bkCopyNum");
            String btitle = myRs.getString("bk_title");
            String bauthor = myRs.getString("bk_author");
            String bcat = myRs.getString("bkCatNum");
           // String bedn = myRs.getString("bkCopyEd");
            String bstat = myRs.getString("copyStat_desc");

             temp = new BookCopy(bookId, copyId,copyNum, btitle, bauthor, bcat, "", bstat);
            }
            return temp;
        } finally {
            db.close();
        }
    }

}
