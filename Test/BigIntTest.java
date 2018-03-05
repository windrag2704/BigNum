import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class BigIntTest {

    @Test
    void add() {
        for (int j = 0; j < 20; j++) {
            StringBuilder num1Str = new StringBuilder();
            StringBuilder num2Str = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                num1Str.append((int) (Math.random() * 1000000));
                num2Str.append((int) (Math.random() * 10000000));
            }
            BigInteger num1B = new BigInteger(num1Str.toString());
            BigInteger num2B = new BigInteger(num2Str.toString());
            BigInt num1 = new BigInt(num1Str.toString());
            BigInt num2 = new BigInt(num2Str.toString());
            assertEquals(num1B.add(num2B).toString(), num1.add(num2).toString());
        }
    }

    @Test
    void sub() {
        for (int j = 0; j < 20; j++) {
            StringBuilder num1Str = new StringBuilder();
            StringBuilder num2Str = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                num1Str.append((int) (Math.random() * 100000000 + 99999999));
                num2Str.append((int) (Math.random() * 10000000 + 9999999));
            }
            BigInteger num1B = new BigInteger(num1Str.toString());
            BigInteger num2B = new BigInteger(num2Str.toString());
            BigInt num1 = new BigInt(num1Str.toString());
            BigInt num2 = new BigInt(num2Str.toString());
            assertEquals(num1B.subtract(num2B).toString(), num1.sub(num2).toString());
        }
    }

    @Test
    void moreThan() {
        for (int j = 0; j < 20; j++) {
            StringBuilder num1Str = new StringBuilder();
            StringBuilder num2Str = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                num1Str.append((int) (Math.random() * 1000000));
                num2Str.append((int) (Math.random() * 1000000));
            }
            BigInteger num1B = new BigInteger(num1Str.toString());
            BigInteger num2B = new BigInteger(num2Str.toString());
            BigInt num1 = new BigInt(num1Str.toString());
            BigInt num2 = new BigInt(num2Str.toString());
            assertEquals(num1B.compareTo(num2B) == 1, num1.moreThan(num2));
        }
    }


    @Test
    void lessThan() {
        for (int j = 0; j < 20; j++) {
            StringBuilder num1Str = new StringBuilder();
            StringBuilder num2Str = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                num1Str.append((int) (Math.random() * 1000000));
                num2Str.append((int) (Math.random() * 1000000));
            }
            BigInteger num1B = new BigInteger(num1Str.toString());
            BigInteger num2B = new BigInteger(num2Str.toString());
            BigInt num1 = new BigInt(num1Str.toString());
            BigInt num2 = new BigInt(num2Str.toString());
            assertEquals(num1B.compareTo(num2B) == -1, num1.lessThan(num2));
        }
    }


    @Test
    void equals() {
        for (int j = 0; j < 20; j++) {
            StringBuilder num1Str = new StringBuilder();
            StringBuilder num2Str = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                num1Str.append((int) (Math.random() * 1000000));
                num2Str.append((int) (Math.random() * 10000000));
            }
            BigInteger num1B = new BigInteger(num1Str.toString());
            BigInteger num2B = new BigInteger(num2Str.toString());
            BigInt num1 = new BigInt(num1Str.toString());
            BigInt num2 = new BigInt(num2Str.toString());
            assertEquals(num1B.compareTo(num2B) == 0, num1.equals(num2));
        }
    }


    @Test
    void mul() {
        for (int j = 0; j < 20; j++) {
            StringBuilder num1Str = new StringBuilder();
            StringBuilder num2Str = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                num1Str.append((int) (Math.random() * 1000000));
                num2Str.append((int) (Math.random() * 10000000));
            }
            BigInteger num1B = new BigInteger(num1Str.toString());
            BigInteger num2B = new BigInteger(num2Str.toString());
            BigInt num1 = new BigInt(num1Str.toString());
            BigInt num2 = new BigInt(num2Str.toString());
            assertEquals(num1B.multiply(num2B).toString(), num1.mul(num2).toString());
        }
    }


    @Test
    void div() {
        for (int j = 0; j < 20; j++) {
            StringBuilder num1Str = new StringBuilder();
            StringBuilder num2Str = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                num1Str.append((int) (Math.random() * 10000000 + 9999999));
                num2Str.append((int) (Math.random() * 1000 + 999));
            }
            BigInteger num1B = new BigInteger(num1Str.toString());
            BigInteger num2B = new BigInteger(num2Str.toString());
            BigInt num1 = new BigInt(num1Str.toString());
            BigInt num2 = new BigInt(num2Str.toString());
            assertEquals(num1B.divide(num2B).toString(), num1.div(num2).toString());
        }
    }


    @Test
    void mod() {
        for (int j = 0; j < 20; j++) {
            StringBuilder num1Str = new StringBuilder();
            StringBuilder num2Str = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                num1Str.append((int) (Math.random() * 1000000 + 999999));
                num2Str.append((int) (Math.random() * 100000 + 99999));
            }
            BigInteger num1B = new BigInteger(num1Str.toString());
            BigInteger num2B = new BigInteger(num2Str.toString());
            BigInt num1 = new BigInt(num1Str.toString());
            BigInt num2 = new BigInt(num2Str.toString());
            assertEquals(num1B.mod(num2B).toString(), num1.mod(num2).toString());
        }
    }

}