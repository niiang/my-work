package theendwork;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


public class Subwayshortpath {
    private Map<String, Map<String,Double>> subwayGraph; 
    static double total;
    public static String SUBWAY_FILE_PATH;
    public Subwayshortpath(String filepath){
        Subwayshortpath.SUBWAY_FILE_PATH=filepath;
    }
    public double gettotal(){
        return total;
    }
    public Subwayshortpath(Map<String, Map<String, Map<String, Double>>> subwayGraph) {
        // 将原始的 subwayGraph 转换为邻接列表形式的图
        this.subwayGraph = new HashMap<>();
        for (Map.Entry<String, Map<String, Map<String, Double>>> lineEntry : subwayGraph.entrySet()) {
            //String lineName = lineEntry.getKey();
            for (Map.Entry<String, Map<String, Double>> stationEntry : lineEntry.getValue().entrySet()) {
                String station = stationEntry.getKey();
                Map<String, Double> distances = stationEntry.getValue();
                for (Map.Entry<String, Double> distanceEntry : distances.entrySet()) {
                    String neighbor = distanceEntry.getKey();
                    Double neighbordistance=distanceEntry.getValue();
                    // 由于地铁图是无向的，我们添加双向连接
                    this.subwayGraph.computeIfAbsent(station, k -> new HashMap<>()).put(neighbor,neighbordistance);

                    //this.subwayGraph.computeIfAbsent(neighbor, k -> new ArrayList<>()).add(station);
                }
            }
        }
    }

    public List<String> getShortestPath(String start, String end) {
        Map<String, Double> distances = new HashMap<>();
        Map<String, String> previousStations = new HashMap<>();
        PriorityQueue<Map.Entry<String, Double>> minHeap = new PriorityQueue<>((a, b) -> Double.compare(a.getValue(), b.getValue()));
        distances.put(start, 0.0);
        minHeap.offer(new AbstractMap.SimpleEntry<>(start, 0.0));

        while (!minHeap.isEmpty()) {
            Map.Entry<String, Double> current = minHeap.poll();
            String currentStation = current.getKey();
            Double distanceToCurrent = current.getValue();

            if (currentStation.equals(end)) {
                return reconstructPath(previousStations, end);
            }

            if (subwayGraph.containsKey(currentStation)) {
                for (Map.Entry<String, Double> adjacent : subwayGraph.get(currentStation).entrySet()) {
                    String station = adjacent.getKey();
                    double distanceToAdjacent = distanceToCurrent + adjacent.getValue();
                    total=distanceToAdjacent;
                    if (!distances.containsKey(station) || distanceToAdjacent < distances.get(station)) {
                        distances.put(station, distanceToAdjacent);
                        previousStations.put(station, currentStation);
                        minHeap.offer(new AbstractMap.SimpleEntry<>(station, distanceToAdjacent));
                    }
                }
            }
        }

        return Collections.emptyList(); // 如果没有路径，则返回空列表
    }

    private List<String> reconstructPath(Map<String, String> previousStations, String end) {
        List<String> path = new ArrayList<>();
        for (String station = end; station != null; station = previousStations.get(station)) {
            path.add(0, station);
        }
        return path;
    }
    
    public List<String> getshortpath(String start,String end,String filepath){
        SUBWAY_FILE_PATH=filepath;
        Findmindistance loader = new Findmindistance(SUBWAY_FILE_PATH);
        loader.loadSubwayData(SUBWAY_FILE_PATH);
        Map<String, Map<String, Map<String, Double>>> subwayGraphData = Findmindistance.getSubwayGraph(); // 实际的地铁网络数据
        Subwayshortpath path = new Subwayshortpath(subwayGraphData);
        List<String> shortestPath = path.getShortestPath(start, end);
        return shortestPath;
    }

    public void printshortpath(String start,String end){
        Findmindistance loader = new Findmindistance(SUBWAY_FILE_PATH);
        loader.loadSubwayData(SUBWAY_FILE_PATH);
        Map<String, Map<String, Map<String, Double>>> subwayGraphData = Findmindistance.getSubwayGraph(); // 实际的地铁网络数据
        Subwayshortpath path = new Subwayshortpath(subwayGraphData);
        if(path.subwayGraph.containsKey(start)&&path.subwayGraph.containsKey(end)){
        List<String> shortestPath = path.getShortestPath(start, end);
        System.out.println("从 " + start + " 到 " + end + " 的最短路径是: " + shortestPath);
    }
    else{
        System.out.println("输入有误");
    }

    }
}
    
