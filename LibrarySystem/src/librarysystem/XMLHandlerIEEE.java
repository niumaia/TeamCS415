/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;
import java.util.ArrayList;
import java.util.List;
import librarysystem.Journal;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 *
 * @author tabunakawai_n
 */
public class XMLHandlerIEEE  extends DefaultHandler  {
    
    private List<Journal> jList = null;
    private Journal j = null;
    
 
    //getter method for employee list
    public List<Journal> getJournalList() {
        return jList;
    }
    
        boolean bisbn = false;
	boolean btitle = false;
	boolean bauthor = false;
	boolean bpubtitle = false;
	boolean babstract = false;
        boolean bpy = false;
        
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)  throws SAXException {
 
        if (qName.equalsIgnoreCase("document")) {
         
         //   String id = attributes.getValue("rank");
           
            j = new Journal();
            j.setID(Integer.parseInt("0"));
            //initialize list
            if (jList == null) jList = new ArrayList<>();
            
        }else if (qName.equalsIgnoreCase("isbn")) {
			bisbn = true;
	}else if (qName.equalsIgnoreCase("title")) {
			btitle = true;
	}else if (qName.equalsIgnoreCase("py")) {
			bpy = true;
	}else if (qName.equalsIgnoreCase("authors")) {
			bauthor = true;
	}else if (qName.equalsIgnoreCase("pubtitle")) {
			bpubtitle = true;
	}else if (qName.equalsIgnoreCase("abstract")) {
			babstract = true;
	}else if (qName.equalsIgnoreCase("Error")) {
			return;
        }
    }
    
     @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("document")) {
            //add Employee object to list
            jList.add(j);
        }    
    }
    
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        
		if (btitle) {
		//	System.out.println("Title : " + new String(ch, start, length));
			btitle = false;
                        j.settitle(new String(ch, start, length));
		}else if (bisbn) {
		//	System.out.println("ISBN : " + new String(ch, start, length));
			bisbn = false;
                        j.setisbn(new String(ch, start, length));
		}else if (bpy) {
		//	System.out.println("Year : " + new String(ch, start, length));
			bpy = false;
                        j.setYear(new String(ch, start, length));
		}else if (bauthor) {
		//	System.out.println("Author(s) : " + new String(ch, start, length));
			bauthor = false;
                        j.setauthors(new String(ch, start, length));
		}else if (bpubtitle) {
		//	System.out.println("Publication : " + new String(ch, start, length));
			bpubtitle = false;
                        j.setPublication(new String(ch, start, length));
		}if (babstract) {
		//	System.out.println("Abstract : " + new String(ch, start, length));
			babstract = false;
                        j.setAbstract(new String(ch, start, length));
                  //     System.out.println(j.getAbstract());
		}
    }
        
    
}
