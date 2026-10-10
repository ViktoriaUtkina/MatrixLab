public class Matrix {
    private int rows;
    private int cols;
    private  Complex[][] data;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new Complex[rows][cols];
        //инициализация нолями
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] = new Complex(0,0);
            }
        }
    }

    public  void printMatrix(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(data[i][j].toString() + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public Matrix add(Matrix arr){
        if(this.rows != arr.rows || this.cols != arr.cols){
            System.out.println("Ошибка: Размеры матриц не совпадают.");
            return null;
        }
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] = this.data[i][j].add(arr.data[i][j]);
            }
        }
        return result;
    }

    public Matrix subtract(Matrix arr){
        if (this.rows != arr.rows || this.cols != arr.cols){
            System.out.println("Ошибка: Размеры матриц не совпадают.");
            return null;
        }
        Matrix result = new Matrix(rows,cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] = this.data[i][j].subtract(arr.data[i][j]);
            }
        }
        return  result;
    }
    public  Matrix multiply(Matrix arr){
        if(this.cols != arr.rows){
            System.out.println("Ошибка: Число столбцов 1-ой матрицы не равно числу строк 2-ой.");
            return  null;
        }
        Matrix result = new Matrix(this.rows,arr.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < arr.cols; j++) {
                Complex sum = new Complex(0,0);
                for (int k = 0; k < this.cols; k++) {
                    sum = sum.add(this.data[i][k].multiply(arr.data[k][j]));
                }
                result.data[i][j] = sum;
            }
        }
        return result;
    }

    public Matrix transpose(){
        Matrix result = new Matrix(cols,rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[j][i] = this.data[i][j];
            }
        }
        return result;
    }

    private Matrix createMinor(int excludedRow, int excludedCol){
        Matrix minor = new Matrix(rows - 1, cols - 1);
        int minorRow = 0;
        for (int i = 0; i < rows; i++) {
            if (i == excludedRow) continue;
            int minorCol = 0;
            for (int j = 0; j < cols; j++) {
                if (j == excludedCol) continue;
                minor.data[minorRow][minorCol] = this.data[i][j];
                minorCol++;
            }
            minorRow++;
        }
        return  minor;
    }

    public Complex determinant(){
        if (rows != cols){
            System.out.println("Ошибка: Матрица не квадратная.");
            return null;
        }
        if (rows == 1){
            return  data[0][0];
        }
        if (rows == 2){
            return  data[0][0].multiply(data[1][1]).subtract(data[0][1].multiply(data[1][0]));
        }

        Complex det = new Complex(0,0);
        for (int j = 0; j < cols; j++) {
            Matrix minor = createMinor(0,j);
            Complex minorDet = minor.determinant();
            Complex term = data[0][j].multiply(minorDet);
            if (j % 2 == 0){
                det = det.add(term);
            } else{
                det = det.subtract(term);
            }
        }
        return det;
    }

    public Matrix inverse(){
        if (rows != cols){
            System.out.println("Ошибка: Матрица не квадратная.");
            return null;
        }
        Complex det = determinant();
        if (det == null || det.isZero()){
            System.out.println("Ошибка: Определитель равен нулю -> обратной матрицы не существует.");
            return null;
        }

        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Matrix minor = createMinor(i,j);
                Complex minorDet = minor.determinant();
                if ((i + j)% 2 != 0){
                    minorDet = new Complex(-minorDet.getReal(),-minorDet.getImaginary());
                }
                result.data[j][i] = minorDet.divide(det);
            }
        }
        return result;
    }

    //деление матриц: A/B = A* B^(-1)
    public Matrix divide(Matrix arr){
        if (this.rows != arr.rows || this.cols != arr.cols){
            System.out.println("Ошибка: Размеры матриц должны совпадать.");
            return null;
        }
        if (arr.rows != arr.cols){
            System.out.println("Ошибка: Деление возможно только на квадратную матрицу.");
            return null;
        }
        Matrix inv = arr.inverse();
        if (inv == null) return null;
        return this.multiply(inv);
    }

    public int getRows() {return rows; }
    public int getCols() {return cols; }
    public Complex[][] getData() {return data; }
}
