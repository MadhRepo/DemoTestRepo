package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StockInfoReader {
    public static List<StockInfo> readFromCsv(String filePath) throws IOException, CsvValidationException {
        List<StockInfo> stockList = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] fields;
            reader.readNext(); // skip header
            while ((fields = reader.readNext()) != null) {
                if (fields.length >= 5) {
                    stockList.add(new StockInfo(fields[0], fields[1], fields[2], fields[3], fields[4]));
                }
            }
        }
        return stockList;
    }
}