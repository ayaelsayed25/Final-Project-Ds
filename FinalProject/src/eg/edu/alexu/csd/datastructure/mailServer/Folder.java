package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.WatchEvent;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;

public class Folder implements IFolder {
	File Dir;
	
    public String appPath;
    {
        try {
            String temp = new File(".").getCanonicalPath();//get the current path
            appPath = temp + "\\MailServer";
            createFolder(appPath);//true that i want to create folder not csv file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public Folder()
	{
		
	}
	
	public Folder(String name, String email)
	{
		String path = appPath + "\\" + email + "\\" + name;
		Dir = new File(path);
	}
    public void createFolder(String path)//create folder for given path
    {
        File newFolder = new File(path);
        newFolder.mkdir();
    }
    public void createCsv(String path) throws IOException {
        File newCsvFile = new File(path + ".csv");
        BufferedWriter bw = new BufferedWriter(new FileWriter(newCsvFile)); //we use bufferedWriter to create an actual physical file on the disk
    }
    public void createTxt(String path) throws IOException {
        File newTxtFile = new File(path + ".txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(newTxtFile)); //we use bufferedWriter to create an actual physical file on the disk
        bw.close();
    }
    public void createEmailDirs(String email) throws IOException { //create the main folders for specific Email (inbox, draft...) and the index files
        String path = appPath + "\\" + email;
        System.out.println(path);
        createFolder(path);
        createFolder(path + "\\inbox");
        createCsv(path + "\\inbox\\index");
        createFolder(path + "\\draft");
        createCsv(path + "\\draft\\index");
        createFolder(path + "\\sent");
        createCsv(path + "\\sent\\index");
        createFolder(path + "\\trash");
        createCsv(path + "\\trash\\index");
    }
    
    public void copyFolder(File sourceFolder, File destinationFolder) throws IOException
    {
        if (sourceFolder.isDirectory()) 
        {
            if (!destinationFolder.exists()) 
            {
                destinationFolder.mkdir();
                System.out.println("Directory created :: " + destinationFolder);
            }
            String files[] = sourceFolder.list();
            for (String file : files) 
            {
                File srcFile = new File(sourceFolder, file);
                File destFile = new File(destinationFolder, file);
                copyFolder(srcFile, destFile);
            }
        }
        else
        {
            Files.copy(sourceFolder.toPath(), destinationFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied :: " + destinationFolder);
        }
    }


    public void moveFolder(File source, File destination) throws IOException
    {
    	if(source.isDirectory())
    	{
    		Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
    		String files[] = source.list();
    		for(String file : files)
    		{
    			File src = new File(source, file);
    			File dest = new File(destination, file);
    			moveFolder(src, dest);
    		}
    		source.delete();
    	}
    	else
    	{
    		Files.move(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
    		System.out.println("File moved :: " + destination);
    	}
    }
    
    void deleteFolder(File file) throws IOException
    {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                if (! Files.isSymbolicLink(f.toPath())) {
                    deleteFolder(f);
                }
            }
        }
        file.delete();
    }
            
}

