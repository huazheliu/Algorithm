package cn.xyzliu.array;

/**
 * MyArray类用Java语言描述了基本数据类型数组的增删查改，有以下方面需要注意：
 * 1、在删除功能中无法删除最后一个元素
 * 2、添加元素、修改元素和删除元素最开始都有一个数组越界的判断，其内容不尽相同
 * 添加的判断为if(index<0||index>size)  若给一个空数组的添加第一个元素，此时size为0，index也为0，所以添加元素时有可能相等
 * 而删除和修改的判断为if(index<0||index>=size)  因为数组下标范围为0～size-1，因此下标不可能等于size
 */
public class MyArray {
    //成员数组变量
    private int[] array;
    //数组实际元素个数
    private int size;

    /**
     * 有参构造，生成一个容量为capacity的数组，并将数组元素个数设为0
     * @param capacity 数组容量
     */
    public  MyArray(int capacity){
        array = new int[capacity];
        size = 0;
    }

    /**
     * 插入元素
     * @param index 元素插入位置
     * @param element 将要插入的元素
     */
    public void insert(int index, int element){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("超出数组实际容量");
        }
        if(index > array.length){
            resize(); //当要插入的下标超过数组最大容量时，给数组扩容
        }
        //从数组最后一个元素开始，从右向左循环，将元素逐个向右移动一个位置
        //当数组中有size个元素时，数组下标为0～(size-1)，因此最后一个元素的下标为size-1
        for (int i = size - 1; i >= index ; i--) {
            array[i + 1] = array[i];
        }
        //将元素放入腾空的位置
        array[index] = element;
        size++; //数组元素数量加1
    }

    /**
     * 删除数组元素
     * 删除数组元素的一个缺陷：无法删除最后一个元素
     * @param index 将要删除元素的下标
     */
    public int delete(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("超出数组实际范围！");
        }
        //拿到要删除的元素，并将其返回到主函数中
        int deletedElement = array[index];
        //从index后面的第一个元素开始移动，从左向右循环，每一元素都向前移动一个位置
        //i必须小于size - 1，否则会出现超出数组范围的错
        //例如一个数组的容量为6，且此时size为6，则数组的下标范围为0～5
        //当index为5时，执行到array[5] = array[6]时则会报数组越界的错误
        if(index == size - 1){
            System.out.println("抱歉，无法删除数组最后一个元素");
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        if(index != size - 1){
            size--; //数组元素个数减1
        }
        return deletedElement;
    }

    /**
     * 修改数组元素
     * @param index 要被修改元素的下标
     * @param element 修改后的元素
     */
    public void modify(int index, int element){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("超出数组实际范围！");
        }
        array[index] = element;
    }

    /**
     * 获取数组元素
     * @param index 将要获取元素的下标
     * @return 获取到的元素
     */
    public int getElement(int index){
        return array[index];
    }

    /**
     * 数组扩容
     */
    public void resize(){
        int[] newArray= new int[array.length * 2];
        //从旧数组复制到新数组
        System.arraycopy(array,0,newArray,0,array.length);
        array = newArray;
    }

    /**
     * 输出数组
     */
    public void output(){
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args) {
        //利用类的有参构造方法创建一个对象，同时生成一个容量为6的空数组
        MyArray myArray = new MyArray(6);
        //调用insert方法添加元素
        myArray.insert(0,3);
        myArray.insert(0,2);
        myArray.insert(0,1);
        myArray.insert(3,6);
        myArray.insert(4,7);
        myArray.insert(5,8);
        myArray.output();
        System.out.println("将下标为2的元素修改为5");
        myArray.modify(2,5);
        myArray.output();
        System.out.println("删除下标为5的元素");
        myArray.delete(5);
        myArray.output();
        System.out.println("获取下标为4的元素");
        System.out.println(myArray.getElement(4));
        myArray.output();
    }
}
