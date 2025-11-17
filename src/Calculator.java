import java.util.ArrayDeque;
import java.util.Deque;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    static StringBuilder sb = new StringBuilder();

    public static void calculate(){
        Deque<String> deque = new ArrayDeque<>();


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