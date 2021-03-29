    package Operation_Nightwatcher.Activity.ProblemClasses.Test;

import android.opengl.EGLImage;

import Operation_Nightwatcher.Activity.ProblemClasses.AbstractQuestions;
import Operation_Nightwatcher.Activity.ProblemClasses.AccelerationQuestion;
import Operation_Nightwatcher.Activity.ProblemClasses.EquationsofMotion;
import Operation_Nightwatcher.Activity.ProblemClasses.FrictionProblems;
import Operation_Nightwatcher.Activity.ProblemClasses.GravitationEnergy;
import Operation_Nightwatcher.Activity.ProblemClasses.Integration;
import Operation_Nightwatcher.Activity.ProblemClasses.KineticEnergy;
import Operation_Nightwatcher.Activity.ProblemClasses.MagneticField;
import Operation_Nightwatcher.Activity.ProblemClasses.QuadraticEquation;
import Operation_Nightwatcher.Activity.ProblemClasses.WorkDoneQuestions;

class AbstractQuestionsTest {


    @org.junit.jupiter.api.Test
    void getQuestion() {

//        EquationsofMotion que = new EquationsofMotion();
//        System.out.println(que.getQuestion());
//        System.out.println(que.getAnswer());
//
//        KineticEnergy ke = new KineticEnergy();
//        System.out.println(ke.getQuestion());
//        System.out.println(ke.getAnswer());
//
//        GravitationEnergy ge = new GravitationEnergy();
//        System.out.println(ge.getQuestion());
//        System.out.println(ge.getAnswer());
//
//        FrictionProblems fr = new FrictionProblems();
//        System.out.println(fr.getQuestion());
//        System.out.println(fr.getAnswer());
//
//        QuadraticEquation qe = new QuadraticEquation();
//        System.out.println(qe.getQuestion());
//        System.out.println(qe.getAnswer());
//
//        AccelerationQuestion aq = new AccelerationQuestion();
//
//        //needs to work on negative value generated for asnwer
//        System.out.println("************");
//        WorkDoneQuestions wd = new WorkDoneQuestions();
//        System.out.println(wd.getQuestion());
//        System.out.println(wd.getAnswer());
//
//        MagneticField mg = new MagneticField();
//        System.out.println(mg.getQuestion());
//        System.out.println(mg.getAnswer());
//
//        Integration ai = new Integration();
//        System.out.println(ai.getQuestion());
//        System.out.println(ai.getAnswer());

        AbstractQuestions ab = new AbstractQuestions();
        ab.generateQuestionChoice();
        System.out.println(ab.getQuestion());
        System.out.println(ab.getAnswer());

    }

    @org.junit.jupiter.api.Test
    void getAnswer() {
    }
}