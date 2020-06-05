package com.gerelef.model;

import java.io.*;

public class IOLibManager {
    private static IOLibManager instance = null;
    //define insertion in inserter class
    //define deletion in deleter here
    //define search in crawler class
    //maybe use this to optimize https://stackoverflow.com/questions/304268/getting-a-files-md5-checksum-in-java

    static Crawler crawler = new Crawler();

    private IOLibManager(){}

    public static IOLibManager getInstance() {
        if (instance == null)
            instance = new IOLibManager();

        return instance;
    }



}
