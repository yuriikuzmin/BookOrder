package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderEd {
    private String findPrice;
    FileReader reader;
    Scanner scanner=new Scanner(System.in);
    public void orderEd(){
        System.out.println("Введите запрос для удаления :\no,buy,<size>-удалить продавца с самой высокой ценой,\no,sell,<size>-удалить пердложения покупателей с самой низкой ценой");
        String ed=scanner.nextLine();

        if(ed.startsWith("o")){
            String temp=edSell(ed);
            //удалить предложение покупателей в самой низкой ценой
            if(temp.equals("sell")){
                findSell();
                deleteStringSell();
                System.out.println("Предложение покупателя с самой низкой ценой удалено");
            }
            //удалить предложение продавцов в самой высокой ценой
            if(temp.equals("buy")){
                findBuy();
                deleteStringBuy();
                System.out.println("Предложение продавца с самой высокой ценой удалено");
            }
        }
    }
    public String edSell(String ed){//метод определения действия из запроса
        String sell=null;
        String[]words=ed.split(",");
        int i=0;
        for(String word: words){
            if(i==1){
                sell=word;
            }
            i++;
        }
        return sell;
    }
    public int priceTwo(String scan){//Метод для выделения из сторки файла второго слова "price" значения цены
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
    public String findSell(){ //Метод нахождения строки покупателя с миним ценами
        try {
           reader = new FileReader("input.txt");
           Scanner scanner=new Scanner(reader);
           int constPrice=1000000000;
           while (scanner.hasNextLine()) {
               String scan= scanner.nextLine();
               if(scan.startsWith("u")&& scan.endsWith("bid")) {
                   int pr = priceTwo(scan);
                   if (pr < constPrice) {
                       constPrice = pr;
                   }
               }
           }
           findPrice=Integer.toString(constPrice);
           reader.close();
           scanner.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        System.out.println(findPrice);
        return findPrice;
    }
    public String findBuy(){ //Метод нахождения строки продавца максим ценами
        try {
            reader = new FileReader("input.txt");
            Scanner scanner=new Scanner(reader);
            int constPrice=0;
            while (scanner.hasNextLine()) {
                String scan= scanner.nextLine();
                if(scan.startsWith("u")&& scan.endsWith("ask")) {
                    int pr = priceTwo(scan);
                    if (pr > constPrice) {
                        constPrice = pr;
                    }
                }
            }
            findPrice=Integer.toString(constPrice);
            reader.close();
            scanner.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return findPrice;
    }
    public void deleteStringSell(){//Метод удаления строки продавца с высокими ценами
        try {
            Scanner in=new Scanner(new File("input.txt"));
            ArrayList<String> data=new ArrayList<String>();
            String check;
            while (in.hasNextLine()){
                check=in.nextLine();

               if(edPrice(check)&&check.startsWith("u")&&check.endsWith("bid")){
                   data.add("");
               }else{
                   data.add(check);}
            }
            FileWriter fileWriter=new FileWriter("input.txt", false);
            PrintWriter out=new PrintWriter(fileWriter);
            for(String s:data){
                out.println(s);
            }
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteStringBuy(){//Метод удаления строки покупателя с низкими ценами
        try {
            Scanner in=new Scanner(new File("input.txt"));
            ArrayList<String> data=new ArrayList<String>();
            String check;
            while (in.hasNextLine()){
                check=in.nextLine();
               if(edPrice(check)&&check.startsWith("u")&& check.endsWith("ask")){
                    data.add("");
                }else {
                    data.add(check);
                }
            }
            FileWriter fileWriter=new FileWriter("input.txt", false);
            PrintWriter out=new PrintWriter(fileWriter);
            for(String s:data){
                out.println(s);
            }
            in.close();
            out.close();
            fileWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean edPrice(String check){//Метод для выделения из сторки запроса второго слова "size"
        boolean price=false;
        String[]words=check.split(",");
        int i=0;
        for(String word: words){
            if(i==1){
                if(findPrice.equals(word)){
                    price=true;
                }
            }
            i++;
        }

        return price;
    }

}
