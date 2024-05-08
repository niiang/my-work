package exercise4;
import java.util.ArrayList;
import java.util.List;
// 对群机器人类
// 对群机器人类
class AoERobot extends Robot {
    public AoERobot() {
        super("群体机器人", 1000, 100); // 确保在构造函数中设置了攻击力
    }

    // 重写攻击方法，面对单一目标时不触发被动技能
    @Override
    public void attack(Robot target) {
        // 获取当前攻击的目标列表
        List<Robot> targets = new ArrayList<>();
        targets.add(target);

        // 根据目标数量决定是否触发被动技能
        int damage;
        if (getTargetCount(targets) > 1) {
            // 面对多个目标时触发被动技能
            damage = (int) (getAttackPower() * 0.5);
            System.out.println(name + " triggers AoE passive skill against " + targets.size() + " targets!");
        } else {
            // 面对单一目标时不触发被动技能
            damage = getAttackPower(); // 使用getAttackPower()方法获取攻击力
        }

        target.health -= damage;
        System.out.println(name + " attacks " + target.getName() + " for " + damage + " damage.");
    }
}