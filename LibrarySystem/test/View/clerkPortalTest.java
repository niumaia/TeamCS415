 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.util.Date;
import librarysystem.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tabunakawai_n
 */
public class clerkPortalTest {
    
    public clerkPortalTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of showBalance method, of class clerkPortal.
     */
    @Test
    public void testShowBalance() {
        System.out.println("showBalance");
        Double balance = 50.00;
        Double paid = 10.00;
        Double subtotal = 20.00;
        clerkPortal instance = new clerkPortal();
        instance.showBalance(balance, paid, subtotal);
        // TODO review the generated test code and remove the default call to fail.
      //  fail("The test case is a prototype.");
    }

    /**
     * Test of populateFineDetails method, of class clerkPortal.
     */
    @Test
    public void testPopulateFineDetails() {
        System.out.println("populateFineDetails");
        String studID = "";
        clerkPortal instance = new clerkPortal();
        instance.populateFineDetails(studID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkInBookCopy method, of class clerkPortal.
     */
    @Test
    public void testCheckInBookCopy() {
        System.out.println("checkInBookCopy");
        clerkPortal instance = new clerkPortal();
        instance.checkInBookCopy();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addStudentFine method, of class clerkPortal.
     */
    @Test
    public void testAddStudentFine() {
        System.out.println("addStudentFine");
        String student = "";
        Date returnDate = null;
        String desc = "";
        String reference = "";
        Double amt = null;
        clerkPortal instance = new clerkPortal();
        instance.addStudentFine(student, returnDate, desc, reference, amt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLoggedInUser method, of class clerkPortal.
     */
    @Test
    public void testSetLoggedInUser() {
        System.out.println("setLoggedInUser");
        String username = "";
        clerkPortal instance = new clerkPortal();
        instance.setLoggedInUser(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of populateReservedList method, of class clerkPortal.
     */
    @Test
    public void testPopulateReservedList() {
        System.out.println("populateReservedList");
        clerkPortal instance = new clerkPortal();
        instance.populateReservedList();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of populateBookList method, of class clerkPortal.
     */
    @Test
    public void testPopulateBookList() {
        System.out.println("populateBookList");
        clerkPortal instance = new clerkPortal();
        instance.populateBookList();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of populateBookCopyList method, of class clerkPortal.
     */
    @Test
    public void testPopulateBookCopyList() {
        System.out.println("populateBookCopyList");
        clerkPortal instance = new clerkPortal();
        instance.populateBookCopyList();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearControls method, of class clerkPortal.
     */
    @Test
    public void testClearControls() {
        System.out.println("clearControls");
        clerkPortal instance = new clerkPortal();
        instance.clearControls();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of issueBookCopy method, of class clerkPortal.
     */
    @Test
    public void testIssueBookCopy() {
        System.out.println("issueBookCopy");
        String StudentID = "";
        clerkPortal instance = new clerkPortal();
        instance.issueBookCopy(StudentID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateBookCopy method, of class clerkPortal.
     */
    @Test
    public void testUpdateBookCopy() throws Exception {
        System.out.println("updateBookCopy");
        BookCopy t = null;
        int status = 0;
        clerkPortal instance = new clerkPortal();
        instance.updateBookCopy(t, status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIssueDetails method, of class clerkPortal.
     */
    @Test
    public void testGetIssueDetails() {
        System.out.println("getIssueDetails");
        String catNum = "";
        clerkPortal instance = new clerkPortal();
        instance.getIssueDetails(catNum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBookCopyDetails method, of class clerkPortal.
     */
    @Test
    public void testGetBookCopyDetails() {
        System.out.println("getBookCopyDetails");
        String catNum = "";
        clerkPortal instance = new clerkPortal();
        instance.getBookCopyDetails(catNum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class clerkPortal.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        clerkPortal.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
