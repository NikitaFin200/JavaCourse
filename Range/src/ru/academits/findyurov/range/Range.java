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
        return "(" + this.from + ", " + this.to + ")";
    }


    public void print() {
        System.out.print("(" + from + "," + to + "), ");
    }



    public static void printArray(Range[] array) {
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

    public Range getIntersection(Range range2) {
        if ((this.to <= range2.from) || (range2.to <= this.from)) {
            return null;
        }

        return new Range(Math.max(this.from, range2.from), Math.min(this.to, range2.to));
    }

    public Range[] getUnion(Range range2) {
        if ((this.to < range2.from) || (range2.to < this.from)) {
            return new Range[]{new Range(this.from, this.to), new Range(range2.from, range2.to)};
        }

        return new Range[]{new Range(Math.min(this.from, range2.from), Math.max(this.to, range2.to))};
    }

    public Range[] getDifference(Range range2) {
        if ((this.to <= range2.from) || (range2.to <= this.from)) {
            return new Range[]{new Range(this.from, this.to)};
        }

        if (this.from >= range2.from && this.to <= range2.to) {
            return new Range[0];
        }

        if (range2.from > this.from && range2.to < this.to) {
            return new Range[]{new Range(this.from, range2.from), new Range(range2.to, this.to)};
        }

        if (range2.from > this.from) {
            return new Range[]{new Range(this.from, range2.from)};
        }

        range2.setFrom(range2.to);
        range2.setTo(this.to);

        return new Range[]{new Range(range2.to, this.to)};
    }
}
