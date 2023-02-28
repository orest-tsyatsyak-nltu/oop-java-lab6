package org.orest.tsiatsiak;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class UserCommunicationProvider {

    private final Scanner scanner;

    private final PrintStream printStream;

    public UserCommunicationProvider(InputStream inputStream, PrintStream outputStream) {
        this.scanner = new Scanner(inputStream);
        this.printStream = outputStream;
    }

    public void print(String s){
        printStream.print(s);
    }

    public String readLine(){
        return scanner.nextLine();
    }

}
