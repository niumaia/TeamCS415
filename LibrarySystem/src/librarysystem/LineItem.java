/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Date;
/**
 *
 * @author tabunakawai_n
 */
@Entity
@Table(name = "line_item")
@NamedQueries({
    @NamedQuery(name = "LineItem.findAll", query = "SELECT l FROM LineItem l")})
public class LineItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "item_id")
    private Integer itemId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "transaction_id", referencedColumnName = "transaction_id")
    @ManyToOne
   // private Transaction transactionId;
    private Integer transactionId;
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    @ManyToOne
  //  private Account accountId;
    private Integer accountId;
    // b.bk_title,b.bk_author,c.bkCatnum,i.checkIndate -i.issueduedate days ,l.amount,t.transaction_id
    private String title;
    private String author;
    private String catalogNum;
    private int numDays;
    private Date due;

    public LineItem() {
    }

    public LineItem(int tran,int account,String description, String title, String author,String catalog,int num,Double fine,Date date)  
    {
        this(0, tran, account, description, title, author, catalog, num, fine,date) ;
    }
    
    public LineItem(int item,int tran,int account,String description, String title, String author,String catalog,int num,Double fine,Date date) {
        super();
	this.transactionId = tran;
        this.accountId = account;
	this.itemId = item;
        this.author = author;
	 this.author = author;
          this.title = title;
        this.catalogNum = catalog;
	this.numDays = num;
        this.amount = fine;
        this.due = date;
    }
    
    
    public LineItem(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Double getAmount() {
        return amount;
    }
    
     public Date getDueDate() {
        return due;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getTitle() {
        return title;
    }
    
    public int getDays() {
        return numDays;
    }
   
      public String getCatalog() {
        return catalogNum;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemId != null ? itemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LineItem)) {
            return false;
        }
        LineItem other = (LineItem) object;
        if ((this.itemId == null && other.itemId != null) || (this.itemId != null && !this.itemId.equals(other.itemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "librarysystem.LineItem[ itemId=" + itemId + " ]";
    }
    
}
