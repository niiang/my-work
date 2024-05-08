package exercise4;
import java.util.ArrayList;
import java.util.List;
class FissionRobot extends Robot {
    public FissionRobot() {
        super("分裂机器人", 500, 80);
    }

    // 当机器人阵亡时分裂为3个迷你机器人
    public List<Robot> split() {
        if (this.isDead()) {
            List<Robot> miniRobots = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                miniRobots.add(new MiniRobot());
            }
            // 原机器人阵亡
            this.health = 0;
            return miniRobots;
        }
        return null;
    }
    
}