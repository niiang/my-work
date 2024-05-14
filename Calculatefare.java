package theendwork;

import java.util.List;
import java.util.Map;

public class Calculatefare {
    Subwayshortpath path;
    List <String> shortpath;
    double d;
    public void load(){//装载
        Findmindistance loader = new Findmindistance();
        loader.loadSubwayData("C:\\Users\\25431\\.vscode\\java\\theendwork\\subway.txt");
        Map<String, Map<String, Map<String, Double>>> subwayGraphData = Findmindistance.getSubwayGraph(); // 实际的地铁网络数据
        Subwayshortpath path = new Subwayshortpath(subwayGraphData);
        this.path=path;
    }
    public void print(String start,String end){//获取最短路径
        load();
        shortpath=path.getshortpath(start,end);
        this.d=path.gettotal();
    }
    public double calculateFare(double distance) {
        double fare = 0.0;
        if (distance <= 4) {
            fare = 2;
        } else if (distance <= 8) {
            fare = 3;
        } 
        else if (distance<=12){
            fare=4;
        }
        else if(distance<=18){
            fare=5;
        }
        else if (distance <= 24) {
            fare = 6 ;
        } else if (distance <= 32) {
            fare = 7 ;
        } 
        else if(distance<=40){
            fare=8;
        }
        else if (distance <= 50) {
            fare = 9;
        } 
        else {
            fare = 10 + (Math.ceil((distance - 50) / 20));
        }
        return fare;
    }
    public void getfare(String start,String end){
        Calculatefare s= new Calculatefare();
        s.print(start,end);
        int fare=(int)s.calculateFare(s.d);
        System.out.println("从"+start+"到"+end+"普通单程票价："+fare+"元");
    } 
    
    public void withdiscount(String start,String end,String discount){
        Calculatefare s= new Calculatefare();
        s.print(start,end);
        int fare=(int)s.calculateFare(s.d);
        //System.out.println("从"+start+"到"+end+"票价："+fare+"元");
        if(discount.equals("武汉通")){
            System.out.println("使用"+discount+"后，从"+start+"到"+end+"票价："+(double)(fare*0.9)+"元");
        }
        if(discount.equals("日票")){
            System.out.println(-1);
        }
    }


    // public static void main(String[] args) {
    //     Calculatefare s= new Calculatefare();
    //     s.print("武汉火车站","华中科技大学");
    //     int fare=(int)s.calculateFare(s.d);
    //     System.out.println(fare+"元");
    // }
}
