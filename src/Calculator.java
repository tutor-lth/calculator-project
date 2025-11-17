import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    static StringBuilder sb = new StringBuilder();

    public static void calculate(){
        Scanner scanner = new Scanner(System.in);

        jump: while (true){
            double num1;

            while (true){
                System.out.print("첫 번째 숫자를 입력해주세요: ");

                try {
                    num1 = scanner.nextDouble();
                    scanner.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("숫자를 입력해주세요.");
                    scanner.nextLine();
                }
            }

            String operator;
            String[] type = new String[] {"+", "-", "*", "/", "%", "^", "sqrt"};

            while (true){
                System.out.print("연산자를 입력하세요 (+, -, *, /, %, ^, sqrt): ");

                operator = scanner.next();
                scanner.nextLine();

                boolean flag = false;

                for (String s : type){
                    if (operator.equals(s)){
                        flag = true;
                        break;
                    }
                }

                if (flag) break;
                else System.out.println("지원하지 않는 연산자입니다.");
            }

            double num2;

            while (true){
                System.out.print("두 번째 숫자를 입력해주세요: ");

                try {
                    num2 = scanner.nextDouble();
                    scanner.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("숫자를 입력해주세요.");
                    scanner.nextLine();
                }
            }

            double result = switch (operator) {
                case "+" -> num1 + num2;
                case "-" -> num1 - num2;
                case "*" -> num1 * num2;
                case "/" -> num1 / num2;
                case "%" -> num1 % num2;
                case "^" -> Math.pow(num1, num2);
                case "sqrt" -> Math.pow(num1, 1 / num2);
                default -> 0;
            };

            System.out.println("결과: " + result);
            sb.append(num1).append(' ').append(operator).append(' ').append(num2).append(" = ").append(result).append("\n");

            while (true){
                System.out.print("계속 계산하시겠습니까? (y/n): ");
                String s = scanner.next();
                scanner.nextLine();

                if (s.equals("y") || s.equals("n")){
                    if (s.equals("n")){
                        break jump;
                    } else {
                        break;
                    }
                } else {
                    System.out.println("올바른 명령을 입력해주세요.");
                }
            }
        }
    }

    public static void showLog(){
        System.out.println("=== 계산이력 ===");
        System.out.print(sb);
        System.out.println("=== 계산이력 ===");
    }

    public static void deleteLog(){
        sb.setLength(0);
        System.out.println("계산이력을 초기화했습니다.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Java 계산기 ===");

        while (true){
            System.out.println("=== 계산기 메뉴 ===");
            System.out.println("1. 계산하기");
            System.out.println("2. 계산 이력 보기");
            System.out.println("3. 이력 지우기");
            System.out.println("0. 종료");
            System.out.print("선택: ");

            int choice;

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("메뉴에 해당하는 숫자를 입력해주세요.");
                scanner.nextLine();
                continue;
            }

            boolean isExist = false;

            switch (choice){
                case 1 -> calculate();
                case 2 -> showLog();
                case 3 -> deleteLog();
                case 0 -> isExist = true;
            }

            if (isExist) break;
        }

        System.out.println("계산기를 종료합니다.");
        scanner.close();
    }

}