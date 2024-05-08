package exercise4;
import java.util.ArrayList;
import java.util.List;
public abstract class Robot {
    protected String name;
    protected int health;
    private int attackPower;
    protected List<String> passiveSkills;

    public Robot(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.passiveSkills = new ArrayList<>();
    }

    // 攻击方法
    public void attack(Robot target) {
        int damage = attackPower;
        // 应用被动技能
        for (String skill : passiveSkills) {
            if ("aoe".equals(skill)) {
                damage = (int) (damage * 0.5);
            } else if ("reattack".equals(skill)) {
                // 这里简化处理，实际上应该有概率判断
                damage += attackPower;
            }
            // 其他被动技能处理...
        }
        target.health -= damage;
        System.out.println(name + " attacks " + target.name + " for " + damage + " damage.");
    }

    // 检查是否阵亡
    public boolean isDead() {
        return health <= 0;
    }

    // 获取机器人状态
    public String getStatus() {
        return name + " - Health: " + health + ", Attack: " + attackPower;
    }

    // 添加被动技能
    public void addPassiveSkill(String skill) {
        passiveSkills.add(skill);
    }
    public String getName() {
        return name;
    }

    protected boolean triggerPassiveSkill(double probability) {
        return Math.random() < probability;
    }
    protected int getTargetCount(List<Robot> targets) {
        long count = targets.stream().filter(target -> !target.isDead()).count();
        return (int) count; // 确保count不会超过int的最大值
    }
    public int getAttackPower() {
        return attackPower;
    }
    public void resetReattackFlag() {
        // 如果有子类使用了这个标记，这里可以添加相应的重置逻辑
        // 例如，对于 SingleRobot，我们可以添加以下代码：
        // this.hasReattacked = false;
    }
    public int getHealth() {
        return health;
    }
}
