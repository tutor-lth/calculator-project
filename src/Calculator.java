import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    static StringBuilder sb = new StringBuilder();

    public static void calculate(){
        Scanner scanner = new Scanner(System.in);

        double num1;

        while (true){
            System.out.print("첫 번째 숫자: ");

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
            System.out.print("연산자: ");

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
            System.out.print("두 번째 숫자: ");

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
        sb.append(num1).append(' ').append(num2).append(" = ").append(result).append("\n");
    }

    public static void showLog(){

    }

    public static void deleteLog(){

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