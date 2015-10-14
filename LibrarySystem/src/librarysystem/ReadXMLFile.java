/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import java.util.List;
import org.xml.sax.helpers.DefaultHandler;

public class ReadXMLFile {
    
    //public static List<Journal> blist ;//= new ArrayList<Journal>();

  //  public List<Journal> getSearchResults() {
        
    //
  //      return blist;
  //  }
    
   public static void main(String argv[]) {
  //  public ReadXMLFile(String param){
    //  public List<Journal> getReservedBooks() 
    //getResults();
    //}
    
  //public void getResults(){
        
    try {

	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser = factory.newSAXParser();

	DefaultHandler handler = new DefaultHandler() {
        boolean bisbn = false;
	boolean btitle = false;
	boolean bauthor = false;
	boolean bpubtitle = false;
	boolean babstract = false;
        boolean bpy = false;

	public void startElement(String uri, String localName,String qName,Attributes attributes) throws SAXException {

	//	System.out.println("Start Element :" + qName);
                if (qName.equalsIgnoreCase("isbn")) {
			bisbn = true;
		}

		if (qName.equalsIgnoreCase("title")) {
			btitle = true;
		}
                
                if (qName.equalsIgnoreCase("py")) {
			bpy = true;
		}

		if (qName.equalsIgnoreCase("authors")) {
			bauthor = true;
		}

		if (qName.equalsIgnoreCase("pubtitle")) {
			bpubtitle = true;
		}

		if (qName.equalsIgnoreCase("abstract")) {
			babstract = true;
		}

	}

	public void endElement(String uri, String localName,String qName) throws SAXException {

	//	System.out.println("End Element :" + qName);

	}

	public void characters(char ch[], int start, int length) throws SAXException {
                Journal j = new Journal();
                //  List<Journal> jList = null;
                List<Journal>   blist = new ArrayList<Journal>();
                  
		if (btitle) {
			System.out.println("Title : " + new String(ch, start, length));
			btitle = false;
                        j.settitle(new String(ch, start, length));
		}
                
                if (bisbn) {
			System.out.println("ISBN : " + new String(ch, start, length));
			bisbn = false;
                        j.setisbn(new String(ch, start, length));
		}
                
                if (bpy) {
			System.out.println("Year : " + new String(ch, start, length));
			bpy = false;
                        j.setYear(new String(ch, start, length));
		}

		if (bauthor) {
			System.out.println("Author(s) : " + new String(ch, start, length));
			bauthor = false;
                        j.setauthors(new String(ch, start, length));
		}

		if (bpubtitle) {
			System.out.println("Publication : " + new String(ch, start, length));
			bpubtitle = false;
                        j.setPublication(new String(ch, start, length));
		}

		if (babstract) {
			System.out.println("Abstract : " + new String(ch, start, length));
			babstract = false;
                        j.setAbstract(new String(ch, start, length));
                     //   System.out.println(j.getAbstract());
		}
             blist.add(j);
                 //   JournalGrid model = new JournalGrid(blist);

                   // jTableJournals.setModel(model);
	}

     };

     //  saxParser.parse("c:\\file.xml", handler);
          saxParser.parse("http://ieeexplore.ieee.org/gateway/ipsSearch.jsp?au=Xing", handler);
 
     } catch (Exception e) {
       e.printStackTrace();
     }
  
   }

}