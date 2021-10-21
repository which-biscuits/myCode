//
// Created by 11412 on 2021/5/8.
//
#include <iostream>
#ifndef GRAPH_CLASS_H
#define GRAPH_CLASS_H
using namespace std;
#define MAXQSiZE 100

enum Status {error=0,normal=1};
// 数据域
typedef struct Data{
    char ch;
} Data;
// 队列数据单元
typedef int QueueData;
//队列
typedef struct {
    QueueData* base; // 存储空间的基地址
    int front;  // 头指针
    int rear;   // 尾指针
} SqQueue;

class Queue {
public:
    // 循环队列的初始化
    static Status InitQueue(SqQueue* Sq) {
        Sq->base = new QueueData[MAXQSiZE];  // 为队列分配一个最大容量为MAXSIZE的数组空间
        if (!Sq->base) return error;    // 分配存储空间失败
        Sq->front = Sq->rear = 0;   // 头指针和尾指针置为0 队列为空
        return normal;
    }
    // 求队列长度
    static int QueueLength(SqQueue Sq) {
        return (Sq.rear - Sq.front + MAXQSiZE) % MAXQSiZE;
    }
    // 入队
    static Status EnQueue(SqQueue *Sq, QueueData data) {
        if ((Sq->rear + 1) % MAXQSiZE == Sq->front) return error;    // 表示队满
        Sq->base[Sq->rear] = data;     // 新元素放在队尾
        Sq->rear = (Sq->rear + 1) % MAXQSiZE;   // 队尾指针加一
        return normal;
    }
    // 出队
    static Status OutQueue(SqQueue *Sq, QueueData *data) {
        if (Sq->rear == Sq->front) return error;    // 队空
        (*data) = Sq->base[Sq->front];  // 取队首元素
        Sq->front = (Sq->front + 1) % MAXQSiZE; // 队头指针加一
        return normal;
    }
    // 取队头元素
    static QueueData GetHead(SqQueue Sq) {
        if (Sq.front != Sq.rear)    // 队列非空
            return Sq.base[Sq.front];   // 返回队头元素, 指针不变
    }
};

#endif //GRAPH_CLASS_H
