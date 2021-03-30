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
	            //System.out.println(line); //html kodu yazdırıyor
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
	
	
	public static List<String> keywords(String a) throws FileNotFoundException {
		
		int counter=1;
		List<String> WordList=new ArrayList<String>();  
	    List<Integer> CounterList=new ArrayList<Integer>(); 
	    List<String> fileWords = new ArrayList<>();
	    List<Integer> biggest=new ArrayList<Integer>(); 
	    List<String> biggestWords=new ArrayList<String>(); 
	    
	    String[] words=URLparsing(a).split(" "); //url içerisindeki kelimeleri alıyor
		/* ,".",",",":",";","_","-","(",")","[","]","{","}","!","'","^","+",
		"%","&","/","=","?","*","|","<",">" */
		File f = new File("C:\\\\Users\\\\fatma\\\\source\\\\repos\\\\DemoMavenEclipseProject\\\\connectors.txt"); //ayıklanacak bağlaçlar
	    Scanner scanner = new Scanner(f);
	    
	    //ayıklanacak bağlaçlar dosyadan çekiliyor
	    for(int i=0;scanner.hasNext();i++) {
	    	fileWords.add(i, scanner.nextLine());
	    }
	    
	    //frekanslar hesaplanıyor
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
	    //frekansı en yüksek 10 kelimeyi buluyor
	    int flag=0;	    
	    int bigger=CounterList.get(0);
	    
	    for(int j=0;j<20;j++) {
	    	for(int i=1;i<CounterList.size();i++) {
		    	if(CounterList.get(i)>bigger) {
		    		bigger=CounterList.get(i);
		    		flag=i;
		    		//System.out.println("\n"+i+" a"+bigger);
		    	}
		    }
	    	biggest.add(bigger);
	    	biggestWords.add(WordList.get(flag));
	    	CounterList.remove(flag);
	    	bigger=CounterList.get(0);
	    	flag=0;
	    }
	    
	    //frekansı en yüksek 10 kelimeyi yazdırıyor
	    for(int i=0;i<biggestWords.size();i++) {
	    	System.out.println("\nt"+i+" "+biggest.get(i)+" "+biggestWords.get(i));
	    }
	    return biggestWords;
	}
	
	
	
	
	
	
	
    public static void main( String[] args ) throws FileNotFoundException
    {
    	List<String> biggestWords=new ArrayList<String>();
    	List<String> biggestWords2=new ArrayList<String>();
	    Scanner input=new Scanner(System.in);
	    
	    System.out.println("Input URL:");
	    String a=input.nextLine();
	    
	    biggestWords=keywords(a);
	    
	    //    https://en.wikipedia.org/wiki/German_Workers%27_Party
	    //	  https://en.wikipedia.org/wiki/Adolf_Hitler
	    //frequency
	    
	    System.out.println("\n------------------------------------");
	    System.out.println("\n"+a+" için nahtar kelimeler:");
	    for(int i=0;i<biggestWords.size();i++) {
	    	System.out.println(" "+biggestWords.get(i));
	    }
	    
	    System.out.println("Input URL:");
	    String b=input.nextLine();
	    biggestWords2=keywords(b);
	    System.out.println("\n"+b+" için nahtar kelimeler:");
	    for(int i=0;i<biggestWords2.size();i++) {
	    	System.out.println(" "+biggestWords2.get(i));
	    }
	    
	    //benzerlik
	    int flag=0;
	    for(int i=0;i<biggestWords.size();i++) {
	    	if(biggestWords2.contains(biggestWords.get(i))) {
	    		System.out.println("\nbenzer kelime "+biggestWords.get(i));
	    		flag++;
	    	}
	    }
	    System.out.println(a+" ve "+b+" arasındaki benzerlik oranı %"+(flag*500)/100);
    }
}
/*public int similarity(List<Integer> biggest,List<Integer> CounterList) {

int frequency=0;
int frequencySum=0;
int sum=0;
for(int i=0;i<biggest.size();i++) {
	frequencySum+=biggest.get(i);
}
for(int i=0;i<CounterList.size();i++) {
	sum+=CounterList.get(i);
}
frequency=frequencySum/sum;
return frequency;
}
*/