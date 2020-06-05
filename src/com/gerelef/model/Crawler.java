package com.gerelef.model;

import com.gerelef.books.Book;
import com.gerelef.books.LiteraryBook;
import com.gerelef.books.ScientificBook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Crawler {
    File bookFile = new File(".\\books.txt");
    BufferedReader bufferedReader;

    private final static String literatureIdentifier = "ΛΟΓΟΤΕΧΝΙΚΟ";
    private final static String scientificIdentifier = "ΕΠΙΣΤΗΜΟΝΙΚΟ";

    Crawler(){
        try {
            if (!bookFile.exists()){
                bookFile.createNewFile();
                System.out.println("Created file at " + bookFile.getAbsolutePath());
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    LinkedList<Book> searchFile(String s, boolean firstMatchExit) throws IOException, InvalidFormatException {
        bufferedReader = new BufferedReader(new FileReader(bookFile));
        boolean match = false;

        LinkedList<Book> results = new LinkedList<>();
        String line = bufferedReader.readLine().toUpperCase();
        while(line != null){
            if (line.equals(literatureIdentifier)){
                match = subSearch(5, literatureIdentifier, s, bufferedReader, results);
            }
            else if (line.equals(scientificIdentifier)){
                match = subSearch(6, scientificIdentifier, s, bufferedReader, results);
            }

            if (match && firstMatchExit){
                return results;
            }

            line = bufferedReader.readLine();
        }

        bufferedReader.close();
        return results;
    }

    private boolean subSearch(int offset, String identifier, String s, BufferedReader br, LinkedList<Book> results)
            throws IOException,
            InvalidFormatException {
        String[] temp = new String[offset];
        String line = br.readLine().toUpperCase();

        boolean foundMatch = false;

        int i = 0;
        while(line != null && !line.isEmpty()){
            if (s.equals(line))
                foundMatch = true;

            temp[i] = line;
            line = br.readLine();

            ++i;
        }

        //we read less lines than we should've, throw an exception
        if(offset != i ){
            throw new InvalidFormatException();
        }

        if (foundMatch) {
            Book book;
            if (identifier.equals(literatureIdentifier))
                book = new LiteraryBook(temp[0], temp[1], Long.parseLong(temp[2]), Integer.parseInt(temp[3]), temp[4]);
            else
                book = new ScientificBook(temp[0], temp[1], Long.parseLong(temp[2]), Integer.parseInt(temp[3]), temp[4], temp[5]);

            results.add(book);
            return true;
        }

        return false;
    }

    class InvalidFormatException extends Throwable{}
}
