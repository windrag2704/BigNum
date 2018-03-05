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
        StringBuilder baseStr = new StringBuilder();
        for (int i = 0; i < elementSize; i++) {
            baseStr.append("0");
        }
        ArrayList<String> num = new ArrayList<>();
        for (int i = 0; i < buff.size(); i++) {
            num.add(buff.get(i).toString());
            int length = num.get(i).length();
            if (length < elementSize && i != buff.size() - 1) {
                StringBuilder temp = new StringBuilder(baseStr);
                num.set(i, temp.replace(elementSize - length, elementSize, num.get(i)).toString());
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = num.size() - 1; i >= 0; i--) {
            result.append(num.get(i));
        }
        return result.toString();
    }

    public boolean moreThan(BigInt other) {
        if (other.buff.size() > buff.size()) {
            return false;
        } else if (other.buff.size() < buff.size()) {
            return true;
        } else {
            for (int i = buff.size() - 1; i >= 0; i--) {
                if (buff.get(i) > other.buff.get(i)) {
                    return true;
                }
                if (buff.get(i) < other.buff.get(i)) {
                    return false;
                }
            }
            return false;
        }
    }

    public boolean lessThan(BigInt other) {
        if (other.buff.size() > buff.size()) {
            return true;
        } else if (other.buff.size() < buff.size()) {
            return false;
        } else {
            for (int i = buff.size() - 1; i >= 0; i--) {
                if (buff.get(i) < other.buff.get(i)) {
                    return true;
                }
                if (buff.get(i) > other.buff.get(i)) {
                    return false;
                }
            }
            return false;
        }
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

    private void mulOnDivisor(int count) {
        for (int i = 0; i < count; i++) {
            buff.add(0, (long) 0);
        }
    }

    public BigInt mul(BigInt other) {
        BigInt result = new BigInt(0);
        for (int i = 0; i < other.buff.size(); i++) {
            BigInt temp = this.mulOnElement(other.buff.get(i));
            temp.mulOnDivisor(i);
            result = result.add(temp);
        }
        return result;
    }

    private BigInt mul10(int count) {
        BigInt result = new BigInt(this);
        result.mulOnDivisor(count / elementSize);
        for (int i = 0; i < count % elementSize; i++ ) {
            result = result.mul(new BigInt(10));
        }
        return result;
    }

}
