package theendwork;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class TransferStations {
    
    public static String SUBWAY_FILE_PATH;
    
    public TransferStations(String filepath){
        TransferStations.SUBWAY_FILE_PATH=filepath;
    }
    
    public static void fillStationLinesMap(Map<String, Set<String>> stationLines) {
        try (BufferedReader reader = new BufferedReader(new FileReader(SUBWAY_FILE_PATH))) {
            String line;
            String currentLineName = null;//当前线路名
            Set<String> currentLineStations = new HashSet<>();//存站点名
            
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
                String stationName = tokenizer.nextToken(); // 获取站点名称
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
    public  void fillStationLinesMap1 (Map<String, Set<String>> stationLines) {
        try (BufferedReader reader = new BufferedReader(new FileReader(SUBWAY_FILE_PATH))) {
            String line;
            String currentLineName = null;//当前线路名
            Set<String> currentLineStations = new HashSet<>();//存站点名
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
                String stationName = tokenizer.nextToken(); // 获取站点名称
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
    public static Set<String> identifyTransferStations(Map<String, Set<String>> stationLines) {
        Set<String> transferStations = new HashSet<>();
        for (Map.Entry<String, Set<String>> entry : stationLines.entrySet()) {
            if (entry.getValue().size() > 0) {
                // 构造站点信息字符串，格式为 "<站点名称，线路1，线路2>"
                StringBuilder sb = new StringBuilder();
                sb.append("<").append(entry.getKey()).append("，");
                int i=0;
                for (String line : entry.getValue()) {
                    sb.append(line).append("、");
                    i++;//计数器
                }
                sb.deleteCharAt(sb.length() - 1).append(">");
                if(i>1){
                    transferStations.add(sb.toString());}  
            }
        }
        return transferStations;
    }


    public List<String> tsa(Map<String, Set<String>> stationLines){
        List<String> transferStations = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : stationLines.entrySet()) {
            if (entry.getValue().size() > 1) {
                // 构造站点信息字符串，格式为 "<站点名称，线路1，线路2>"
                
                transferStations.add(entry.getKey());
            }
        }
        return transferStations;
    }
    public List<String> gettransfer(){
        Map<String, Set<String>> stationLines = new HashMap<>();
        // 假设这是从文件中读取的站点线路数据
        fillStationLinesMap(stationLines);//填充数据
        tsa(stationLines);
        List<String> transferStations = tsa(stationLines);
        return transferStations;
    }

    public void print(){
        Map<String, Set<String>> stationLines = new HashMap<>();
        // 假设这是从文件中读取的站点线路数据
        fillStationLinesMap(stationLines);//填充数据
        tsa(stationLines);
        Set<String> transferStations = identifyTransferStations(stationLines);//算法实现
        System.out.println("地铁中转站信息：");
        System.out.println("中转站数量: " + transferStations.size());
        for (String station : transferStations){
            System.out.println(station);
        }
    }
}






