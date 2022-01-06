package com.example.sp18.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

    // número + letra minúscula + letra maiúscula + caractere especial + 8 a 30 caracteres
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,30}$";

//    ^                                 # start
//    (?=.*[0-9])                       # positive lookahead, digit [0-9]
//    (?=.*[a-z])                       # positive lookahead, one lowercase character [a-z]
//    (?=.*[A-Z])                       # positive lookahead, one uppercase character [A-Z]
//    (?=.*[!@#&()–[{}]:;',?/*~$^+=<>]) # positive lookahead, one of the special character in this [..]
//    .                                 # matches anything
//    {8,20}                            # length at least 8 characters and maximum of 20 characters
//    $                                 # end

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public static boolean isValid(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
