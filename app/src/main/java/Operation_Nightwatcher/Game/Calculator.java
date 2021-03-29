package Operation_Nightwatcher.Game;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Stack;

//functionality:
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
    public final static String myOps = "*-/+^";
    //for storing the previous answer calculated
    private static BigDecimal myPrevious;

    //constructs the calculator object
    public Calculator()
    {
        myPrevious = new BigDecimal(0);
    }

    //calculates expression given a string input from the calculator GUI
    public static BigDecimal calculate(String theInput)
    {
        String calculateString = theInput.replace(" ", "");
        if(calculateString.length() < 1)
        {
            return null;
        }
        //validation:
        Exception error = null;
        error = checkValidity(calculateString);
        if(error != null)
        {
            displayErrorMessage(error);
            return null;
        }

        calculateString = insertHiddenMultiplication(calculateString);

        BigDecimal answer = null;

        boolean calcSuccess = true;
        try
        {
            answer = eval(calculateString);
        }
        catch(StackOverflowError e)
        {
            calcSuccess = false;
            displayErrorMessage(new Exception("Out of memory"));
        }
        catch(Exception e)
        {
            calcSuccess = false;
            displayErrorMessage(new Exception("Unexpected character"));
        }
        if(calcSuccess)
        {
            myPrevious = answer;
        }

        return answer;
    }

    //function to insert hidden multiplication signs where multiplication is needed
    //i.e. 3(3) = 3*3 or 3*(3)
    //this ensures that the eval() function can run correctly
    private static String insertHiddenMultiplication(String theInput)
    {
        String result = theInput;

        for(int i = 0; i < result.length(); i++)
        {
            //cases to consider:
            //operand before open parentheses
            //operand after function end parentheses
            //operand with special operand (i.e. pi or e)

            //whats an operand?:
            //1. numbers
            //2. function (look for closed parentheses)
            //3. pi || e
            char current = result.charAt(i);

            boolean op1IsNumber = current >= '0' && current <= '9';
            boolean op1IsEndOfExpression = current == ')';
            boolean op1IsSpecialChar = current == 'e' || (current == 'i' && result.charAt(i - 1) == 'p');

            if(i + 1 < result.length())
            {
                if(op1IsNumber || op1IsEndOfExpression || op1IsSpecialChar)
                {
                    char next = result.charAt(i + 1);

                    boolean op2IsStartOfExpression = next == '(';
                    boolean op2IsFunction = next == 's' || next == 'c' || next == 't' || next == 'a' || next == ';';
                    boolean op2IsSpecialChar = next == 'p' || next == 'e';

                    if(op2IsFunction || op2IsSpecialChar || op2IsStartOfExpression)
                    {
                        result = result.substring(0, i + 1) + '*' + result.substring(i + 1);
                        i++;
                    }
                }
            }

        }

        return result;
    }


    private static BigDecimal eval(final String str) {
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
                if (pos < str.length()) { throw new RuntimeException("Unexpected: " + (char)ch); }
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
                    if (func.equals("pi")) x = Math.PI;
                    else if (func.equals("e")) x = Math.E;
                    else {
                        x = parseFactor();
                        if (func.equals("sqrt")) x = Math.sqrt(x);
                        else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                        else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                        else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                        else if (func.equals("asin")) x = Math.asin(Math.toRadians(x));
                        else if (func.equals("acos")) x = Math.acos(Math.toRadians(x));
                        else if (func.equals("atan")) x = Math.atan(Math.toRadians(x));
                        else if (func.equals("ln")) x = Math.log(x);
                        else throw new RuntimeException("Unknown function: " + func);
                    }
                } else {
                   throw new RuntimeException("Unexpected: " + (char)ch);
                }


                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

    //checks if the input string is valid
    private static Exception checkValidity(String theInput)
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
            return new Exception("Please surround function parameters with parentheses");
        }

        if(!checkDecimals(theInput))
        {
            return new Exception("Invalid decimal point syntax");
        }

        return null;
    }

    //checks if all closing parentheses have a matching open parenthesis
    //also checks if any open parentheses don't have any arguments
    private static boolean checkParentheses(String theInput)
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
        Stack<Character> openParentheses = new Stack<>();
        for(int i = 0; i < theInput.length(); i++)
        {
            if(theInput.charAt(i) == ')')
            {
                closedParentheses.push(theInput.charAt(i));
            }

            if(theInput.charAt(i) == '(')
            {
                openParentheses.push(theInput.charAt(i));
            }
        }

        return closedParentheses.size() <= openParentheses.size();
    }

    //checks to see if there are 2 operators in a row or improperly positioned
    //also checks to see if negative signs are properly positioned
    private static boolean checkOperators(String theInput)
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
    private static boolean checkFunctions(String theInput)
    {
        for(int i = 0; i < theInput.length(); i++)
        {
            if(theInput.charAt(i) >= 'a' && theInput.charAt(i) <= 'z')
            {
                StringBuilder funcName = new StringBuilder();
                funcName.append(theInput.charAt(i));

                while(theInput.charAt(i) >= 'a' && theInput.charAt(i) <= 'z' && !(funcName.toString().equals("pi") || funcName.toString().equals("e")))
                {

                    i++;
                    if(i >= theInput.length())
                    {
                        return false;
                    }

                    if(i < theInput.length())
                    funcName.append(theInput.charAt(i));

                }
                if(theInput.charAt(i) != '(' && !(funcName.toString().equals("pi") || funcName.toString().equals("e")))
                {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkDecimals(String theInput)
    {
        for(int i = 0; i < theInput.length(); i++)
        {
            if(theInput.charAt(i) == '.')
            {
                if(i + 1 < theInput.length())
                {
                    if(!(theInput.charAt(i + 1) >= '0' && theInput.charAt(i + 1) <= '9'))
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    //displays an error message depending on what error occurred
    private static void displayErrorMessage(Exception theException)
    {
        ExpressionBuilder.displayError(theException);
    }

    //to return the previous answer
    public static BigDecimal getMyPrevious()
    {
        return myPrevious;
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
