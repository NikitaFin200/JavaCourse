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
        return "(" + from + ", " + to + ")";
    }

    public void print() {
        System.out.print("(" + from + "," + to + ")" + ", ");
    }

    public static void arraysPrint(Range[] array) {
        if (array.length == 0) {
            System.out.print("[]");
        } else {
            System.out.print("[");
            for (Range element : array) {
                element.print();
            }
            System.out.print("]");
        }
    }

    public Range getIntersections(Range range1, Range range2) {
        if ((range1.to <= range2.from) || (range2.to <= range1.from)) {
            return null;
        }

        double from = Math.max(range1.from, range2.from);
        double to = Math.min(range1.to, range2.to);
        return new Range(from, to);
    }

    public Range[] getUnion(Range range1, Range range2) {
        if ((range1.to < range2.from) || (range2.to < range1.from)) {
            return new Range[]{new Range(range1.from, range1.to), new Range(range2.from, range2.to)};
        }

        double from = Math.min(range1.from, range2.from);
        double to = Math.max(range1.to, range2.to);
        return new Range[]{new Range(from, to)};
    }

    public Range[] getDifference(Range range1, Range range2) {
        if ((range1.to <= range2.from) || (range2.to <= range1.from)) {
            return new Range[]{new Range(range1.from, range1.to)};
        }

        if (range1.from >= range2.from && range1.to <= range2.to) {
            return new Range[0];
        }

        if (range2.from > range1.from && range2.to < range1.to) {
            return new Range[]{new Range(range1.from, range2.from), new Range(range2.to, range1.to)};
        }

        if (range2.from > range1.from) {
            return new Range[]{new Range(range1.from, range2.from)};
        }

        return new Range[]{new Range(range2.to, range1.to)};
    }
}
