package rss;


import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@SuppressWarnings({ "rawtypes", "unused" })
public class TextExtracting {
	
	private String rss_desc, extractedTxt = null;
	private String file_path, rss_source;
	private Connection con, con_auto;
	//private ResultSet rs;
	private PreparedStatement ps, ps2, ps3, ps4,ps5,ps6, ps7, ps8, ps_med;
    private URL rssLink;
    public static int progressSize = 0;
    public static int processTime;
    private XmlReader reader = null;
	private SyndFeed feed;
	
	public TextExtracting(String rss_desc, String file_path) {
		super();
		this.rss_desc = rss_desc;
		this.file_path = file_path;
	
	}
	

	public String createRssFeedFiles() {
		   
		   String sql, query, link, text_tag, tag_attr, attr_val, rssTitle, rssPubDate, txtLink, rss_medium = null, rss_con = null;
		   ResultSet rssFeed, rss_info, rs, rss_med;
		   URL rssLink;
		   int counter, reg_id;
		   HttpURLConnection httpcon;
		   Iterator itr;
		   XmlReader reader;
		   java.util.Date utilDate = new java.util.Date();
		   
		   String output_msg ="done";
		   int rss_id, med_id;
		 
    try {
		   con = SqliteConnection.getConn();
		   
		
		   sql = "SELECT * FROM rss_feed_metadata where rss_description LIKE ?";// for manual extraction
		   ps = con.prepareStatement(sql);
		   ps.setString(1, rss_desc);
		   rssFeed = ps.executeQuery();
			
			while(rssFeed.next()){
			   	 rss_id = rssFeed.getInt(1);
				 link = rssFeed.getString(2);
				 text_tag = rssFeed.getString(3);
				 tag_attr = rssFeed.getString(4);
				 attr_val = rssFeed.getString(5);
				 rss_source = rssFeed.getString(7);
				 med_id = rssFeed.getInt(8);
				 reg_id = rssFeed.getInt(9);
				
				
				 ps8= con.prepareStatement("SELECT region_country_name FROM region_country WHERE id_region_country = ?");
				 ps8.setInt(1, reg_id);
				 rs= ps8.executeQuery();
				 if(rs.next()){
					 rss_con = rs.getString(1);
				     rs.close();
				 }
				 ps8.close();    
				 
				 
				 ps8= con.prepareStatement("SELECT rss_feed_medium_title  FROM rss_feed_medium WHERE id_rss_feed_medium = ?");
				 ps8.setInt(1, med_id);
				 rss_med= ps8.executeQuery();
				 if(rss_med.next()){
					 rss_medium = rss_med.getString(1);
					 rss_med.close();
				 }
				 ps8.close();
				 
				 if (link != null)  {
                   rssLink  = new URL(link);
					 reader = new XmlReader(rssLink);
					 SyndFeed feed = new SyndFeedInput().build(reader);
				//	 feed.getEntries().size();
					 counter = 1;                   
					 // parsing the xml file  
					for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
						   output_msg = "";
					        SyndEntry entry = (SyndEntry) i.next();
					        rssTitle = entry.getTitle();
//					   	 if(itr.hasNext()){
//		                        rssPubDate= itr.next().toString();  
//		                    }else{
//		                    	rssPubDate = entry.getPublishedDate().toString();
//		                     }
//					     
					        
					        
					        	
						    txtLink =  entry.getLink();
						   
						    	 
						    	 
					 //   Charset.forName("ISO-8859-6").encode(txtLink);
						   // for manual extraction
								
								 rssPubDate = utilDate.toString();
	 							 rssPubDate = rssPubDate.replaceAll(":", " ");
								  extractText(txtLink, text_tag, tag_attr, attr_val);
								 if(extractedTxt != null && extractedTxt != ""){
								 //rssTitle = rss_desc+"_" +String.format("%04d", counter)+"_"+rssPubDate;
								 createFile(extractedTxt, rssTitle, counter, rssPubDate,  rssTitle, rss_medium, rss_con);
								counter++;
								 
								 }
						}
	                }
			}
			
			rssFeed.close();
			ps.close();
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
		}
	  
