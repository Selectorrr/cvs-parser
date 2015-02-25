import com.google.common.base.Function;
import com.google.common.collect.*;
import com.google.gson.Gson;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalTime;
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
        List<Chart> charts = Lists.newArrayList();
        for (String key : data.keySet()) {
            Collection<CSVRecord> csvRecords = data.get(key);
            List<List<Long>> temperature = Lists.newArrayList(Iterables.transform(csvRecords, new Function<CSVRecord, List<Long>>() {
                @Override
                public List<Long> apply(CSVRecord strings) {
                    return ImmutableList.of(Long.valueOf(strings.get(3)), Long.valueOf(strings.get(1)));
                }
            }));
            charts.add(new Chart(key, "Температура", temperature));
            List<List<Long>> wetness = Lists.newArrayList(Iterables.transform(csvRecords, new Function<CSVRecord, List<Long>>() {
                @Override
                public List<Long> apply(CSVRecord strings) {
                    return ImmutableList.of(Long.valueOf(strings.get(3)), Long.valueOf(strings.get(2)));
                }
            }));
            charts.add(new Chart(key, "Влажность", wetness));
        }
        result = result + "window.charts = " + gson.toJson(charts) + ";\r\n";
        File file = new File("data.js");
//        result = result.replace("\"", "");
        FileUtils.writeStringToFile(file, result);
        System.out.println("file created: " + file.getAbsolutePath());
    }

    private static class Chart {
        public String name;
        public String yLabel;
        public List<List<Long>> data;

        public Chart(String name, String yLabel, List<List<Long>> data) {
            this.name = name;
            this.yLabel = yLabel;
            this.data = data;
        }
    }
}
