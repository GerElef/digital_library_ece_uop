package com.gerelef.model;

import com.gerelef.books.Book;
import com.gerelef.books.LiteraryBook;
import com.gerelef.books.ScientificBook;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.gerelef.model.Helper.*;

class Crawler {

    private ArrayList<Book> books = new ArrayList<>();
    // -1 init so the file definitely hasn't been loaded into cache, .lastModifier() returns 0L >=
    private long loadedDataDate = -1;

    File bookFile = new File(".\\books.txt");
    BufferedReader bufferedReader;

    Crawler(){
        try {
            if (!bookFile.exists()){
                if (bookFile.createNewFile())
                    System.out.println("Created file at " + bookFile.getAbsolutePath());
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Couldn't create file because " + ex.toString());
            System.exit(-1);
        }
    }

    ArrayList<Book> getAllBooks(){
        try{
            if (!dataIsUpdated())
                loadBooksFromFile();
        } catch (IOException ex) {
            System.exit(-1);
        }
        return books;
    }

    private boolean dataIsUpdated(){
        return books != null && (loadedDataDate >= bookFile.lastModified());
    }

    private void loadBooksFromFile() throws IOException {
        bufferedReader = new BufferedReader(new FileReader(bookFile));
        String line = bufferedReader.readLine();
        ArrayList<Book> tempBooks = new ArrayList<>();

        while(line != null){
            try{
                switch (line) {
                    case literatureIdentifier:
                        tempBooks.add(getLiteratureBook());
                        break;
                    case scientificIdentifier:
                        tempBooks.add(getScientificBook());
                        break;
                    case "":
                        //do nothing, line is empty
                        break;
                    default:
                        /*if the line result falls out of boundaries, throw an exception
                         * to validate and try to fix the db */
                        throw new InvalidFormatException();
                }

            } catch (InvalidFormatException e) {
                System.out.println("DB is corrupt; trying to fix it now... ");
                //call function to fix the db here
                fixDatabase();
                bufferedReader.close();
                loadedDataDate = bookFile.lastModified();
                return;
            }

            line = bufferedReader.readLine();
        }

        //stores books into the cache as an array
        //updates the loadedDataDate to the latest value
        books = tempBooks;
        loadedDataDate = bookFile.lastModified();

        bufferedReader.close();
    }

    private void fixDatabase() throws IOException {
        //do stuff...
        //clear any loaded data in the cache, and try to rebuild the db
        BufferedReader reader = new BufferedReader(new FileReader(bookFile));

        ArrayList<Book> tempBooks = new ArrayList<>();

        String line = reader.readLine();
        while(line != null){
            line = normalizeGreek(line).toUpperCase().trim();

            Book b = null;
            if (line.equals(getLiteratureIdentifier())){
                b = getCorruptLiteratureBook(reader);
            }
            else if (line.equals(getScientificIdentifier())){
                b = getCorruptScientificBook(reader);
            }

            if (b != null){
                tempBooks.add(b);
            }

            line = reader.readLine();
        }

        reader.close();
        books = tempBooks;
        outputDataToDisk(books);
    }

    private LiteraryBook getCorruptLiteratureBook(BufferedReader br){
        try{
            String title  = validateName(Helper.normalizeGreek(br.readLine()).toUpperCase());
            String writer = validateName(Helper.normalizeGreek(br.readLine()).toUpperCase());
            long ISBN = convertISBN(br.readLine().trim());
            int date  = convertDate(br.readLine().trim());
            String bookType = validateType(normalizeGreek(br.readLine().trim()).toUpperCase(), getLiteraryFields());

            return new LiteraryBook(title, writer, ISBN, date, bookType);
        } catch (Exception | InvalidFormatException e) {
            return null;
        }
    }

    private ScientificBook getCorruptScientificBook(BufferedReader br){
        try{
            String title  = validateName(Helper.normalizeGreek(br.readLine()).toUpperCase());
            String writer = validateName(Helper.normalizeGreek(br.readLine()).toUpperCase());
            long ISBN = convertISBN(br.readLine().trim());
            int date  = convertDate(br.readLine().trim());
            String bookType = validateType(normalizeGreek(br.readLine().trim()).toUpperCase(), getScientificFields());
            String scientificField = br.readLine();

            return new ScientificBook(title, writer, ISBN, date, bookType, scientificField);
        } catch (Exception | InvalidFormatException e) {
            return null;
        }
    }

    private LiteraryBook getLiteratureBook() throws IOException, InvalidFormatException {
        String title  = validateName(bufferedReader.readLine().trim());
        String writer = validateName(bufferedReader.readLine().trim());
        long ISBN = convertISBN(bufferedReader.readLine().trim());
        int date = convertDate(bufferedReader.readLine().trim());
        String bookType = validateType(bufferedReader.readLine().trim(), getLiteraryFields());

        return new LiteraryBook(title, writer, ISBN, date, bookType);
    }

    private ScientificBook getScientificBook() throws IOException, InvalidFormatException {
        String title = validateName(bufferedReader.readLine().trim());
        String writer = validateName(bufferedReader.readLine().trim());
        long ISBN = convertISBN(bufferedReader.readLine().trim());
        int date = convertDate(bufferedReader.readLine().trim());
        String bookType = validateType(bufferedReader.readLine().trim(), getScientificFields());
        String scientificField = bufferedReader.readLine();

        return new ScientificBook(title, writer, ISBN, date, bookType, scientificField);
    }

    void addBook(Book b){
        try {
            if (!dataIsUpdated())
                loadBooksFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        books.add(b);
        outputDataToDisk(books);
        loadedDataDate = bookFile.lastModified();
    }

    void removeBook(Book b){
        try{
            if (!dataIsUpdated())
                loadBooksFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        books.remove(b);
        outputDataToDisk(books);
        loadedDataDate = bookFile.lastModified();
    }

    private void outputDataToDisk(ArrayList<Book> books) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(bookFile));
            for (Book book : books) {
                bw.write(book.getOutputFormat());
                bw.write("");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class InvalidFormatException extends Throwable{}
}
