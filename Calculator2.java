package SelfTraining;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator2 {

    public static void main(String[] args) {

        // Scanner 객체 생성 (입력)
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Java 계산기 ===");

        // 1. while 반복문 & try-catch 예외처리문
        while (true) {
            try {
                System.out.println("첫 번째 숫자를 입력하세요: ");
                double num1 = scanner.nextDouble();

                System.out.println("연산자를 입력하세요 (+, -, *, /): ");
                char ch = scanner.next().charAt(0);

                System.out.println("두 번째 숫자를 입력하세요: ");
                double num2 = scanner.nextDouble();

                double result = 0;
                // 2. 연산하기
                if (ch == '+') {
                    result = num1 + num2;
                } else if (ch == '-') {
                    result = num1 - num2;
                } else if (ch == '*') {
                    result = num1 * num2;
                } else if (ch == '/') {
                    if (num2 == 0) {
                        System.out.println("0으로 나눌 수 없습니다.");
                        continue;
                    }
                    result = num1 / num2;

                } else if (ch == '%') {
                    result = num1 % num2;

                } else if (ch == '^') {
                    result = Math.pow(num1, num2);

                } else if (ch == 's') {
                    result = Math.sqrt(num1);

                } else {
                    System.out.println("지원하지 않는 연산자입니다");
                    continue;
                }
                System.out.println("결과: " + num1 + ch + num2 + "=" + result);

            }catch (InputMismatchException e) {
                System.out.println("정확한 숫자를 입력해주세요.");

                scanner.nextLine();
                continue;
            }
            //  계속 진행할 건가요?
            System.out.println("계속 진행하시겠습니까? (y/n): ");
            String answer = scanner.next();

            if (answer.equalsIgnoreCase("y")) {
                System.out.println();
                continue;
            }else {
                System.out.println("계산기를 종료합니다.");
                break;
            }
        }
        scanner.close();
    }

}
