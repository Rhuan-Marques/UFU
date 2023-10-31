package POO.Entrega2.Circulo;
import java.util.Scanner;
class Main {
  public static void main(String[] args){
    double r, x, y;
    Circle circulo = new Circle();
    Scanner input = new Scanner(System.in);
    x = input.nextDouble();
    y = input.nextDouble();
    r = input.nextDouble();
    input.close();
    circulo.constructor_not_constructor(r, x, y);
    circulo.print_values();

}

}
