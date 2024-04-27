import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SubwayTransferStations extends database  {
    //private <identifyTransferStations>;
    public static void main(String[] args) {
        Map<String, Set<String>> stationLines = new HashMap<>();
        
        // 假设这是从文件中读取的站点线路数据
        fillStationLinesMap(stationLines);

        Set<String> transferStations = identifyTransferStations(stationLines);
        System.out.println("地铁中转站信息：");
        
        System.out.println("中转站数量: " + transferStations.size());
        for (String station : transferStations){
            System.out.println(station);
        }
            
    }     
    
    // 填充方法，根据实际情况实现
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
                    i++;
                }
                sb.deleteCharAt(sb.length() - 1).append(">");
                if(i>1){
                    transferStations.add(sb.toString());}
                    
            }
        }
        return transferStations;
    }
}
