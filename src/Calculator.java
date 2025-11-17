import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    // 메뉴 선택 번호 리스트
    static List<Integer> menuNumList = List.of(1,2,3,0);

    public static int inputMenuNumberCheck(Scanner sc) {
        int inputMenuNumber;

        while (true) {
            System.out.print("""
                === Java 계산기 ===
                1. 계산하기
                2. 계산 이력 보기
                3. 이력 지우기
                0. 종료하기
                """);
            System.out.print("메뉴 숫자를 입력해주세요 (1, 2, 3, 0): ");

            try {
                inputMenuNumber = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("잘못된 메뉴를 입력하셨습니다, 다시 입력해주세요");
                sc.nextLine(); // 잘못된 입력 제거
                continue;
            }

            if(!menuNumList.contains(inputMenuNumber)) {
                System.out.println("올바른 메뉴를 입력해주세요");
                sc.nextLine(); // 잘못된 입력 제거
                continue;
            }

            sc.nextLine(); // 중간 버퍼 삭제
            return inputMenuNumber;
        }
    }

    public static int inputCalNumberCheck(Scanner sc) {
        int inputNumber;

        while (true) {
            try {
                inputNumber = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("숫자를 입력해주세요 : ");
                sc.nextLine(); // 잘못된 입력 제거
                continue;
            }

            sc.nextLine(); // 중간 버퍼 삭제
            return inputNumber;
        }
    }

    public static String inputOperatorCheck(Scanner sc) {
        String inputOperator;

        while (true) {
            System.out.print("연산자를 입력하세요 (+, -, *, /, %, ^, sqrt) : ");
            inputOperator = sc.nextLine();

            if (!inputOperator.equals("+")
                    && !inputOperator.equals("-")
                    && !inputOperator.equals("*")
                    && !inputOperator.equals("/")
                    && !inputOperator.equals("%")
                    && !inputOperator.equals("^")
                    && !inputOperator.equals("sqrt")) {
                System.out.println("지원하지 않는 연산자입니다");
                continue;
            }

            return inputOperator;
        }
    }

    public static String ynCheck(Scanner sc) {
        while (true) {
            String yn = sc.nextLine().toUpperCase();

            if (yn.equals("Y"))
                return "y";
            else if (yn.equals("N"))
                return "n";
            else
                System.out.println("잘못된 값을 입력하셨습니다, 다시 진행해주세요 (y/n)");
        }
    }

    public static void main(String[] args) {
        int menuNumber;
        double number1, number2 = 0, result = 0;
        String operator, continueCal;
        List<String> resultList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            menuNumber = inputMenuNumberCheck(scanner);
            if (menuNumber == 0) {
                break;
            } else if (menuNumber == 1) {
                while (true) {
                    System.out.print("첫번째 숫자를 입력하세요 : ");
                    number1 = inputCalNumberCheck(scanner);
                    operator = inputOperatorCheck(scanner);
                    if (!operator.equals("sqrt")) {
                        System.out.print("두번째 숫자를 입력하세요 : ");
                        number2 = inputCalNumberCheck(scanner);
                    }

                    switch (operator) {
                        case "+" -> result = number1 + number2;
                        case "-" -> result = number1 - number2;
                        case "*" -> result = number1 * number2;
                        case "/" -> {
                            if (number2 == 0) {
                                System.out.println("0 으로 나눌 수 없습니다.");
                                System.out.println("처음부터 다시 계산합니다.");
                                continue;
                            }

                            result = number1 / number2;
                        }
                        case "%" -> result = number1 % number2;
                        case "^" -> result = Math.pow(number1, number2);
                        case "sqrt" -> {
                            result = Math.sqrt(number1);
                        }
                    }

                    if (!operator.equals("sqrt")) {
                        System.out.printf("결과 : %.1f %s %.1f = %.1f%n", number1, operator, number2, result);
                        resultList.add(String.format("%.1f %s %.1f = %.1f%n", number1, operator, number2, result));
                    } else {
                        System.out.printf("결과 : %s(%.1f) = %.1f%n", operator, number1, result);
                        resultList.add(String.format("%s(%.1f) = %.1f%n", operator, number1, result));
                    }

                    System.out.print("계속 계산하시겠습니까? (y/n) : ");
                    continueCal = ynCheck(scanner);
                    if (continueCal.equals("n"))
                        break;
                }
            } else if (menuNumber == 2) {
                if(resultList.isEmpty()) {
                    System.out.println("계산한 이력이 없습니다");
                } else {
                    System.out.println("계산 이력을 출력합니다");
                    resultList.forEach(str -> System.out.print(str));
                }
            } else if (menuNumber == 3) {
                System.out.println("계산 이력을 지웁니다");
                resultList.clear();
            }
        }

        System.out.println("계산기를 종료합니다");
        scanner.close();
    }
}