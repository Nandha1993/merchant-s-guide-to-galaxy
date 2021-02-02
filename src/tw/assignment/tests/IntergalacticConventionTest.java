package tw.assignment.tests;

import org.junit.Assert;
import org.junit.Test;
import tw.assignment.application.IntegalacticConventionProcessor;

import java.util.ArrayList;
import java.util.List;

public class IntergalacticConventionTest {


    @Test
    public void validateInputNotesTest() {
        List<String> notes = new ArrayList();
        notes.add("glob is I");
        notes.add("prok is V");
        notes.add("pish is X");
        notes.add("tegj is L");
        notes.add("glob glob Silver is 34 Credits");
        notes.add("glob prok Gold is 57800 Credits");
        notes.add("pish pish Iron is 3910 Credits");
        IntegalacticConventionProcessor processor = new IntegalacticConventionProcessor();
        processor.processNotesForConvention(notes);
        Assert.assertEquals(processor.getCredits().size(),4);
        Assert.assertEquals(processor.getPreciousMetalCredits().size(), 3);
        Assert.assertEquals(processor.getCredits().get("glob"),new Double(1.0));
        Assert.assertEquals(processor.getCredits().get("prok"),new Double(5.0));
        Assert.assertEquals(processor.getCredits().get("pish"),new Double(10.0));
        Assert.assertEquals(processor.getCredits().get("tegj"),new Double(50.0));
        Assert.assertEquals(processor.getPreciousMetalCredits().get("Silver"),new Double(17.0));
        Assert.assertEquals(processor.getPreciousMetalCredits().get("Gold"),new Double(14450.0));
        Assert.assertEquals(processor.getPreciousMetalCredits().get("Iron"),new Double(195.5));
    }

    @Test
    public void intergalactictoRomanConventionTest() {
        List<String> notes = new ArrayList();
        notes.add("glob is I");
        notes.add("prok is V");
        notes.add("pish is X");
        notes.add("tegj is L");
        IntegalacticConventionProcessor processor = new IntegalacticConventionProcessor();
        processor.intergalactictoRomanConvention(notes);
        Assert.assertEquals(processor.getCredits().size(),4);
        Assert.assertEquals(processor.getCredits().get("glob"),new Double(1.0));
        Assert.assertEquals(processor.getCredits().get("prok"),new Double(5.0));
        Assert.assertEquals(processor.getCredits().get("pish"),new Double(10.0));
        Assert.assertEquals(processor.getCredits().get("tegj"),new Double(50.0));
    }

    @Test
    public void intergalactictoRomanConventionTestWithInvalidInput() {
        List<String> notes = new ArrayList();
        notes.add("glob is D");
        notes.add("prok is G");
        notes.add("pish is X");
        notes.add("tegj is F");
        IntegalacticConventionProcessor processor = new IntegalacticConventionProcessor();
        processor.intergalactictoRomanConvention(notes);
        Assert.assertEquals(processor.getCredits().size(),2);
        Assert.assertEquals(processor.getCredits().get("glob"),new Double(500.0));
        Assert.assertEquals(processor.getCredits().get("pish"),new Double(10.0));
    }

    @Test
    public void intergalacticConventionTest() {
        List<String> notes = new ArrayList();
        notes.add("glob is I");
        notes.add("prok is V");
        notes.add("pish is X");
        notes.add("tegj is L");
        notes.add("glob glob Silver is 34 Credits");
        notes.add("glob prok Gold is 57800 Credits");
        notes.add("pish pish Iron is 3910 Credits");
        IntegalacticConventionProcessor processor = new IntegalacticConventionProcessor();
        processor.processNotesForConvention(notes);
        processor.intergalactictoRomanConvention(notes);
        List<String> questions = new ArrayList();
        questions.add("how much is pish tegj glob glob ?");
        questions.add("how many Credits is glob prok Silver ?");
        questions.add("how many Credits is glob prok Gold ?");
        questions.add("how many Credits is glob prok Iron ?");
        questions.add("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
        processor.processConventions(questions);
        Assert.assertEquals(processor.getCredits().get("pish tegj glob glob "),new Double(42.0));
        Assert.assertEquals(processor.getCredits().get("glob prok Silver "),new Double(68.0));
        Assert.assertEquals(processor.getCredits().get("glob prok Gold "),new Double(57800.0));
        Assert.assertEquals(processor.getCredits().get("glob prok Iron "),new Double(782.0));
        Assert.assertEquals(processor.getCredits().get("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"),null);
    }

}
