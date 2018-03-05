public class Main {
    public static void main(String[] args) {
        BigInt n = new BigInt("1000000000000000000000000000000000000000000");
        BigInt n1 = new BigInt("1");
        BigInt sum = n.add(n1);
        BigInt sub = n.sub(n1);
        BigInt m = new BigInt("1234567896");
        BigInt m1 = new BigInt("123456789");
        System.out.println(m.mul(m1));
        System.out.println(m.div(m1));
        System.out.println(sum);
        System.out.println(sub);
        System.out.println("Done!");
    }
}
