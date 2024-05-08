package exercise4;

class MiniRobot extends Robot {
    public MiniRobot() {
        super("迷你机器人", 100, 16); // 确保在构造函数中设置了攻击力
    }

    // 重写攻击方法，继承父类的技能
    @Override
    public void attack(Robot target) {
        super.attack(target);
        // 迷你机器人可以有额外的技能，例如...
    }
}