package blobandclob;

import java.io.*;
import java.util.Scanner;

public class Combine1 {
	
		static String str;	//Static String for file name selection
		static String i;	//Static String for indicating selected options in switch case
		
	public static void FrontPage() {	//FrontPage method
		
		
		//Front Page of application
		System.out.println("Welcome to document(.txt) creator!");
		System.out.println("This application was designed to enter and or read only a single line of text in a document!");
		System.out.println();
		System.out.println("1) Create File");
		System.out.println("2) Write to File");
		System.out.println("3) Display content of File");
		System.out.println("4) Exit application");
		Scanner sc = new Scanner(System.in);	
		i = sc.next();	//Options selected
		Options();		//Option method
	}
	
	public static void Options() {		//Options method

		
		try {
			
			Scanner sc = new Scanner(System.in);	//Scanner for file name input
			Scanner sd = new Scanner(System.in);	//Scanner for inserting data into file

			switch(i) {
			
			//Option 1 selected
			case "1":
			System.out.println("Enter the name of your file!");
			str= sc.next();
			File f = new File(str+".txt");	//Hold for file name
			
			//Check for existence of file name
			if(!f.exists()) {	//If file does not exist, create file
			boolean newFile = f.createNewFile();	//File created
			System.out.println(str+".txt" + " has been created.");
			System.out.println("Returning back to front page.");
			System.out.println();
			FrontPage();	//Returning to FrontPage();
			
			} else {	//If file exist, do not create file and let user know
				System.out.println("File already exist! Please try again!");
				System.out.println();
				FrontPage();	//Returning to FrontPage();
			}
			
			//Option 2 selected
			case "2":
				System.out.println("Which file would you like to write to?");
				str = sc.next();
				File f1 = new File(str+".txt");	//Hold for file name
				
				if(f1.exists()) {	//If file exists, insert data to file
				System.out.println("Please input your message. You will be sent back to the front page after pressing enter.");
				System.out.println();
				FileWriter fw = new FileWriter(str+".txt");	//Opening file
				BufferedWriter br = new BufferedWriter(fw);	//Set file for writing
				br.write(sd.nextLine());	//Write in file
				br.close();					//Stop writing
				System.out.println();
				FrontPage();				//Return to Front Page
				} else {	//If file does not exist, let user know
					System.out.println("File does not exist! Please try again!");
					System.out.println();
					FrontPage();	//returning to FrontPage();
				}
				
			//Option 3 selected
			case "3":
				System.out.println("Input name of document to read from: ");
				str = sc.next();
				File f2 = new File(str+".txt");	//Hold for file name
				
				if(f2.exists()) {	//If file exist, read data in file
				FileReader fr = new FileReader(str+".txt");		//Select file to read
				System.out.println("Displaying content of " + str+".txt");
				BufferedReader br1 = new BufferedReader(fr);	//Set file for reading
				String s = br1.readLine();		//Start reading
				System.out.println(s);
				System.out.println();
				System.out.println("Returning to front page.");
				System.out.println();
				FrontPage();				//Returning to FrontPage();
				} else {	//If files does not exist, stop reading and let user know
					System.out.println("File does not exist! Please try again!");
					System.out.println();
					FrontPage();		//FrontPage();
				}
			
			//Option 4 selected
			case "4":	
				System.out.println("See you again soon!");
				System.exit(0);		//Close application
				
			default:	//When 1 - 4 not chosen, or alphabets inputed
				System.out.println("Invalid option! Returning to front page!");
				System.out.println();
				FrontPage();	//Returning to FrontPage();
			}
				
			} catch(IOException e) {
				
			}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FrontPage();	//Start Program.
		
	}
}
			
		
	

