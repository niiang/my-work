package theendwork;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SubwayPathFinder {
    private Map<String, List<String>> subwayGraph; // 邻接列表形式的地铁图
    public SubwayPathFinder(){
        
    }
    public SubwayPathFinder(Map<String, Map<String, Map<String, Double>>> subwayGraph) {
        // 将原始的 subwayGraph 转换为邻接列表形式的图
        this.subwayGraph = new HashMap<>();
        for (Map.Entry<String, Map<String, Map<String, Double>>> lineEntry : subwayGraph.entrySet()) {
            //String lineName = lineEntry.getKey();
            for (Map.Entry<String, Map<String, Double>> stationEntry : lineEntry.getValue().entrySet()) {
                String station = stationEntry.getKey();
                Map<String, Double> distances = stationEntry.getValue();
                for (Map.Entry<String, Double> distanceEntry : distances.entrySet()) {
                    String neighbor = distanceEntry.getKey();
                    //Double neighbordistance=distanceEntry.getValue();
                    // 由于地铁图是无向的，我们添加双向连接
                    this.subwayGraph.computeIfAbsent(station, k -> new ArrayList<>()).add(neighbor);

                    //this.subwayGraph.computeIfAbsent(neighbor, k -> new ArrayList<>()).add(station);
                }
            }
        }
    }

    // 使用 DFS 找到所有路径
    public List<List<String>> findAllPaths(String start, String end) {
        List<List<String>> allPaths = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        dfs(start, end, visited, new ArrayList<>(), allPaths);
        return allPaths;
    }

    private void dfs(String currentStation, String end, Set<String> visited, List<String> currentPath, List<List<String>> allPaths) {
        visited.add(currentStation); // 标记当前站点为已访问
        currentPath.add(currentStation); // 将当前站点添加到路径中

        if (currentStation.equals(end)) { // 如果到达终点，复制当前路径并添加到所有路径的集合中
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            // 遍历当前站点的所有邻居站点
            List<String> neighbors = subwayGraph.get(currentStation);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor)) { // 如果邻居站点不在当前路径中，继续搜索
                        dfs(neighbor, end, visited, currentPath, allPaths);
                    }
                }
            }
        }

        visited.remove(currentStation); // 回溯，移除当前站点
        currentPath.remove(currentPath.size() - 1); // 回溯，移除当前路径的最后一个站点
    }
    
    // 测试类
    public void getpath(String start,String end){
        Findmindistance loader = new Findmindistance();
        loader.loadSubwayData("C:\\Users\\25431\\.vscode\\java\\theendwork\\subway.txt");
        Map<String, Map<String, Map<String, Double>>> subwayGraphData = Findmindistance.getSubwayGraph(); // 实际的地铁网络数据
        SubwayPathFinder finder = new SubwayPathFinder(subwayGraphData);
        Integer i=0;
        List<List<String>> paths = finder.findAllPaths(start, end);
        for (List<String> path : paths) {
            i++;
            System.out.println(path);
        }
        System.out.println(start+"到"+end+"的总线路有:"+i+"条");
    }

    public static void main(String[] args) {
        // 假设 subwayGraph 已经根据之前的指导加载了地铁网络数据
        Findmindistance loader = new Findmindistance();
        loader.loadSubwayData("C:\\Users\\25431\\.vscode\\java\\theendwork\\subway.txt");
        Map<String, Map<String, Map<String, Double>>> subwayGraphData = Findmindistance.getSubwayGraph(); // 实际的地铁网络数据

        SubwayPathFinder finder = new SubwayPathFinder(subwayGraphData);
        String start = "华中科技大学"; // 起点站
        String end = "武汉火车站"; // 终点站
        Integer i=0;
        List<List<String>> paths = finder.findAllPaths(start, end);
        for (List<String> path : paths) {
            i++;
            System.out.println(path);
        }
        System.out.println(start+"到"+end+"的总线路有:"+i+"条");
    }
    
}
