package main.algorithm;

public class BitMapDemo {
    // 保存数据的数组
    private byte[] bits;

    // 能够存储多少数据
    private int capacity;

    public BitMapDemo(int capacity) {
        this.capacity = capacity;
        // 1bits能够存储8个数据，那么capacity数据需要多少个bit呢，capacity/8 + 1，右移3位相当于除以8
        bits = new byte[(capacity >> 3) + 1];
    }

    public void add(int num) {
        // num/8得到byte[]的index
        int arrayIndex = num >> 3;

        // num%8得到在byte[index]的位置
        int position = num % 8;

        // 将1左移position后，那个位置自然就是1，然后和以前的数据做或操作，这样那个位置就替换成1，其他位置的数值不变。
        bits[arrayIndex] |= (1 << position);
    }

    public boolean contain(int num) {
        // 找到num在byte[]的index
        int arrayIndex = num >> 3;

        // num%8得到在byte[arrayIndex]的位置
        int position = num & 0x07;

        // 将1左移position后，那个位置自然就是1，然后和以前的数据做与操作，判断当前为是否为0或1
        return (bits[arrayIndex] & (1 << position)) != 0;
    }

    public void clear(int num) {
        // 找到num在byte[]的index
        int arrayIndex = num >> 3;

        // num%8得到在byte[arrayIndex]的位置
        int position = num & 0x07;

        // 将1左移position后，那个位置的值为1，然后取反，再与当前值做与操作，即可清除当前位的值（置为0）
        bits[arrayIndex] &= ~(1 << position);
    }

    public static void main(String[] args) {
        BitMapDemo bitmap = new BitMapDemo(100);
        bitmap.add(5);
        bitmap.add(7);
        bitmap.add(10);
        for (byte b : bitmap.bits) {
            System.out.print(Integer.toBinaryString(b) + " ");
        }

        System.out.println();

        boolean isExist5 = bitmap.contain(5);
        System.out.println("是否存在5：" + isExist5);

        boolean isExist6 = bitmap.contain(6);
        System.out.println("是否存在6：" + isExist6);

        boolean isExist7 = bitmap.contain(7);
        System.out.println("是否存在7：" + isExist7);

        boolean isExist10 = bitmap.contain(10);
        System.out.println("是否存在10：" + isExist10);

        bitmap.clear(10);
        boolean isExist10_2 = bitmap.contain(10);
        System.out.println("是否存在10：" + isExist10_2);
    }
}
