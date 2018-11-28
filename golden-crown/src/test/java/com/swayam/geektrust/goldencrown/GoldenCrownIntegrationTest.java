package com.swayam.geektrust.goldencrown;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.Test;

public class GoldenCrownIntegrationTest {

    private static final String INPUT_TEST_FILE_PATH = "/goldencrown/testdata/integration_test_input_data";
    private static final String EXPECTED_TEST_OUTPUT_TEST_FILE_PATH = "/goldencrown/testdata/expected_test_output_data";

    @Test
    public void integrationTest_1() throws IOException {

        for (int i = 1; i <= 4; i++) {
            String inputFilePath = INPUT_TEST_FILE_PATH + "_" + i + ".txt";
            String expectedOutputFilePath = EXPECTED_TEST_OUTPUT_TEST_FILE_PATH + "_" + i + ".txt";

            testAgainstGivenInput(inputFilePath, expectedOutputFilePath);
        }
    }

    private void testAgainstGivenInput(String inputFilePath, String expectedOutputFilePath) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(bos);
        System.setOut(out);

        InputStream inputStream = GoldenCrownIntegrationTest.class.getResourceAsStream(inputFilePath);
        System.setIn(inputStream);

        GoldenCrown.main(null);

        out.flush();

        String allOutput = new String(bos.toByteArray());

        assertEquals(Files.readAllLines(Paths.get(GoldenCrownIntegrationTest.class.getResource(expectedOutputFilePath).getPath())),
                Arrays.asList(allOutput.split("\n")));
    }

}
