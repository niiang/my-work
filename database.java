import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class database {
    private static final String SUBWAY_FILE_PATH = "C:\\Users\\25431\\.vscode\\java\\the end work\\subway.txt";

    public static void fillStationLinesMap(Map<String, Set<String>> stationLines) {
        try (BufferedReader reader = new BufferedReader(new FileReader(SUBWAY_FILE_PATH))) {
            String line;
            String currentLineName = null;
            Set<String> currentLineStations = new HashSet<>();
            
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    // 空行，表示线路结束
                    if (!currentLineStations.isEmpty() && currentLineName != null) {
                        // 将线路名称和站点集合添加到映射中
                        for (String station : currentLineStations) {
                            stationLines.computeIfAbsent(station, k -> new HashSet<>()).add(currentLineName);
                        }
                    }
                    currentLineStations.clear();
                    currentLineName = null;
                    continue;
                }
                
                if (line.startsWith("站点名称")) {
                    // 忽略列标题行
                    continue;
                }
                
                StringTokenizer tokenizer = new StringTokenizer(line, "—-\t");
                String stationName = tokenizer.nextToken(); // 站点名称
                //String distance = tokenizer.nextToken(); // 间距（KM）
                
                // 如果是新线路的开始，保存之前的线路信息
                if (currentLineName != null && !currentLineName.equals("站点名称") && !stationName.equals("站点名称")) {
                    currentLineStations.add(stationName);
                } else {
                    // 线路名称通常在文件中每个线路的第二行出现
                    currentLineName = stationName;
                    // 将线路名称作为站点，以便在映射中标识线路
                    currentLineStations.add(currentLineName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
