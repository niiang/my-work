package exercise4;
import java.util.ArrayList;
//import java.util.Collections;
import java.util.List;
public class RobotBattlefission {
    // 主类，包含战斗逻辑
    public static void main(String[] args) {
        // 创建机器人实例
        List<Robot> robots = new ArrayList<>();
        
        robots.add(new SingleRobot());
        robots.add(new FissionRobot());
        
        

        

        // 随机选择两个机器人进行1v1 PK
        Robot robot1 = robots.get(0);
        Robot robot2 = robots.get(1);
        
        System.out.println("1v1 PK: " + robot1.getName() + " vs " + robot2.getName());
        int round = 1;
        while (!robot1.isDead() && !robot2.isDead()) {
            System.out.println("Round " + round + ":");
            // 攻击逻辑
            robot1.attack(robot2);
            if (robot2.isDead()) {
                // 检查是否为裂变机器人并处理分裂
                if (robot2 instanceof FissionRobot) {
                    List<Robot> miniRobots = ((FissionRobot) robot2).split();
                    if (miniRobots != null) {
                        robots.addAll(miniRobots); // 将迷你机器人添加到战斗列表
                        Robot robot3 = robots.get(2);
                        Robot robot4 = robots.get(3);
                        Robot robot5 = robots.get(4);
                        
                            System.out.println("Round " + ++round + ":");
                            robot3.attack(robot1);
                            robot1.attack(robot3);
                            robot4.attack(robot1);
                            robot1.attack(robot4);
                            robot5.attack(robot1);
                            robot1.attack(robot5);
                        
                    }
                }
                break;
            }
            robot2.attack(robot1);
            if (robot1.isDead()) {
                // 检查是否为裂变机器人并处理分裂
                if (robot1 instanceof FissionRobot) {
                    List<Robot> miniRobots = ((FissionRobot) robot1).split();
                    if (miniRobots != null) {
                        robots.addAll(miniRobots); // 将迷你机器人添加到战斗列表
                    }
                }
                break;
            }
            
            robot1.resetReattackFlag();
            robot2.resetReattackFlag();
            round++;
        }
        
        System.out.println("After battle, " + robot1.getName() + " status: " + robot1.getStatus());
        System.out.println("After battle, " + robot2.getName() + " status: " + robot2.getStatus());
        if (robot1.isDead()) {
            System.out.println("The winner is " + robot2.getName());
        } else if (robot2.isDead()) {
            System.out.println("The winner is " + robot1.getName());
        } else {
            System.out.println("It's a draw!");
        }
    }
}
