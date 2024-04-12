package org.camposmdev.model.card.attribute;

import java.util.List;

public class AttributeModifier {
    private AttributeType thresholdType;
    private byte thresholdValue;
    private ThresholdCompare compare;
    private boolean everyOtherTime;
    private AttributeType modType;
    private byte modValue;
    private List<RollEvent> events;

    public AttributeType getThresholdType() {
        return thresholdType;
    }

    public AttributeModifier setThresholdType(AttributeType thresholdType) {
        this.thresholdType = thresholdType;
        return this;
    }

    public Byte getThresholdValue() {
        return thresholdValue;
    }

    public AttributeModifier setThresholdValue(byte thresholdValue) {
        this.thresholdValue = thresholdValue;
        return this;
    }

    public ThresholdCompare getCompare() {
        return compare;
    }

    public AttributeModifier setCompare(ThresholdCompare compare) {
        this.compare = compare;
        return this;
    }

    public boolean isEveryOtherTime() {
        return everyOtherTime;
    }

    public AttributeModifier setEveryOtherTime(boolean everyOtherTime) {
        this.everyOtherTime = everyOtherTime;
        return this;
    }

    public AttributeType getModType() {
        return modType;
    }

    public AttributeModifier setModType(AttributeType modType) {
        this.modType = modType;
        return this;
    }

    public Byte getModValue() {
        return modValue;
    }

    public AttributeModifier setModValue(byte modValue) {
        this.modValue = modValue;
        return this;
    }

    public List<RollEvent> getEvents() {
        return events;
    }

    public AttributeModifier setEvents(List<RollEvent> events) {
        this.events = events;
        return this;
    }
}
