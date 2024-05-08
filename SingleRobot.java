package exercise4;

// 对单机器人类
// 对单机器人类
class SingleRobot extends Robot {
    private static final double REATTACK_PROBABILITY = 0.5; // 再次攻击的概率
    private boolean hasReattacked = false; // 标记是否已经触发过再次攻击

    public SingleRobot() {
        super("单机器人", 800, 80);
    }

    // 重写攻击方法，实现概率触发再次攻击，且每回合只触发一次
    @Override
    public void attack(Robot target) {
        // 首先进行正常攻击
        super.attack(target);

        // 检查是否已经触发过再次攻击，如果没有，则根据概率决定是否触发
        if (!hasReattacked && triggerPassiveSkill(REATTACK_PROBABILITY)) {
            hasReattacked = true; // 标记已经触发过再次攻击
            System.out.println(name + " triggers reattack passive skill!");
            attack(target); // 再次攻击目标
        }
    }

    // 重置标记方法，以便在新的回合中可以再次触发被动技能
    public void resetReattackFlag() {
        hasReattacked = false;
    }
}