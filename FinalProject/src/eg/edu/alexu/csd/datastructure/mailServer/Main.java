package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;
import java.io.IOException;

import DataStructures.DoublyLinkedList;

public class Main {

    public static DoublyLinkedList sortLines(DoublyLinkedList linesList, DoublyLinkedList list, int idx)
    {
    	DoublyLinkedList lines = new DoublyLinkedList();
    	for(int i=0; i< list.size(); i++)
    	{
    		for(int j =0; j< linesList.size(); j++)
    		{
    			String[] s = ((String)linesList.get(j)).split(",");
    			if(s[idx].equals((String) list.get(i)))
    			{
    				lines.add(linesList.get(j));
    				break;
    			}
    		}
    	}
    	return lines;
    }
	public static void main(String[] args) throws IOException{
	
		DoublyLinkedList list = new DoublyLinkedList();
		//yyyy-MM-dd_HH-mm
		list.add("am");
		list.add("aa");
		list.add("why");
		list.add("lo");

		Sort sort = new Sort(false, false, false, false);
		DoublyLinkedList l = sort.quickSort(list);
		DoublyLinkedList lines = new DoublyLinkedList();
		lines.add("aha,why,ok");
		lines.add("ammm,am,ok");
		lines.add("aha,aa,lol");
		lines.add("mm,lo,hh");
		lines = sortLines(lines, l, 1);
		String searchWord = "h";
		DoublyLinkedList found = new DoublyLinkedList();
		lines.show();
		for(int i=0; i<lines.size(); i++)
		{
			String[] temp = ((String)lines.get(i)).split(",");
			String s = temp[2];
			if(searchWord.indexOf(s) >= 0)
			{
				System.out.println("hi");
				found.add(lines.get(i));
			}
		}
		found.show();
		
		
	}
}
 