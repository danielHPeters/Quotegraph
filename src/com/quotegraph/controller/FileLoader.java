package com.quotegraph.controller;

import com.quotegraph.model.DayQuote;

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
 * Loads the quotes data from a file.
 *
 * @author Daniel Peters
 * @version 1.2
 */
public class FileLoader extends AbstractDataLoader {

  /**
   * Default constructor. Sets the file name to default.
   *
   * @param source source file location
   */
  public FileLoader(String source) {
    super(source);
    this.failed = false;
    this.load();
  }

  /**
   * Load the data from the file and add each line to the list.
   */
  @Override
  public final void load() {
    try (BufferedReader br = new BufferedReader(new FileReader(this.source + ".csv"))) {
      List<DayQuote> list = new ArrayList<>();
      List<Double> ts = new ArrayList<>();
      SimpleDateFormat format = new SimpleDateFormat(this.defaultDateFormat);
      String s;

      while ((s = br.readLine()) != null) {
        String[] token = s.split(";");
        Date dat = format.parse(token[0]);
        double start = Double.parseDouble(token[1]);
        double high = Double.parseDouble(token[2]);
        double low = Double.parseDouble(token[3]);
        double close = Double.parseDouble(token[4]);

        list.add(new DayQuote(dat, start, high, low, close));
        ts.add(TimestampGenerator.dateToTimeStamp(dat));
      }

      this.data = list;
      this.minClose = Collections.min(list).getClose();
      this.maxClose = Collections.max(list).getClose();
      this.timeStamps = ts;
      this.minTimeStamp = Collections.min(ts);
      this.maxTimeStamp = Collections.max(ts);
    } catch (IOException ex) {
      System.out.println("Failed to open file: " + this.source + ".csv");
      this.failed = true;
    } catch (ParseException ex) {
      System.out.println("Failed to parse date. " + ex);
    }
  }
}
