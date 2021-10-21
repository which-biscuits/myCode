//
// Created by 11412 on 2021/5/8.
//

#ifndef GRAPH_GRAPH_H

#include "Queue.h"
#define GRAPH_GRAPH_H
#define MAXINT 32767    // ����Ȩֵ
#define MVNum 20    // ���󶥵���
enum connect {NO=0,YES=1};  // ��Ȩͼ�����ڱ�ʾ
enum GraphKind {DG, DN, UDG, UDN};  // ͼ������ DG:�ڽӱ� DN:�ڽӾ���
enum visited {False=0, True=1}; // �ж϶����Ƿ񱻷���
visited Visited[MVNum];

// �ߵ�Ȩֵ����
typedef struct {
    union {
        connect conct;
        int weight;
    };
} ArcCell;
// �ڽӾ����ʾͼ
typedef struct {
    Data vexs[MVNum];   // �����
    ArcCell arcs[MVNum][MVNum];     // �ڽӾ���
    int vexNum,arcNum;      // ͼ�ĵ�ǰ�����ͱ���
    GraphKind kind;
} AMGraph;

// �ڽӱ��ʾ
typedef struct ArcNode{ // �߽��
    int adjvex;     // �ñ���ָ��Ķ����λ��
    struct ArcNode* nextArc;    // ָ����һ���ߵ�ָ��
    ArcCell info;   // �ͱ���ص���Ϣ
} ArcNode;
// ������Ϣ
typedef struct VNode {
    Data data;
    ArcNode *firstNode; // ָ���һ���������ڸö���ıߵ�ָ��
} VNode;
// �ڽӱ�ͼ
typedef struct {
    VNode vertices[MVNum];
    int vexNum, arcNum; // ͼ�Ķ������ͱ���
    GraphKind kind;
} ALGraph;

// ��С��Ȩֵ��λ
typedef struct {
    int adjvex;     // ��СȨֵ����
    int lowcast;   // ��С��Ȩֵ
} Closedge;
Closedge closedge[MVNum];

class Graph {
public:
    // ����DataԪ��
    static void showData(Data data) {
        cout << data.ch;
    }

    // ��λ������ͼ�е�λ��
    static int LocateUDNVex(AMGraph G, char v) {
        for (int i = 0; i < G.vexNum; ++i) {
            if (G.vexs[i].ch == v)
                return i;
        }
        return -1;
    }
    // �����ڽӾ�������ͼ
    static void CreateUDN(AMGraph* G) {
        // �����ڽӾ����ʾ��, ����������
        G->kind = UDN;
        cout << "�������ܶ�����, �ܱ���:";
        cin >> G->vexNum >> G->arcNum;
        cout << "��������������Ϣ:";
        for (int i = 0; i < G->vexNum; ++i) {
            cin >> G->vexs[i].ch;
        }
        for (int i = 0; i < G->vexNum; ++i) {
            for (int j = 0; j < G->vexNum; ++j) {
                G->arcs[i][j].weight = MAXINT;  // ��ʼ�������Ȩֵ
            }
        }
        cout << "������ߵĶ����Լ�Ȩֵ:";
        char v1,v2;
        int w;
        for (int k = 0; k < G->arcNum; ++k) {
            cin >> v1 >> v2 >> w;
            int i = LocateUDNVex((*G), v1), j = LocateUDNVex((*G), v2);
            if (i == -1 || j == -1)
                cout << "���㲻����,����������";
            else {
                G->arcs[i][j].weight = w;
                G->arcs[j][i].weight = w;
            }
        }
    }
    // �����ڽӾ�������ͼ
    static void ShowUDN(AMGraph G) {
        cout << "������:" << G.vexNum << " ����:" << G.arcNum;
        cout << "\nͼ������:" << G.kind << "\n";
        for (int i = 0; i < G.vexNum; ++i) {
            showData(G.vexs[i]);
            cout << " ";
        }
        cout << "\n";
        for (int i = 0; i < G.vexNum; ++i) {
            for (int j = 0; j < G.vexNum; ++j) {
                printf_s("%d\t",G.arcs[i][j]);
            }
            printf_s("\n");
        }
    }
    // �����ڽӾ�������������������
    static void DFS_AM(AMGraph G, int v) {
        cout << v << " ";
        showData(G.vexs[v]);
        cout << " / ";
        Visited[v] = True;
        for (int i = 0; i < G.vexNum; ++i) {
            if ((G.arcs[v][i].weight != MAXINT) && (Visited[i] != True))
                DFS_AM(G,i);
        }
    }
    // �����ڽӾ���Ĺ��������������
    static void BFS_AM(AMGraph G, int v) {
        for (auto & i : Visited) {  // ��ʼ��Visited
            i = False;
        }
        cout << v << " ";   // ���ʽ��v
        showData(G.vexs[v]);
        cout << " / ";
        Visited[v] = True;
        SqQueue Sq;
        Queue::InitQueue(&Sq);  // ��������
        Queue::EnQueue(&Sq, v);     // v����
        while (Sq.front != Sq.rear){    // ���зǿ�
            QueueData u;
            Queue::OutQueue(&Sq, &u);   // ȡ������Ԫ��
            for (int i = 0; i < G.vexNum; ++i) {
                if (G.arcs[u][i].weight != MAXINT && Visited[i] != True) {
                    cout << i << " ";   // ���ʽ��v
                    showData(G.vexs[i]);
                    cout << " / ";
                    Visited[i] = True;
                    Queue::EnQueue(&Sq, i);
                }
            }
        }
    }

