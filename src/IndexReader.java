import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IndexReader {
  static List<String> lines = new ArrayList<>();
  public static List<String> read() {
    try (Stream<String> stream = Files.lines(Paths.get("D:\\index.txt"), Charset.defaultCharset())) {
      lines = stream.collect(Collectors.toList());
    } catch (Exception e) {
    }
    return lines;
  }

  public static void main(String[] args) throws Exception{
    read();
    List<String> distinctGoods = new ArrayList<>();
    for (String str : lines) {
      String goods = str.substring(0, str.indexOf("#"));
      System.out.println(goods);
      if (!distinctGoods.contains(goods))
        distinctGoods.add(goods);
    }

    int sum = 0, maxSum = 0;
    String expensiveGoods = "";
    Date date, maxDate = new Date(1, Calendar.JANUARY, 1);
    for (String goods : distinctGoods) {
      for (String str : lines) {
        if (goods.equals(str.substring(0, str.indexOf("#"))))
          sum += Integer.parseInt(str.substring(str.lastIndexOf("#")));
        if (maxSum < sum) {
          maxSum = sum;
          expensiveGoods = goods;

          }
      }
    }

    for (String str : lines) {
      if (expensiveGoods.equals(str.substring(0, str.indexOf("#")))) {
        date = new SimpleDateFormat("dd.MM.yyyy").parse(str.substring(str.indexOf("#"), str.lastIndexOf("#")));
        if (maxDate.getTime() < date.getTime())
          maxDate = date;
      }
    }
    System.out.println(expensiveGoods + "" + maxDate.getDate() + "." + maxDate.getMonth() + 1 + "." + maxDate.getYear()+ " " + maxSum);
  }
}
