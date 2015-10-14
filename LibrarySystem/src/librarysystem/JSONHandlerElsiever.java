/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.io.*;
import java.net.*;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONHandlerElsiever {

   // private static final String KEY = "ae63f3bc300cefaee6b7468b0717d7dd";
    private String url;
    List<Journal> blist = null;

    
    public JSONHandlerElsiever(String s) {
        this.url = s;
    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }

            return buffer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public List<Journal> parseJson() {
      //  String elsevier = "http://api.elsevier.com/content/search/scidir?apiKey=" + KEY + "&query=ttl(neural)";
        try {
            //URL uri = new URL(elsevier);

            JSONParser parser = new JSONParser();
            Object obj = parser.parse(readUrl(url));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject searchObj = (JSONObject) jsonObject.get("search-results");

            if (searchObj.containsKey("entry")) {
                JSONArray entryarray = (JSONArray) searchObj.get("entry");
                Journal j = null;// new Journal();
                blist = new ArrayList<Journal>();
                //JSONArray autharray = null;

                for (int i = 0; i < entryarray.size(); i++) {
                    j = new Journal();
                    JSONObject jnext = (JSONObject) entryarray.get(i);
                    
                    j.setID(0);
                    j.setAbstract(jnext.get("prism:teaser").toString());
                    j.setPublication(jnext.get("prism:publicationName").toString());
                    j.settitle(jnext.get("dc:title").toString());
                    j.setisbn(jnext.get("prism:issn").toString());
                    j.setYear(jnext.get("prism:coverDisplayDate").toString());
                    String auths = "";
                    
                    if (jnext.containsKey("authors")) {
                        JSONObject jauthors = (JSONObject) jnext.get("authors");
                        JSONArray autharray = (JSONArray) jauthors.get("author");
                        
                            for (int x = 0; x < autharray.size(); x++) {
                                JSONObject anext = (JSONObject) autharray.get(x);
                                auths = auths + " " + anext.get("given-name") + " " + anext.get("surname");
                            }
                    j.setauthors(auths);        
                    }else  j.setauthors("");
                   
                    blist.add(j);
                }               
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return blist;
    }

}
