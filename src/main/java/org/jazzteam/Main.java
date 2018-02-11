package org.jazzteam;

import org.jazzteam.converter.impl.Converter;

import java.util.Scanner;

/**
 * Number converter to russian string
 * Created by Alexander Shukaylo
 * https://github.com/Skydance97/number-to-string
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
            long number = scanner.nextLong();
            System.out.println(number + " -> " + new Converter().convert(number));
        }
    }
}
