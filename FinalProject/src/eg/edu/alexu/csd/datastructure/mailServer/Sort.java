package eg.edu.alexu.csd.datastructure.mailServer;

import DataStructures.DoublyLinkedList;
import DataStructures.Stack;

public class Sort implements ISort{

	boolean date;
	boolean sender;
	boolean receiver;
	boolean subject;
	
	public Sort(boolean date, boolean sender, boolean receiver, boolean subject)
	{
		this.date = date;
		this.sender = sender;
		this.receiver = receiver;
		this.subject= subject;
		
	}
	

	public DoublyLinkedList quickSort(DoublyLinkedList words)
	{
		Stack stack = new Stack();
		stack.push(0);
		stack.push(words.size());
		while (!stack.isEmpty())
		{
			int end = (int) stack.pop();
			int start = (int) stack.pop();
			if (end - start < 2)
			{
				continue;
		    }
			int p = start + ((end - start) / 2);
			p = partitionSorting(words, p, start, end);
			stack.push(p + 1);
			stack.push(end);
			stack.push(start);
			stack.push(p);
			}
		return words;
	}	
	
	public int partitionSorting(DoublyLinkedList input, int position, int start, int end) {
        int l = start;
        int h = end - 2;
        String piv = (String) input.get(position);
        swap(input, position, end - 1);
        outer:
        while (l < h) {
        	String low = (String)input.get(l);
        	String high = (String)input.get(h);
        	for(int i=0; i<low.length(); i++)
        	{
        		if(i== piv.length() || (int)low.charAt(i) > (int) piv.charAt(i))
        			break;
        		else if((int)low.charAt(i) < (int) piv.charAt(i))
        		{
        			l++;
        			continue outer;
        		}
        		else if((int)low.charAt(i) == (int) piv.charAt(i) && (i == low.length() - 1) && (i != piv.length() - 1))
        		{
        			l++;
        			continue outer;
        		}
        	}
        	for(int i=0; i<high.length(); i++)
        	{
        		if(i== piv.length() || (int)high.charAt(i) > (int) piv.charAt(i))
        		{
        			h--;
        			continue outer;
        		}
        		else if((int)high.charAt(i) < (int) piv.charAt(i))
        		{
        			break;
        		}
        		else if((int)high.charAt(i) == (int) piv.charAt(i) && (i == high.length() - 1) && (i == piv.length() - 1))
        		{
        			h--;
        			continue outer;
        		}
        		else if((int)high.charAt(i) == (int) piv.charAt(i) && (i == high.length() - 1) && (i != piv.length() - 1))
        			break;
        	}
        	swap(input, l, h);
        }
        int idx = h;
        String high = (String)input.get(h);
        for(int i = 0; i< high.length(); i++)
        {
    		if(i== piv.length() || (int)high.charAt(i) > (int) piv.charAt(i))
    			break;
    		else if((int)high.charAt(i) < (int) piv.charAt(i))
    		{
    			idx++;
    			break;
    		}
    		else if((int)high.charAt(i) == (int) piv.charAt(i) && (i == high.length() - 1) && (i != piv.length() - 1))
    		{
    			idx++;
    			break;
    		}
        }
        swap(input, end - 1, idx);
        return idx;
    }
	
	public void swap(DoublyLinkedList list, int i, int j)
	{
		String temp = (String) list.get(i); list.set(i, (String) list.get(j)) ; list.set(j, temp); 
	}

}
