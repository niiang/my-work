package theendwork;

public class Test {
    public static void main(String[] args) {
        //说明：本Test类可以直接运行得到结果，只要将本仓库的subway.txt下载后的目录输入到yourfilepath就可以了。
        //注意事项：实验三慎测试。会出现万条起步
        //注意事项：一定是本仓库的subway，因为该subway是调整后的，方便代码读取，原版的subway存在一些问题，例如站点不全什么的，线路格式不统一。
        //ps：代码很稀烂，不过能实现功能。

        String yourfilepath="C:\\Users\\25431\\.vscode\\java\\theendwork\\subway.txt";
        TransferStations transferStations=new TransferStations(yourfilepath);
        transferStations.print();//得到中转站
        System.out.println();
        //以上是实验一

        String start="华中科技大学";//起始站
        String end="湖口";//终点站
        //下面是实验二


        Findmindistance find = new Findmindistance(yourfilepath);
        find.printminditance("华中科技大学", 5);//得到小于n的线路距离
        System.out.println();
        //实验三


        // SubwayPathFinder allpath=new SubwayPathFinder(yourfilepath);
        // allpath.getpath("华中科技大学","武汉火车站");//得到所有的线路
        System.out.println();

        
        //实验四
        Subwayshortpath shortpath=new Subwayshortpath(yourfilepath);
        shortpath.printshortpath(start,end);//得到最短距离
        System.out.println();


        //实验五
        SubwayPathPresenter present=new SubwayPathPresenter(yourfilepath);
        present.geteasyline(start,end);//最短距离简化输出
        System.out.println();


        // //实验六
        Calculatefare fare=new Calculatefare(yourfilepath);
        fare.getfare(start,end);//计算费用
        System.out.println();


        //实验七
        fare.withdiscount(start,end,"武汉通");//使用优惠方式后的费用,优惠方式包括武汉通和日票
        System.out.println();
    }
    
}
