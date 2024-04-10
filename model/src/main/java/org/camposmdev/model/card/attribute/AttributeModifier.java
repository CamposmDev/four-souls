package org.camposmdev.model.card.attribute;

public class AttributeModifier {
    private AttributeType thresholdType;
    private byte thresholdValue;
    private ThresholdCompare compare;
    private boolean everyOtherTime;
    private AttributeType modType;
    private byte modValue;
    private RollEvent[] events;

    public AttributeType thresholdType() {
        return thresholdType;
    }

    public AttributeModifier setThresholdType(AttributeType thresholdType) {
        this.thresholdType = thresholdType;
        return this;
    }

    public Byte thresholdValue() {
        return thresholdValue;
    }

    public AttributeModifier setThresholdValue(byte thresholdValue) {
        this.thresholdValue = thresholdValue;
        return this;
    }

    public ThresholdCompare compare() {
        return compare;
    }

    public AttributeModifier setCompare(ThresholdCompare compare) {
        this.compare = compare;
        return this;
    }

    public boolean everyOtherTime() {
        return everyOtherTime;
    }

    public AttributeModifier setEveryOtherTime(boolean everyOtherTime) {
        this.everyOtherTime = everyOtherTime;
        return this;
    }

    public AttributeType modType() {
        return modType;
    }

    public AttributeModifier setModType(AttributeType modType) {
        this.modType = modType;
        return this;
    }

    public Byte modValue() {
        return modValue;
    }

    public AttributeModifier setModValue(byte modValue) {
        this.modValue = modValue;
        return this;
    }

    public RollEvent[] events() {
        return events;
    }

    public AttributeModifier setEvents(RollEvent[] events) {
        this.events = events;
        return this;
    }
}
