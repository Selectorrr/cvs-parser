import com.google.common.base.Function;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.gson.Gson;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.List;

/**
 * Created by Selector on 23.02.2015.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Reader in = new FileReader(args[0]);
        Iterable<CSVRecord> records = CSVFormat.newFormat(',').parse(in);
        Gson gson = new Gson();

        Multimap<String, CSVRecord> data = ArrayListMultimap.create();
        for (CSVRecord record : records) {
            data.put(record.get(0), record);
        }
        String result = "";
        int i = 1;
        for (String key : data.keySet()) {
            Collection<CSVRecord> csvRecords = data.get(key);
            List<String> x = Lists.newArrayList(Iterables.transform(csvRecords, new Function<CSVRecord, String>() {
                @Override
                public String apply(CSVRecord strings) {
                    return strings.get(3);
                }
            }));
            List<String> one = Lists.newArrayList(Iterables.transform(csvRecords, new Function<CSVRecord, String>() {
                @Override
                public String apply(CSVRecord strings) {
                    return strings.get(1);
                }
            }));
            List<String> two = Lists.newArrayList(Iterables.transform(csvRecords, new Function<CSVRecord, String>() {
                @Override
                public String apply(CSVRecord strings) {
                    return strings.get(2);
                }
            }));
            Chart chart = new Chart(key, one, two, x);
            gson.toJson(chart);
            result = result + "window.chart" + i + " = \r\n" + gson.toJson(chart) + ";\r\n";
            i = i + 1;
        }
        File file = new File("data.js");
        FileUtils.writeStringToFile(file, result);
        System.out.println("file created: " + file.getAbsolutePath());
    }


    private static class Chart {
        public String name;
        public List<String> temperature;
        public List<String> wetness;
        public List<String> dateTime;

        public Chart(String name, List<String> temperature, List<String> wetness, List<String> dateTime) {
            this.name = name;
            this.temperature = temperature;
            this.wetness = wetness;
            this.dateTime = dateTime;
        }
    }
}
