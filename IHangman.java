package eg.edu.alexu.csd.datastructure.hangman.cs01;

import java.io.FileNotFoundException;
import java.io.*;
import java.lang.*;
import java.util.*;
public interface IHangman {
void setDictionary(String[] words);

public String selectRandomSecretWord();

String guess(Character c) throws Exception;

void setMaxWrongGuesses(Integer max);
}

