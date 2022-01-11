import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/*Emma Chiusano
 * SCE 017
 * Professor Oudghiri
 * Program to model the class recursion, which will read through files
 * Date of creation: 21 September, 2021
 * Last date of editing: 22 September, 2022*/
public class Recursion {
	public static void main(String[]args) {
		//inport scanner
		Scanner keyboard=new Scanner(System.in);
		System.out.println("Enter a directory:");
		//saves the path of the directory
		String path=keyboard.nextLine();
		System.out.println("Enter a filename:");
		//this si teh file we will be looking for
		String filename=keyboard.nextLine();
		String found=searchFile(path,filename);
		if (found.equals("")) {
			System.out.println("File "+filename+" not found.");
		}else {
			System.out.println("File "+filename+" found "+found);
		}
		System.out.println("Enter a file/directory:");
		//saves teh path
		path=keyboard.nextLine();
		System.out.println("Enter a word:");
		//word we will search for
		String word=keyboard.nextLine();
		findWord(path,word);

	}
	/*Method to search files
	 * @param String path, String filename
	 * @return found if file is found,, "" if not found*/
	public static String searchFile(String path, String filename) {
	File dir=new File (path);
	if (!dir.isDirectory()) {
		return "";
	}else {
		File[]files=dir.listFiles();
		for(int i=0; i<files.length;i++) {
			if (files[i].isFile()) {
				if (files[i].getName().equals(filename)) {
					return files[i].getAbsolutePath();
			}
		}
		else if (files[i].isDirectory()) {
			String found=searchFile(files[i].getAbsolutePath(),
									filename);
			if(!found.equals("")) {
				return found;
			}
	}
	}
	}
	return "";
	}
	/*Method to find the word in our files
	 * @param String path, String word
	 * @return the number of times a word is found*/
	public static void findWord(String path, String word) {
		File dir=new File(path);
		if(dir.isFile()) {
			int count=countWord(dir, word);
			if (count!=0) {
				System.out.println(word+" appears "+count+" times in "+dir.getAbsolutePath());
			}
		}//this else is for the first if
		else if(dir.isDirectory()) {
				File[]files=dir.listFiles();
				for(int i=0; i<files.length;i++){
					findWord(files[i].getAbsolutePath(),word);
				}
			}
	}
	/*Method to cound the number of times a word is found
	 * @param File file, String word
	 * @return the number of times the word is found*/
	private static int countWord(File file, String word) {
		int count=0;
		try{
			Scanner readFile=new Scanner(file);
			while(readFile.hasNextLine()) {
				String line=readFile.nextLine();
				int index=line.indexOf(word,0);
				while (index!=-1) {
					count++;
					index =line.indexOf(word, index+1);
				}
			}
			readFile.close();
			return count;
		}
		catch (FileNotFoundException e) {
			return 0;
		}
	}
}
