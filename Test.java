package theendwork;

public class Test extends TransferStations  {
    public static void main(String[] args) {
        //TransferStations transferStations=new TransferStations();
        //transferStations.print();
        //以上是实验一
        String start="华中科技大学";
        String end="湖北大学";
        //下面是实验二
        Findmindistance find = new Findmindistance();
        find.printminditance("华中科技大学", 1);
        
        //实验三
        //SubwayPathFinder allpath=new SubwayPathFinder();
        //allpath.getpath("华中科技大学","武汉火车站");

        //实验四
        // Subwayshortpath shortpath=new Subwayshortpath();
        // shortpath.printshortpath("华中科技大学","武汉火车站");

        //实验六
        Calculatefare fare=new Calculatefare();
        fare.getfare(start,end);

        //实验七
        fare.withdiscount(start,end,"武汉通");//起始站，终点站，优惠方式

    }
    
}
