#include <iostream>
#define MAX_TREE_SIZE 100
using namespace std;

// 数据单元
typedef struct Data {
    char ch;
} Data;

// 双亲表示法结点
typedef struct PTNode {
    Data data;  // 数据域
    int parent; // 双亲位置域
} PTNode;

// 双亲表示法链表
typedef struct {
    PTNode nodes[MAX_TREE_SIZE];
    int n,r;    // 节点数和根节点的位置
} PTree;

// 孩子表示法结点
typedef struct CTNode {
    int child;
    CTNode *next;
} *ChildPtr;

// 双亲结点结构
typedef struct {
    Data data;
    ChildPtr firstChild;    // 孩子链的头指针
} CTBox;

// 孩子表示法树结构
typedef struct {
    CTBox nodes[MAX_TREE_SIZE];
    int n,r;    // 结点数和根节点的位置
} CTree;

// 孩子兄弟表示法
typedef struct CSNode {
    Data data;
    CSNode *firstChild;
    CSNode *nextSibling;
} CSNode,*CSTree;

// 访问结点
void visit(Data data) {
    cout << data.ch;
}
int main() {
    std::cout << "Hello, World!" << std::endl;
    return 0;
}