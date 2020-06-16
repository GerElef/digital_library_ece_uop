package com.gerelef.model;

public class Helper {
    final static String literatureIdentifier = "ΛΟΓΟΤΕΧΝΙΚΟ";
    final static String scientificIdentifier = "ΕΠΙΣΤΗΜΟΝΙΚΟ";

    private final static String[] literaryFields = new String[]{"ΜΥΘΙΣΤΟΡΗΜΑ", "ΝΟΥΒΕΛΑ", "ΔΙΗΓΗΜΑ", "ΠΟΙΗΣΗ"};
    private final static String[] scientificFields = new String[]{"ΠΕΡΙΟΔΙΚΟ", "ΒΙΒΛΙΟ", "ΠΡΑΚΤΙΚΑ ΣΥΝΕΔΡΙΩΝ"};

    private Helper(){}

    public static String getLiteratureIdentifier(){
        return literatureIdentifier;
    }

    public static String getScientificIdentifier(){
        return scientificIdentifier;
    }

    public static String[] getLiteraryFields() {
        return literaryFields;
    }

    public static String[] getScientificFields() {
        return scientificFields;
    }

    //normalizes all greek vowels with accented characters to lowercase with no accents
    public static String normalizeGreek(String s){
        return s.replaceAll("[Άά]", "α")
        .replaceAll("[Έέ]", "ε")
        .replaceAll("[Ήή]", "η")
        .replaceAll("[Ίί]", "ι")
        .replaceAll("[Όό]", "ο")
        .replaceAll("[Ύύ]", "υ")
        .replaceAll("[Ώώ]", "ω");
    }

    public static String validateName(String s) throws Crawler.InvalidFormatException {
        //if the string is uppercase, in greek or english and has no accents it's all good
        if(s.toUpperCase().equals(s) && (isInGreek(s) || isInEnglish(s)) && hasNoAccents(s))
            return s;

        throw new Crawler.InvalidFormatException();
    }

    public static boolean isInGreek(String s) {
        return s.matches("[α-ωΑ-Ωά-ώΆ-Ώ ]+");
    }

    public static boolean isInEnglish(String s) {
        return s.matches("[a-zA-Z ]+");
    }

    public static boolean hasNoAccents(String s){
        //if has no accents return true otherwise false
        //this is regex for a unicode block that matches all diacritic characters
        //if the regex doesn't match, it'll return 1 element (the string itself) and so it's length will be 1,
        //matching true
        //sort of hack-y solution, might find a better one later
        return s.split("\\p{InCombiningDiacriticalMarks}+").length == 1;
    }

    public static long convertISBN(String s) throws Crawler.InvalidFormatException {
        //if string is 13 digits long, only digits, starts with 978 or 979, it's all good
        if (s.length() == 13 && (s.startsWith("978") || s.startsWith("979")) && s.matches("[0-9]+")) {
            return Long.parseLong(s);
        }
        //if the string len isn't 13 or doesn't start with 978 or 979 or isn't numbers only, throw exception
        throw new Crawler.InvalidFormatException();
    }

    public static int convertDate(String s) throws Crawler.InvalidFormatException {
        if (!s.isEmpty() && s.matches("[0-9]+") && s.length() <= 4){
            return Integer.parseInt(s);
        }
        //if the string len isn't 4 or isn't numbers only, throw exception
        throw new Crawler.InvalidFormatException();
    }

    public static String validateType(String s, String[] fields) throws Crawler.InvalidFormatException {
        for (String field : fields) {
            if (s.equals(field))
                return s;
        }

        throw new Crawler.InvalidFormatException();
    }
}