    // ��λ������ͼ�е�λ��
    static int LocateUDGVex(ALGraph G, char v) {
        for (int i = 0; i < G.vexNum; ++i) {
            if (G.vertices[i].data.ch == v)
                return i;
        }
        return -1;
    }
    // �����ڽӱ�����ͼ
    static void CreateUDG(ALGraph* G) {
        // �����ڽӱ�����, ��������ͼG
        G->kind = UDG;      // ����������
        cout << "\n�������ܶ�����, �ܱ���:";
        cin >> G->vexNum >> G->arcNum;  // �������ܶ������Լ��ܱ���
        cout << "��������������Ϣ:";
        for (int i = 0; i < G->vexNum; ++i) {   // �������붥����Ϣ
            cin >> G->vertices[i].data.ch;
            G->vertices[i].firstNode = nullptr; // ��ʼ����ͷ����ָ����
        }
        cout << "�������������㼰Ȩֵ:";
        char v1,v2;
        int w;
        for (int k = 0; k < G->arcNum; ++k) {
            cin >> v1 >> v2 >> w;   // �������� �����Լ�Ȩֵ
            int i = LocateUDGVex(*G,v1);
            int j = LocateUDGVex(*G,v2);
            auto* p1 = new ArcNode;  // ����һ���µı߽��
            p1->adjvex = j; // �ڽӵ����Ϊj
            p1->nextArc = G->vertices[i].firstNode;   // ǰ��
            p1->info.weight = w;
            G->vertices[i].firstNode = p1;
            auto* p2 = new ArcNode;
            p2->adjvex = i;
            p2->nextArc = G->vertices[j].firstNode;
            p2->info.weight = w;
            G->vertices[j].firstNode = p2;
        }
    }
    // �����ڽӱ�����ͼ
    static void showUDG(ALGraph G) {
        cout << "ͼ������Ϊ : " << G.kind;
        cout << " ͼ���ܶ�����Ϊ : " << G.vexNum << " ͼ���ܱ���Ϊ : " << G.arcNum;
        cout << "\n";
        for (int i = 0; i < G.vexNum; ++i) {
            cout << i << " ";
            showData(G.vertices[i].data);
            ArcNode* next = G.vertices[i].firstNode;
            while (next!=nullptr) {
                cout << " / ";
                showData(G.vertices[next->adjvex].data);
                cout << " " << next->info.weight;
                next = next->nextArc;
            }
            cout << "\n";
        }
    }
    // �����ڽӱ�����������������
    static void DFS_AL(ALGraph G, int v) {
        cout << v << " ";
        showData(G.vertices[v].data);
        cout << " / ";
        Visited[v] = True;
        ArcNode* next = G.vertices[v].firstNode;
        while (next != nullptr) {
            if (Visited[next->adjvex] != True)
                DFS_AL(G, next->adjvex);
            next = next->nextArc;
        }
    }
    // �����ڽӱ�Ĺ�����������㷨
    static void BFS_AL(ALGraph G, int v) {
        for (auto & i : Visited) {
            i = False;
        }
        cout << v << " ";   // ���ʽ��v
        showData(G.vertices[v].data);
        cout << " / ";
        Visited[v] = True;
        SqQueue Sq;
        Queue::InitQueue(&Sq);  // ��������
        Queue::EnQueue(&Sq, v);     // v����
        while (Sq.front != Sq.rear) {    // ���зǿ�
            QueueData u;
            Queue::OutQueue(&Sq, &u);   // ȡ������Ԫ��
            ArcNode* next = G.vertices[u].firstNode;    // ָ���һ����
            while (next != nullptr) {
                if (Visited[next->adjvex] != True){
                    cout << next->adjvex << " ";   // ���ʽ��v
                    showData(G.vertices[next->adjvex].data);
                    cout << " / ";
                    Visited[next->adjvex] = True;
                    Queue::EnQueue(&Sq, next->adjvex);  // ����
                }
                next = next->nextArc;
            }
        }
    }

    // ��С��������Prim�㷨
    static void MiniSpanTree_Prim(AMGraph G, int v) {
        for (auto & l : closedge) {
            l.lowcast = MAXINT;
        }
        for (int i = 0; i < G.vexNum; ++i)
            if (i !=  v)closedge[i] = {v, G.arcs[v][i].weight}; // ��ʼ��closedge
        closedge[v].lowcast = 0;
        for (int j = 1; j < G.vexNum; ++j) {
            int k = Min(G);
            Data v0 = G.vexs[closedge[k].adjvex]; // u0Ϊ��С�ߵ�һ������
            Data v1 = G.vexs[k];
            cout << " (";showData(v0);cout << ",";showData(v1);cout << ") ";
            closedge[k].lowcast = 0; // �����㲢��U����
            for (int i = 0; i < G.vexNum; ++i) {
                if (G.arcs[i][k].weight < closedge[i].lowcast)  // �²��붥�������ѡ����С��
                    closedge[i] = {k, G.arcs[i][k].weight};
            }
        }
    }
    static int Min(AMGraph G) {
        int minn = 0;
        for (int i = 0; i < G.vexNum; ++i) {
            if ((closedge[i].lowcast < closedge[minn].lowcast && closedge[i].lowcast != 0) || closedge[minn].lowcast == 0)
                minn = i;
        }
        return minn;
}
};

#endif //GRAPH_GRAPH_H
