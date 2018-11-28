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

    private static final String INPUT_TEST_FILE_PATH = "/goldencrown/testdata/integration_test_input_data.txt";
    private static final String EXPECTED_TEST_OUTPUT_TEST_FILE_PATH = "/goldencrown/testdata/expected_test_output_data.txt";

    @Test
    public void integrationTest() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(bos);
        System.setOut(out);

        InputStream inputStream = GoldenCrownIntegrationTest.class.getResourceAsStream(INPUT_TEST_FILE_PATH);
        System.setIn(inputStream);

        GoldenCrown.main(null);

        out.flush();

        String allOutput = new String(bos.toByteArray());

        assertEquals(Files.readAllLines(Paths.get(GoldenCrownIntegrationTest.class.getResource(EXPECTED_TEST_OUTPUT_TEST_FILE_PATH).getPath())),
                Arrays.asList(allOutput.split("\n")));
    }

}
