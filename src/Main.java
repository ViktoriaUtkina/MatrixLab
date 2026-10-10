import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        Matrix matrixA = null;
        Matrix matrixB = null;

        while (running){
            System.out.println("\nВыберите действие:");
            System.out.println("1. Задать первую матрицу (A).");
            System.out.println("2. Задать вторую матрицу (B).");
            System.out.println("3. Вычислить сумму двух матриц.");
            System.out.println("4. Вычислить разность двух матриц.");
            System.out.println("5. Перемножить две матрицы");
            System.out.println("6. Вычислить частное двух матриц.");
            System.out.println("7. Найти определитель матрицы.");
            System.out.println("8. Транспонировать матрицу.");
            System.out.println("9. Выйти из программы.");
            System.out.print("Ваш выбор: ");

            int choice = readInt(scanner);

            switch (choice){
                case 1:
                    matrixA = inputMatrix(scanner);
                    System.out.println("Матрица A сохранена.");
                    break;
                case 2:
                    matrixB = inputMatrix(scanner);
                    System.out.println("Матрица B сохранена.");
                    break;
                case 3:
                    if (matrixA == null || matrixB == null){
                        System.out.println("Ошибка: Не задана одна или обе матрицы.");
                    } else {
                        Matrix sum = matrixA.add(matrixB);
                        if (sum != null){
                            System.out.println("Результат сложения:");
                            sum.printMatrix();
                        }
                    }
                    break;
                case 4:
                    if (matrixA == null || matrixB == null){
                        System.out.println("Ошибка: Не задана одна или обе матрицы.");
                    } else {
                        Matrix diff = matrixA.subtract(matrixB);
                        if (diff != null){
                            System.out.println("Результат вычитания:");
                            diff.printMatrix();
                        }
                    }
                    break;
                case 5:
                    if (matrixA == null || matrixB == null){
                        System.out.println("Ошибка: Не задана одна или обе матрицы.");
                    } else{
                        Matrix mult = matrixA.multiply(matrixB);
                        if (mult != null){
                            System.out.println("Результат умножения:");
                            mult.printMatrix();
                        }
                    }
                    break;
                case 6:
                    if (matrixA == null || matrixB == null){
                        System.out.println("Ошибка: Не задана одна или обе матрицы.");
                    } else{
                        Matrix div = matrixA.divide(matrixB);
                        if (div != null){
                            System.out.println("Результат деления:");
                            div.printMatrix();
                        }
                    }
                    break;
                case 7:
                    System.out.println("Определитель какой матрицы вычислить?");
                    System.out.println("1. Первой (A)");
                    System.out.println("2. Второй (B)");
                    System.out.print("Ваш выбор: ");
                    int detChoice = readInt(scanner);
                    if (detChoice == 1 && matrixA != null){
                        Complex det = matrixA.determinant();
                        if (det != null){
                            System.out.println("Определитель матрицы A: " + det.toString());
                        }
                    } else if (detChoice == 2 && matrixB != null){
                        Complex det = matrixB.determinant();
                        if (det != null){
                            System.out.println("Определитель матрицы B: " + det.toString());
                        }
                    } else {
                        System.out.println("Матрица не задана или выбор неверный.");
                    }
                    break;
                case 8:
                    System.out.println("Какую матрицу транспонировать?");
                    System.out.println("1. Первую (A)");
                    System.out.println("2. Вторую (B)");
                    System.out.print("Ваш выбор: ");
                    int trChoice = readInt(scanner);
                    if (trChoice == 1 && matrixA != null){
                        Matrix t = matrixA.transpose();
                        System.out.println("Транспонированная матрица A:");
                        t.printMatrix();
                    } else if (trChoice == 2 && matrixB != null){
                        Matrix t = matrixB.transpose();
                        System.out.println("Транспонированная матрица B:");
                        t.printMatrix();
                    } else{
                        System.out.println("Матрица не задана или выбор неверный.");
                    }
                    break;
                case 9:
                    running = false;
                    System.out.println("Выход из программы.");
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
        scanner.close();
    }

    private  static int readInt(Scanner scanner){
        while (true){
            try {
                return scanner.nextInt();
            } catch (Exception e){
                System.out.print("Ошибка. Введите целое число: ");
                scanner.next();
            }
        }
    }

    private  static Matrix inputMatrix(Scanner scanner){
        int rows = 0, cols = 0;
        while (true){
            System.out.print("Введите количество строк: ");
            rows = readInt(scanner);
            System.out.print("Введите количество столбцов: ");
            cols = readInt(scanner);
            if (rows > 0 && cols >0 && rows <= 20 && cols <= 20){
                break;
            } else{
                System.out.println("Размерность должна быть от 1 до 20.");
            }
        }

        Matrix matrix = new Matrix(rows,cols);
        System.out.println("Введите элементы матрицы (действительная и мнимая части через пробел):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Элемент [" + i + "][" + j + "]: ");
                while (true){
                    try {
                        int real = scanner.nextInt();
                        int imag = scanner.nextInt();
                        matrix.getData()[i][j] = new Complex(real, imag);
                        break;
                    } catch (Exception e){
                        System.out.print("Ошибка. Введите два целых числа: ");
                        scanner.nextLine();
                    }
                }
            }
        }
        return matrix;
    }
}
