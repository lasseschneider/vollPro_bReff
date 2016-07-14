package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

/**
 * Created by Lasse on 14.07.2016.
 */
public class CSVParser {
    final static String DELIMITER = ";";

    public static ArrayList<Person> getPersonList(String filePath){
        ArrayList<Person> resultList = new ArrayList<Person>();
        //Input file which needs to be parsed
        BufferedReader fileReader = null;
        try
        {
            String line = "";
            //Create the file reader
            fileReader = new BufferedReader(new FileReader(filePath));

            //Read the file line by line
            line = fileReader.readLine();
            SimpleDateFormat s = new SimpleDateFormat("dd.MM.yyyy");
            while ((line = fileReader.readLine()) != null)
            {
                //Get all tokens available in line
                String[] tokens = line.split(DELIMITER);

                resultList.add(new Person(tokens[0],tokens[1],new Date(s.parse(tokens[2]).getTime()),0));

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }
}
