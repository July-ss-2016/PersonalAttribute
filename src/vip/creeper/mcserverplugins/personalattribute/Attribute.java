package vip.creeper.mcserverplugins.personalattribute;

public class Attribute {
    private PersonalAttributeType attributeType;
    private int attributeLevel;

    public Attribute(PersonalAttributeType attributeType, int attributeLevel) {
        this.attributeType = attributeType;
        this.attributeLevel = attributeLevel;
    }

    public PersonalAttributeType getAttributeType() {
        return attributeType;
    }

    public int getAttributeLevel() {
        return attributeLevel;
    }
}
