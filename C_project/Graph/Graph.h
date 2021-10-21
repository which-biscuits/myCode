//
// Created by 11412 on 2021/5/8.
//

#ifndef GRAPH_GRAPH_H

#include "Queue.h"
#define GRAPH_GRAPH_H
#define MAXINT 32767    // 极大权值
#define MVNum 20    // 极大顶点数
enum connect {NO=0,YES=1};  // 无权图的相邻表示
enum GraphKind {DG, DN, UDG, UDN};  // 图的类型 DG:邻接表 DN:邻接矩阵
enum visited {False=0, True=1}; // 判断顶点是否被访问
visited Visited[MVNum];

// 边的权值类型
typedef struct {
    union {
        connect conct;
        int weight;
    };
} ArcCell;
// 邻接矩阵表示图
typedef struct {
    Data vexs[MVNum];   // 顶点表
    ArcCell arcs[MVNum][MVNum];     // 邻接矩阵
    int vexNum,arcNum;      // 图的当前点数和边数
    GraphKind kind;
} AMGraph;

// 邻接表表示
typedef struct ArcNode{ // 边结点
    int adjvex;     // 该边所指向的顶点的位置
    struct ArcNode* nextArc;    // 指向下一条边的指针
    ArcCell info;   // 和边相关的信息
} ArcNode;
// 顶点信息
typedef struct VNode {
    Data data;
    ArcNode *firstNode; // 指向第一条边依附于该顶点的边的指针
} VNode;
// 邻接表图
typedef struct {
    VNode vertices[MVNum];
    int vexNum, arcNum; // 图的顶点数和边数
    GraphKind kind;
} ALGraph;

// 最小边权值定位
typedef struct {
    int adjvex;     // 最小权值顶点
    int lowcast;   // 最小边权值
} Closedge;
Closedge closedge[MVNum];

class Graph {
public:
    // 遍历Data元素
    static void showData(Data data) {
        cout << data.ch;
    }

