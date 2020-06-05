package com.gerelef.model;

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

    LinkedList<String> searchFile(String s, boolean firstMatchExit) throws IOException, InvalidFormatException {
        bufferedReader = new BufferedReader(new FileReader(bookFile));
        boolean match = false;

        LinkedList<String> results = new LinkedList<>();
        String line = bufferedReader.readLine();
        while(line != null){
            if (line.equals(literatureIdentifier)){
                match = subSearch(5, literatureIdentifier, s, bufferedReader, results);
            }
            else if (line.equals(scientificIdentifier)){
               match =  subSearch(6, scientificIdentifier, s, bufferedReader, results);
            }

            if (match && firstMatchExit){
                return results;
            }

            line = bufferedReader.readLine();
        }

        return results;
    }

    private boolean subSearch(int offset, String identifier, String s, BufferedReader br, List<String> results) throws IOException, InvalidFormatException {
        String[] temp = new String[offset + 1];
        String line = br.readLine();

        temp[0] = identifier;

        boolean foundMatch = false;

        int i = 0;
        while(!line.equals("")){
            if (s.equals(line))
                foundMatch = true;

            temp[i + 1] = line;
            line = br.readLine();

            ++i;
        }

        //we read less lines than we should've, throw an exception
        if(offset != i ){
            throw new InvalidFormatException();
        }

        if (foundMatch) {
            results.addAll(Arrays.asList(temp));
            return true;
        }

        return false;
    }

    class InvalidFormatException extends Throwable{}
}
