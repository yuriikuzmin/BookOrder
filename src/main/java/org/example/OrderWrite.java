package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class OrderWrite {
    public String orderWrite() {//Записывает в файл и возвращает записанную строку
        FileWriter writer;
        String line;
        {
            try {
                writer = new FileWriter("input.txt", true);
                Scanner scan=new Scanner(System.in);
                line= scan.nextLine();
                writer.write(line+"\n");
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return line;
    }


}