    // 定位顶点在图中的位置
    static int LocateUDNVex(AMGraph G, char v) {
        for (int i = 0; i < G.vexNum; ++i) {
            if (G.vexs[i].ch == v)
                return i;
        }
        return -1;
    }
    // 创建邻接矩阵无向图
    static void CreateUDN(AMGraph* G) {
        // 采用邻接矩阵表示法, 创建无向网
        G->kind = UDN;
        cout << "请输入总顶点数, 总边数:";
        cin >> G->vexNum >> G->arcNum;
        cout << "请输入各顶点的信息:";
        for (int i = 0; i < G->vexNum; ++i) {
            cin >> G->vexs[i].ch;
        }
        for (int i = 0; i < G->vexNum; ++i) {
            for (int j = 0; j < G->vexNum; ++j) {
                G->arcs[i][j].weight = MAXINT;  // 初始化矩阵的权值
            }
        }
        cout << "请输入边的顶点以及权值:";
        char v1,v2;
        int w;
        for (int k = 0; k < G->arcNum; ++k) {
            cin >> v1 >> v2 >> w;
            int i = LocateUDNVex((*G), v1), j = LocateUDNVex((*G), v2);
            if (i == -1 || j == -1)
                cout << "顶点不存在,请重新输入";
            else {
                G->arcs[i][j].weight = w;
                G->arcs[j][i].weight = w;
            }
        }
    }
    // 遍历邻接矩阵无向图
    static void ShowUDN(AMGraph G) {
        cout << "顶点数:" << G.vexNum << " 边数:" << G.arcNum;
        cout << "\n图的类型:" << G.kind << "\n";
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
    // 采用邻接矩阵的深度优先搜索遍历
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
    // 采用邻接矩阵的广度优先搜索遍历
    static void BFS_AM(AMGraph G, int v) {
        for (auto & i : Visited) {  // 初始化Visited
            i = False;
        }
        cout << v << " ";   // 访问结点v
        showData(G.vexs[v]);
        cout << " / ";
        Visited[v] = True;
        SqQueue Sq;
        Queue::InitQueue(&Sq);  // 创建队列
        Queue::EnQueue(&Sq, v);     // v进队
        while (Sq.front != Sq.rear){    // 队列非空
            QueueData u;
            Queue::OutQueue(&Sq, &u);   // 取出队列元素
            for (int i = 0; i < G.vexNum; ++i) {
                if (G.arcs[u][i].weight != MAXINT && Visited[i] != True) {
                    cout << i << " ";   // 访问结点v
                    showData(G.vexs[i]);
                    cout << " / ";
                    Visited[i] = True;
                    Queue::EnQueue(&Sq, i);
                }
            }
        }
    }

    // 定位顶点在图中的位置
    static int LocateUDGVex(ALGraph G, char v) {
        for (int i = 0; i < G.vexNum; ++i) {
            if (G.vertices[i].data.ch == v)
                return i;
        }
        return -1;
    }
    // 创建邻接表无向图
    static void CreateUDG(ALGraph* G) {
        // 采用邻接表创建法, 创建无向图G
        G->kind = UDG;      // 输入表的类型
        cout << "\n请输入总顶点数, 总边数:";
        cin >> G->vexNum >> G->arcNum;  // 输入表的总顶点数以及总边数
        cout << "请输入各顶点的信息:";
        for (int i = 0; i < G->vexNum; ++i) {   // 依次输入顶点信息
            cin >> G->vertices[i].data.ch;
            G->vertices[i].firstNode = nullptr; // 初始化表头结点的指针域
        }
        cout << "请输入两个顶点及权值:";
        char v1,v2;
        int w;
        for (int k = 0; k < G->arcNum; ++k) {
            cin >> v1 >> v2 >> w;   // 依次输入 顶点以及权值
            int i = LocateUDGVex(*G,v1);
            int j = LocateUDGVex(*G,v2);
            auto* p1 = new ArcNode;  // 生成一个新的边结点
            p1->adjvex = j; // 邻接点序号为j
            p1->nextArc = G->vertices[i].firstNode;   // 前插
            p1->info.weight = w;
            G->vertices[i].firstNode = p1;
            auto* p2 = new ArcNode;
            p2->adjvex = i;
            p2->nextArc = G->vertices[j].firstNode;
            p2->info.weight = w;
            G->vertices[j].firstNode = p2;
        }
    }
    // 遍历邻接表无向图
    static void showUDG(ALGraph G) {
        cout << "图的类型为 : " << G.kind;
        cout << " 图的总顶点数为 : " << G.vexNum << " 图的总边数为 : " << G.arcNum;
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
    // 采用邻接表的深度优先搜索遍历
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
    // 采用邻接表的广度优先搜索算法
    static void BFS_AL(ALGraph G, int v) {
        for (auto & i : Visited) {
            i = False;
        }
        cout << v << " ";   // 访问结点v
        showData(G.vertices[v].data);
        cout << " / ";
        Visited[v] = True;
        SqQueue Sq;
        Queue::InitQueue(&Sq);  // 创建队列
        Queue::EnQueue(&Sq, v);     // v进队
        while (Sq.front != Sq.rear) {    // 队列非空
            QueueData u;
            Queue::OutQueue(&Sq, &u);   // 取出队列元素
            ArcNode* next = G.vertices[u].firstNode;    // 指向第一个弧
            while (next != nullptr) {
                if (Visited[next->adjvex] != True){
                    cout << next->adjvex << " ";   // 访问结点v
                    showData(G.vertices[next->adjvex].data);
                    cout << " / ";
                    Visited[next->adjvex] = True;
                    Queue::EnQueue(&Sq, next->adjvex);  // 进队
                }
                next = next->nextArc;
            }
        }
    }

    // 最小生成树的Prim算法
    static void MiniSpanTree_Prim(AMGraph G, int v) {
        for (auto & l : closedge) {
            l.lowcast = MAXINT;
        }
        for (int i = 0; i < G.vexNum; ++i)
            if (i !=  v)closedge[i] = {v, G.arcs[v][i].weight}; // 初始化closedge
        closedge[v].lowcast = 0;
        for (int j = 1; j < G.vexNum; ++j) {
            int k = Min(G);
            Data v0 = G.vexs[closedge[k].adjvex]; // u0为最小边的一个顶点
            Data v1 = G.vexs[k];
            cout << " (";showData(v0);cout << ",";showData(v1);cout << ") ";
            closedge[k].lowcast = 0; // 将顶点并入U集合
            for (int i = 0; i < G.vexNum; ++i) {
                if (G.arcs[i][k].weight < closedge[i].lowcast)  // 新并入顶点后重新选择最小边
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
