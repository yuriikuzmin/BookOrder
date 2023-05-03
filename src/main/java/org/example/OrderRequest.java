package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class OrderRequest {

    FileReader reader;
    String line;//переменна сканирования вводимого запроса
    String question;
    int count=0;
    public void orderRequest(){

        System.out.println("Bведите запрос ввиде:\nq,best_bid - для выбора лучшего покупателя,\nq,best_ask - для выбора лучшего продавца,\nq,size,<price> - для выбора всех предложениий, с указанной стоимостью");

        OrderWrite orderWrite=new OrderWrite();
        line=orderWrite.orderWrite();
         String temp=sizePrice(line);
         if(temp.equals("size")){//Отработка по запросу с ценой

                 try {
                     reader = new FileReader("input.txt");
                     Scanner scanner=new Scanner(reader);

                     int constPrice=priceThree(line);//возвращает значение цены указанное в запросе
                     while (scanner.hasNextLine()) {
                         String scan= scanner.nextLine();
                         if(scan.startsWith("u")) {
                             int pr = price(scan);
                             if (constPrice==pr) {
                                 count++ ;

                             }
                         }
                     }
                     reader.close();
                 } catch (FileNotFoundException e) {
                     throw new RuntimeException(e);
                 } catch (IOException e) {
                     throw new RuntimeException(e);
                 }
                String countString=Long.toString(count);//преобразуем число записей с найденной ценой
                questionWrite(countString); //запишем в выходной файл


         }
            if(line.startsWith("q") ){
                if(line.equals("q,best_bid")){//Отработка по покупателю
                    try {
                        reader = new FileReader("input.txt");
                        Scanner scanner=new Scanner(reader);
                        int constPrice=1000000000;
                        while (scanner.hasNextLine()) {
                            String scan= scanner.nextLine();
                            if(scan.startsWith("u")&& scan.endsWith("bid")) {
                                int pr = price(scan);
                                if (pr <= constPrice) {
                                    constPrice = pr;
                                    question = scan;
                                }
                            }
                        }
                        reader.close();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    questionWrite(question);
                    outputRead();
                }
                if(line.equals("q,best_ask")){ //Отработка по продавцу
                    try {
                        reader = new FileReader("input.txt");
                        Scanner scanner=new Scanner(reader);
                        int constPrice=1000000000;
                        while (scanner.hasNextLine()) {
                            String scan= scanner.nextLine();
                            if(scan.startsWith("u")&& scan.endsWith("ask")) {
                                int pr = price(scan);
                                if (constPrice>pr) {
                                    constPrice = pr;
                                    question = scan;
                                }
                            }
                        }
                        reader.close();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    questionWrite(question);
                    outputRead();
                }


            }else {
                System.out.println("Введите правильный запрос");
            }
    }
    public int price(String scan){//Метод для выделения из сторки файла второго слова "price" значения цены
        int price=0;
        String[]words=scan.split(",");
        int i=0;
        for(String word: words){
             if(i==1){
              String word1 =word;
                price=Integer.valueOf(word1).intValue();
            }
            i++;
        }
        return price;
    }
    public void questionWrite(String question){ //Метод для записи результатов запроса в файл
        try {
            FileWriter quest=new FileWriter("output.txt", true);
            quest.write(question+"\n");
            quest.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void outputRead() {
        FileReader reader;
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
    public String sizePrice(String scan){//Метод для выделения из сторки запроса второго слова "size"
        String price=null;
        String[]words=scan.split(",");
        int i=0;
        for(String word: words){
            if(i==1){

                price=word;
            }
            i++;
        }
        return price;
    }

    public int priceThree(String scan){//Метод для выделения из сторки файла третьего слова "price" значения цены
        int price=0;
        String[]words=scan.split(",");
        int i=0;
        for(String word: words){
            if(i==2){
                String word1 =word;
                price=Integer.valueOf(word1).intValue();
            }
            i++;
        }
        return price;
    }
}
