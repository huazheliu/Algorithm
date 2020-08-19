package cn.xyzliu.linkedlist;

import java.lang.module.FindException;

public class MyLinkedList {
    //定义头节点指针，该指针永远都指向第一个节点
    private Node head;

    //定义尾节点指针，该指针永远都指向最后一个节点
    private Node last;

    //链表实际长度
    private int size;

    //定义一个静态内部类，表示一个节点
    public static class Node{
        int data;  //节点存放的数据
        Node next; //该节点所指向下一节点的位置

        public Node(int data){
            this.data = data;
        }
    }

    /**
     * 链表插入元素
     * @param data 插入元素
     * @param index 插入位置
     */
    public void insert(int data, int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("超出链表实际长度！");
        }
        Node insertNode = new Node(data);
        if(size == 0){
            //空链表
            head = insertNode;  //因为是空链表，因此第一个节点插入后头指针和尾指针都会指向它
            last = insertNode;
        } else if(index == 0){
            //头部插入
            /*思路：第一步：把新节点的next指针指向原先的头节点
                    第二步：把新节点变为链表的头节点*/

            //将当前链表的头节点地址赋值给将要插入节点的next指针，如此新插入的节点便插在了链表的最前面
            insertNode.next = head; //insertNode.next表示insertNode节点所指向的下一个节点的地址，head表示头节点的地址。

            //将新插入的insertNode节点的地址赋值给head，使head永远指向头节点
            head = insertNode;
        } else if (size == index){
            //尾部插入
            /*思路：第一步：将尾节点的next指针指向新插入的节点即可
                    第二步：将新节点变为尾节点*/

            //将当前链表的尾节点的next指针指向新插入的insertNode节点的地址，如此新插入的节点便插在了链表的最后
            last.next = insertNode; //last.next表示链表尾节点指向的下一个节点的地址，insertNode表示新插入节点的地址

            //将新插入节点的地址赋值为尾节点，使之永远指向最后一个节点
            last = insertNode;
        } else{
            //中间插入
            /*思路：第一步：新节点的next指针指向插入位置的节点
                    第二步：插入位置前的节点的next指针指向新插入的节点*/
            //获取插入位置前的节点
            Node preNode = getNode(index - 1);
            //新插入节点的next指针指向插入节点的位置
            insertNode.next = preNode.next;  //等同于preNode.next = getNode(index);
            //插入位置前的节点的next指针指向新插入的节点
            preNode.next = insertNode;
        }
        size++;  //链表节点数加1
    }

    /**
     * 查找节点
     * @param index 需要查找节点的下标
     * @return 返回查找到的节点
     */
    public Node getNode(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("超出链表节点范围！");
        }
        Node tmp = head;
        for (int i = 0; i < index; i++) {
            //将下一节点的地址赋值给当前节点，以达到遍历的效果
            tmp = tmp.next;  //tmp表示当前节点的地址，tmp.next表示当前节点所指向下一节点的地址
        }
        return tmp;//返回查找到的节点
    }

    /**
     * 修改节点数据
     * @param data 修改后的数据
     * @param index 需要修改的节点的下标
     */
    public void modify(int data, int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("超出链表节点范围！");
        }
        Node tmp = head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        tmp.data = data;
    }

    /**
     * 链表删除节点
     * @param index 被删除节点的下标
     * @return 返回被删除的节点
     */
    public Node remove(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("超出链表节点范围！");
        }
        Node removeNode = null;
        if(index == 0){
            //删除头节点  思路：将链表的头节点设为原先头节点的next指针即可
            //将头节点赋值给removeNode以便返回给调用函数
            removeNode = head;
            //将链表的头节点设为原先头节点的next指针
            head = head.next;
        } else if(index == size - 1){
            //删除尾节点  思路：将倒数第二个节点的next指针指向空即可

            Node preNode = getNode(index - 1); //获取倒数二个节点
            removeNode = preNode.next; //将要删除节点地址赋值给removeNode,以便返回给调用函数

            //使得最后一个节点的地址赋值给last，使得last永远指向最后一个节点。
            last = preNode;
        } else{
            //删除中间节点  思路：将要删除节点的前置节点的next指针指向要删除接节点的下一个指针即可

            Node preNode = getNode(index - 1);  //获取前置节点
            Node nextNode = preNode.next.next;  //获取后置节点
            removeNode = preNode.next;  //将要删除节点地址赋值给removeNode,以便返回给调用函数
            preNode.next = nextNode;
        }
        size--;  //链表节点数减1
        return removeNode;
    }

    /**
     * 输出链表所保存的数据
     */
    public void output(){
        Node tmp = head;
        while (tmp != null){
            System.out.println(tmp.data);
            tmp = tmp.next;
        }
    }

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.insert(3,0);
        myLinkedList.insert(7,1);
        myLinkedList.insert(9,2);
        myLinkedList.insert(5,3);
        myLinkedList.insert(6,1);
        myLinkedList.output();
        System.out.println("修改下标为0的节点的数据为10");
        myLinkedList.modify(10,0);
        myLinkedList.output();
        System.out.println("删除下标为0的节点");
        myLinkedList.remove(0);
        myLinkedList.output();
    }
}
