import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.function.Function;


public class Calculator {


    public ArrayList<String> history = new ArrayList<>();
    public Double prevCaclResult = null;
    public void addHistory(String history){
        this.history.add(history);
    }

    public void showHistory(){
        if (this.history.isEmpty()){
            System.out.println("계산 이력이 없습니다.");
            return;
        }
        for(int i = 0; i < this.history.size();i++){
            System.out.println((i+1)+") "+this.history.get(i));
        }

    }

    public void deleteHistory(){
        this.history.clear();
        System.out.println("계산 이력 지우기 완료");
    }

    public void cacluate(double num1, double num2 ,String operator){

        //이전 계산 값 사용하기

        double result = 0;

        if (operator.equals("+"))
        {
            result = num1+num2;
        } else if (operator.equals("-")) {
            result = num1-num2;
        } else if (operator.equals("*")) {
            result = num1*num2;
        } else if (operator.equals("/")) {
            result = num1/num2;
        }else if (operator.equals("%")) {
            result = num1%num2;
        }else if (operator.equals("^")) {
            result = Math.pow(num1,num2);
        }else if (operator.equals("sqrt")) {
            result = Math.sqrt(num1);
        }

        prevCaclResult = result;

        //계산 결과 출력하기
        String resultStr;
        if(operator.equals("sqrt")){
            resultStr = num1 + " " + operator  + " = "+ result;
        }else{
            resultStr = num1 + " " + operator + " " + num2 + " = " + result;
        }

        System.out.println("결과: "+resultStr);

        //계산 이력 남기기
        this.addHistory(resultStr);

    }

    public double checkNum_double(Scanner sc) {
        double num = 0;
        boolean check = true;
        while (check){
            try {
                num = sc.nextInt();
                check = false;
                sc.nextLine(); // 버퍼 제거
                // 입력 받는 코드
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해주세요.");
                sc.nextLine(); // 잘못된 입력 제거
                continue;
            }
        }
        return num;
    }

    public void endCaculator(){
        System.out.println("계산기를 종료합니다.");
    }
    public String checkOperator(Scanner sc){

        while (true){
            String operator = sc.nextLine();
            if(!operator.equals("+")
                    && ! operator.equals("-")
                    && !operator.equals("*")
                    && !operator.equals("/")
                    && !operator.equals("%")
                    && !operator.equals("^")
                    && !operator.equals("sqrt")
            ){
                System.out.print("+ - * / % ^ sqrt 중 하나를 입력하세요: ");
            }else{
                return operator;
            }
        }

    }
    public void showMenuInfo(){
        System.out.print("\n=== 계산기 메뉴 ===\n1. 계산하기 \n2. 계산 이력 보기 \n3. 이력 지우기 \n0. 종료\n선택: ");
    }
    public int selectMenu(int input){

        while (true) {

            System.out.println();
            if (input == 1) {
                System.out.println("=== 1. 계산하기 ===");
            } else if (input == 2) {
                System.out.println("=== 2. 계산 이력 보기 ===");
            } else if (input == 3) {
                System.out.println("=== 3. 이력 지우기 ===");
            } else if (input == 0) {
                System.out.println("=== 계산기 종료 ===");
            } else {
                System.out.print("메뉴 중 하나를 선택하세요: ");
                continue;
            }
            break;
        }

        return input;
    }
    public static void main(String[] args) {

        Calculator calculator = new Calculator();
        Scanner sc = new Scanner(System.in);

        // 숫자 입력 받기
        Function<Scanner, Double> checkNum_int = (scanner) -> {
            double num = 0;
            while (true) {
                try {
                    num = scanner.nextInt();
                    scanner.nextLine(); // 버퍼 비우기
                    break;
                } catch (Exception e) {
                    System.out.print("숫자를 입력하세요: ");
                    scanner.nextLine(); // 버퍼 비우기
                }
            }
            return num;
        };

        // y 또는 n 입력받기
        Function<Scanner, String> checkNum_yn = (scanner) -> {
            String input ;
            while (true) {
                try {
                    input = scanner.nextLine();
                    if (!input.equals("y") && !input.equals("n")) {
                        System.out.print("y 또는 n을 입력하세요: ");
                    }else{
                        break;
                    }

                } catch (Exception e) {
                    System.out.print("y 또는 n을 입력하세요: ");
                }
            }
            return input;
        };

        int menu = -1;
        while (menu != 0) {

            //계산기 메뉴 보여주기
            calculator.showMenuInfo();

            //메뉴 선택하기
            while (true)
            {
                int input = checkNum_int.apply(sc).intValue();
                if (input >=0 && input <4){
                    menu = calculator.selectMenu((int)input);
                    break;
                }else{
                    System.out.print("0~3 숫자를 입력하세요: ");
                }
            }

            switch(menu){
                case 0:
                    calculator.endCaculator();
                    break;

                case 1:
                    while (true) {
                        double num1 = 0;
                        double num2 = 0;
                        String operator;
                        boolean usePrevCalc = false;
                        //이전 결과 사용 여부
                        if (calculator.prevCaclResult != null) {
                            System.out.print("이전 결과(" + calculator.prevCaclResult + ")를 사용하시겠습니까? (y/n): ");
                            String inputPrev = checkNum_yn.apply(sc);
                            if (inputPrev.equals("y")) {
                                num1 = calculator.prevCaclResult;
                                usePrevCalc = true;
                            }
                        }

                        System.out.println();
                        //첫 번째 숫자 입력
                        if (!usePrevCalc) { // 이전 계산 결과 사용 여부
                            System.out.print("첫 번째 숫자를 입력하세요: ");
                            num1 = checkNum_int.apply(sc);
                        }

                        //연산자 입력
                        System.out.print("연산자를 입력하세요 (+, -, *, /, %, ^, sqrt): ");
                        operator = calculator.checkOperator(sc);

                        //두 번째 숫자 입력
                        if(!operator.equals("sqrt")) {
                            System.out.print("두 번째 숫자를 입력하세요: ");
                            num2 = checkNum_int.apply(sc);
                            if (operator.equals("/") && num2 == 0) {

                                while (num2 == 0) {
                                    System.out.print("0으로 나눌 수 없습니다. 다시 입력해주세요: ");
                                    num2 = checkNum_int.apply(sc);
                                }
                            }
                        }
                        // 계산하기
                        calculator.cacluate(num1, num2, operator);

                        //계속해서 계산할 것인지
                        System.out.print("\n계속 계산하시겠습니까? (y/n): ");

                        String continueCacl = checkNum_yn.apply(sc);
                        if (continueCacl.equals("n")) {
                            calculator.prevCaclResult = null;
                            break;
                        }
                    }
                    break;

                case 2:
                    calculator.showHistory();
                    break;

                case 3:
                    calculator.deleteHistory();
                    break;

                default:
                    break;
            }

        }


        sc.close();
    }

}