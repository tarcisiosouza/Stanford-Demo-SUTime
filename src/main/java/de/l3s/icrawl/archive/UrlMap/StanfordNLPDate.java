package de.l3s.icrawl.archive.UrlMap;

/**
 * 
 */
//needs joda-time-2.4.jar, jollyday-0.4.7.jar

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.protobuf.TextFormat.ParseException;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.StringTokenizer;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.time.*;
import edu.stanford.nlp.time.SUTime.Temporal;
import edu.stanford.nlp.time.SUTime.Time;
import edu.stanford.nlp.util.CoreMap;

public class StanfordNLPDate {
	private static String format = "null";

	/**
	 * Example usage: java SUTimeDemo "Three interesting dates are 18 Feb 1997,
	 * the 20th of july and 4 days from today."
	 *
	 * @param args
	 *            Strings to interpret
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		String url = "http://www.motor-talk.de:80/forum/stern-tv-vom-12-12-2005-bmw-320d-made-in-germany-t1746813.html";
//		http://www.rtl.de/rtl-nachrichtenarchiv/nachrichten-vom-13-04-2012/seite-2.html
//		http://www.szenenight.de:80/partyfotos/04-04-2012-abiparty-sz-walle/bild/0124/anzeigen.html
//		http://www.motor-talk.de:80/forum/stern-tv-vom-05-03-2008-bmw-320d-made-in-germany-t1746813.html
		String[] text2 = getDate (url);
		
		System.out.println("After Regex: "+text2[0]);
		String result = null;
		if (text2[0]!=null)
			result = convertToDate (text2[0]);
//		if (result!=null)
//		if (result != null)
//		System.out.println ("After parsing: "+result + " " + format);
	
		/*
		SimpleDateFormat datef = new SimpleDateFormat ("yyyy-MM-dd");
		try {
			Date date = datef.parse(text2[0]);
			System.out.println (date.toString());
			} catch (Exception e)
			{
				System.out.println ("exception");
			}
			*/
	}
	private static String[] getDate(String desc) {
		  int count=0;
		  String[] allMatches = new String[1];
//		  Matcher m = Pattern.compile("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19)[9]\\d").matcher(desc);
		  Matcher m = Pattern.compile("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19)\\d\\d").matcher(desc);
//		  Matcher m1 = Pattern.compile("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](20)[01]\\d").matcher(desc);
		  Matcher m1 = Pattern.compile("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](20)\\d\\d").matcher(desc);
//		  Matcher m2 = Pattern.compile("(19)[9]\\d[- /.](0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|[12][0-9]|3[01])").matcher(desc);
		  Matcher m2 = Pattern.compile("(19)\\d\\d[- /.](0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|[12][0-9]|3[01])").matcher(desc);
//		  Matcher m3 = Pattern.compile("(20)[01]\\d[- /.](0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|[12][0-9]|3[01])").matcher(desc);
		  Matcher m3 = Pattern.compile("(20)\\d\\d[- /.](0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|[12][0-9]|3[01])").matcher(desc);
//		  Matcher m4 = Pattern.compile("(19)[9]\\d(0[1-9]|[12][0-9]|3[01])(0[1-9]|[12][0-9]|3[01])").matcher(desc);
		  Matcher m4 = Pattern.compile("(19)\\d\\d(0[1-9]|[12][0-9]|3[01])(0[1-9]|[12][0-9]|3[01])").matcher(desc);
//		  Matcher m5 = Pattern.compile("(20)[01]\\d(0[1-9]|[12][0-9]|3[01])(0[1-9]|[12][0-9]|3[01])").matcher(desc);
		  Matcher m5 = Pattern.compile("(20)\\d\\d(0[1-9]|[12][0-9]|3[01])(0[1-9]|[12][0-9]|3[01])").matcher(desc);
//		  Matcher m6 = Pattern.compile("(19)[9]\\d[- /.](0[1-9]|[12][0-9])").matcher(desc);
		  Matcher m6 = Pattern.compile("(19)\\d\\d[- /.](0[1-9]|[12][0-9])").matcher(desc);
//		  Matcher m7 = Pattern.compile("(20)[01]\\d[- /.](0[1-9]|[12][0-9])").matcher(desc);
		  Matcher m7 = Pattern.compile("(20)\\d\\d[- /.](0[1-9]|[12][0-9])").matcher(desc);
		  
		  if (m.find()) {
		    allMatches[count] = m.group();
		    return allMatches;
		  }
		  
		  if (m1.find()) {
			    allMatches[count] = m1.group();
			    return allMatches;
		  }
		  
		  if (m2.find()) {
			    allMatches[count] = m2.group();
			    return allMatches;
			    
			  }
		  
		  if (m3.find()) {
			    allMatches[count] = m3.group();
			    return allMatches;
			    
			  }
		  if (m4.find()) {
			  	
			    allMatches[count] = m4.group();
			    return allMatches;
		  }
		  if (m5.find()) {
			  	
			    allMatches[count] = m5.group();
			    return allMatches;
		  }
		  if (m6.find()) {
			  	
			    allMatches[count] = m6.group();
			    return allMatches;
		  }
		  
		  if (m7.find()) {
			  	
			    allMatches[count] = m7.group();
			    return allMatches;
		  }
		  
		 
		  return allMatches;
		}
	
	    public static String convertToDate(String input) throws java.text.ParseException, ParseException {
	        Date date = null;
	        
	        if(null == input) {
	            return null;
	        }
	        
	          ArrayList<SimpleDateFormat>  dateFormats = new ArrayList<SimpleDateFormat>() {/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
	            add(new SimpleDateFormat("M/dd/yyyy"));
	            add(new SimpleDateFormat("dd/MM/yyyy"));
	            add(new SimpleDateFormat("dd.M.yyyy"));
	            add(new SimpleDateFormat("M/dd/yyyy hh:mm:ss a"));
	            add(new SimpleDateFormat("dd.M.yyyy hh:mm:ss a"));
	            add(new SimpleDateFormat("dd.MMM.yyyy"));
	            add(new SimpleDateFormat("dd-MMM-yyyy"));
	            add(new SimpleDateFormat("dd-MM-yyyy"));
	            add(new SimpleDateFormat("yyyyMMdd"));
	            add(new SimpleDateFormat("yyyy-MM-dd"));
	            add(new SimpleDateFormat("yyyy-MM"));
	            
	            add(new SimpleDateFormat("dd.MM.yyyy"));
	            add(new SimpleDateFormat("yyyy/MM/dd"));
	            add(new SimpleDateFormat("yyyy.MM.dd"));
	          }
	          };
	          
	        for (SimpleDateFormat form : dateFormats) {
	            form.setLenient(false);
	            
	            try {
	            	date = form.parse(input);	
	            	format = form.toPattern().toString();
	            	return date.toString();
	            } catch (Exception e)
	            {
	            	continue;
	            }
				
	        }
	        if (date == null)
	        	return "";
	        
	        return date.toString();
	    }
	public static String parseDate(String date_str) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat df11 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat df3 = new SimpleDateFormat("EEE, MMM d, yyyy");
        SimpleDateFormat df4 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        SimpleDateFormat df5 = new SimpleDateFormat("EEE, MMM. dd, yyyy");
        SimpleDateFormat df6 = new SimpleDateFormat("EEE, MMM dd, yyyy");
        SimpleDateFormat df7 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat df8 = new SimpleDateFormat("EEEEE d MMM yyyy");
        SimpleDateFormat df9 = new SimpleDateFormat("EEEEE, MMM. dd, yyyy");
        SimpleDateFormat df10 = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat df12 = new SimpleDateFormat("MMM dd, yyyy, HH:mm a");
        SimpleDateFormat df14 = new SimpleDateFormat("MMM dd, yyyy, H:mm a");
        SimpleDateFormat df13 = new SimpleDateFormat("dd.MM.yyyy");
        
        SimpleDateFormat df15 = new SimpleDateFormat("MMMMM dd, yyyy, HH:mm a");
        SimpleDateFormat df16 = new SimpleDateFormat("MMMMM dd, yyyy, H:mm a");
        SimpleDateFormat df17 = new SimpleDateFormat("MMM. dd, yyyy");
        SimpleDateFormat df18 = new SimpleDateFormat("MMMMM dd, yyyy");
        SimpleDateFormat df19 = new SimpleDateFormat("EEEEE, MMM. dd, yyyy");
        SimpleDateFormat df_20 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        SimpleDateFormat df21 = new SimpleDateFormat("MMMM dd, yyyy");
        SimpleDateFormat df22 = new SimpleDateFormat("MMMM dd, yyyy HH:mm a");
        SimpleDateFormat df23 = new SimpleDateFormat("EEE MMMM dd HH:mm:ss z yyyy");
        SimpleDateFormat df24 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df27 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df28 = new SimpleDateFormat("dd-mm-yyyy");
        List<SimpleDateFormat> lst = new ArrayList<SimpleDateFormat>();
        lst.add(df);
        lst.add(df1);
        lst.add(df2);
        lst.add(df3);
        lst.add(df4);
        lst.add(df5);
        lst.add(df6);
        lst.add(df7);
        lst.add(df8);
        lst.add(df9);
        lst.add(df10);
        lst.add(df11);
        lst.add(df12);
        lst.add(df13);
        lst.add(df14);
        lst.add(df15);
        lst.add(df16);
        lst.add(df17);
        lst.add(df18);
        lst.add(df19);
        lst.add(df_20); 
        lst.add(df21);
        lst.add(df22);
        lst.add(df23);
        lst.add(df24);
        
        lst.add(df28);
        lst.add(df27);
        SimpleDateFormat df_simple = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat df_simple2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
        	Date dt = df_simple2.parse(date_str);
        	format = df_simple2.toPattern().toString();
        	return df_simple2.format(dt);
        } catch (Exception e)
        {
        	
        }
        
        for (SimpleDateFormat df_tmp : lst) {
            try {
                Date dt = df_tmp.parse(date_str);
                format = df_tmp.toPattern().toString();
               
                return df_simple.format(dt);
            } catch (Exception e) {
                continue;
            }
        }
        return "";

    }

}
