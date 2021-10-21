#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAXSIZE 100

// 数据单元
typedef struct Data {
    char ch;
} Data;
// 顺序存储结构
typedef Data SqBiTree[MAXSIZE];
// 二叉链式存储结构
typedef struct BiTNode {
    Data data;
    struct BiTNode *lchild,*rchild;
} BiTNode, *BiTree;
// 三叉链式存储结构
typedef struct B3TNode {
    Data data;
    struct B3TNode *lchild,*rchild,*parent;
} B3TNode, *B3Tree;
// 指针线索二叉树
typedef struct BiThrNode {
    Data data;
    struct BiThrNode *lchild,*rchild;   // 左右指针
    struct BiThrNode *pred,*succ;   // 前趋,后继指针
} BiThrNode, *BiThrTree;
// 标志线索二叉树
typedef struct ThrNode {
    Data data;
    struct ThrNode *lchild,*rchild;
    int LTag,RTag;
} ThrNode,*ThrTree;

// 访问数据单元
void visit(Data data) {
    printf_s("%c",data.ch);
}
// 先序遍历的顺序建立二叉链表
void CreateBiTree(BiTree *T, char inputs[],int *index){
    int in = (*index)++;
    char input = inputs[in];
//    printf_s("%d,%c\n",in,input);
    if (input == '#') {*T = NULL;}    // 递归结束,返回空指针(建空树)
    else{
        *T = (BiTree) malloc(sizeof(BiTNode));
        (*T)->data.ch = input;
//        printf_s("%c lchild-started\n",input);
        CreateBiTree(&((*T)->lchild),inputs,index);    // 递归创建左子树
//        printf_s("%c rchild-started\n",input);
        CreateBiTree(&((*T)->rchild),inputs,index);    // 递归创建右子树
    }
}
// 中序遍历二叉树
void InOrderTraverse(BiTree T){
    if (T) {    // 若二叉树非空
        InOrderTraverse(T->lchild); // 递归遍历左子树
        visit(T->data); // 访问根节点
        InOrderTraverse(T->rchild); // 递归遍历右子树
    }
}
// 先序遍历二叉树
void PreOrderTraverse(BiTree T){
    if (T) {    // 若二叉树非空
        visit(T->data); // 访问根节点
        PreOrderTraverse(T->lchild); // 递归遍历左子树
        PreOrderTraverse(T->rchild); // 递归遍历右子树
    }
}
// 统计叶子结点个数,先序
void CountLeaf(BiTree T, int *Count){
    if (T){
        if((!T->lchild)&&(!T->rchild))
            (*Count)++;
        CountLeaf(T->lchild,Count);
        CountLeaf(T->rchild,Count);
    }
}
// 求二叉树的深度
int Depth(BiTree T){
    if (!T) return 0;
    else {
        int dLeft = Depth(T->lchild);
        int dRight = Depth(T->rchild);
        return  (dLeft>dRight?dLeft:dRight) + 1;
    }
}
// 复制二叉树
void CopyTree(BiTree T, BiTree *new){
    if(T == NULL) *new = NULL;
    else{
        *new = (BiTree) malloc(sizeof(BiTNode));
        (*new)->data = T->data;
        CopyTree(T->lchild,&((*new)->lchild));
        CopyTree(T->rchild,&((*new)->rchild));
    }
}
// 查找字母位置
int FindInRoot(const char ino[], char root, int low, int high){
    for(int i = low;i< high;i++){
        if(ino[i]==root)
            return i;
    }

}
// 先序+中序恢复二叉树
void restore(BiTree *T, char pre[], char ino[], int ps, int low, int high){
    if(low>=high) *T = NULL;
    else {
        *T = (BiTree) malloc(sizeof(BiTNode));
        (*T)->data.ch = pre[ps];
        int find = FindInRoot(ino,pre[ps],low,high);    // 在中序序列中查找根节点的位置
        restore(&((*T)->lchild),pre,ino,ps+1,low,find);   // 恢复左子树
        restore(&((*T)->rchild),pre,ino,ps+1+(high-low)/2,find+1,high); // 恢复右子树
    }
}
// 建立中序线索二叉树
void InThreading(BiThrTree *H,BiTree T,BiThrTree *pre){
    if (T){
        *H = (BiThrTree) malloc(sizeof(BiThrNode));
        InThreading(&((*H)->lchild),T->lchild,pre); // 左子树线索化
        (*pre)->succ = (*H);
        (*H)->pred = (*pre);
        (*H)->data = T->data;
        (*pre) = (*H);
        InThreading(&((*H)->rchild),T->rchild,pre); // 左子树线索化
    }
}
void InOrderThreading(BiThrTree *H,BiTree T){
    *H = (BiThrTree) malloc(sizeof(BiThrNode));     // 头结点
    (*H)->data.ch = ' ';
    if (!T){
        (*H)->pred = (*H);
        (*H)->succ = (*H);
    } else {
        BiThrTree pre = (*H);
        InThreading(&((*H)->lchild),T,&pre);
    }
}

