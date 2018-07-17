package helper;

import java.util.Scanner;

public class HelperFunction {
    Scanner input = new Scanner(System.in);

    public int intNumberValidate(String num) {
        boolean condition = true;
        int choice = 0;


        while (condition) {
            try {

                choice = Integer.parseInt(num);
                condition = false;

            } catch (Exception e) {
                System.out.println("Enter the integer number: ");
                num = input.nextLine();
                condition = true;
            }
        }
        return choice;
    }


    public int intRangeValidate(int min , int max , int choice) {  // 2,6,10

        boolean condition = true;
        int num = 0;

        while (condition) {
            if (choice >= min && choice <= max) {
                num = choice;
                condition = false;
            } else {
                System.out.println("Enter correct range number: ");
                choice = input.nextInt();
                condition = true;
            }
        }

        return num;
    }


}
