package src.java.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Wrap the config preprocessing.
 */
public class ConfigParser {
    private static final String COMMENT_CHR = "#";
    private static final String SEPARATOR = "=";
    private static final String DEFAULT_PATH = "./config";
    private final ArrayList<String> keys;
    private final ArrayList<String> values;

    public ConfigParser() {
        this(DEFAULT_PATH);
    }

    public ConfigParser(String path) {
        this(new File(path));
    }

    public ConfigParser(File file) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            Logger.warning(e.getMessage());
        }

        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
        while (scanner != null && scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(COMMENT_CHR)[0].split(SEPARATOR);
            if (line.length >= 2) {
                keys.add(line[0].trim());
                values.add(line[1].trim());
            }
        }
    }

    /**
     * @param key to find the corresponding value.
     * @return the corresponding value.
     */
    public String getValue(String key) {
        for (int i = 0; i < this.keys.size(); i++) {
            if (this.keys.get(i).equals(key)) {
                return this.values.get(i);
            }
        }
        return "";
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < this.keys.size(); i++) {
            s.append(this.keys.get(i)).append(" = ").append(this.values.get(i)).append('\n');
        }
        return s.toString();
    }
}
