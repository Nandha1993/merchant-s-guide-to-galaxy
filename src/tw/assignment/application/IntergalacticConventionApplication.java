package tw.assignment.application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class IntergalacticConventionApplication {
    public static void main(String args[]) throws IOException {
        //path to input file , right click the input file from resources and copy the path
        List<String> conventionInput = Files.lines(new File("/Users/I340241/IdeaProjects/Assignment/src/tw/assignment/resources/Input.txt").toPath())
                .map(s -> s.trim())
                .collect(Collectors.toList());
        IntegalacticConventionProcessor integalacticConventionProcessor = new IntegalacticConventionProcessor(conventionInput);
        integalacticConventionProcessor.doIntergalactingConvention();
    }
}
