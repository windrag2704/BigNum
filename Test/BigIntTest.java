import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class BigIntTest {

    @Test
    void add() {
        for (int j = 0; j < 1000; j++) {
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
        assertEquals("1000000000000", new BigInt("999999999999").add(new BigInt(1)).toString());
        assertEquals("123456789123456789", new BigInt(0).add(new BigInt("123456789123456789")).toString());
    }

    @Test
    void sub() {
        for (int j = 0; j < 1000; j++) {
            StringBuilder num1Str = new StringBuilder();
            StringBuilder num2Str = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                num1Str.append((int) (Math.random() * 100000000 + 99999999));
                num2Str.append((int) (Math.random() * 1000000 + 999999));
            }
            BigInteger num1B = new BigInteger(num1Str.toString());
            BigInteger num2B = new BigInteger(num2Str.toString());
            BigInt num1 = new BigInt(num1Str.toString());
            BigInt num2 = new BigInt(num2Str.toString());
            assertEquals(num1B.subtract(num2B).toString(), num1.sub(num2).toString());
        }
        assertThrows(IllegalArgumentException.class, () ->
            new BigInt("123456789").sub(new BigInt("987654321"))
        );
        assertEquals("123123123123123", new BigInt("123123123123123").sub(new BigInt(0)).toString());
        assertEquals("999999999999", new BigInt("1000000000000").sub(new BigInt(1)).toString());
    }

    @Test
    void compareTo() {
        for (int j = 0; j < 1000; j++) {
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
            assertEquals(num1B.compareTo(num2B), num1.compareTo(num2));
        }
    }

    @Test
    void equals() {
        for (int j = 0; j < 1000; j++) {
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
            assertEquals(num1B.compareTo(num2B) == 0, num1.equals(num2));
            BigInt num1copy = new BigInt(num1B.toString());
            assertTrue(num1.equals(num1copy));
        }
    }


    @Test
    void mul() {
        for (int j = 0; j < 1000; j++) {
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
        for (int j = 0; j < 1000; j++) {
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
        assertThrows(ArithmeticException.class, () ->
            new BigInt("2349235235").div(new BigInt(0))
        );
        assertEquals("0", new BigInt(0).div(new BigInt("234234234234234")).toString());
    }


    @Test
    void mod() {
        for (int j = 0; j < 1000; j++) {
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
        assertEquals("0", new BigInt(0).mod(new BigInt("123123123123")).toString());
        assertEquals("5", new BigInt(5).mod(new BigInt("123123123123")).toString());
    }

}