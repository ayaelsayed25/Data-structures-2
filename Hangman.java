
package eg.edu.alexu.csd.datastructure.hangman.cs01;
import java.io.*;
import java.lang.*;
import java.util.*;

public class Hangman implements IHangman{
    ArrayList<String> words = new ArrayList<String>(); //creating an array list to save the words in
    String secretWord; //the secret word
    String[] wordsArr; //creating an array to save the words in
    int size; //the size of the words array
    int max, guesses; //max : maximum number of guesses, and guesses is the number of remaining attempts 
    String str, myStr;  //creating strings to save the secret words in
    StringBuilder returnStr; //a string builder needed to change the letters of a string
    public Hangman (){
        //opening a file once the object is instantiated
        File myFile= new File("C:\\Users\\ECC\\Documents\\NetBeansProjects\\HangManGame\\src\\eg\\edu\\alexu\\csd\\datastructure\\hangman\\cs01\\filetoread.txt");
        try {
			boolean createNewFile = myFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void readFile() {
        //Here : the words in the file are saved in the array list
       try( BufferedReader bufReader = new BufferedReader(new FileReader("C:\\Users\\ECC\\Documents\\NetBeansProjects\\HangManGame\\src\\eg\\edu\\alexu\\csd\\datastructure\\hangman\\cs01\\filetoread.txt"))){
            String line = bufReader.readLine();
            while (line != null) {
            words.add(line);
            line = bufReader.readLine();
            }
            bufReader.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
       
           String[] myWords = words.toArray(new String[words.size()]); //converting the array list into an array
           setDictionary(myWords);

    }
    @Override
    public void setDictionary(String[] words) {
        wordsArr = words; //using the member wordsarray to save the words in
        size = wordsArr.length;
    }
    
    public String selectRandomSecretWord() {
        Random rand = new Random(); 
        int x = rand.nextInt(size); //select the index of the array randomly
        return wordsArr[x]; //returning the secret word
    }
    public void setString(String word)
    {
        secretWord = word; //the member secretword is now intialized
        char[] charArray = new char[secretWord.length()]; //creating an array of chars
        Arrays.fill(charArray, '-'); // filling it with - 's
        str = new String(charArray); //turning it into a string
        returnStr = new StringBuilder(str); //intializing the string builder

    }
    
    public void gameLoop() throws Exception
    {
        setString(selectRandomSecretWord()); //setting the secret word
        setMaxWrongGuesses(5); //setting the max num of attempts
        Scanner obj = new Scanner(System.in); 
        char myChar;
        while(true)
        {
            do {   //scan the character as long as it is not between a and z
                 System.out.print("Choose a letter in range [a - z]\n"); myChar = obj.next().charAt(0);
            } while (!Character.toString(myChar).matches("^[a-zA-Z]*$" ) );
            System.out.println(guess(myChar));
            if( myStr.equals(secretWord) ) //if the word myStr equals the secret word then print winner and terminate
            {
                System.out.println("You Guessed it right! The word was "+myStr);
                return;
        }
    }
    }

    @Override
    public String guess(Character c) throws Exception {
        char myChar = Character.toLowerCase(c); //converting the char to lowercase 
        char randomChar;
        int flag = 0;
        for(int i = 0; i<secretWord.length(); i++)
        {
            randomChar = Character.toLowerCase(secretWord.charAt(i));  //choosing the character i from the secret word and making it lowercase
            if(guesses == 0)   //if there is no more gusses throw a runtime error and terminate the program
            {
                throw new RuntimeException("You exceeded The maximum number of gusses! Hard Luck..");
                
            }
            if(randomChar == myChar)
            {
                flag = 1;
                returnStr.setCharAt(i, secretWord.charAt(i)); //if you find them equal then set flag = 1 
                //and replace the ith letter from the stringbuilder with that letter

            }
        }
        if(flag == 0) // if flag is still zero then the letter is not in the word :: subtract one from the guesses
        {
            guesses -- ;
            System.out.println("Wrong ! Now you have " +  guesses + " attempts :( ");
        }
        myStr = returnStr.toString(); //turn the stringbuilder to a string and return it
        return myStr;
    }
        

    @Override
    public void setMaxWrongGuesses(Integer max) { //set the maximum number of attempts
        this.max = max;
        guesses = max;
    }

    
}