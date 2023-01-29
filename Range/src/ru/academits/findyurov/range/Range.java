package ru.academits.findyurov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {   // конструктор для заполнения полей
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersections(Range range1, Range range2) {
        if ((range1.to <= range2.from) || (range2.to <= range1.from)) {
            return null;
        }

        double from = Math.max(range1.from, range2.from);
        double to = Math.min(range1.to, range2.to);
        return new Range(from, to);
    }


}
