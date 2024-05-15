package theendwork;

public class Test {
    public static void main(String[] args) {
        
        String yourfilepath="C:\\Users\\25431\\.vscode\\java\\theendwork\\subway.txt";
        TransferStations transferStations=new TransferStations(yourfilepath);
        transferStations.print();
        //以上是实验一
        String start="华中科技大学";
        String end="湖口";
        //下面是实验二
        Findmindistance find = new Findmindistance(yourfilepath);
        find.printminditance("华中科技大学", 5);
        
        //实验三
        // SubwayPathFinder allpath=new SubwayPathFinder(yourfilepath);
        // allpath.getpath("华中科技大学","武汉火车站");

        //实验四
        Subwayshortpath shortpath=new Subwayshortpath(yourfilepath);
        shortpath.printshortpath(start,end);

        // //实验六
        Calculatefare fare=new Calculatefare(yourfilepath);
        fare.getfare(start,end);

        //实验七
        fare.withdiscount(start,end,"武汉通");//起始站，终点站，优惠方式

    }
    
}
