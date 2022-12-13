package src.java.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Wrap the operation for data preprocessing.
 */
public class ResourceParser {
    private static final String SEPARATOR = "/";
    private final ArrayList<ArrayList<String>> content;
    private ArrayList<String> keys = null;

    public ResourceParser(String path) {
        this(new File(path));
    }

    public ResourceParser(File file) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);

            // Read the keys in title line with SEPARATOR
            String[] titleLine = scanner.nextLine().split(SEPARATOR);
            this.keys = new ArrayList<>(Arrays.asList(titleLine));
        } catch (FileNotFoundException e) {
            Logger.warning(e.getMessage());
        }

        // Read the content in following lines with exact same length with title
        this.content = new ArrayList<>();
        while (scanner != null && scanner.hasNextLine() && this.keys.size() > 0) {
            ArrayList<String> row = new ArrayList<>();
            for (String _k : this.keys) {
                if (!scanner.hasNext()) {
                    return;
                }
                row.add(scanner.next());
            }
            this.content.add(row);
        }
    }

    public int getRowCount() {
        return this.content.size();
    }

    public String getRowValue(int row, String key) {
        if (this.keys.contains(key) && row < this.content.size()) {
            int key_i = this.keys.indexOf(key);
            return this.content.get(row).get(key_i);
        }
        throw new RuntimeException("No such key or resource.");
    }
}
