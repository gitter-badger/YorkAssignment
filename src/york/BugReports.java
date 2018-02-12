package york;

import java.util.LinkedList;

public class BugReports {
	LinkedList<Long> list=new LinkedList<Long>() ;
	public long findBugReolutionTime()
	{
		
		long currentMax=list.get(0);
		for(int i=1;i<list.size();i++)
		{	if(currentMax<list.get(i))
				currentMax=list.get(i);
		}
		return currentMax;
	}
}