		return output_msg;
	}
	
	// create text files
	public void createFile(String textFile, String fileName, int file_num, String dateTime, String title, String med, String con){
       
		StringBuilder sb = new StringBuilder();
		Writer writer = null;
		int len, len1, len2, len3;
		

		//remove empty spaces from the end
		
		len = rss_source.length()-1;
	       while(rss_source.charAt(len) == ' '){
				rss_source = rss_source.substring(0, len);
				len = rss_source.length()-1;
			}
	       
	     
	       len1 = rss_desc.length()-1;
	       while(rss_desc.charAt(len1) == ' '){
	    	   rss_desc = rss_desc.substring(0, len1);
				len1 = rss_desc.length()-1;
			}
	       
		len2 = med.length()-1;
       while(med.charAt(len2) == ' '){
			med= med.substring(0, len2);
			len2 = med.length()-1;
		}

	
		len3 = con.length()-1;
      while(con.charAt(len3) == ' '){
			con = con.substring(0, len3);
			len3 = con.length()-1;
		}
		
    //remove empty spaces from the beginning 
    
      while(med.charAt(0) == ' '){
			med = med.substring(1, len2);
			len2 = med.length()-1;
		}
  
      while(con.charAt(0) == ' '){
			con = con.substring(1, len3);
			len3 = con.length()-1;
		}
	

       while(rss_source.charAt(0) == ' '){
			rss_source = rss_source.substring(1, len);
			len = rss_source.length()-1;
		}
       
       while(rss_desc.charAt(0) == ' '){
    	   rss_desc = rss_desc.substring(1, len1);
			len1 = rss_desc.length()-1;
		}

		sb.append(file_path).append("\\");
		sb.append(med).append("\\");
		sb.append(con).append("\\");
		sb.append(rss_source).append("\\");
		sb.append(rss_desc);
		
		try {   
			File dir = new File(sb.toString());
			dir.mkdirs();
			fileName =  rss_desc+"_" +String.format("%04d", file_num)+"_"+dateTime;
			sb.append("\\").append(fileName).append(".txt");
			//String name =dir.getPath()+"\\"+fileName+".txt";

			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(dir.getAbsolutePath()+"\\"+fileName+".txt"), "utf-8"));

			writer.write(textFile);
			
		//	}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {

				writer.close();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	
	public void extractText(String link, String tag, String attribute, String value) { 	
        
        String tag_name = null;
        StringBuilder text = null;
        extractedTxt = null;
        String httpLink;
        URL url;
		try {
		//	httpLink = URLEncoder.encode(link, "UTF-8");
	
			url = new URL(link);
		
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
    //    con.setRequestMethod("GET");
        connection.setRequestMethod("GET");
      //  connection.("User-Agent", Constants.DEFAULT_USER_AGENT_WINDOWS);
      //  connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
//        connection.addRequestProperty("User-Agent", 
//                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
//        connection.setRequestProperty("Content-Type", 
//                   "application/x-www-form-urlencoded");
      //connection.setRequestProperty("Content-Language", "ar"); 
//     
//        connection.setUseCaches(false);
//        connection.setDoInput(true);
//        connection.setDoOutput(true);
        connection.connect();
        int responseCode = connection.getResponseCode();
        
//        if(responseCode == 200){
        Source source=new Source(new URL(link));
//                        String tag_con=null;
        source.fullSequentialParse();
        if("div".equals(tag))
            tag_name = HTMLElementName.DIV;
  
        else if("p".equals(tag))
            tag_name = HTMLElementName.P;
        
        else if("span".equals(tag))
            tag_name = HTMLElementName.SPAN;
        else if("font".equals(tag))
            tag_name = HTMLElementName.FONT;
        /**
        switch (tag){
        case "div": tag_name = "HTMLElementName.DIV";
                                break;
        case "p": 	tag_name = "HTMLElementName.P";
                                break;
        }**/
        // HTML file parsing 
//                        net.htmlparser.jericho.Element child_element;
       List<net.htmlparser.jericho.Element> linkElements=source.getAllElements(tag_name);
       text = new StringBuilder();
        for (net.htmlparser.jericho.Element linkElement : linkElements) {
                String att_value =linkElement.getAttributeValue(attribute);
                
                                        if (value.equals(att_value)){ // searching for  tags that contain the main text
//                                                          List<net.htmlparser.jericho.Element> childElements = linkElement.getChildElements();
                                        //  if (childElements == null)
                                                text.append(" ").append(linkElement.getContent().getTextExtractor().toString());
//                                                          else{
//                                                               Iterator elItr= childElements.iterator();
//                                                              while(elItr.hasNext()){
//                                                                  child_element = (net.htmlparser.jericho.Element)elItr.next();
//                                                                  tag_con = new String(child_element.getContent().getTextExtractor().toString().getBytes("iso-8859-1"), "UTF-8");
//                                                                  text.append(" ").append(tag_con);
//                                                                  
//                                                              }
                                        }
                                     }
 
          extractedTxt = text.toString();
        } catch (IOException e) {
			// TODO Auto-generated catch block
        	System.out.println(e.getMessage());
		}
        

  
   
}	



	public void initializeSynFeed(XmlReader xr){
		  try {
			feed = new SyndFeedInput().build(xr);
		} catch (IllegalArgumentException e) {
			feed = null;
			System.out.println(e.getMessage());
		} catch (FeedException e) {
			feed = null;
			System.out.println(e.getMessage());
		}
	}
	

public void initializeURL(String link){
	 
		try {
			rssLink =  new URL(link);
		} catch (MalformedURLException e) {
			rssLink = null;
			System.out.println(e.getMessage());
		}
	
}

}