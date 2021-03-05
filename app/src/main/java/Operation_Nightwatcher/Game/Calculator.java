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


//for the dropdown calculator in the game
public class Calculator {
    //list of operations
    public final String myOps = "*-/+^";
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

        try
        {
            answer = eval(calculateString);
        }
        catch(StackOverflowError e)
        {
            displayErrorMessage(new Exception("Out of memory"));
        }
        catch(Exception e)
        {
            displayErrorMessage(new Exception("Error"));
        }
        myPrevious = answer;
        return answer;
    }

    public static BigDecimal eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            BigDecimal parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return new BigDecimal(x);
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else if (func.equals("asin")) x = Math.asin(Math.toRadians(x));
                    else if (func.equals("acos")) x = Math.acos(Math.toRadians(x));
                    else if (func.equals("atan")) x = Math.atan(Math.toRadians(x));
                    else if (func.equals("ln")) x = Math.log(x);
                    else if (func.equals("pi")) x = Math.PI;
                    else if (func.equals("e")) x = Math.E;
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
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

        if(!checkFunctions(theInput))
        {
            return new Exception("Please surround function parameters without parentheses");
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

    //checks if each function has parentheses after it
    public boolean checkFunctions(String theInput)
    {
        for(int i = 0; i < theInput.length(); i++)
        {
            if(theInput.charAt(i) >= 'a' && theInput.charAt(i) >= 'a')
            {
                while(theInput.charAt(i) >= 'a' && theInput.charAt(i) >= 'a')
                {
                    i++;
                }
                if(theInput.charAt(i) != '(')
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
        System.out.println(theException.getMessage());
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



//                PREVIOUS SOLUTION -- INCOMPLETE
//    //calculation here:
//        try
//    {
//        /*************************INFIX TO POSTFIX******************************************/
//        Stack<String> postfix = new Stack<>();
//        Stack<String> operators = new Stack<>();
//        StringBuilder operand = new StringBuilder();
//
//        for(int i = 0; i < calculateString.length(); i++)
//        {
//            //infix to postfix algorithm
//            String current = String.valueOf(calculateString.charAt(i));
//            //if it is an operand, push it (numbers)
//            if(!(myOps.contains(current) || myFuncs.contains(current)))
//            {
//                operand.append(current);
//            }
//
//            else
//            {
//                //current character is an operator or function, so our operand is done
//                //push it to the postfix stack
//                postfix.push(operand.toString());
//                //reset the operand string builder
//                operand = new StringBuilder();
//                if(current == ")" || i == calculateString.length() - 1)
//                {
//                    //expression is to build a string to represent the inside of the parentheses
//                    StringBuilder expression = new StringBuilder("(");
//                    while(operators.peek() != "(")
//                    {
//                        expression.append(operators.pop());
//                    }
//                    operators.pop(); //discard left parentheses
//                    expression.append(")");
//                    //check if next character is a function character
//                    if(!operators.isEmpty())
//                    {
//                        String nextOperator = operators.peek();
//                        if(myFuncs.contains(nextOperator))
//                        {
//                            //insert function character at beginning of expression
//                            expression.insert(0, operators.pop());
//                        }
//                    }
//
//                    postfix.push(expression.toString());
//                }
//
//                else
//                {
//                    //push based on precedence
//                    if(firstPriority.contains(current) || operators.contains("("))
//                    {
//                        operators.push(current);
//                    }
//
//                    else if(secondPriority.contains(current))
//                    {
//                        boolean flag = false;
//                        for(String s : operators)
//                        {
//                            if(firstPriority.contains(s))
//                            {
//                                flag = true;
//                                break;
//                            }
//                        }
//
//                        if(flag)
//                        {
//                            Stack<String> higherPriority = new Stack<>();
//                            while(operators.peek() != "(")
//                            {
//                                higherPriority.push(operators.pop());
//                            }
//                            operators.push(current);
//                            while(!higherPriority.isEmpty())
//                            {
//                                operators.push(higherPriority.pop());
//                            }
//                        }
//
//                        else
//                        {
//                            operators.push(current);
//                        }
//                    }
//
//                    else
//                    {
//                        boolean flag = false;
//                        for(String s : operators)
//                        {
//                            if(firstPriority.contains(s) || secondPriority.contains(s))
//                            {
//                                flag = true;
//                                break;
//                            }
//                        }
//
//                        if(flag)
//                        {
//                            Stack<String> higherPriority = new Stack<>();
//                            while(operators.peek() != "(")
//                            {
//                                higherPriority.push(operators.pop());
//                            }
//                            operators.push(current);
//                            while(!higherPriority.isEmpty())
//                            {
//                                operators.push(higherPriority.pop());
//                            }
//                        }
//
//                        else
//                        {
//                            operators.push(current);
//                        }
//                    }
//                }
//            }
//        }
//
//        //check is operators stack is empty: pop all the parentheses
//        while(!operators.isEmpty())
//        {
//            if(operators.contains("("))
//            {
//                StringBuilder expression = new StringBuilder("(");
//                while(operators.peek() != "(")
//                {
//                    expression.append(operators.pop());
//                }
//                operators.pop(); //discard left parentheses
//                expression.append(")");
//                //check if next character is a function character
//                if(!operators.isEmpty())
//                {
//                    String nextOperator = operators.peek();
//                    if(myFuncs.contains(nextOperator))
//                    {
//                        //insert function character at beginning of expression
//                        expression.insert(0, operators.pop());
//                    }
//                }
//                postfix.push(expression.toString());
//            }
//
//            else
//            {
//                postfix.push(operators.pop());
//            }
//        }
//        /*************************END INFIX TO POSTFIX******************************************/
//        /*************************CALCULATE POSTFIX******************************************/
//
//        Stack<String> postfixOperands = new Stack<>();
//        Stack<String> postFixOperators = new Stack<>();
//        while(!postfix.isEmpty())
//        {
//            String operand1 = postfix.pop();
//            String operand2 = postfix.pop();
//
//            if(operand1.contains("(") || operand2.contains("("))
//            {
//                //have to multiply or
//            }
//        }
//    }
//        catch (StackOverflowError o)
//    {
//        displayErrorMessage(new Exception("Overflow error"));
//    }
//        catch(Exception e)
//    {
//        displayErrorMessage(new Exception("Error"));
//    }
    //another idea:
    //make a tree
    //steps:
    //simplify trig expressions
    //Read characters left to right
    //if operand, read operator and another operand (operand could be parenthetical expression)
    //if character after operand is parentheses, multiply is the sign
    //parentheses = recursion of performOperation
    //1st run through - make nodes
    //
}
