package Operation_Nightwatcher.Activity.ProblemClasses;

import java.util.Random;

public class Integration extends AbstractQuestions {

    String myQue;
    String ans;

    //http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.714.5588&rep=rep1&type=pdf
    public Integration(){
        // Call class function
        Function function;
        function = new Function();

        // ENTER the desired values of a, b and n !!!
        double upper = -2;
        double lower = 2;
        int n = 50000;
        // Applies simpson method to function
        double result = function.IntSimpson(upper,lower,n);

        // Show results
//        System.out.println("\u222B "+ function.currentQuest +" Integral is: " + result);

        myQue = "Find \u222B " + function.currentQuest + "\nwhere upper limit = "+upper+" and lower limit = "+lower;
        ans = result+"";
    }


    @Override
    public String getQuestion() {
        return myQue;
    }

    @Override
    public String getAnswer() {
        return ans;
    }
}

/*------------------------------------------------------------------------------------------------------
 * Small program that numerically calculates an integral according to Simpson's algorithm.
 *  - the expression of the function f:
 *  - the lower and upper limits b of the integral:
 *  - the number of measurements n (n is integer !!!):
 *------------------------------------------------------------------------------------------------------*/
// Class function: Defines Simpson's rule

/**
 * This class takes Upper limit, lower limit and number of passes.
 * Higher the number of passes produce more accurate answers.
 */
class Function{

    Random rnd = new Random();
    String currentQuest;

    //Possible function for integration - not too complex
    /** easy ones*/
    //1. 9 -x^2
    String q2;
    //2. 3x^2 + 2x + 5
    String q3;
    //3. x^2 + 1
    String q4;
    //4.
    /** Intermediate ones */
    //1. 0.2 + 25x - 200x^2 + 675x^3 - 900x^4 + 400x^5
    String q1;
    int a;
    int choice;

    Function(){
        generateQuestion();
    }

    void generateQuestion(){
        a = rnd.nextInt(5)+1;
        q1 = "0.2+25x-"+(a*46)+"x²+"+(a*100)+"x\u00B3-400x\u2074+900x\u2075 ";
        q3 = (a+2)+"x²+"+a+"x+"+(a+3);
        q2 = (a+4)+" - x²";
        q4 = "x²+"+a;

        choice = rnd.nextInt(4);
//        c=0;
        switch (choice) {
            case 0:
                currentQuest = q4;
                break;
            case 1:
                currentQuest = q3;
                break;
            case 2:
                currentQuest = q2;
                break;
            case 3:
                currentQuest = q1;
                break;
            default:
                break;

        }

    }

    // Define the function to integrate
    double f (double x) {

        switch (choice) {
            case 0:
                return x*2 + a;
            case 1:
                return (a+2)*x*x + a*x + (a+3);
            case 2:
                return (a+4) - x * x;
            case 3:
//                return (0.2 + 25 * x - 252 * Math.pow(x, 2) + 500 * Math.pow(x, 3) - 400 * Math.pow(x, 4) + 900 * Math.pow(x, 5));
                return (0.2 + 25 * x - (a*46) * Math.pow(x, 2) + (a*100) * Math.pow(x, 3) - 400 * Math.pow(x, 4) + 900 * Math.pow(x, 5));
            default:
                break;

        }

        return 0;
    }

    /** Simpson's method for integral calculus
    * a = lower bound
    * b = upper bound of integration
    * n = number of passes (higher = less margin of error, but takes longer)
     */
    double IntSimpson(double a, double b,int n){
        int i,z;
        double h,s;

        n=n+n;
        s = f(a)*f(b);
        h = (b-a)/n;
        z = 4;

        for(i = 1; i<n; i++){
            s = s + z * f(a+i*h);
            z = 6 - z;
        }
        return (s * h)/3;
    }
}
