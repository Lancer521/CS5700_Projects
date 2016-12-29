package Output;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ty on 12/5/2016.
 * Used for logging the time duration and count of each algorithm called
 */
public class Log {

  private static Log instance;
  private Map<String, Integer> count;
  private Map<String, Long> time;

  private Log() {
    count = new HashMap<>();
    time = new HashMap<>();
  }

  public static Log getInstance() {
    if (instance == null) {
      instance = new Log();
    }
    return instance;
  }

  public void logData(String algorithmName, long timeElapsed) {
    if (!count.containsKey(algorithmName)) {
      count.put(algorithmName, 0);
    } else {
      count.put(algorithmName, count.get(algorithmName) + 1);
    }

    if (!time.containsKey(algorithmName)) {
      time.put(algorithmName, timeElapsed);
    } else {
      time.put(algorithmName, time.get(algorithmName) + timeElapsed);
    }
  }

  public void writeToFile(){
    writeToFile("src/Output/log.txt");
  }

  public void writeToFile(String filePath) {
    try {
      PrintWriter writer = new PrintWriter(filePath);

      for(String key : count.keySet()){
        writer.println(key + ": " + count.get(key));
      }

      writer.println();

      long totalTime = 0;
      for(String key : time.keySet()){
        writer.println(key + ": " + time.get(key));
        totalTime += time.get(key);
      }
      writer.println();
      writer.println("Total time: " + totalTime);

      writer.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

//  public int getCount(String algorithmName) {
//    /*if (count.get(algorithmName)) {
//
//    }*/
//  }
}
