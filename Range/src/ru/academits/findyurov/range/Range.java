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

    @Override
    public String toString() {
        //System.out.println("("+this.from+", "+this.to+") ");
        return "(" + from + ", " + to + ")";
    }

    public Range getIntersection(Range range2) {
        if ((to <= range2.from) || (range2.to <= from)) {
            return null;
        }

        return new Range(Math.max(this.from, range2.from), Math.min(this.to, range2.to));
    }

    public Range[] getUnion(Range range2) {
        if ((this.to < range2.from) || (range2.to < this.from)) {
            return new Range[]{new Range(from, to), new Range(range2.from, range2.to)};
        }

        return new Range[]{new Range(Math.min(this.from, range2.from), Math.max(this.to, range2.to))};
    }

    public Range[] getDifference(Range range2) {
        if ((to <= range2.from) || (range2.to <= from)) {
            return new Range[]{new Range(from, to)};
        }

        if (from >= range2.from && to <= range2.to) {
            return new Range[0];
        }

        if (range2.from > this.from && range2.to < this.to) {
            return new Range[]{new Range(from, range2.from), new Range(range2.to, to)};
        }

        if (range2.from > from) {
            return new Range[]{new Range(from, range2.from)};
        }

        return new Range[]{new Range(range2.to, to)};
    }
}
