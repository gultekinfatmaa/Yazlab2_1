package ToolsQA.DemoMavenEclipseProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

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
	
	
	public static List<Integer> frequency(String a) throws FileNotFoundException {
		
		int counter=1;
	    List<String> WordList=new ArrayList<String>();  
	    List<Integer> CounterList=new ArrayList<Integer>(); 
	    List<String> fileWords = new ArrayList<>();
	    List<Integer> biggest=new ArrayList<Integer>(); 
	    
	    String[] words=URLparsing(a).split(" ");
		
		File f = new File("C:\\\\Users\\\\fatma\\\\source\\\\repos\\\\DemoMavenEclipseProject\\\\connectors.txt");
	    Scanner scanner = new Scanner(f);
	    for(int i=0;scanner.hasNext();i++) {
	    	fileWords.add(i, scanner.nextLine());
	    }
	    for(int i=0;i<words.length;i++) {
	    	if(!(WordList.contains(words[i]))) {
	    		for(int j=i+1;j<words.length;j++) {
	    	    	if(words[i].equals(words[j]) &&  !fileWords.contains(words[i]) ) {
	    	    		counter++;
	    	    	}
	    	    }
	    		WordList.add(words[i]);
	        	CounterList.add(counter);
	        	counter=1;
	    	}
	    }
	    int flag=0;	    
	    int bigger=CounterList.get(0);
	    
	    for(int j=0;j<5;j++) {
	    	for(int i=1;i<CounterList.size();i++) {
		    	if(CounterList.get(i)>bigger) {
		    		bigger=CounterList.get(i);
		    		flag=i;
		    		System.out.println("\n"+i);
		    	}
		    }
	    	biggest.add(bigger);
	    	CounterList.set(flag, 0);
	    	flag=0;
	    }
	    System.out.printf(WordList.get(flag) + " " + CounterList.get(flag));
	    
	    return biggest;
	}
	
	
    public static void main( String[] args ) throws FileNotFoundException
    {
    	
	    Scanner input=new Scanner(System.in);
	    
	    System.out.println("Input URL:");
	    String a=input.nextLine();
	    
	    System.out.println(frequency(a).get(0));
	    //    https://en.wikipedia.org/wiki/German_Workers%27_Party
	    //	  https://en.wikipedia.org/wiki/Adolf_Hitler
	    
	    
	    //tüm kelimeleri frekansları ile birlikte yazdırır
	    /*
	    for(int i=0;i<CounterList.size();i++) {
	    	System.out.println(CounterList.get(i)+" adet "+WordList.get(i));
	    }
	    */
	    System.out.println("\n------------------------------------");
	    
	    
	    /*System.out.println("\n 1. ASAMA");
	    System.out.println(WordList.get(flag) + " " + CounterList.get(flag));*/
    	
	    //2. ASAMA
	    /*System.out.println("Input URL:");
	    String b=input.nextLine();
	    
	    frequency(b);*/
	    
    }
}
