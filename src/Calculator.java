import java.util.Scanner;
import java.util.ArrayList;

public class Calculator {
    public ArrayList<String> records = new ArrayList<>();
    public Double lastResult = null;

    public void addRecord(String record) {
        records.add(record);
    }

    public void showRecords() {
        if (records.isEmpty()) {
            System.out.println("계산 이력이 없습니다.");
            return;
        }
        for (int i = 0; i < records.size(); i++) {
            System.out.println((i + 1) + ") " + records.get(i));
        }
    }

    public void removeAllRecords() {
        records.clear();
        System.out.println("계산 이력 지우기 완료");
    }

    public void calculate(double num1, double num2 , String operator){

        double result = 0;

        //사용자에게 입력받은 연산자로 계산하기
        if (operator.equals("+")) {
            result = num1 + num2;
        } else if (operator.equals("-")) {
            result = num1 - num2;
        } else if (operator.equals("*")) {
            result = num1 * num2;
        } else if (operator.equals("/")) {
            result = num1 / num2;
        } else if (operator.equals("%")) {
            result = num1 % num2;
        } else if (operator.equals("^")) {
            result = Math.pow(num1, num2);
        } else if (operator.equals("sqrt")) {
            result = Math.sqrt(num1);
        }

        //이전 결과 저장하기
        lastResult = result;

        //계산 결과 출력하기
        String resultStr;
        if(operator.equals("sqrt")){
            resultStr = num1 + " " + operator  + " = "+ result;
        }else{
            resultStr = num1 + " " + operator + " " + num2 + " = " + result;
        }
        System.out.println("결과: "+resultStr);

        //계산 이력 남기기
        addRecord(resultStr);

    }

    public void endCalculator(){
        System.out.println("계산기를 종료합니다.");
    }

    public void showMenu(){
        System.out.print("\n=== 계산기 메뉴 ===\n1. 계산하기 \n2. 계산 이력 보기 \n3. 이력 지우기 \n0. 종료\n선택: ");
    }

    public int selectMenu(int menu){

        //메뉴 선택하기
        switch (menu) {
            case 1:
                System.out.println("=== 1. 계산하기 ===");
                break;
            case 2:
                System.out.println("=== 2. 계산 이력 보기 ===");
                break;
            case 3:
                System.out.println("=== 3. 이력 지우기 ===");
                break;
            case 0:
                System.out.println("=== 계산기 종료 ===");
                break;
            default:
                System.out.println("메뉴 중 하나를 선택하세요.");
                break;
        }

        return menu;
    }

    public static void main(String[] args) {

        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);

        int menu = -1;
        while (menu != 0) {

            //계산기 메뉴 보여주기
            calculator.showMenu();

            //메뉴 선택하기
            while (true) {
                int input = (int)readDouble(scanner);
                if (input >= 0 && input < 4) {
                    menu = calculator.selectMenu(input);
                    break;
                } else {
                    System.out.print("0~3 숫자를 입력하세요: ");
                }
            }

            switch (menu) {
                case 0:
                    calculator.endCalculator();
                    break;
                case 1:
                    while (true) {

                        double num1 = 0;
                        double num2 = 0;
                        String operator;
                        boolean useLastResult = false;

                        //이전 결과 사용 여부
                        if (calculator.lastResult != null) {
                            System.out.print("이전 결과(" + calculator.lastResult + ")를 사용하시겠습니까? (y/n): ");
                            String input = readYesNo(scanner);

                            if (input.equals("y")) {
                                num1 = calculator.lastResult;
                                useLastResult = true;
                            }
                        }

                        System.out.println();

                        //첫 번째 숫자 입력
                        if (!useLastResult) { // 이전 계산 결과 사용 여부
                            System.out.print("첫 번째 숫자를 입력하세요: ");
                            num1 = readDouble(scanner);
                        }


                        System.out.print("연산자를 입력하세요 (+, -, *, /, %, ^, sqrt): ");

                        //연산자 입력
                        while (true) {
                            operator = scanner.nextLine();
                            if (!operator.equals("+")
                                && !operator.equals("-")
                                && !operator.equals("*")
                                && !operator.equals("/")
                                && !operator.equals("%")
                                && !operator.equals("^")
                                && !operator.equals("sqrt")
                            ) {
                                System.out.print("+ - * / % ^ sqrt 중 하나를 입력하세요: ");
                            } else {
                                break;
                            }
                        }

                        //두 번째 숫자 입력
                        if (!operator.equals("sqrt")) {
                            System.out.print("두 번째 숫자를 입력하세요: ");
                            num2 = readDouble(scanner);

                            if (operator.equals("/") && num2 == 0) {

                                while (num2 == 0) {
                                    System.out.print("0으로 나눌 수 없습니다. 다시 입력해주세요: ");
                                    num2 = readDouble(scanner);
                                }
                            }
                        }

                        // 계산하기
                        calculator.calculate(num1, num2, operator);

                        System.out.print("\n계속 계산하시겠습니까? (y/n): ");

                        //계속해서 계산할 것인지
                        String inputYN = readYesNo(scanner);
                        if (inputYN.equals("n")) {
                            calculator.lastResult = null;
                            break;
                        }
                    }
                    break;
                case 2:
                    calculator.showRecords();
                    break;
                case 3:
                    calculator.removeAllRecords();
                    break;
                default:
                    break;
            }
        }
        scanner.close();
    }

    // 숫자 입력 받기
    private static double readDouble(Scanner sc) {
        double userInput;
        while (true) {
            try {
                userInput = sc.nextInt();
                sc.nextLine(); // 버퍼 비우기
                break;
            } catch (Exception e) {
                System.out.print("숫자를 입력하세요: ");
                sc.nextLine(); // 버퍼 비우기
            }
        }
        return userInput;
    }

    // y 또는 n 입력받기
    private static String readYesNo(Scanner sc){
        String userInput;
        while (true) {
            try {
                userInput = sc.nextLine();
                if (!userInput.equals("y") && !userInput.equals("n")) {
                    System.out.print("y 또는 n을 입력하세요: ");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.print("y 또는 n을 입력하세요: ");
            }
        }
        return userInput;
    }

}