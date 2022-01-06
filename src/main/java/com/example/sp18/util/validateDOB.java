package com.example.sp18.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// classe para validar data de nascimento (dob - date of birth)

public class validateDOB {

    final static String DATE_FORMAT = "dd-MM-yyyy";

    public static boolean isDateValid(String date)
    {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}




