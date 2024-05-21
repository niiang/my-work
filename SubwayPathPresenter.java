package theendwork;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SubwayPathPresenter {
    TransferStations transferStations;
    Subwayshortpath path;
    List <String> shortpath;
    List <String> transfer;
    Map<String, Set<String>> getLines;//获取站点线路
    public static String SUBWAY_FILE_PATH;
    public SubwayPathPresenter(String path){
        SubwayPathPresenter.SUBWAY_FILE_PATH=path;
    }
    public  void load(){//装在载
        Findmindistance loader = new Findmindistance(SUBWAY_FILE_PATH);
        loader.loadSubwayData(SUBWAY_FILE_PATH);
        Map<String, Map<String, Map<String, Double>>> subwayGraphData = Findmindistance.getSubwayGraph(); // 实际的地铁网络数据
        Subwayshortpath path = new Subwayshortpath(subwayGraphData);
        this.path=path;
    }
    public void print(String start,String end){//获取最短路径
        load();
        shortpath=path.getshortpath(start,end,SUBWAY_FILE_PATH);
    }


    public void loadtransfer(){
        TransferStations transferStations=new TransferStations(SUBWAY_FILE_PATH);
        // 假设这是从文件中读取的站点线路数据
        Map<String, Set<String>> stationLines = new HashMap<>();
        // 假设这是从文件中读取的站点线路数据
        transferStations.fillStationLinesMap1(stationLines);//填充数据
        transferStations.tsa(stationLines);
        this.transferStations=transferStations;
        this.getLines=stationLines;
    }
    public void gettransfer(){
        loadtransfer();
        transfer=transferStations.gettransfer();
    }
    
    
    public void geteasyline(String start,String end) {
        SubwayPathPresenter s= new SubwayPathPresenter(SUBWAY_FILE_PATH);
        s.print(start,end);
        s.gettransfer();
        List <String>trans=new ArrayList<>();
        List <String>line=new ArrayList<>();
        HashSet<String> startline=new HashSet<>();
        Set<String> transline=new HashSet<>();
        Set<String> endline=new HashSet<>();
        Integer j=0;
        for(String t:s.transfer){
            if(s.shortpath.contains(t)){
                trans.add(t);
                j++;
            }
        }
        startline.addAll(s.getLines.get(s.shortpath.get(0)));
        endline.addAll(s.getLines.get(s.shortpath.get(s.shortpath.size()-1)));
        if(j==0){
            transline.addAll(startline);
            transline.retainAll(endline);
            line.add(transline.toString());
            System.out.println("从"+s.shortpath.get(0)+"到"+s.shortpath.get(s.shortpath.size()-1)+"坐"+line.get(0).toString().split("\\[|\\]")[1]);
        }
        if(j==1){
            transline.addAll(s.getLines.get(trans.get(0)));
            transline.retainAll(startline);
            line.add(transline.toString());
            transline.clear();
            transline.addAll(s.getLines.get(trans.get(0)));
            transline.retainAll(endline);
            line.add(transline.toString());
            transline.clear();
            System.out.println("从"+s.shortpath.get(0)+"到"+trans.get(0)+"坐"+line.get(0).toString().split("\\[|\\]")[1]);
            System.out.println("从"+trans.get(0)+"到"+s.shortpath.get(s.shortpath.size()-1)+"坐"+line.get(1).toString().split("\\[|\\]")[1]);
        }
        if(j==2){
            transline.addAll(s.getLines.get(trans.get(0)));
            transline.retainAll(startline);
            line.add(transline.toString());
            transline.clear();
            transline.addAll(s.getLines.get(trans.get(1)));
            transline.retainAll(s.getLines.get(trans.get(0)));
            line.add(transline.toString());
            transline.clear();
            transline.addAll(s.getLines.get(trans.get(1)));
            transline.retainAll(endline);
            line.add(transline.toString());
            transline.clear();
            System.out.println("从"+s.shortpath.get(0)+"到"+trans.get(0)+"坐"+line.get(0).toString().split("\\[|\\]")[1]);
            System.out.println("从"+trans.get(0)+"到"+trans.get(1)+"坐"+line.get(1).toString().split("\\[|\\]")[1]);
            System.out.println("从"+trans.get(1)+"到"+s.shortpath.get(s.shortpath.size()-1)+"坐"+line.get(2).toString().split("\\[|\\]")[1]);
        }
        if (j==3) {
            transline.addAll(s.getLines.get(trans.get(0)));
            transline.retainAll(startline);
            line.add(transline.toString());
            transline.clear();
            transline.addAll(s.getLines.get(trans.get(0)));
            transline.retainAll(s.getLines.get(trans.get(1)));
            line.add(transline.toString());
            transline.clear();
            transline.addAll(s.getLines.get(trans.get(1)));
            transline.retainAll(s.getLines.get(trans.get(2)));
            line.add(transline.toString());
            transline.clear();
            transline.addAll(s.getLines.get(trans.get(2)));
            transline.retainAll(endline);
            line.add(transline.toString());
            transline.clear();
            System.out.println("从"+s.shortpath.get(0)+"到"+trans.get(0)+"坐"+line.get(0).toString().split("\\[|\\]")[1]);
            System.out.println("从"+trans.get(0)+"到"+trans.get(1)+"坐"+line.get(1).toString().split("\\[|\\]")[1]);
            System.out.println("从"+trans.get(1)+"到"+trans.get(2)+"坐"+line.get(2).toString().split("\\[|\\]")[1]);
            System.out.println("从"+trans.get(2)+"到"+s.shortpath.get(s.shortpath.size()-1)+"坐"+line.get(3).toString().split("\\[|\\]")[1]);
        }
}
}

