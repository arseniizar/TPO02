package com.example.tpo02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class FlashcardsApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        chooseProfile(sc);
        ApplicationContext context = SpringApplication.run(FlashcardsApp.class, args);
        FlashcardsController controller = context.getBean(FlashcardsController.class);
        controller.run();
    }

    private static void chooseProfile(Scanner tempScanner) {
        System.out.println("""
                |!| Select display profile:
                |1| Default
                |2| Uppercase
                |3| Lowercase
                |?| Type your option:
                """);
        String chosenOption = tempScanner.nextLine();
        String profile = switch (chosenOption) {
            case "1" -> "default";
            case "2" -> "upper";
            case "3" -> "lower";
            default -> throw new IllegalStateException("|ERROR| Unexpected value: " + chosenOption);
        };
        System.setProperty("spring.profiles.active", profile);
    }

}
