import java.util.ArrayList;

public class BigInt {
    private ArrayList<Integer> buff = new ArrayList<>();
    private final int elementSize = 6;
    private final int divisor = (int)Math.pow(10, elementSize);
    private void init(String num) {
        if (num.length() == 0){
            buff.add(0);
        }
        int n = elementSize;
        for (int i = num.length(); i > 0 ; i -= n) {
            if (i < elementSize) n = num.length() % elementSize;
            buff.add(Integer.parseInt(num.substring(i - n, i)));
        }
    }
    public BigInt(String num) {
        init(num);
    }
    public BigInt(Integer num) {
        init(num.toString());
    }
    public BigInt(BigInt num) {
        num.buff.addAll(buff);
    }
    private BigInt(ArrayList<Integer> buff) {
        this.buff = buff;
    }
    public BigInt add(BigInt other) {
        int remember = 0;
        int size = buff.size() > other.buff.size() ? buff.size() : other.buff.size();
        ArrayList<Integer> result = new ArrayList<>(buff);
        while(result.size() < size) {
            result.add(0);
        }
        for (int i = 0; i < other.buff.size(); i++) {
            int temp = buff.get(i) + other.buff.get(i) + remember;
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
        int size = buff.size() > other.buff.size() ? buff.size() : other.buff.size();
        ArrayList<Integer> result = new ArrayList<>(buff);
        while(result.size() < size) {
            result.add(0);
        }
        for (int i = 0; i < other.buff.size(); i++) {
            int temp = buff.get(i) - other.buff.get(i) - remember;
            if (temp < 0) {
                temp += divisor;
                remember = 1;
            } else {
                remember = 0;
            }
            result.set(i, temp);
        }
        if (remember != 0) {
            int index = other.buff.size();
            result.set(index, result.get(index) - remember);
        }
        while(result.get(result.size() - 1) == 0) {
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
}
