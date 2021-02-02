package tw.assignment.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IntegalacticConventionProcessor {

    public List<String> conventionInput;
    // Map that has credits of intergalactic units like glob, prok... (
    public Map<String,Double> credits = new HashMap<>();
    // Map that has credits of metal like silver, gold , iron
    public Map<String,Double> preciousMetalCredits = new HashMap<>();

    public IntegalacticConventionProcessor(List<String> conventionInput) {
        this.conventionInput = conventionInput;
    }

    public IntegalacticConventionProcessor() {
    }


    /**
     * Convention of intergalactic roman to credits takes place here
     * splitted into 2 steps
     * splitted the input data into 2 parts - notes and questions
     * notes has given criteria
     * step 1 : processing the input data of notes and deriving the credits of each pf intergalactic units
     * step 2 : convention of intergalactic roman to intergalactic credits for the given questions input
     */
    public void doIntergalactingConvention() {
        List<String> notes = conventionInput
                .stream()
                .filter(s -> !s.isEmpty() && !s.contains("?") && !s.contains("How many") && !s.contains("How much"))
                .collect(Collectors.toList());
        List<String> questions = conventionInput
                .stream()
                .filter(s -> !s.isEmpty() && s.contains("?") || s.contains("How many") || s.contains("How much"))
                .collect(Collectors.toList());
        // text detailing your notes on the convention between intergalactic units and roman numerals
        processNotesForConvention(notes);
        // process convention based on the notes
        processConventions(questions);
    }

    /**
     *
     * @param notes
     * This method process the input notes for calculating the total credits of the questions
     * It calculates the Per unit Rare metal value transaction eg:- glob glob Silver is 34 Credits
     */
    public void processNotesForConvention(List<String> notes) {
        intergalactictoRomanConvention(notes);
        notes.stream().filter(note -> note.contains("Credits")).forEach(note -> {
            String romanString[] = note.substring(0, note.indexOf(" is")).split(" ");
            int creditValue =  Integer.parseInt(note.replaceAll("\\D+",""));
            int sum=0, factor =0;
            String preciousMetal = null;
            double preciousMetalCredit;
            for (int i = 0; i < romanString.length; i++) {
                if (credits.containsKey(romanString[i])) {
                    sum += credits.get(romanString[i]);
                    if(i != 0 && credits.get(romanString[i]) > credits.get(romanString[i-1])){
                        factor += credits.get(romanString[i-1]);
                    }
                }
                else {
                    preciousMetal = romanString[i];
                }
            }
            sum = sum - (2*factor);
            preciousMetalCredit = ((double)creditValue/sum);
            preciousMetalCredits.put(preciousMetal,preciousMetalCredit);
        });
    }

    /**
     *
     * @param notes
     * This method puts intergalactic unit and its credits in the map from the input notes
     */
    public void intergalactictoRomanConvention(List<String> notes) {
        notes.stream().filter(note -> !note.contains("Credits")).forEach(note -> {
            if(note.contains("I")) {
                credits.put(note.substring(0,note.indexOf(' ')),(double)1);
            }
            else if(note.contains("V")) {
                credits.put(note.substring(0,note.indexOf(' ')),(double)5);
            }
            else if(note.contains("X")) {
                credits.put(note.substring(0,note.indexOf(' ')),(double)10);
            }
            else if(note.contains("L")) {
                credits.put(note.substring(0,note.indexOf(' ')),(double)50);
            }
            else if(note.contains("C")) {
                credits.put(note.substring(0,note.indexOf(' ')),(double)100);
            }
            else if(note.contains("D")) {
                credits.put(note.substring(0,note.indexOf(' ')),(double)500);
            }
            else if(note.contains("M")) {
                credits.put(note.substring(0,note.indexOf(' ')),(double)1000);
            }
        });
    }

    /**
     *
     * @param questions
     * This method gives the result, intergalactic credits
     *  The logic used for convention is stated with example below :
     *  49 -> XLIX (roman)
     *  sum up XLIX = 10+50+1+10 = 71, here 50(L)>10(X) && 10(X)>1(I) => 10+1=11 , factor =11
     *  romanToDigit = (71 - 2*factor ) = 49
     *  example 2 : LXIV = 50+10+1+5 = 66, here 5(V)>1(I) => 1, factor = 1
     *  romanToDigit = (66 - 2*factor ) = 64
     */
    public void processConventions(List<String> questions) {
        questions.stream().forEach(question -> {
            double sum = 0, specialCredit = 1, factor= 0;
            String intergalacticString = question.substring(question.indexOf("is")+3, question.indexOf('?'));
            String romanString[] = intergalacticString.split(" ");
            for (int i = 0; i < romanString.length; i++) {
                if (credits.containsKey(romanString[i])) {
                    sum += credits.get(romanString[i]);
                    if(i != 0 && credits.get(romanString[i]) > credits.get(romanString[i-1])){
                        factor += credits.get(romanString[i-1]);
                    }
                } else if(preciousMetalCredits.containsKey(romanString[i])) {
                    specialCredit = preciousMetalCredits.get(romanString[i]);
                }
            }
            sum = sum-2*factor;
            if(sum == 0) {
                System.out.println("I have no idea what you are talking about");
            }else {
                credits.put(intergalacticString,sum*specialCredit);
                System.out.println(intergalacticString+"is "+ credits.get(intergalacticString)+" Credits");
            }

        });
    }

    public Map<String, Double> getCredits() {
        return credits;
    }

    public Map<String, Double> getPreciousMetalCredits() {
        return preciousMetalCredits;
    }

}
