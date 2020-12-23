package com.smartsimon;

public class NoFileNameException extends Exception {

    private static String NO_FILE_NAME_EXCEPTION = "No filename found!";

    public NoFileNameException() {
        super(NO_FILE_NAME_EXCEPTION);
    }
}
