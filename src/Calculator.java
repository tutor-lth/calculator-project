import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Calculator {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    //연산 결과를 저장하는 리스트
    static List<Double> resultList = new ArrayList<>();

    //내부 클래스로 정의
    //예외 클래스의 선언 .
    //연산자 입력이 아닌경우의 예외처리
    static class InvalidateOperatorException extends Exception {
        InvalidateOperatorException(String message) {
            super(message);
        }
    }

    //연산자 검사 메서드
    static void validateOperator(String operator) throws InvalidateOperatorException {
        if (!(operator.equals("+") || operator.equals("-") ||
                operator.equals("*") || operator.equals("/") ||
                operator.equals("%") || operator.equals("^") ||
                operator.equals("sqrt")
        )) {
            throw new InvalidateOperatorException("잘못된 연산자입니다: " + operator);
        }
    }

    //내부 클래스로 정의
    //예외 클래스의 선언
    //0으로 나누는 경우의 예외처리
    static class InvalidateDivisionException extends Exception {
        InvalidateDivisionException(String message) {
            super(message);
        }
    }

    //나누는 숫자 검사 메서드
    static void validateDivisionNum(double divsionNum) throws InvalidateDivisionException {
        if (divsionNum == 0) {
            throw new InvalidateDivisionException("잘못된 입력입니다: " + divsionNum);
        }
    }

    //두수와 연산자를 받아서 연산후 리턴하는 메서드
    //함수 오버로딩
    //sqrt 연산인 경우 , num1 까지만 필요하다
    static double calculater(double num1_, String ch) {
        return Math.sqrt(num1_);
    }

    static double calculater(double num1_, double num2_, String ch) {
        if (ch.equals("+")) {
            return num1_ + num2_;
        } else if (ch.equals("-")) {
            return num1_ - num2_;
        } else if (ch.equals("*")) {
            return num1_ * num2_;
        } else if (ch.equals("/")) {
            return num1_ / num2_;
        } else if (ch.equals("%")) {
            return num1_ % num2_;
        } else {
            return Math.sqrt(num1_);
        }
    }

    static void menu() throws IOException {
        System.out.println("=== 계산기 메뉴 ===");
        System.out.println("1. 계산하기");
        System.out.println("2. 계산 이력 보기");
        System.out.println("3. 이력 지우기");
        System.out.println("0. 종료");
        System.out.printf("선택: ");
        int cmd = Integer.parseInt(bufferedReader.readLine());//명령어 를 저장하는 변수
        if (cmd == 1) {  //1.계산하기
            double num1;//첫 번째 숫자.
            double num2;//두 번째 숫자.
            String operator; //연산자 +, -, *, /,
            double result;//결과
            System.out.println("=== Java 계산기 ===");
            System.out.printf("첫 번째 숫자를 입력 하세요: ");
            while (true) {   //숫자가 아닌값을 입력 받았을때의 예외처리
                try {
                    num1 = Integer.parseInt(bufferedReader.readLine());
                    break; //숫자를 제대로 입력 받았다면
                } catch (NumberFormatException e) {
                    System.out.printf("숫자를 입력해주세요 : ");
                }
            }
            System.out.printf("연산자를 입력하세요 (+, -, *, /, %%, ^, sqrt): ");
            while (true) {   //연산자가 아닌값을 입력 받았을때의 예외처리
                try {
                    operator = bufferedReader.readLine();
                    validateOperator(operator);
                    break; //연산자 입력이 잘되었다면
                } catch (InvalidateOperatorException e) {
                    System.out.printf("지원하지 않는 연산자입니다\n");
                    System.out.printf("연산자를 입력하세요 (+, -, *, /, %%, ^, sqrt): ");
                }
            }
            if (operator.equals("sqrt")) {   //sqrt 연산 : 매개변수가 1개만 필요한경우
                result = calculater(num1, operator);
                resultList.add(result);//자료구조에 결과 저장
                System.out.printf("결과: %.1f %s = %.1f\n", num1, operator, result);
            } else {     //그 이외의 연산 : 매개변수가 2개 필요한 경우
                System.out.printf("두 번째 숫자를 입력 하세요: ");
                while (true) {   //1.숫자가 아닌값을 입력 받았을때의 예외처리
                    try {        //2.나누기 연산인 경우 입력값이 0일경우에 예외처리
                        num2 = Integer.parseInt(bufferedReader.readLine());
                        if (operator.equals("/")) {
                            validateDivisionNum(num2);
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.printf("숫자를 입력해주세요 : ");
                    } catch (InvalidateDivisionException e) {
                        System.out.printf("0으로 나눌 수 없습니다\n");
                        System.out.printf("숫자를 입력해주세요 : ");
                    }
                }
                result = calculater(num1, num2, operator);
                resultList.add(result);//자료구조에 결과 저장
                System.out.printf("결과: %.1f %s %.1f = %.1f\n", num1, operator, num2, result);
            }
            //Step 2: 연산 로직 구현
            while (true) {
                System.out.printf("이전 결과(%.1f) 를 사용하시겠습니까? (y/n): ", result);
                String sw = bufferedReader.readLine();
                if (sw.equals("n")) {
                    System.out.println("계산기를 종료합니다.");
                    break;
                } else {
                    //계속 연산
                    num1 = result;
                    System.out.printf("연산자를 입력하세요 (+, -, *, /, %%, ^, sqrt): ");
                    while (true) {   //숫자가 아닌값을 입력 받았을때의 예외처리
                        try {
                            operator = bufferedReader.readLine();
                            validateOperator(operator);
                            break; //숫자를 제대로 입력 받았다면
                        } catch (InvalidateOperatorException e) {
                            System.out.printf("지원하지 않는 연산자입니다\n");
                            System.out.printf("연산자를 입력하세요 (+, -, *, /, %%, ^, sqrt): ");
                        }
                    }
                    if (operator.equals("sqrt")) {   //sqrt 연산 : 매개변수가 1개만 필요한경우
                        result = calculater(num1, operator);
                        resultList.add(result);//자료구조에 결과 저장
                        System.out.printf("결과: %.1f %s = %.1f\n", num1, operator, result);
                    } else {     //그 이외의 연산 : 매개변수가 2개 필요한 경우
                        System.out.printf("숫자를 입력 하세요: ");
                        while (true) {   //숫자가 아닌값을 입력 받았을때의 예외처리
                            try {
                                num2 = Integer.parseInt(bufferedReader.readLine());
                                if (operator.equals("/")) {
                                    validateDivisionNum(num2);
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.printf("숫자를 입력해주세요 : ");
                            } catch (InvalidateDivisionException e) {
                                System.out.printf("0으로 나눌 수 없습니다\n");
                                System.out.printf("숫자를 입력해주세요 : ");
                            }
                        }
                        result = calculater(num1, num2, operator);
                        resultList.add(result);//자료구조에 결과 저장
                        System.out.printf("결과: %.1f %s %.1f = %.1f\n", num1, operator, num2, result);
                    }
                }
            }
        } else if (cmd == 2) {  //2. 계산 이력 보기
            System.out.printf("=== <계산 이력> ===\n");
            for (int i = 1; i < resultList.size() + 1; i++) {
                System.out.printf("%d 번째 %.1f\n", i, resultList.get(i - 1));
            }
        } else if (cmd == 3) {  //3. 이력 지우기
            System.out.printf("숫자 입력: ");
            resultList.remove(Integer.parseInt(bufferedReader.readLine()) - 1);
        } else { //0. 종료
            System.out.println("=== 계산기를 종료합니다 ===");
            System.exit(1);
        }
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            menu();
        }
    }
}

