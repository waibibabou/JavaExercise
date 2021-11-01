package citel_task;

import java.util.Scanner;

public class Equations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a=scanner.nextDouble();
        double b=scanner.nextDouble();
        double c=scanner.nextDouble();
        double temp=Math.pow(b,2)-4*a*c;
        if(temp<0) System.out.println("The equation has no real roots");
        else if(temp==0){
            System.out.println(-b/(2*a));
        }
        else{
            System.out.println(String.format("%.6f",(-b-Math.pow(temp,0.5))/(2*a))+" "+String.format("%.6f",(-b+Math.pow(temp,0.5))/(2*a)));
//            System.out.println((-b-Math.pow(temp,0.5))/(2*a)+" "+(-b+Math.pow(temp,0.5))/(2*a));
        }

    }
}
