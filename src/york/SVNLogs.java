package york;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SVNLogs {
	
	private Map<String,Integer> countMap=new HashMap<String,Integer>();
	private Map<String,Integer> countRevisionsMap=new HashMap<String,Integer>();
	public  void add( String element  ) { 	//adds committers to map for task 1
        if( !countMap.containsKey( element ) ){
          
           countMap.put( element, 1 );
        } else { 
        	
            countMap.put( element, (countMap.get( element )) + 1 );
          
        }
        
    }

    public  int getCount( String element ) {	//get count of committers
        if( ! countMap.containsKey( element ) ) {
            return 0;
        }
        return countMap.get( element );
    }
  public Pair getMax()
  {
	  int maxVal=0;
	  Pair p = new Pair();
	  final Set<String> keys = countMap.keySet();
	  for(String k:keys)
	  {
		 if(maxVal<getCount(k))
		 { 
			maxVal=getCount(k);
		 	p.setValue(k, maxVal);
		 }

	  }
	
	  return p;
  }
  
  
 public void addIssue(String element)		//add Jira Issue ID to map for task 3
 {
	 if( !countRevisionsMap.containsKey( element ) ){
		 countRevisionsMap.put( element, 1 );
      }
	 else {  	
    	  countRevisionsMap.put( element, (countRevisionsMap.get( element )) + 1 );
      }
 }
 public float[] countRevisionForIssues()		//counts max, min and avg revision for each Jira Issue 
 {
 
	 Pair p=new Pair();
	 final Set<String> keys = countRevisionsMap.keySet();
	 int maxVal=0,minVal=10000;
	 int sum=0,count=0;
	 float [] stats=new float[3];//max ,min,avg
	 for(String k:keys)
	  {
	//	 System.out.println("Key is "+k+" count is "+countRevisionsMap.get(k));
		 //get count if key is in map
		 if( ! countRevisionsMap.containsKey( k ) )
		  		count=0;
		 else
				count= countRevisionsMap.get(k);
		 
		 sum+=count;			//for average
		 
		 //check if count of this key is greater than previous counts
		 if(maxVal<count)
		 { 
			maxVal=count;	//update if count of current key is > max
		 	p.setValue(k, maxVal);
		 }
		 //check for minimum
		 if(count<minVal)
		 {
			 minVal=count;		///update if current is < min
		 }
		 
	  }
	 
	 stats[0]=maxVal;
	 stats[1]=minVal;
	 stats[2]=sum/keys.size();
	 System.out.println("Sum/total = "+sum+"/"+keys.size());
	 
	// System.out.println("Max revisions "+p.getValue()+" are associated with Jira Issue "+p.getKey());
	 return stats;
 }
 

}
