#include <iostream>
#include <string.h>
using namespace std;

// 存储结构
typedef struct {
    float weight;    // 结点的权值
    int parent,lChild,rChild;  // 结点的双亲/左孩子/右孩子
} HTNode, *HuffmanTree; // 动态分配数组存储哈夫曼树

// 哈夫曼编码表
typedef char **HuffmanCode; // 动态分配数组存储哈夫曼编码表

// 选择两个双亲域为0且权值最小的结点,并返回他们的序号
void Select(HuffmanTree HT, int i, int *s1, int *s2) {
    (*s1) = 0;
    (*s2) = 0;
    for (int j = 1; j <=i ; ++j) {
        if (HT[j].parent == 0) {
            if ((*s1) == 0)
                (*s1) = j;
            else if (HT[*s1].weight >= HT[j].weight) {
                (*s2) = (*s1);
                (*s1) = j;
            }
            else if (HT[*s2].weight > HT[j].weight || (*s2) == 0)
                (*s2) = j;
        }

    }
}

// 创建哈夫曼树
void CreateHuffmanTree(HuffmanTree *HT,int n) {
    // w存放n个权值
    if(n<=1) return;
    int m = n * 2 - 1;
    (*HT) = new HTNode[m+1];    // 为赫夫曼树分配一组顺序空间
    for (int i = 1; i <= m; ++i) {
        (*HT)[i].parent=0;
        (*HT)[i].lChild=0;
        (*HT)[i].rChild=0;
    }
    cout << "请输入权值:";
    for (int j = 1; j <= n; ++j) {   // 输入前n个单元叶子节点的权值
        cin >> (*HT)[j].weight;
    }
    int s1,s2;
    for (int k = n+1; k <= m; ++k) {    // 通过n-1次选择/删除/合并来创建哈夫曼树
        Select(*HT,k-1,&s1,&s2);
        (*HT)[s1].parent = k;
        (*HT)[s2].parent = k;
        (*HT)[k].lChild = s1;
        (*HT)[k].rChild = s2;
        (*HT)[k].weight = (*HT)[s1].weight + (*HT)[s2].weight;
    }
}

// 遍历哈夫曼表
void show(HuffmanTree HT, int n) {
    for (int i = 1; i <=2*n-1 ; ++i) {
        cout << i << '\t' << HT[i].weight << '\t' << HT[i].parent << '\t' << HT[i].lChild << '\t' << HT[i].rChild << "\n";
    }
}

// 求哈夫曼编码
void CreateHuffmanCode(HuffmanTree HT, HuffmanCode *HC, int n) {
    (*HC) = new char *[n+1];    // 分配存储n个字符编码的编码空间
    char *cd = new char[n]; // 分配临时存储每个字符编码的动态数组空间
    cd[n-1] = '\0'; // 编码结束符
    for (int i = 1; i <= n; ++i) {  // 逐个字符求哈夫曼编码
        int start = n-1;    // start 开始时指向最后, 即编码结束符位置
        int th=i;
        int parent = HT[i].parent;
        while (parent != 0) {
            if (HT[parent].lChild == th) cd[--start] = '0';
            else cd[--start] = '1';
            th = parent;
            parent = HT[parent].parent; // 继续向上回溯
        }
        (*HC)[i] = new char[n-start];   // 为第i个字符编码分配空间
        strcpy((*HC)[i], &cd[start]);
    }
    delete cd;
}

int main() {
    HuffmanTree HT = nullptr;

    // 5 29 7 8 14 23 3 11
    CreateHuffmanTree(&HT,8);
    show(HT,8);
    HuffmanCode HC = nullptr;
    CreateHuffmanCode(HT, &HC, 8);
    for (int i = 1; i <= 8; ++i) {
        cout << HC[i];
        cout << "\n";
    }
    return 0;
}
