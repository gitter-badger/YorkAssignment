package york;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.xml.stream.*;


public class Driver {

	
     
    // There are several entry points to this class
    // this is just to show one of them.
      public static void main(String argv[]) throws Exception {
    	 
    	  //Task1

    	  System.out.println("Question 1 analysis");
    	  FileReader f= new FileReader();
    	  f.readLogFile("hbase_svn_log.xml");
    	  System.out.println("\n"+ f.svn.getMax().getKey()+" commited largest number of revisions i.e. "+f.svn.getMax().getValue());    
    	  System.out.println("===================================");
    	  //Task2
    	  System.out.println("Question 2 Analysis");
    	  f.folderPath = "temp\\";
    	  final File folder = new File(f.folderPath);
    	  f.listFilesForFolder(folder);  //own function
    	  //get maximum bug resolution time 
          System.out.println("Max Bug Resolution Time in Seconds:"+f.rep.findBugReolutionTime()+" seconds");
          System.out.println("===================================");

          //Task3

    	  System.out.println("Question 3 Analysis");
          f.readLogForIssues("hbase_svn_log.xml");
          float [] stats=new float [3];
    	  stats=f.svn.countRevisionForIssues();
    	  System.out.println("Max revisions:"+stats[0]+ "  Min revisions:"+stats[1]+" Avg revisions:"+stats[2]);
  }

}