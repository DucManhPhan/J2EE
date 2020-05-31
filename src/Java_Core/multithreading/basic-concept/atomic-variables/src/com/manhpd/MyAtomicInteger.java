package com.manhpd;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

public class MyAtomicInteger extends AtomicInteger {

    private static Unsafe unsafe = null;

    static {
        Field unsafeField;
        try {
            unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            unsafe = (Unsafe) unsafeField.get(null);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private AtomicInteger countIncrement = new AtomicInteger(0);

    public MyAtomicInteger(int counter) {
        super(counter);
    }

    public int myIncrementAndGet() {
        long valueOffset = 0L;

        try {
            valueOffset = unsafe.objectFieldOffset(AtomicInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException | SecurityException ex) {
            System.out.println(ex);
        }

        int v;
        do {
            v = unsafe.getIntVolatile(this, valueOffset);
            countIncrement.incrementAndGet();
        } while (!unsafe.compareAndSwapInt(this, valueOffset, v, v + 1));

        return v;
    }

    public int getIncrements() {
        return this.countIncrement.get();
    }

}
