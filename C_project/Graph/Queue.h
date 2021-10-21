//
// Created by 11412 on 2021/5/8.
//
#include <iostream>
#ifndef GRAPH_CLASS_H
#define GRAPH_CLASS_H
using namespace std;
#define MAXQSiZE 100

enum Status {error=0,normal=1};
// ������
typedef struct Data{
    char ch;
} Data;
// �������ݵ�Ԫ
typedef int QueueData;
//����
typedef struct {
    QueueData* base; // �洢�ռ�Ļ���ַ
    int front;  // ͷָ��
    int rear;   // βָ��
} SqQueue;

class Queue {
public:
    // ѭ�����еĳ�ʼ��
    static Status InitQueue(SqQueue* Sq) {
        Sq->base = new QueueData[MAXQSiZE];  // Ϊ���з���һ���������ΪMAXSIZE������ռ�
        if (!Sq->base) return error;    // ����洢�ռ�ʧ��
        Sq->front = Sq->rear = 0;   // ͷָ���βָ����Ϊ0 ����Ϊ��
        return normal;
    }
    // ����г���
    static int QueueLength(SqQueue Sq) {
        return (Sq.rear - Sq.front + MAXQSiZE) % MAXQSiZE;
    }
    // ���
    static Status EnQueue(SqQueue *Sq, QueueData data) {
        if ((Sq->rear + 1) % MAXQSiZE == Sq->front) return error;    // ��ʾ����
        Sq->base[Sq->rear] = data;     // ��Ԫ�ط��ڶ�β
        Sq->rear = (Sq->rear + 1) % MAXQSiZE;   // ��βָ���һ
        return normal;
    }
    // ����
    static Status OutQueue(SqQueue *Sq, QueueData *data) {
        if (Sq->rear == Sq->front) return error;    // �ӿ�
        (*data) = Sq->base[Sq->front];  // ȡ����Ԫ��
        Sq->front = (Sq->front + 1) % MAXQSiZE; // ��ͷָ���һ
        return normal;
    }
    // ȡ��ͷԪ��
    static QueueData GetHead(SqQueue Sq) {
        if (Sq.front != Sq.rear)    // ���зǿ�
            return Sq.base[Sq.front];   // ���ض�ͷԪ��, ָ�벻��
    }
};

#endif //GRAPH_CLASS_H
