
package eg.edu.alexu.csd.datastructure.hangman.cs01;
import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

    
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to the game! Guess a cool term related to Astrophysics, Particle physics, or Cosmology!");
        Hangman myObj = new Hangman(); //instantiate an object of type Hangman
        myObj.readFile(); //From here the game starts
        myObj.gameLoop();
    }
    
}
