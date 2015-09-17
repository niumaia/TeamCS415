package librarysystem;

import com.mysql.jdbc.Connection;

import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookDA {

    private dbConnect dbConn;
    Connection db;

    public BookDA() throws Exception {
       dbConn = new dbConnect("Staff");
        db = (Connection) dbConn.getStaffConnector();

    }

    public List<Book> findBooks(String title, String author, String pub, String isbn) throws Exception {
        List<Book> blist = new ArrayList<Book>();
        PreparedStatement myStmt = null;
        String sql = "select bk_id ,bk_title ,bk_author ,bk_publisher ,bk_ISBN,bk_edition from books "
                + " where bk_title like ? and bk_author like ? and bk_publisher like ? and bk_ISBN like ? "
                + " order by bk_title";
        ResultSet myRs = null;
        

        try {
            myStmt = db.prepareStatement(sql);
            myStmt.setString(1, "%" + title + "%");
            myStmt.setString(2, "%" + author + "%");
            myStmt.setString(3, "%" + pub + "%");
            myStmt.setString(4, "%" + isbn + "%");
            myRs = myStmt.executeQuery();

            while (myRs.next()) {

                int bookid = myRs.getInt("bk_id");
                String btitle = myRs.getString("bk_title");
                String bauthor = myRs.getString("bk_author");
                String bpub = myRs.getString("bk_publisher");
                String bisbn = myRs.getString("bk_ISBN");
                String bedn = myRs.getString("bk_edition");
                Book tempBk = new Book(bookid, btitle, bauthor, bpub, bisbn,bedn);
                //int copies = getBookCopies(bookid);
                //tempBk.setCopies(copies);
                
                blist.add(tempBk);
            }

            return blist;
        } finally {
            db.close();
        }
    }

    public void addBook(Book b) throws Exception {
        PreparedStatement myStmt = null;
        String sql = "insert into books (bk_title,bk_author,bk_publisher,bk_ISBN,bk_edition) values (?, ?, ?,?,?)";

        try {
            // prepare statements
            myStmt = db.prepareStatement(sql);

            // set params
            myStmt.setString(1, b.gettitle());
            myStmt.setString(2, b.getauthor());
            myStmt.setString(3, b.getpublisher());
            myStmt.setString(4, b.getisbn());
            myStmt.setString(5, b.getedition());

            // execute SQL			
            myStmt.executeUpdate();
        } finally {
            //DAOUtils.close(myStmt);
            db.close();
        }

    }

    public void updateBook(Book b) throws Exception {
        PreparedStatement myStmt = null;
        String sql = "update books set bk_title = ?,bk_author = ?,bk_publisher = ?,bk_ISBN = ?,bk_edition=? where bk_id=?";

        try {
            // prepare statement
            myStmt = db.prepareStatement(sql);

            // set params
            myStmt.setString(1, b.gettitle());
            myStmt.setString(2, b.getauthor());
            myStmt.setString(3, b.getpublisher());
            myStmt.setString(4, b.getisbn());
             myStmt.setString(5, b.getedition());
            myStmt.setInt(6, b.getBookId());            

            // execute SQL
            myStmt.executeUpdate();

        } finally {
            db.close();
        }
    }

    public int getBookCopies(int bookid) throws Exception {
        PreparedStatement myStmt = null;
        String sql = "select count(*) num from bookcopy where bk_id=?";
        
        int num = 0;

        try {
            // prepare statements
            myStmt = db.prepareStatement(sql);
            // set params
            myStmt.setInt(1, bookid);
            // execute SQL			
            ResultSet myRs = myStmt.executeQuery();
           // myRs.next();
            while (myRs.next()) {
            num = myRs.getInt("num");
            }
            return num;
        } finally {
            //DAOUtils.close(myStmt);
            db.close();
        }

    }

    public List<Book> searchBooks() throws Exception {
        List<Book> blist = new ArrayList<Book>();
        PreparedStatement myStmt = null;
        String sql = "select * from books";
        ResultSet myRs = null;

        try {
            myStmt = db.prepareStatement(sql);
            myRs = myStmt.executeQuery(sql);

            while (myRs.next()) {

                int id = myRs.getInt("bk_id");
                String title = myRs.getString("bk_title");
                String author = myRs.getString("bk_author");
                String publisher = myRs.getString("bk_publisher");
                String isbn = myRs.getString("bk_ISBN");
                 String edn = myRs.getString("bk_edition");

                Book tempBk = new Book(id, title, author, publisher, isbn,edn);
                blist.add(tempBk);
            }

            return blist;
        } finally {
            db.close();
        }
    }

    public Book getBook(int id) throws Exception {
        Book b = null;
        PreparedStatement myStmt = null;
        String sql = "select * from books where bk_id = ?";
        ResultSet myRs = null;

        try {
            myStmt = db.prepareStatement(sql);

            // prepare statement
            myStmt.setInt(1, id);
            myRs = myStmt.executeQuery(sql);

            while (myRs.next()) {

                int bkid = myRs.getInt("id");
                String title = myRs.getString("title");
                String author = myRs.getString("author");
                String publisher = myRs.getString("publisher");
                String isbn = myRs.getString("bk_ISBN");
                String edn = myRs.getString("bk_edition");

                b = new Book(bkid, title, author, publisher, isbn,edn);

            }

            return b;
        } finally {
            db.close();
        }
    }

}
