import javafx.util.Pair;

import java.util.ArrayList;

public class BigInt {
    private ArrayList<Long> buff = new ArrayList<>();
    private final int elementSize = 8;
    private final long divisor = (long) Math.pow(10, elementSize);

    private void init(String num) {
        if (num.length() == 0) {
            buff.add((long) 0);
        }
        int n = elementSize;
        for (int i = num.length(); i > 0; i -= n) {
            if (i < elementSize) n = num.length() % elementSize;
            buff.add(Long.parseLong(num.substring(i - n, i)));
        }
    }

    public BigInt(String num) {
        init(num);
    }

    public BigInt(Integer num) {
        init(num.toString());
    }

    public BigInt(Long num) {
        init(num.toString());
    }

    public BigInt(BigInt num) {
        buff.addAll(num.buff);
    }

    private BigInt(ArrayList<Long> buff) {
        this.buff = buff;
    }

    public BigInt add(BigInt other) {
        long remember = 0;
        BigInt maxNum = buff.size() > other.buff.size() ? this : other;
        BigInt minNum = buff.size() <= other.buff.size() ? this : other;
        ArrayList<Long> result = new ArrayList<>(minNum.buff);
        while (result.size() < maxNum.buff.size()) {
            result.add((long) 0);
        }
        for (int i = 0; i < maxNum.buff.size(); i++) {
            long temp = result.get(i) + maxNum.buff.get(i) + remember;
            remember = temp / divisor;
            temp %= divisor;
            result.set(i, temp);
        }
        if (remember != 0) {
            result.add(remember);
        }
        return new BigInt(result);
    }

    public BigInt sub(BigInt other) {
        if (this.compareTo(other) == -1) throw new IllegalArgumentException();
        int remember = 0;
        ArrayList<Long> result = new ArrayList<>(other.buff);
        while (result.size() < buff.size()) {
            result.add((long) 0);
        }
        for (int i = 0; i < buff.size(); i++) {
            long temp = buff.get(i) - result.get(i) - remember;
            if (temp < 0) {
                temp += divisor;
                remember = 1;
            } else {
                remember = 0;
            }
            result.set(i, temp);
        }
        while (result.get(result.size() - 1) == 0) {
            if (result.size() == 1) break;
            result.remove(result.size() - 1);
        }
        return new BigInt(result);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        String format = "%0" + elementSize + "d";
        result.append(buff.get(buff.size() - 1));
        for (int i = buff.size() - 2; i >= 0; i--) {
            result.append(String.format(format, buff.get(i)));
        }
        return result.toString();
    }

    public int compareTo(BigInt other) {
        if (this.buff.size() > other.buff.size()) {
            return 1;
        } else if (this.buff.size() < other.buff.size()) {
            return -1;
        }
        for (int i = buff.size() - 1; i >= 0; i--) {
            if (buff.get(i) > other.buff.get(i)) {
                return 1;
            } else if (buff.get(i) < other.buff.get(i)) {
                return -1;
            }
        }
        return 0;
    }

    public boolean equals(BigInt other) {
        return buff.equals(other.buff);
    }

    private BigInt mulOnElement(Long other) {
        long remember = 0;
        ArrayList<Long> result = new ArrayList<>(buff);
        for (int i = 0; i < buff.size(); i++) {
            long temp = buff.get(i) * other + remember;
            remember = temp / divisor;
            temp %= divisor;
            result.set(i, temp);
        }
        if (remember != 0) {
            result.add(remember);
        }
        return new BigInt(result);
    }

    private void mulOnBase(int count) {
        for (int i = 0; i < count; i++) {
            buff.add(0, (long) 0);
        }
    }

    public BigInt mul(BigInt other) {
        BigInt result = new BigInt(0);
        for (int i = 0; i < other.buff.size(); i++) {
            BigInt temp = this.mulOnElement(other.buff.get(i));
            temp.mulOnBase(i);
            result = result.add(temp);
        }
        while (result.buff.size() > 1) {
            if (result.buff.get(result.buff.size() - 1) == 0) {
                result.buff.remove(result.buff.size() - 1);
            } else {
                break;
            }
        }
        return result;
    }

    private BigInt mul10(int count) {
        BigInt result = new BigInt(this);
        result.mulOnBase(count / elementSize);
        for (int i = 0; i < count % elementSize; i++) {
            result = result.mul(new BigInt(10));
        }
        return result;
    }

    private Pair<BigInt, BigInt> divide(BigInt other) {
        if (other.toString().equals("0")) throw new ArithmeticException();
        BigInt dividend = new BigInt(this);
        StringBuilder quotient = new StringBuilder();
        int multiplier = dividend.toString().length() - other.toString().length();
        while (multiplier >= 0) {
            BigInt temp = new BigInt(other);
            temp = temp.mul10(multiplier);
            int quotientDig = 0;
            while (dividend.compareTo(temp) != -1) {
                quotientDig++;
                dividend = dividend.sub(temp);
            }
            quotient.append(quotientDig);
            multiplier--;
        }
        if (quotient.length() == 0) {
            quotient.append(0);
        } else if (quotient.charAt(0) == '0') {
            quotient.deleteCharAt(0);
        }
        return new Pair<>(dividend, new BigInt(quotient.toString()));
    }

    public BigInt div(BigInt other) {
        return this.divide(other).getValue();
    }

    public BigInt mod(BigInt other) {
        return this.divide(other).getKey();
    }

}
