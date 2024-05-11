package theendwork;

public class Test extends TransferStations  {
    public static void main(String[] args) {
        //TransferStations transferStations=new TransferStations();
        //transferStations.print();
        //以上是实验一
        //下面是实验二
        String startStation = "华中科技大学";
        int n = 2;
        Findmindistance find = new Findmindistance();
        find.printminditance(startStation, n);
    }
    
}
