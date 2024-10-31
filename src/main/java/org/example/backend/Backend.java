package org.example.backend;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class Backend {
    private static final String file = "data.txt";
    private final File f;

    public Backend() throws IOException {
        this.f = new File(file);
        if (!f.exists()) {
            if (f.getParentFile() != null && !f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            f.createNewFile();
        }
    }

    public void write(String line) {
        try {
            FileWriter fw = new FileWriter(f, true);
            fw.write(line + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public String read() {
        StringBuilder content = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }
}