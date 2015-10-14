/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

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
public class bookDialogTest {
    
    public bookDialogTest() {
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
     * Test of processBook method, of class bookDialog.
     */
    @Test
    public void testProcessBook() {
        System.out.println("processBook");
        String title = "a";
        String author = "b";
        String publisher = "c";
        String isbn = "d";
        String edition = "e";
        clerkPortal cp = new clerkPortal();
        
        bookDialog instance = new bookDialog(cp,true);
        instance.processBook(title, author, publisher, isbn, edition);
        // TODO review the generated test code and remove the default call to fail.
    //    fail("The test case is a prototype.");
       assert(true);
    }

    
    /**
     * Test of main method, of class bookDialog.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        bookDialog.main(args);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
