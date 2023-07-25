package net.gsimken.bgameslibrary.utils;

import net.fabricmc.loader.api.FabricLoader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVwriter {
    public static void updateCSV(String fileName,String column, String value) {
        File gameDir = FabricLoader.getInstance().getGameDir().toFile();
        File dataDir = new File(gameDir, "test-data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        File file = new File(dataDir, fileName);
        CSVPrinter csvPrinter = null;
        try {
            CSVFormat format = CSVFormat.DEFAULT;
            if (!file.exists()) {
                csvPrinter = new CSVPrinter(new FileWriter(file), format.withHeader(column));
            } else {
                csvPrinter = new CSVPrinter(new FileWriter(file, true), format);

            }

            csvPrinter.printRecord(value);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (csvPrinter != null) {
                try {
                    csvPrinter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
