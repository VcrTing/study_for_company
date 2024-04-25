package org.example.mainThread;

public class CasTest {
    public static boolean cas(int v, int o, int n) {
        if (v != o) return false;
        v = n; return true;
    }
    public static int casInt(int v, int o, int n) {
        if (v != o) return v; return n;
    }

    public static void main(String[] args) throws InterruptedException {
        Int vv = new Int();
        new Thread(() -> {
            for (int i = 0; i < 10000; i++) { vv.selfLockAdd(1); } }).start();
        Thread.sleep(2000);
        System.out.println("标准答案应该是 30000，但是你没有借用 unsafe 类去做 cas，答案 < 30000，但是如果用了，就是 标准答案");
        System.out.println(vv.get());
    }

}

/**
*
new Thread(() -> {
for (int i = 0; i < 10000; i++) {
// src[0] = selfLockAdd(src[0], src[0] + 1);
vv.selfLockAdd(1);
}
}).start();
new Thread(() -> {
for (int i = 0; i < 10000; i++) {
vv.selfLockAdd(1);
}
}).start();
*/