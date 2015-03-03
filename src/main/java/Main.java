import com.google.common.base.Function;
import com.google.common.collect.*;
import com.google.gson.Gson;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.Comparator;
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
        ImmutableSortedSet<String> sortedKeys = ImmutableSortedSet.copyOf(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Ordering.natural().compare(o1, o2);
            }
        }, data.keySet());
        for (String key : sortedKeys) {
            Collection<CSVRecord> csvRecords = data.get(key);
            List<Double> temperature = Lists.newArrayList(Iterables.transform(csvRecords, new Function<CSVRecord, Double>() {
                @Override
                public Double apply(CSVRecord strings) {
                    return Double.valueOf(strings.get(1));
                }
            }));
            List<Double> wetness = Lists.newArrayList(Iterables.transform(csvRecords, new Function<CSVRecord, Double>() {
                @Override
                public Double apply(CSVRecord strings) {
                    return Double.valueOf(strings.get(2));
                }
            }));
            List<Long> time = Lists.newArrayList(Iterables.transform(csvRecords, new Function<CSVRecord, Long>() {
                @Override
                public Long apply(CSVRecord strings) {
                    return Long.valueOf(strings.get(3)) + 21600 * 1000;
                }
            }));
            charts.add(new Chart(key, temperature, wetness, time));
        }
        result = result + "window.charts = " + gson.toJson(charts) + ";\r\n";
        File file = new File("data.js");
//        result = result.replace("\"", "");
        FileUtils.writeStringToFile(file, result);
        System.out.println("file created: " + file.getAbsolutePath());
    }

    private static class Chart {
        public String name;
        public List<List<Number>> temperature;
        public List<List<Number>> wetness;

        public Chart(String name, List<Double> temperature, List<Double> wetness, List<Long> time) {
            this.name = name;
            this.temperature = Lists.newArrayList();
            this.wetness = Lists.newArrayList();
            for (int i = 0; i < time.size(); i++) {
                this.temperature.add(ImmutableList.<Number>of(time.get(i), temperature.get(i)));
                this.wetness.add(ImmutableList.<Number>of(time.get(i), wetness.get(i)));
            }
        }
    }
}
