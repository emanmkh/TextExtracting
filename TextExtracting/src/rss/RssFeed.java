package rss;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class RssFeed {
	
	private String link; 
	private String desc;
	private String tag;
	private String tag_attr;
	private String  attr_value;
	private String  txt_source;
	private String  rss_medium;
	private String rss_con;
	private Connection c = null;
	//private Statement stmt = null;
	private ResultSet rs,rs2;
	private PreparedStatement ps, ps2;
	
	public RssFeed(){
		this.link = new String();
		this.desc = new String();
		this.tag = new String();
		this.tag_attr = new String();
		this.attr_value = new String();
		this.txt_source = new String();
		this.rss_medium =new String();
		this.rss_con = new String();
	}
	public RssFeed(String link, String desc, String tag, String tag_attr,
			String attr_value, String txt_source, String rss_medium, String rss_con) {
		this.link = link;
		this.desc = desc;
		this.tag = tag;
		this.tag_attr = tag_attr;
		this.attr_value = attr_value;
		this.txt_source = txt_source;
		this.rss_medium = rss_medium;
		this.rss_con = rss_con;
	}
	

	public String insert(){
		int con_id = 0, med_id =0, num;
		String output = null;
		try {
		  
		      c = SqliteConnection.getConn();
		      
		      ps2= c.prepareStatement("SELECT id_region_country FROM region_country WHERE region_country_name LIKE ?");
		      ps2.setString(1, rss_con);
			  rs= ps2.executeQuery();
				 if(rs.next()){
					 con_id = rs.getInt(1);
				     rs.close();
				 }
				 ps2.close(); 
				 
			      ps2= c.prepareStatement("SELECT id_rss_feed_medium  FROM rss_feed_medium WHERE rss_feed_medium_title LIKE ?");
			      ps2.setString(1, rss_medium);
			      rs2= ps2.executeQuery();
					 if(rs2.next()){
						 med_id = rs2.getInt(1);
						 rs2.close();
					 }
					 ps2.close(); 
				
		 if(con_id != 0 && med_id != 0){		 
		      String sql = "INSERT INTO rss_feed_metadata (rss_feed_link,text_tag,tag_atrribute,attribute_value,rss_description, rss_source, rss_feed_medium_id_rss_feed_medium, country_region_id_country_region) " +
		                   "VALUES (?,?,?,?,?,?,?,?);"; 
		      ps = c.prepareStatement(sql);
		      ps.setString(1, link);
		      ps.setString(2, tag);
		      ps.setString(3, tag_attr);
		      ps.setString(4, attr_value);
		      ps.setString(5, desc);
		      ps.setString(6, txt_source);
		      ps.setInt(7, med_id);
		      ps.setInt(8,con_id);
		      num = ps.executeUpdate();
		      ps.close();
		      if(num == 1)
		        output="تمت الاضافة بنجاح";
		      else
		    	output="خطأ لم تتم الاضافة";
		 }   
	} catch ( Exception e ) {
		output = e.getClass().getName() + ": " + e.getMessage();
		System.out.println(output);
	    }
		return output;
}

}
