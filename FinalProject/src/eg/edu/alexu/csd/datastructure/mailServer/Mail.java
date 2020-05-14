package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import DataStructures.LinkedList;

public class Mail implements IMail {
    String subject;
    String content;
    String sender;
    String receiver;
    LinkedList attachments = new LinkedList();
    String date;
    public Mail(String receiver, String sender, String subject, String content) {
        this.receiver = receiver;
        this.sender = sender ; 
        this.subject = subject;
        this.content = content;
        date = new SimpleDateFormat("yyyy-MM-dd_HH-mm").format(Calendar.getInstance().getTime());
    }
    
    public String appPath;
    {
        try {
            String temp = new File(".").getCanonicalPath();//get the current path
            appPath = temp + "\\MailServer";
            System.out.println(appPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getDate(){
        return date;
    }

    public void addIndex(IMail mail, String folder, boolean bool) throws IOException { //update index file in specific folder draft ,inbox....
        Mail myMail = (Mail)mail;
        folder = folder.toLowerCase(); //if true : in the same account, false : the reciever's account
        int indexNumber = 0;
        File index ;
        if(bool)
            index = new File(String.format("%s\\%s\\%s\\index.csv", appPath, myMail.sender, folder));
        else
        	index = new File(String.format("%s\\%s\\%s\\index.csv", appPath, myMail.receiver, folder));
        if (index.exists()){
            BufferedReader br = new BufferedReader(new FileReader(index));
            while(br.readLine() !=null )
            {
                indexNumber++;
            }
            br.close();
        }
        FileWriter fw = new FileWriter(index,true);
        indexNumber++;
        fw.append(indexNumber + "," + myMail.date + "," + myMail.sender + "," + myMail.receiver + "," + myMail.subject + "\n");
        fw.close();
    }
    
    public void deleteIndex(Mail myMail, String folder, boolean bool) throws IOException
    {
        LinkedList linesList = new LinkedList(); //to save the csv file in
        File index;
        if(bool)
        	{
        	    index= new File(String.format("%s\\%s\\%s\\index.csv", appPath, myMail.sender, folder) );
        	}
        else
        	{
        	    index= new File(String.format("%s\\%s\\%s\\index.csv", appPath, myMail.receiver, folder) );
        	}
        if (index.exists()){
            BufferedReader br = new BufferedReader(new FileReader(index));  //to know how many users are in our database and compare the new users
            String s;
            while((s = br.readLine())!=null )
            {
            	String[] data = s.split(",");
            	if(!data[1].equals(myMail.getDate()))
                {
            		linesList.add(s);
                }
            }
            br.close();
            FileWriter fw = new FileWriter(index);
            for(int i=0; i<linesList.size(); i++)
            {
            	fw.append((String) linesList.get(i) + "\n");
            }
            fw.close();
        }
    }
    public void saveMail(IMail mail, String folder) throws IOException {//save the mail in specific folder
        Mail myMail = (Mail)mail;
        folder = folder.toLowerCase();
        String mailFolder = String.format("%s\\%s\\%s\\%s", appPath, myMail.sender, folder, myMail.date);//save by the date
        String mailTxtFolder = String.format("%s\\%s\\%s\\%s\\%s", appPath, myMail.sender, folder, myMail.date, myMail.subject);
        Folder saveAt = new Folder();
        saveAt.createFolder(mailFolder);//create the mail folder
        saveAt.createTxt(mailTxtFolder); //create the txt file
        FileWriter myWriter = new FileWriter(mailTxtFolder + ".txt");
        myWriter.write(myMail.content);
        myWriter.close();
        //Move attachments : 
        LinkedList attachments = myMail.attachments;
        for(int i=0; i<attachments.size(); i++)
        {
        	File file = (File) attachments.get(i);
        	String name = mailFolder+"\\" + file.getName();
        	File dest = new File(name);
        	Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }


}
