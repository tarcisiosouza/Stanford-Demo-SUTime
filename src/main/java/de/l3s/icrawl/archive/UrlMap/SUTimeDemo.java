package de.l3s.icrawl.archive.UrlMap;

import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.time.*;
import edu.stanford.nlp.util.CoreMap;

public class SUTimeDemo {

	/**
	 * Example usage: java SUTimeDemo "Three interesting dates are 18 Feb 1997,
	 * the 20th of july and 4 days from today."
	 *
	 * @param args
	 *            Strings to interpret
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		Properties props = new Properties();
		AnnotationPipeline pipeline = new AnnotationPipeline();
		pipeline.addAnnotator(new edu.stanford.nlp.pipeline.TokenizerAnnotator(false));
		//pipeline.addAnnotator(new WordsToSentencesAnnotator(false));
		//pipeline.addAnnotator(new POSTaggerAnnotator(false));
		pipeline.addAnnotator(new TimeAnnotator("sutime", props));

		// String doc="";
		//URL url = new URL("http://en.wikipedia.org/wiki/Angela_Merkel");
		//BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		
		//String text ="2006";

		//String url = "http://fussballdaten.de/bilder/szenen/2015-06-11/2006/coupet.jpg";
		String url ="http://www.transfermarkt.de/bilder/minifotos/s_73092_1025_2013_09_23_1.jpg";
		
		
		//replace all special characters except "-" as needed for date
		String text1 =url.replaceAll("[^\\w\\s\\-]", " ").replaceAll("_", " ");
		String text2 = remove_invalid_years (text1);
		System.out.println("Parsed: "+text1);
		System.out.println("Parsed 2: "+text2);
		Annotation annotation = new Annotation(text2);
			annotation.set(CoreAnnotations.DocDateAnnotation.class, "2013-07-14");
			pipeline.annotate(annotation);
			// System.out.println(annotation.get(CoreAnnotations.TextAnnotation.class));
			List<CoreMap> timexAnnsAll = annotation.get(TimeAnnotations.TimexAnnotations.class);

			for (CoreMap cm : timexAnnsAll) {
						System.out.println(cm.get(TimeExpression.Annotation.class).getTemporal());
			}
			 System.out.println("--");
		
		
	}

	private static String remove_invalid_years(String text1) {
		
		int number=0;
		String current;
		StringTokenizer token = new StringTokenizer (text1);
		while (token.hasMoreTokens())
		{
			try 
			{
				current = token.nextToken();
				if (current.length()!=4)
					continue;
				else
					number = Integer.parseInt(current);
				if (number < 1970)
					text1 = text1.replaceAll(Integer.toString(number),"");
			} catch (Exception e)
			{
				continue;
			}
			
		}
		
		return text1;
	}

}