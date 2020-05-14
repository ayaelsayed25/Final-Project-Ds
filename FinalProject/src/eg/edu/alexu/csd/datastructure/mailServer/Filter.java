package eg.edu.alexu.csd.datastructure.mailServer;

import DataStructures.DoublyLinkedList;
import DataStructures.Stack;

public class Filter implements IFilter{

	String searchWord;
	boolean date, sender, receiver, subject;
	int index;
	public Filter(boolean date, boolean sender, boolean receiver, boolean subject, String searchWord)
	{
		this.date = date;
		this.sender = sender;
		this.receiver = receiver;
		this.subject = subject;
		this.searchWord = searchWord;
		if(date) index = 1;
		else if(sender) index = 2;
		else if(receiver) index = 3;
		else if(subject) index = 4;
		else index = -1;
		if(searchWord.equals(""))
			index = -1;
	}
	public DoublyLinkedList search(DoublyLinkedList lines)
	{
		DoublyLinkedList found = new DoublyLinkedList();
		lines.show();
		for(int i=0; i<lines.size(); i++)
		{
			String[] temp = ((String)lines.get(i)).split(",");
			String s = temp[index];
			if(s.indexOf(searchWord) >= 0)
			{
				System.out.println("hi");
				found.add(lines.get(i));
			}
		}
		found.show();
		return found;
	}	
}
