package boersendiagramm.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Loads the quotes data from a file
 *
 * @author d.peters
 */
public class FileLoader implements DataLoader {

    /**
     * The file containing quotes to be opened
     */
    private String fileName;

    /**
     *
     */
    private List<DayQuote> data;

    /**
     *
     */
    private List<Double> timeStamps;

    /**
     *
     */
    private double minClose;

    /**
     *
     */
    private double maxClose;

    /**
     *
     */
    private double minTimeStamp;

    /**
     *
     */
    private double maxTimeStamp;

    /**
     *
     */
    private boolean failed;

    /**
     * Default constructor. Sets the fileName to default
     */
    public FileLoader() {
        this.failed = false;
        this.fileName = "blackrock.csv";
        this.load();
    }

    /**
     *
     */
    @Override
    public final void load() {

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            double start, hoch, tief, schluss;
            List<DayQuote> list;
            List<Double> ts;
            String s;
            Date dat;
            SimpleDateFormat format;

            format = new SimpleDateFormat("dd.MM.yyyy");
            list = new ArrayList<>();
            ts = new ArrayList<>();

            while ((s = br.readLine()) != null) {

                String[] token = s.split(";");

                dat = format.parse(token[0]);
                start = Double.parseDouble(token[1]);
                hoch = Double.parseDouble(token[2]);
                tief = Double.parseDouble(token[3]);
                schluss = Double.parseDouble(token[4]);
                list.add(new DayQuote(dat, start, hoch, tief, schluss));
                ts.add(TimestampGenerator.dateToTimeStamp(dat));
            }

            this.data = list;
            this.minClose = Collections.min(list).getClose();
            this.maxClose = Collections.max(list).getClose();
            this.timeStamps = ts;
            this.minTimeStamp = Collections.min(ts);
            this.maxTimeStamp = Collections.max(ts);

        } catch (IOException ex) {
            System.out.println("Failed to open file: " + this.fileName);
            this.failed = true;
        } catch (ParseException ex) {
            System.out.println("Failed to parse date. " + ex);
        }

    }

    /**
     *
     * @param source
     */
    @Override
    public void setSource(String source) {
        this.fileName = source + ".csv";
    }

    /**
     *
     * @return
     */
    @Override
    public List<DayQuote> getData() {
        return this.data;
    }

    /**
     *
     * @return
     */
    @Override
    public double getMinClose() {
        return this.minClose;
    }

    /**
     *
     * @return
     */
    @Override
    public double getMaxClose() {
        return this.maxClose;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Double> getTimeStamps() {
        return this.timeStamps;
    }

    /**
     *
     * @return
     */
    @Override
    public double getMinTimeStamp() {
        return this.minTimeStamp;
    }

    /**
     *
     * @return
     */
    @Override
    public double getMaxTimeStamp() {
        return this.maxTimeStamp;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean hasFailed() {
        return this.failed;
    }

}
