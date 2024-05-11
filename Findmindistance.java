package theendwork;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

public class Findmindistance {

    // 地铁线路的邻接列表表示
    static Map<String, Map<String, Map<String, Double>>> subwayGraph;
    public static String lineName=null;
    public Findmindistance() {
        subwayGraph = new HashMap<>();
        // 假设已经从文件中加载了地铁网络数据到 subwayGraph
    }

    // 加载地铁网络数据
    public void loadSubwayData(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 忽略空行
                if (line.trim().isEmpty()) {
                    continue;
                }
                // 解析每一行数据
                parseLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 实现数据加载逻辑
    }
    private void parseLine(String line) {
        // 按 "----" 和 "\t" 分割字符串以获取站点和距离
        String[] parts = line.trim().split("---—|\t");
        
        String stationA=null;
        String stationB=null;
        double distance=0;
        
        if(parts.length==1){
            lineName=parts[0].trim();
        }
        if(parts.length==2){
            stationA = parts[0].split("---|—")[0].trim(); 
            stationB = parts[0].split("---|—")[1].trim();
            distance = Double.parseDouble(parts[1].trim());
        }
        if (stationA!=null) {
            addStationAndDistance(lineName, stationA, stationB, distance);
        }
        
    }

    private void addStationAndDistance(String lineName, String stationA, String stationB, double distance) {
        // 如果线路尚未添加到 subwayGraph，则添加
        subwayGraph.computeIfAbsent(lineName, k -> new HashMap<>());
        Map<String, Map<String, Double>> stationsDistances = subwayGraph.get(lineName);

        // 为站点A添加站点B的距离
        stationsDistances.computeIfAbsent(stationA, k -> new HashMap<>()).put(stationB, distance);

        // 由于是无向图，也为站点B添加站点A的距离
        stationsDistances.computeIfAbsent(stationB, k -> new HashMap<>()).put(stationA, distance);
    }

    // 获取构建好的地铁图
    public Map<String, Map<String, Map<String, Double>>> getSubwayGraph() {
        return subwayGraph;
    }
    // public static void main(String[] args) {
    //     SubwaySearch loader = new SubwaySearch();
    //     loader.loadSubwayData("C:\\Users\\25431\\.vscode\\java\\theendwork\\subway.txt"); // 确保 subway.txt 位于项目的 src 目录下
    //     Collection<String> stations = loader.findStationsWithinDistance("武汉东站", 5);
    //     System.out.println(stations);
    //}
    public void printstation(String stationName, int n){
        Findmindistance loader = new Findmindistance();
        loader.loadSubwayData("C:\\Users\\25431\\.vscode\\java\\theendwork\\subway.txt"); // 确保 subway.txt 位于项目的 src 目录下
        Collection<String> stations = loader.findStationsWithinDistance(stationName, n);
        System.out.println("距离"+stationName+"小于"+n+"的站点集合："+"\n"+"\n"+stations);
    }
    //广度优先搜索，找出距离小于 n 的所有站点
    public Collection<String> findStationsWithinDistance(String stationName, int n) {//stationname是输入的站点进行查询
        Queue<String> queue = new LinkedList<>();
        Map<String, Double> distances = new HashMap<>();
        Set<String> visited = new HashSet<>();
        Collection<String> result = new ArrayList<>();
        //起点
        
        for(String s:getLineName(stationName)){
            
            queue.offer(stationName);
            visited.add(stationName);
            distances.put(stationName, (double) 0);
            while (!queue.isEmpty()) {                  //程序实现
            String currentStation = queue.poll();//当前站
            // 检查当前站点的距离是否已经超出 n
            if (distances.get(currentStation) > n) {
                continue;
            }

            // 获取当前站点的所有邻居站点
            Map<String, Double>neighbors = subwayGraph.get(s).get(currentStation);//调取邻接表的后面部分，需要重写这块的逻辑
            if (neighbors != null) {
                for (Entry<String, Double> neighbor : neighbors.entrySet()) {
                    String neighborStation = neighbor.getKey();//相邻站
                    double distanceToNeighbor = neighbor.getValue();//

                    if (!visited.contains(neighborStation)) {
                        // 计算到邻居站点的总距离
                        try {
                            double totalDistance = distances.get(currentStation)+distanceToNeighbor;
                        if (totalDistance <= n) {
                            queue.offer(neighborStation);//添加
                            distances.put(neighborStation, totalDistance);
                            visited.add(neighborStation);
                        }
                        } catch (Exception e) {
                            // TODO: handle exception
                        }
                        
                    }
                }
            }
        }
    
        //返回所有距离小于或等于 n 的站点的字符串描述
        

        for (String station : visited) {
            if (station==stationName) {
                continue;
            }
            if (distances.get(station) <= n) {
                String linename = s; // 假设这个方法可以获取站点所在的线路名称
                
                result.add("("+station + "，" + linename + "，" + String.format("%.3f", distances.get(station)) +"km"+")");
            }
        }
        visited.clear();
        
        }
    return result;
    }

    // 获取站点所在的线路名称
    public static Set<String> getLineName(String stationName) {
        Set<String> lines = subwayGraph.keySet();
        Set<String> stationLines = new HashSet<>();

        // 遍历所有线路
        for (String lineName : lines) {
            Map<String, Map<String, Double>> stations = subwayGraph.get(lineName);
            // 检查该站点是否在当前线路的站点列表中
            if (stations.containsKey(stationName)) {
                stationLines.add(lineName); // 如果是，则将线路名称添加到结果集中
            }
        }

        return stationLines; // 返回包含站点所在线路的集合
    }
       
        //需要填写逻辑返回线路名称
    
    

  
}
