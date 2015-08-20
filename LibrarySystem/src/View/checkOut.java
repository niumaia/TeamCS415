package View;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Event;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import librarysystem.BookCopy;
import librarysystem.IssueDA;
import librarysystem.Issue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;





public class checkOut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtBookId;
	private JTextField txtAuthor;
	private JTextField txtCopyNo;
	private JTextField txtTitle;

	private JFormattedTextField formattedDateOut;
	private JFormattedTextField formattedDateIn;

	private DateFormat dateFormat = null;
	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JLabel lblNewLabel;
	private JLabel lblSalary;
	private JLabel lblDateOfBirth;
	private JLabel lblmmddyyyy;
	private JLabel lblNewLabel_1;
	private JTextField txtStudID;
	
	private BookCopy bookCopy;

	private clerkPortal clerkPortal ;

	public checkOut(clerkPortal c,	BookCopy t) {
		this();
		bookCopy = t;
		clerkPortal =c;
		setTitle("Issue Book Copy");

		populate(bookCopy);
		
	}
	
	private void populate(BookCopy bookcopy) {

		txtTitle.setText(bookcopy.gettitle());
		txtCopyNo.setText(bookcopy.getcopyNum()+"");
		txtAuthor.setText(bookcopy.getauthor());
		txtBookId.setText(bookcopy.getbookid()+"");
	//	dateFormat.setLenient(false);
		
		Calendar cal = Calendar.getInstance();
		formattedDateOut.setText(dateFormat.format(cal.getTime()));
		cal.add(Calendar.DATE, 14); 
	//	System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48
		
		formattedDateIn.setText(dateFormat.format(cal.getTime()));
		//formattedDateOIn.
		
	//	Date dateOfBirth = theEmployee.getDateOfBirth();
		
	//	if (dateOfBirth != null) {
	//		String dobStr = dateFormat.format(dateOfBirth);
		//	dobFormattedTextField.setText(dobStr);
	//	}
	}
	
	public checkOut() {
		
		dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
		
		setTitle("Book Issue");
		setBounds(100, 100, 462, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblFirstName = new JLabel("Book ID");
			lblFirstName.setBounds(10, 20, 61, 14);
		}
		{
			txtBookId = new JTextField();
			txtBookId.setBounds(81, 12, 86, 30);
			txtBookId.setColumns(10);
		}
		{
			lblLastName = new JLabel("Author");
			lblLastName.setBounds(10, 93, 50, 14);
		}
		{
			txtAuthor = new JTextField();
			txtAuthor.setBounds(81, 85, 295, 30);
			txtAuthor.setColumns(10);
		}
		{
			lblNewLabel = new JLabel("Copy No");
			lblNewLabel.setBounds(203, 20, 77, 14);
		}
		{
			txtCopyNo = new JTextField();
			txtCopyNo.setBounds(290, 12, 86, 30);
			txtCopyNo.setColumns(10);
		}
		{
			lblSalary = new JLabel("Title");
			lblSalary.setBounds(10, 61, 50, 14);
		}
		{
			txtTitle = new JTextField();
			txtTitle.setBounds(81, 50, 295, 30);
			txtTitle.setColumns(10);
		}
		{
			lblDateOfBirth = new JLabel("Issue Date");
			lblDateOfBirth.setBounds(10, 164, 61, 14);
		}
		{
			String maskFormat = "##/##/####";
			MaskFormatter maskFormatter = null;

			try {
				maskFormatter = new MaskFormatter(maskFormat);				
			} catch (Exception exc) {
				exc.printStackTrace();
			}

			formattedDateOut = new JFormattedTextField(maskFormatter);
			formattedDateOut.setBounds(81, 158, 86, 27);
			
			formattedDateIn = new JFormattedTextField(maskFormatter);
			formattedDateIn.setBounds(290, 158, 86, 27);
			formattedDateIn.setText("  /  /    ");
			
			
			
		}
		{
			lblmmddyyyy = new JLabel("(mm/dd/yyyy)");
			lblmmddyyyy.setBounds(81, 183, 86, 20);
		}
		contentPanel.setLayout(null);
		contentPanel.add(lblFirstName);
		contentPanel.add(txtBookId);
		contentPanel.add(lblLastName);
		contentPanel.add(txtAuthor);
		contentPanel.add(lblNewLabel);
		contentPanel.add(txtCopyNo);
		contentPanel.add(lblSalary);
		contentPanel.add(txtTitle);
		contentPanel.add(lblDateOfBirth);
		contentPanel.add(formattedDateOut);
		contentPanel.add(formattedDateIn);
		contentPanel.add(lblmmddyyyy);
		{
			lblNewLabel_1 = new JLabel("Student ID");
			lblNewLabel_1.setBounds(10, 131, 61, 14);
			contentPanel.add(lblNewLabel_1);
		}
		
		txtStudID = new JTextField();
		txtStudID.setBounds(81, 123, 295, 30);
		contentPanel.add(txtStudID);
		txtStudID.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Return Date");
		lblNewLabel_2.setBounds(203, 164, 77, 14);
		contentPanel.add(lblNewLabel_2);
		

		
		JLabel label_7 = new JLabel("(mm/dd/yyyy)");
		label_7.setBounds(290, 183, 86, 20);
		contentPanel.add(label_7);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton saveButton = new JButton("Save");
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {						
						try {
							issueBook();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			
				saveButton.setActionCommand("Save");
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
				
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				
				buttonPane.add(cancelButton);
				getRootPane().setDefaultButton(cancelButton);
			}
		}
		
	}
		

		protected void issueBook() throws Exception{

			IssueDA issue = new IssueDA();
			//BookCopy bc,String studentid,Date out,Date due
			String sdue = formattedDateIn.getText();
			String sout = formattedDateOut.getText();
			

			String studId = txtStudID.getText();
			
			Date out = null;
			Date due = null;
			
			try {
				out = getDateVal(sout);
				due = getDateVal(sdue);
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(this,	"Invalid Date of Birth: " + exc.getMessage(), "Error",	JOptionPane.ERROR_MESSAGE);

				return;
			}
			
			try {	
				
				BookCopy temp = bookCopy;
				//tempEmployee = new Employee(lastName, firstName, email, salary, dateOfBirth);
			
					// save to the database
				issue.checkOut(temp, studId, out, due);
		
				// close dialog
				setVisible(false);
				clerkPortal.refreshView();
			
			JOptionPane.showMessageDialog(clerkPortal,"Book has been Issued", "Saved",JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(clerkPortal,"Error Issuing Book: " + exc.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
		}
			
			

	}
		
		private Date getDateVal(String s) throws Exception {
			
			Date theDate = null;
			String stripped = null;
			
			String info = s;
			
			// remove the mask characters
			if (info != null) {
				stripped = info.replaceAll("/", "");
			}
			
			// check for valid date
			if (stripped != null && stripped.trim().length() > 0) {
				theDate = (Date) dateFormat.parse(info);
			}
			else {
				theDate = null;
			}
					
			return theDate;
		}
}