public class Complex {
    private int real;
    private int imaginary;

    public Complex() {
        this.real = 0;
        this.imaginary = 0;
    }

    public Complex(int real, int imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public String toString(){
        if (imaginary>0){
            return real + " + " + imaginary + "i";
        } else if (imaginary == 0){
            return real + "";
        } else{
            return real + " - " + Math.abs(imaginary) + "i";
        }
    }

    public Complex add(Complex num){
        return new Complex(this.real + num.real, this.imaginary + num.imaginary);
    }

    public Complex subtract(Complex num){
        return new Complex(this.real - num.real, this.imaginary - num.imaginary);
    }

    public Complex multiply(Complex num){
        int newReal = this.real * num.real - this.imaginary * num.imaginary;
        int newImaginary = this.real * num.imaginary + this.imaginary * num.real;
        return new Complex(newReal, newImaginary);
    }

    public Complex divide(Complex num){
        int denominator = num.real * num.real + num.imaginary * num.imaginary;
        if (denominator == 0) {
            System.out.println("Ошибка: нельзя делить на ноль!!!");
            return  null;
        }
        int newReal = (this.real * num.real + this.imaginary * num.imaginary)/denominator;
        int newImaginary = (this.imaginary * num.real - this.real * num.imaginary)/denominator;
        return  new Complex(newReal, newImaginary);
    }

    public boolean isZero(){
        return  real == 0 && imaginary == 0;
    }

    public int getReal(){ return real; }
    public int getImaginary(){ return imaginary; }
}
