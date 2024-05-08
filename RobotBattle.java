package exercise4;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class RobotBattle {
    public static void main(String[] args) {
        
        List<Robot> robots = new ArrayList<>();
        robots.add(new AoERobot());
        robots.add(new SingleRobot());
        Collections.shuffle(robots);

        // 1v1 pk
        Robot robot1 = robots.get(0);
        Robot robot2 = robots.get(1);
        
        System.out.println("1v1 PK: " + robot1.getName() + " vs " + robot2.getName());
        int round = 1;
        while (!robot1.isDead() && !robot2.isDead()) {
            System.out.println("Round " + round + ":");
            robot1.attack(robot2);
            if (robot2.isDead()) {
                break;
            }
            robot2.attack(robot1);
            if (robot1.isDead()) {
                break;
            }
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