// 中序线索二叉树的遍历
void InOrder(BiThrTree H){
    BiThrTree p = H->succ;
    while (p!=H){
        visit(p->data);
        p = p->succ;
    }
}

// 中序标志二叉树的建立
void Threading(ThrTree *H,BiTree T,ThrTree *pre) {
    if(T){
        (*H) = (ThrTree) malloc(sizeof(ThrNode));
        (*H)->rchild = NULL;
        (*H)->lchild = NULL;
        (*H)->RTag = 0;
        (*H)->LTag = 0;
        Threading(&((*H)->lchild),T->lchild,pre);
        if(!T->lchild){
            (*H)->LTag = 1;
            (*H)->lchild = (*pre);
        }
        if (!(*pre)->rchild){
            (*pre)->RTag = 1;
            (*pre)->rchild = (*H);
        }
        (*pre) = (*H);
        Threading(&((*H)->rchild),T->rchild,pre);
    }
}
void OrderThreading(ThrTree *H, BiTree T) {
    (*H) = (ThrTree) malloc(sizeof(ThrNode));
    (*H)->lchild = NULL;
    (*H)->rchild = NULL;
    (*H)->LTag = 0;
    (*H)->RTag = 1;
    if(!T) (*H)->lchild = (*H);
    else {
        ThrTree pre = (*H);
        pre->rchild = NULL;
        pre->lchild = NULL;
        Threading(&((*H)->lchild),T,&pre);
        pre->rchild = (*H);
        pre->RTag = 1;
        (*H)->rchild = pre;
    }
}

// 中序标志二叉树的遍历
void OrderTraverse_Thr(ThrTree H){
    ThrTree p = H->lchild;
    while (p!=H){
        while (p->LTag==0){
            p = p->lchild;
        }
        visit(p->data);
        while (p->RTag==1 && p->rchild!=H){
            p = p->rchild;
            visit(p->data);
        }
        p = p->rchild;
    }
}

int main() {
//    // 顺序存储结构
//    SqBiTree bt;
//    bt[99].ch = 'A';
//    printf_s("SqBiTree [99] = %c\n",bt[99].ch);
//
//    // 二叉链式存储结构
//    BiTNode biTNode;
//    biTNode.data.ch = 'a';
//    printf_s("BiTNode.data = ");
//    visit(biTNode.data);
//    printf_s("\n");
//
//    // 三叉链式存储单元
//    B3Tree b3Tree;
//    B3TNode b3TNode;
//    b3TNode.data.ch = 'b';
//    b3Tree = &b3TNode;
//    printf_s("B3Tree->data = ");
//    visit(b3Tree->data);
//    printf_s("\n");

    // 二叉链表的创建
    BiTree T=NULL;
//    char input[] = "ABC##DE#G##F###";
    char input[] = "ABDH##I##EG##K##CFL##M##GN##O##";
    int index = 0;
    CreateBiTree(&T,input,&index);
//
    // 中序遍历二叉树
    printf("\n中序遍历二叉树:");
    InOrderTraverse(T);
    // 先序遍历二叉树
    printf("\n先序遍历二叉树:");
    PreOrderTraverse(T);

//    // 先序统计叶子结点个数
//    int Count = 0;
//    CountLeaf(T,&Count);
//    printf_s("\n叶子结点个数为: %d",Count);
//
//    // 后序二叉树的深度
//    int depth = 0;
//    depth = Depth(T);
//    printf_s("\n二叉树的深度为: %d",depth);
//
//    // 复制二叉树
//    BiTree new;
//    CopyTree(T,&new);
//    printf_s("\n先序遍历T二叉树");
//    InOrderTraverse(T);
//    printf_s("\n先序遍历new二叉树");
//    InOrderTraverse(new);
//
//    // 先序+中序恢复二叉树
//    BiTree reTree;
//    char pre[] = "ABDHIEGKCFLMGNO";
//    char ino[] = "HDIBGEKALFMCNGO";
//    restore(&reTree,pre,ino,0,0,strlen(ino));
//    printf_s("\n恢复二叉树的中序遍历二叉树:");
//    InOrderTraverse(reTree);
//    printf_s("\n恢复二叉树的先序遍历二叉树:");
//    PreOrderTraverse(reTree);
    BiThrTree H;
    InOrderThreading(&H,T);
    // 中序遍历线索二叉树
    printf_s("\n中序遍历线索二叉树:");
    InOrder(H);

    ThrTree Thr;
    OrderThreading(&Thr,T);
    // 中序遍历标志二叉树
    printf_s("\n中序遍历标志二叉树:");
    OrderTraverse_Thr(Thr);


    return 0;
}