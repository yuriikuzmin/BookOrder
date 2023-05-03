package org.example;

import java.awt.image.ImageObserver;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class OrderRead {
    FileReader reader;
    public void orderRead() {

        {
            try {
                reader = new FileReader("input.txt");
                Scanner scanner=new Scanner(reader);

                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
                reader.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
            }
        }

    }
    public void orderOutRead() {

        {
            try {
                reader = new FileReader("output.txt");
                Scanner scanner=new Scanner(reader);

                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
                reader.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
            }
        }

    }

}



