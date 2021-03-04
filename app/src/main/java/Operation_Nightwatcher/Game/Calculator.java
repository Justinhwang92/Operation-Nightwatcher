package Operation_Nightwatcher.Game;

import java.math.BigDecimal;
import java.util.Stack;

//basic operations
//trig functions + inverse trig
//sqrt, powers
//parentheses
//natural log
//pi
//e

/*
 * negative sign: _
 * trig functions: sin = S, cos = C, tan = T
 * inverse trig functions: sin^-1 = s, cos^-1 = c, tan^-1 = t
 * pi = P, e = E
 * ln = L
 */

//for the dropdown calculator in the game
public class Calculator {
    //list of operations
    public final String myOps = "*-/+^";
    //list of trig functions
    public final String myFuncs = "SCTsctL";
    //list of operands
    public final String myOperands = "0123456789PE";
    //the converted input string
    private String myInput;
    //for storing the previous answer calculated
    private BigDecimal myPrevious;
    //constructs the calculator object
    public Calculator(String theInput)
    {
        myInput = theInput;
        myPrevious = new BigDecimal(0);
    }

    //calculates expression given a string input from the calculator GUI
    public BigDecimal calculate(String theInput)
    {
        String calculateString = theInput.replace(" ", "");

        //validation:
        Exception error = null;
        try
        {
          error = checkValidity(calculateString);
        }
        catch(Exception e)
        {
            displayErrorMessage(error);
            return null;
        }

        BigDecimal answer = new BigDecimal(0);

        Stack<String> operators = new Stack<>();
        Stack<String> operands = new Stack<>();

        //calculation here:
        try
        {
            for(int i = 0; i < calculateString.length(); i++)
            {
                //infix to postfix algorithm

            }
        }
        catch (StackOverflowError o)
        {

        }
        catch(Exception e)
        {
            displayErrorMessage(new Exception("Error"));
        }

        myPrevious = answer;
        return null;
    }

    //checks if the input string is valid
    public Exception checkValidity(String theInput)
    {
        if(!checkParentheses(theInput))
        {
            return new Exception("Invalid parentheses syntax");
        }

        if(!checkOperators(theInput))
        {
            return new Exception("Invalid operator syntax");
        }

        return null;
    }

    //checks if all closing parentheses have a matching open parenthesis
    //also checks if any open parentheses don't have any arguments
    public boolean checkParentheses(String theInput)
    {
        if(theInput.charAt(0) == ')')
        {
            return false;
        }

        if(theInput.charAt(theInput.length() - 1) == '(')
        {
            return false;
        }

        for(int i = 0; i < theInput.length(); i++)
        {
            if(theInput.charAt(i) == '(')
            {
                if(theInput.charAt(i + 1) == ')')
                {
                    return false;
                }
            }
        }

        Stack<Character> closedParentheses = new Stack<>();

        for(int i = 0; i < theInput.length(); i++)
        {
            if(theInput.charAt(i) == ')')
            {
                closedParentheses.push(theInput.charAt(i));
            }

            if(theInput.charAt(i) == '(')
            {
                closedParentheses.pop();
            }
        }

        return closedParentheses.isEmpty();
    }

    //checks to see if there are 2 operators in a row or improperly positioned
    //also checks to see if negative signs are properly positioned
    public boolean checkOperators(String theInput)
    {
        if(myOps.contains(String.valueOf(theInput.charAt(0))) ||
                myOps.contains(String.valueOf(theInput.charAt(theInput.length() - 1))))
        {
            return false;
        }

        //calculator will build input string by using '_' as negative sign
        if(theInput.charAt(theInput.length() - 1) == '_')
        {
            return false;
        }

        for(int i = 1; i < theInput.length() - 1; i++)
        {
            if(theInput.charAt(i) == '_')
            {
                if(!myOps.contains(String.valueOf(theInput.charAt(i - 1))))
                {
                    return false;
                }
            }

            if(myOps.contains(String.valueOf(theInput.charAt(i))))
            {
                if(myOps.contains(String.valueOf(theInput.charAt(i - 1))) ||
                        myOps.contains(String.valueOf(theInput.charAt(i + 1))))
                {
                    return false;
                }
            }
        }

        return true;
    }

    //displays an error message depending on what error occurred
    public void displayErrorMessage(Exception theException)
    {
        //stand-in error message; need to implement the error in the calculator GUI
        System.out.println("Invalid calculator input");
    }


    //to change the previous answer
    public void setMyPrevious(BigDecimal thePrevious)
    {
        myPrevious = thePrevious;
    }

    //to return the previous answer
    public BigDecimal getMyPrevious()
    {
        return myPrevious;
    }

    //gets input string
    public String getMyInput() {
        return myInput;
    }

    //sets input string
    public void setMyInput(String theInput) {
        myInput = theInput;
    }
}
