package vip.creeper.mcserverplugins.personalattribute;

public enum PersonalAttributeType {
    DAMAGE("damage"), SPEED("speed"), HEALTH("health");

    private String name; //不能放到ENUM声明的上面

    PersonalAttributeType(String name) {
        this.name = name;
    }
}
