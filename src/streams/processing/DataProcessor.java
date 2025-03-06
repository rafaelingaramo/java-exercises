package streams.processing;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class DataProcessor {
    public static void main(String[] args) throws IOException {
        new DataProcessor().process();
        new DataProcessor().parallelProcess();
    }

    private void process() throws IOException {
        long startTime = System.currentTimeMillis();
        System.out.println("Start time in milliseconds: " + startTime);

        StringBuilder stringBuilder = readFile();
        String[] lines = stringBuilder.toString().split("\n");

        List<StudentCourseGradeDto> studentCourseGradeDtoList = Arrays.stream(lines)
                .skip(1)
                .map(line -> line.split(","))
                .map(cols -> new StudentCourseGradeDto(cols[0], cols[1], Integer.valueOf(cols[2]), cols[3], cols[4], Boolean.valueOf(cols[5])))
                .toList();

        System.out.println(studentCourseGradeDtoList.size());
        long endTime = System.currentTimeMillis();
        System.out.println("Difference in seconds: " + (endTime - startTime) / 1000.0);
    }

    private void parallelProcess()  throws IOException {
        long startTime = System.currentTimeMillis();
        System.out.println("Start time in milliseconds: " + startTime);

        StringBuilder stringBuilder = readFile();
        String[] lines = stringBuilder.toString().split("\n");

        List<StudentCourseGradeDto> studentCourseGradeDtoList = Arrays.stream(lines)
                .parallel()
                .skip(1)
                .map(line -> line.split(","))
                .map(cols -> new StudentCourseGradeDto(cols[0], cols[1], Integer.valueOf(cols[2]), cols[3], cols[4], Boolean.valueOf(cols[5])))
                .toList();

        System.out.println(studentCourseGradeDtoList.size());
        long endTime = System.currentTimeMillis();
        System.out.println("Difference in seconds: " + (endTime - startTime) / 1000.0);
    }

    private static StringBuilder readFile() throws IOException {
        File file = new File("resources/students.csv");
        InputStreamReader isr = null;
        char[] buffer = new char[1024];
        StringBuilder stringBuilder = new StringBuilder();
        try {
            isr = new FileReader(file);
            while (isr.read(buffer) != -1) {
                stringBuilder.append(buffer);
            }
        } finally {
            if (isr != null)
                isr.close();
        }
        return stringBuilder;
    }
}
