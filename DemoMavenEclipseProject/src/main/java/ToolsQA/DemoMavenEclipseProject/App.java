package ToolsQA.DemoMavenEclipseProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class App 
{
	public static String URLparsing(String webUrl) {
		URL url;
	    InputStream is = null;
	    BufferedReader br;
	    String line;
	    String htmlString ="";
	    String d="";

	    try {
	        url = new URL(webUrl);
	        is = url.openStream();  // throws an IOException
	        br = new BufferedReader(new InputStreamReader(is));

	        while ((line = br.readLine()) != null) {
	            //System.out.println(line);
	            htmlString +=line;
	            
	        }
	    } catch (MalformedURLException mue) {
	         mue.printStackTrace();
	    } catch (IOException ioe) {
	         ioe.printStackTrace();
	    } finally {
	        try {
	            if (is != null) is.close();
	        } catch (IOException ioe) {
	            // nothing to see here
	        }
	    }
    	try {    	
            Document doc = Jsoup.parse(htmlString);
            String title = doc.title();
            String body = doc.body().text();

            System.out.printf("Title: %s%n", title);
            System.out.printf("Body: %s", body);
            d=title+body;
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
		return d.toLowerCase();
	}
	
	
	
    public static void main( String[] args )
    {
    	
	    int counter=1;
	    Scanner input=new Scanner(System.in);
	    List<String> WordList=new ArrayList<String>();  
	    List<Integer> CounterList=new ArrayList<Integer>(); 
	    /*String[] connectors=["one","this","what","and","for","very","i","yes","there","is","am","are","was","were","but","as","not","nor",
	                         "because", "although", "as", "soon" ,"when", "however", "just", "even", "if", "by", "the" ,"while", "in",
	                          "that","until","once", "during", "therefore", "of","at", "just", "much","rather", "without", "unless", "more", "both", "";
	    */
	    System.out.println("Input URL:");
	    String a=input.nextLine();
	    String b="ben sen o bu şu";
	    String[] words=URLparsing(a).split(" ");
	    //System.out.println("\n kelime sayısı "+words.length);
	    
	    
	    //https://en.wikipedia.org/wiki/German_Workers%27_Party
	    //URLparsing(a);
	    System.out.println("\n "+words[1]);
	    
	    for(int i=0;i<words.length;i++) {
	    	//for(int k=0;k<WordList.size();k++) {
	    		if(!(WordList.contains(words[i]))) {
	    			for(int j=i+1;j<words.length;j++) {
	    	    		if(words[i].equals(words[j]) ) {
	    	    			counter++;
	    	    			//words[j]=null;
	    	    		}
	    	    	}
	    			WordList.add(words[i]);
	        		CounterList.add(counter);
	        		counter=1;
	    		}
	    	//}
	    	
	    }
	    for(int i=0;i<CounterList.size();i++) {
	    	System.out.println(CounterList.get(i));
	    }
	    for(int i=0;i<WordList.size();i++) {
	    	System.out.println(WordList.get(i));
	    }
	    //aa b hj ıo aa jj lo lş aa
    	
    }
}
