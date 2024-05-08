package exercise4;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class threevs {
    public static void main(String[] args) {
        // 创建机器人实例
        List<Robot> robots = new ArrayList<>();
        robots.add(new AoERobot()); // 添加对群机器人
        robots.add(new SingleRobot()); // 添加对单机器人
        robots.add(new FissionRobot()); // 添加裂变机器人
        // 确保列表中有至少6个机器人
        while (robots.size() < 6) {
            robots.add(new SingleRobot()); // 举例，添加更多机器人以确保有足够的数量
        }

        // 随机排序机器人列表
        Collections.shuffle(robots);

        // 随机选择六个机器人进行3v3 PK
        System.out.println("3v3 PK:");
        List<Robot> team1 = new ArrayList<>(robots.subList(0, 3)); // 选择前三个机器人作为团队1
        List<Robot> team2 = new ArrayList<>(robots.subList(3, 6)); // 选择后三个机器人作为团队2

        // 打印对战阵容
        printTeamStatus(team1, "Team 1");
        printTeamStatus(team2, "Team 2");

        // 开始战斗
        int round = 1;
        while (!team1.isEmpty() && !team2.isEmpty()) {
            System.out.println("\nRound " + round + ":");
            // 战斗循环
            for (Robot robot : team1) {
                if (!robot.isDead()) {
                    for (Robot opponent : team2) {
                        if (!opponent.isDead()) {
                            robot.attack(opponent);
                        
                            if (opponent.isDead()) {
                                // 如果对手是裂变机器人，处理分裂
                                if (opponent instanceof FissionRobot) {
                                    List<Robot> miniRobots = handleSplit((FissionRobot) opponent);
                                    if (miniRobots != null) {
                                        team2.addAll(miniRobots); // 添加新分裂的迷你机器人到团队2
                                    }
                                }
                                break; // 找到一个活着的对手，进行攻击后结束循环
                            }
                        }
                        else break;
                    }
                }
            }
            // 检查团队1是否还有活着的机器人
            if (team1.stream().allMatch(Robot::isDead)) {
                System.out.println("Team 1 is defeated!");
                break;
            }
            round++;
        }

        // 打印胜利方
        if (team2.isEmpty()) {
            System.out.println("Team 1 wins!");
        } else {
            System.out.println("Team 2 wins!");
        }
    }

    // 打印团队状态的方法
    private static void printTeamStatus(List<Robot> team, String teamName) {
        System.out.println(teamName + ": ");
        for (Robot robot : team) {
            System.out.println("- " + robot.getName() + " (HP: " + robot.getHealth() + ", ATK: " + robot.getAttackPower() + ")");
        }
    }

    // 处理机器人分裂的方法
    private static List<Robot> handleSplit(FissionRobot fissionRobot) {
        if (fissionRobot.isDead()) {
            return fissionRobot.split();
        }
        return null;
    }
}
