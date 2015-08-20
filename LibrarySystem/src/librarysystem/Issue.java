package librarysystem;


import java.util.Date;
public class Issue {
		
		private int id;
		private int copy;
		private String studentID;
		private Date dateIn;
		private Date dateOut;
		private Date dateDue;
		private int due;
		private int fine;

		
		public Issue(int copy, String studentID, Date dateIn, Date dateOut, Date dateDue,int due,int fine ) {

			this(0,copy, studentID, dateIn,  dateOut,dateDue,due,fine);
		}
		
		public Issue(int id,int copy, String studentID, Date dateIn, Date dateOut, Date dateDue,int due,int fine ) {
			super();
			this.id = id;
			this.copy = copy;
			this.studentID = studentID;
			this.dateIn = dateIn;
			this.dateOut = dateOut;
			this.dateDue = dateDue;
			this.due = due;
			this.fine = fine;
		}

		public int getId() {
			return id;
		}

		public void setId(int i) {
			this.id = i;
		}
		
		public String getStudentId() {
			return studentID;
		}

		public void setStudentId(String s) {
			this.studentID = s;
		}
		
		public int getcopy() {
			return copy;
		}

		public void setcopy(int i) {
			this.copy = i;
		}
		
		public int getfine() {
			return fine;
		}

		public void setfine(int i) {
			this.fine = i;
		}
		
		public int getdue() {
			return due;
		}

		public void setdue(int i) {
			this.due = i;
		}

		public Date getDateIn() {
			return dateIn;
		}

		public void setDateIn(Date date) {
			this.dateIn = date;
		}
		
		public Date getDateOut() {
			return dateOut;
		}

		public void setDateOut(Date date) {
			this.dateOut = date;
		}
		
		public Date getDatedue() {
			return dateDue;
		}

		public void setDatedue(Date date) {
			this.dateDue = date;
		}
	  
	  
}