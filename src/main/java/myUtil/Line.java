package myUtil;

/**
 *  geometry line and point
 */
public class Line {
    private int a_up;
    private int a_down;
    private long _b;

    public Line(Point a, Point b) {
        if (a.y == b.y) {
            a_up = 0;
            a_down = 0;
            _b = a.y;
        } else if (a.x == b.x) {
            a_up = -1;
            a_down = 0;
            _b = a.x;
        } else {
            a_up = b.y - a.y;
            a_down = b.x - a.x;
            //assure a_down is positive
            if ((a_up < 0 && a_down < 0) || (a_up > 0 && a_down < 0)) {
                a_up = -a_up;
                a_down = -a_down;
            }

            int gcd = GCD(a_up, a_down);
            a_up = a_up / gcd;
            a_down = a_down / gcd;

            _b = (long) a_down * (long) a.y - (long) a_up * (long) a.x;
        }

    }

    public boolean contains(Point p) {
        if (a_up == 0 && a_down == 0) {
            return _b == p.y;
        } else if (a_up == -1 && a_down == 0) {
            return _b == p.x;
        }
        return _b == (long) a_down * (long) p.y - (long) a_up * (long) p.x;
    }

    @Override
    public int hashCode() {
        return a_up << 16 + a_down << 8 + _b;
    }


    @Override
    public boolean equals(Object o) {
        Line another = (Line) o;

        return a_up == another.a_up && a_down == another.a_down && _b == another._b;
    }

    private int GCD(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        if (a < b) {
            int temp = b;
            b = a;
            a = temp;
        }

        while (a % b != 0) {
            int result = a % b;
            a = b;
            b = result;
        }

        return b;
    }
}

class Point {
    int x;
    int y;

    Point() {
        x = 0;
        y = 0;
    }

    Point(int a, int b) {
        x = a;
        y = b;
    }
}
