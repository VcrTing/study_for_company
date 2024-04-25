package org.example.mainThread;

import sun.misc.Unsafe;

public class Int {
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private volatile int v = 0;
    public int get() { return v; }
    private static long offsetAddr = 0;
    static {
        try {
            offsetAddr = unsafe.objectFieldOffset(CasTest.class.getDeclaredField("v"));
        } catch (Exception ignored) { }
    }
    // 把旧 int 值，新增一个新的值
    public void selfLockAdd(int num) {
        v = unsafe.getAndAddInt(this, offsetAddr, num);
    }
}
