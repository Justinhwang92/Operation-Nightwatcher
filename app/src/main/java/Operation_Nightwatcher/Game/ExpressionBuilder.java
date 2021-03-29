package Operation_Nightwatcher.Game;

import android.view.View;

import com.td.OperationNightwatcher.R;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import Operation_Nightwatcher.Activity.Activity_Room;

public class ExpressionBuilder {

    public static Activity_Room  myRoom;

    public ExpressionBuilder(Activity_Room theRoom)
    {
        myRoom = theRoom;
    }

    public static boolean myInv = false;

    public static StringBuilder myExpression = new StringBuilder();

    public static void onClick(View theView)
    {
        switch (theView.getId())
        {
            case R.id.calc0: updateExpression("0"); break;
            case R.id.calc1: updateExpression("1"); break;
            case R.id.calc2: updateExpression("2"); break;
            case R.id.calc3: updateExpression("3"); break;
            case R.id.calc4: updateExpression("4"); break;
            case R.id.calc5: updateExpression("5"); break;
            case R.id.calc6: updateExpression("6"); break;
            case R.id.calc7: updateExpression("7"); break;
            case R.id.calc8: updateExpression("8"); break;
            case R.id.calc9: updateExpression("9"); break;
            case R.id.calcSin: if(myInv) {updateExpression("asin(");} else {updateExpression("sin(");} myInv = false; break;
            case R.id.calcCos: if(myInv) {updateExpression("acos(");} else {updateExpression("cos(");} myInv = false; break;
            case R.id.calcTan: if(myInv) {updateExpression("atan(");} else {updateExpression("tan(");} myInv = false; break;
            case R.id.calcInv: myInv = !myInv; toggleTrigText(); break;
            case R.id.calcLn: updateExpression("ln("); break;
            case R.id.calcSqrt: updateExpression("sqrt("); break;
            case R.id.calcPi: updateExpression("pi"); break;
            case R.id.calcLeftParentheses: updateExpression("("); break;
            case R.id.calcRightParentheses: updateExpression(")"); break;
            case R.id.calcE: updateExpression("e"); break;
            case R.id.calcPower: updateExpression("^"); break;
            case R.id.calcPeriod: updateExpression("."); break;
            case R.id.calcPlus: updateExpression("+"); break;
            case R.id.calcMinus: updateExpression("-"); break;
            case R.id.calcTimes: updateExpression("*"); break;
            case R.id.calcDivide: updateExpression("/"); break;
            case R.id.calcCE: updateExpression(""); break;
            case R.id.calcAC: updateExpression("clear"); break;
            case R.id.calcEquals: updateExpression("equals"); break;
        }
    }

    public static void displayError(Exception theException)
    {
        myRoom.myCalcText.setText(theException.getMessage());
    }

    public static void resetExpression()
    {
        myExpression = new StringBuilder();
    }

    private static void toggleTrigText()
    {
        if(myInv)
        {
            myRoom.myCalcSin.setText("sin^-1");
            myRoom.myCalcCos.setText("cos^-1");
            myRoom.myCalcTan.setText("tan^-1");
        }

        else
        {
            myRoom.myCalcSin.setText("sin");
            myRoom.myCalcCos.setText("cos");
            myRoom.myCalcTan.setText("tan");
        }
    }

    private static void updateExpression(String theUpdate)
    {
        BigDecimal calculation = new BigDecimal(0);
        boolean calculated = false;
        String formatted = "";

        if(theUpdate.equals(""))
        {
            if(myExpression.length() > 0)
            myExpression.deleteCharAt(myExpression.length() - 1);
        }
        else if(theUpdate.equals("clear"))
        {
            resetExpression();
        }
        else if(theUpdate.equals("equals"))
        {
            calculated = true;
            calculation = Calculator.calculate(myExpression.toString());
            DecimalFormat limitDecimals = new DecimalFormat("#.#########");

            String check = calculation.toPlainString();
            if(check.contains(".") && check.substring(check.indexOf('.')).length() > 10)
            {
                formatted = limitDecimals.format(Calculator.getMyPrevious());
            }

            else
            {
                formatted = calculation.toString();
            }

            if(calculation != null)
                myExpression = new StringBuilder(formatted);
        }
        else
        {
            myExpression.append(theUpdate);
        }

        if(calculation != null)
        {
            if(calculated)
            {
                myRoom.myCalcText.setText(formatted);
            }
            else
            {
                myRoom.myCalcText.setText(myExpression.toString());
            }
        }

    }
}
