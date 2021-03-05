package Operation_Nightwatcher.Game;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.td.OperationNightwatcher.R;

public class ExpressionBuilder {

    public static Button myCalc0;
    public static Button myCalc1;
    public static Button myCalc2;
    public static Button myCalc3;
    public static Button myCalc4;
    public static Button myCalc5;
    public static Button myCalc6;
    public static Button myCalc7;
    public static Button myCalc8;
    public static Button myCalc9;
    public static Button myCalcSin;
    public static Button myCalcCos;
    public static Button myCalcTan;
    public static Button myCalcInv;
    public static Button myCalcLn;
    public static Button myCalcSqrt;
    public static Button myCalcPi;
    public static Button myCalcLP;
    public static Button myCalcRP;
    public static Button myCalcE;
    public static Button myCalcPow;
    public static Button myCalcPeriod;
    public static Button myCalcPlus;
    public static Button myCalcMinus;
    public static Button myCalcTimes;
    public static Button myCalcDivide;
    public static Button myCalcCE;

    public static Button[] myButtons =
    {
        myCalc0,
        myCalc1,
        myCalc2,
        myCalc3,
        myCalc4,
        myCalc5,
        myCalc6,
        myCalc7,
        myCalc8,
        myCalc9,
        myCalc0,
        myCalcSin,
        myCalcCos,
        myCalcTan,
        myCalcInv,
        myCalcLn,
        myCalcSqrt,
        myCalcPi,
        myCalcLP,
        myCalcRP,
        myCalcE,
        myCalcPow,
        myCalcPeriod,
        myCalcPlus,
        myCalcMinus,
        myCalcTimes,
        myCalcDivide,
        myCalcCE
    };

    public static TextView myCalcView;

    public ExpressionBuilder(TextView theTextView, Button... theButtons)
    {
        for(int i = 0; i < theButtons.length; i++)
        {
            myButtons[i] = theButtons[i];
        }

        myCalcView = theTextView;
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
        }
    }

    public static String getExpression()
    {
        return myExpression.toString();
    }

    public static void resetExpression()
    {
        myExpression = new StringBuilder();
    }

    public static void toggleTrigText()
    {
        if(myInv)
        {
            myCalcSin.setText("sin^-1");
            myCalcCos.setText("cos^-1");
            myCalcTan.setText("tan^-1");
        }

        else
        {
            myCalcSin.setText("sin");
            myCalcCos.setText("cos");
            myCalcTan.setText("tan");
        }
    }

    public static void updateExpression(String theUpdate)
    {
        if(theUpdate == "")
        {
            myExpression.deleteCharAt(myExpression.length() - 1);
        }
        else
        {
            myExpression.append(theUpdate);
        }

        myCalcView.setText(myExpression);
    }
}
