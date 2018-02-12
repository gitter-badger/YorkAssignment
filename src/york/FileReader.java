package york;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.regex.Pattern;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.StartElement;

public class FileReader {
	static String folderPath = "E:\\PhD Hunt\\7-York Zhen Ming Jiang\\hbaseBugReport\\hbaseBugReport\\";
	SVNLogs svn=new SVNLogs();
	BugReports rep=new BugReports();
	

public  void listFilesForFolder(final File folder) throws FileNotFoundException {
	for (final File fileEntry : folder.listFiles()) {
		if (fileEntry.isDirectory()) {
			listFilesForFolder(fileEntry);
		} else {
			System.out.println(fileEntry.getName());
			readBugReport(folderPath + fileEntry.getName());
			System.out.println("------------------------------------------");
		}
	}
} //listFilesForFolder

//Task 1
public void readLogFile(String path) throws FileNotFoundException {
	
	System.out.println("Reading SVN Log File "+path);
	XMLInputFactory inputFactory = XMLInputFactory.newInstance();
    InputStream in = new FileInputStream(path); //"hbase_svn_log.xml"
    XMLStreamReader streamReader;
    
	try {
		streamReader = inputFactory.createXMLStreamReader(in);
		streamReader.nextTag();		 //Advance to next element
	    streamReader.nextTag(); 		//Advance to next element

	    while (streamReader.hasNext()) {
	    	
	        if(streamReader.isStartElement()) {
	        	 String str= streamReader.getLocalName();
	        	 
	        	 if(str=="author")
	        	 {
	        		String s= streamReader.getElementText();
	        		// System.out.println("Author:"+s);
	        		 svn.add(s);
	        	 }
	        }
	       streamReader.next();
	    }

	} catch (XMLStreamException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
}
//Task 2
public void readBugReport(String path) throws FileNotFoundException {
	
	System.out.println("Reading Bug Report "+path);
	XMLInputFactory inputFactory = XMLInputFactory.newInstance();
    InputStream in = new FileInputStream(path); //"hbase_svn_log.xml"
    XMLStreamReader streamReader;
    String res_time=null;
    String create_time=null;
    
	try {
		streamReader = inputFactory.createXMLStreamReader(in);
		streamReader.nextTag();		 //Advance to next element
	    streamReader.nextTag(); 		//Advance to next element

	    while (streamReader.hasNext())
	    {
	    	
	        if(streamReader.isStartElement()) {
	        	 String str= streamReader.getLocalName();
	
	        	 if(str=="resolved")
	        	 {
	        		res_time= streamReader.getElementText();
	        		//System.out.println("Resolved time:"+res_time);
	        	 }
	        	else if(str=="created")
	        	{
	        		create_time= streamReader.getElementText();
	        		//System.out.println("Created time:"+create_time);
	        		
	        	}	
	        }
	        
	       streamReader.next();
	       
	    }
    	
	    //take difference of time
	    rep.list.add(MyDateTime.calculateTimeDiff(create_time,res_time)); //MyDateTime.calculateTimeDiff(res_time,create_time);
	    
	  
	} catch (XMLStreamException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

//Task3 
public void readLogForIssues(String path) throws FileNotFoundException {
	
	System.out.println("Reading SVN Log File "+path+" for Task 3");
	XMLInputFactory inputFactory = XMLInputFactory.newInstance();
    InputStream in = new FileInputStream(path); //"hbase_svn_log.xml"
    XMLStreamReader streamReader;
	try {
		streamReader = inputFactory.createXMLStreamReader(in);
		streamReader.nextTag();		 //Advance to next element
	    streamReader.nextTag(); 		//Advance to next element
		
	    while (streamReader.hasNext()) {
	    	//System.out.println("what it prints:"+streamReader.getAttributeValue(0));
	    	  if(streamReader.isStartElement()) {

	        	 String str= streamReader.getLocalName();
	        	 
	        	 if(str=="msg")
	        	 {
	        		String line= streamReader.getElementText();
	        		//tokenize message to get Jira Issue Id and save in 's'
	        		int i = line.indexOf(' ');
	        		String token=null;
	        		if(i>0)
	        			token = line.substring(0, i);
	        		if(token!=null && Pattern.compile( "[0-9]" ).matcher(token ).find())
	        		{	//System.out.println("Token:"+token);
	        			svn.addIssue(token);}
	        	    }
	        }
	       streamReader.next();
	    }

	} catch (XMLStreamException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
}

}
