package eg.edu.alexu.csd.datastructure.mailServer;
import DataStructures.*;

import DataStructures.LinkedList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class App implements IApp {
	int numberOfUsers;
	int size;
	String[] mails;
    public String appPath;
    {
        try {
            String temp = new File(".").getCanonicalPath();//get the current path
            appPath = temp + "\\MailServer";
            Folder mailServer = new Folder();
            mailServer.createFolder(appPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean signin(String email, String password) {
        LinkedList linesList = new LinkedList();
        int numberOfUsers = 0;
        try {
            File emails = new File(appPath + "\\emails.csv");
            if (emails.exists()){
                BufferedReader br = new BufferedReader(new FileReader(emails));
                String s;
                while((s = br.readLine()) != null )//count how many user are there and save the users info in linked list
                {
                    linesList.add(s);
                    numberOfUsers++;
                }
                br.close();
                String[] userEmails = new String[numberOfUsers];
                String[] userPasswords = new String[numberOfUsers];
                for (int i = 0; i < linesList.size(); i++) { //divide the user info on different arrays so we can check for valid emails and passwords
                    String temp = (String) linesList.get(i);
                    String[] data = temp.split(",");
                    userEmails[i] = data[2];
                    userPasswords[i] = data[3];
                }
                for (int i = 0; i < userEmails.length; i++) {
                    if (userEmails[i].equals(email)) {
                        if (userPasswords[i].equals(password)){
                            return true;
                        }
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println("Error in sign in");
        }
        return false;
    }

    public boolean checkAccountExistence(String myContactEmail)
    {
    	numberOfUsers = 0;
        LinkedList linesList = new LinkedList(); //to save the csv file in
        try {
            File emails = new File( appPath + "\\emails.csv");
            if (emails.exists()){
                BufferedReader br = new BufferedReader(new FileReader(emails));  //to know how many users are in our database and compare the new users
                String s;
                while((s = br.readLine())!=null )
                {
                    linesList.add(s);
                    numberOfUsers++;
                }
                br.close();
                String[] userNames = new String[numberOfUsers];//divide the user info extract the emails and save it in array so we can compare it easily
                for (int i = 0; i < linesList.size(); i++) {
                    String temp = (String) linesList.get(i);
                    String[] data = temp.split(",");
                    userNames[i] = data[2];
                }
                for (String userName : userNames) { //loop on user array to find a match
                    String temp = myContactEmail;
                    if (userName.equals(temp)) {
                        return true;
                    }
                }
            }
        }catch (Exception e) {
                System.out.println("Error occurred");
            }
        return false;
    }
    @Override
    public void setViewingOptions(IFolder folder, IFilter filter, ISort sort) {
    	Folder f = (Folder) folder;
    	Sort sorting = (Sort) sort;
    	Filter searching = (Filter) filter;
    	DoublyLinkedList date, sender, receiver, subject;
    	date = new DoublyLinkedList();
    	sender = new DoublyLinkedList();
    	receiver = new DoublyLinkedList();
    	subject = new DoublyLinkedList();
    	DoublyLinkedList linesList = new DoublyLinkedList();
    	try {
    		File index = new File(f.Dir, "index.csv");
    		if(index.exists())
        	{
    			BufferedReader br = new BufferedReader(new FileReader(index));  //to know how many users are in our database and compare the new users
                String s;
                while((s = br.readLine())!=null )
                {
                    linesList.add(s);
                }
                br.close();
                linesList.show();
                for (int i = 0; i < linesList.size(); i++) {
                    String temp = (String) linesList.get(i);
                    String[] data = temp.split(",");
                    date.add(data[1]);
                    sender.add(data[2]);
                    receiver.add(data[3]);
                    subject.add(data[4]);
                }
                //Sorting the emails :
                if(sorting.date)
                {
                	date = sorting.quickSort(date);
                	date.reverse();
                	linesList = sortLines(linesList, date, 1);
                }
                else if(sorting.sender)
                {
                	sender = sorting.quickSort(sender);
                	linesList = sortLines(linesList, sender, 2);
                }
                else if(sorting.receiver)
                {
                	receiver = sorting.quickSort(receiver);
                	linesList = sortLines(linesList, receiver, 3);
                }
                else if(sorting.subject)
                {
                	subject = sorting.quickSort(subject);
                	linesList = sortLines(linesList, subject, 4);
                }
                if(searching.index != -1)
                      linesList = searching.search(linesList);
                size = linesList.size();
                mails = new String[size];
                //Moving emails to an array :
                for(int i=0; i<linesList.size();i++)
                {
                	mails[i] = (String)linesList.get(i);
                }
        	}
    		
    	}catch (Exception e) {
    		System.out.println("Error occured");
    	}
    }
    public DoublyLinkedList sortLines(DoublyLinkedList linesList, DoublyLinkedList list, int idx)
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
    
    
    
    
    @Override
    public boolean signup(IContact contact) {
        if (!validContact(contact)){
            return false;
        }
        Contact myContact = (Contact) contact;
        if(checkAccountExistence(myContact.getEmail()))
        	return false;
        FileWriter fw;
		try {
			fw = new FileWriter(new File( appPath + "\\emails.csv") ,true);
			numberOfUsers++;
	        fw.append(numberOfUsers + "," + myContact.getName() + "," + myContact.getEmail() + "," + myContact.getPassword() + "\n");
	        fw.close();
	        Folder folder = new Folder();
	        folder.createEmailDirs(myContact.getEmail());
		} catch (IOException e) {
			System.out.println("Error occurred");
			e.printStackTrace();
		}
        return true;
    }


    @Override
    public boolean compose(IMail email) {
    	Mail mail = (Mail) email;
    	if(!checkAccountExistence(mail.receiver))
    		return false;
    	if(mail.subject.isBlank())
    		return false;
    	String specialChars = "/\\:*?\"<>|,"; //check for special chars
        for (char c : specialChars.toCharArray()) {
            if (mail.subject.indexOf(c) != -1)// if it contain special char or blank text
                return false;
        }
    	try {
    		mail.saveMail(mail, "draft");
			mail.addIndex(mail, "draft", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
        return true;
    }
    static boolean validContact(IContact contact){//to check for invalid chars
        Contact myContact = (Contact) contact;
        if (myContact.getPassword().isBlank() || myContact.getName().isBlank()){ //if the name or password is blank
            return false;
        }
        String specialChars = "/\\:*?\"<>|,"; //check for special chars
        for (char c : specialChars.toCharArray()) {
            if (myContact.getEmail().indexOf(c) != -1 || myContact.getEmail().isBlank()){ // if it contain special char or blank text
                return false;
            }
        }
        return true;
    }
    
    public boolean sendEmail (Mail mail)
    {
    	String source = String.format("%s\\%s\\%s\\%s", appPath, mail.sender, "draft", mail.getDate());
    	String destination = String.format("%s\\%s\\%s\\%s", appPath, mail.receiver,"inbox", mail.getDate());
    	String sentDest = String.format("%s\\%s\\%s\\%s", appPath, mail.sender, "sent", mail.getDate());
    	Folder folder = new Folder();
    	try {
			folder.copyFolder(new File(source), new File (destination));
			folder.moveFolder(new File(source), new File(sentDest));
	    	mail.addIndex(mail, "sent", true);
	    	mail.addIndex(mail, "inbox", false);
	    	mail.deleteIndex(mail, "draft", true);
		} catch (IOException e) {
			return false;
		}
    	return true;
    }


    @Override
    public IMail[] listEmails(int page) {
        return new IMail[0];
    }

    @Override
    public void deleteEmails(ILinkedList mails) {

    }

    @Override
    public void moveEmails(ILinkedList mails, IFolder des) {

    }
    
    
    
}
