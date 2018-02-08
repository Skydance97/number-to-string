package org.jazzteam;

import java.util.Scanner;

/**
 * Number converter to string
 * Created by @author Alexander Shukaylo
 * https://github.com/Skydance97/NumberConverter
 * on 03.02.2018
 * */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Введите число: ");
            while (!scanner.hasNextLong()) {
                System.out.println("'" + scanner.next() + "' недопустимое значение! Повторите попытку!");
            }
            Long number = scanner.nextLong();


            Converter converter = new Converter(number);
            converter.convert();
            System.out.println(number + " -> " + converter.getConvertedNumber());
        }
    }
}
