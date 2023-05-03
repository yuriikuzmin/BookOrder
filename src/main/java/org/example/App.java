package org.example;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        OrderRead read=new OrderRead();
        System.out.println("Выберите действие и введите :\n1-для ввода данных,\n2-для просмотра файла input.txt,\n3-cоздать запрос,\n4-для просмотра файла output.txt,\n5-для удаления записей");
        Scanner scanner=new Scanner(System.in);
        int title=scanner.nextInt();
        switch (title){
            case 1:
                OrderWrite write=new OrderWrite();
                write.orderWrite();
                break;
            case 2:
                 read.orderRead();
                break;
            case 3:
                OrderRequest orderRequest=new OrderRequest();
                orderRequest.orderRequest();
                break;
            case 4:
                read.orderOutRead();
                break;
            case 5:
                OrderEd orderEd=new OrderEd();
                orderEd.orderEd();
                break;
            default:
                System.out.println("Повторите ввод цифры");

        }



    }
}
