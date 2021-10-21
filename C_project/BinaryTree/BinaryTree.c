#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAXSIZE 100

// ���ݵ�Ԫ
typedef struct Data {
    char ch;
} Data;
// ˳��洢�ṹ
typedef Data SqBiTree[MAXSIZE];
// ������ʽ�洢�ṹ
typedef struct BiTNode {
    Data data;
    struct BiTNode *lchild,*rchild;
} BiTNode, *BiTree;
// ������ʽ�洢�ṹ
typedef struct B3TNode {
    Data data;
    struct B3TNode *lchild,*rchild,*parent;
} B3TNode, *B3Tree;
// ָ������������
typedef struct BiThrNode {
    Data data;
    struct BiThrNode *lchild,*rchild;   // ����ָ��
    struct BiThrNode *pred,*succ;   // ǰ��,���ָ��
} BiThrNode, *BiThrTree;
// ��־����������
typedef struct ThrNode {
    Data data;
    struct ThrNode *lchild,*rchild;
    int LTag,RTag;
} ThrNode,*ThrTree;

// �������ݵ�Ԫ
void visit(Data data) {
    printf_s("%c",data.ch);
}
// ���������˳������������
void CreateBiTree(BiTree *T, char inputs[],int *index){
    int in = (*index)++;
    char input = inputs[in];
//    printf_s("%d,%c\n",in,input);
    if (input == '#') {*T = NULL;}    // �ݹ����,���ؿ�ָ��(������)
    else{
        *T = (BiTree) malloc(sizeof(BiTNode));
        (*T)->data.ch = input;
//        printf_s("%c lchild-started\n",input);
        CreateBiTree(&((*T)->lchild),inputs,index);    // �ݹ鴴��������
//        printf_s("%c rchild-started\n",input);
        CreateBiTree(&((*T)->rchild),inputs,index);    // �ݹ鴴��������
    }
}
// �������������
void InOrderTraverse(BiTree T){
    if (T) {    // ���������ǿ�
        InOrderTraverse(T->lchild); // �ݹ����������
        visit(T->data); // ���ʸ��ڵ�
        InOrderTraverse(T->rchild); // �ݹ����������
    }
}
// �������������
void PreOrderTraverse(BiTree T){
    if (T) {    // ���������ǿ�
        visit(T->data); // ���ʸ��ڵ�
        PreOrderTraverse(T->lchild); // �ݹ����������
        PreOrderTraverse(T->rchild); // �ݹ����������
    }
}
// ͳ��Ҷ�ӽ�����,����
void CountLeaf(BiTree T, int *Count){
    if (T){
        if((!T->lchild)&&(!T->rchild))
            (*Count)++;
        CountLeaf(T->lchild,Count);
        CountLeaf(T->rchild,Count);
    }
}
// ������������
int Depth(BiTree T){
    if (!T) return 0;
    else {
        int dLeft = Depth(T->lchild);
        int dRight = Depth(T->rchild);
        return  (dLeft>dRight?dLeft:dRight) + 1;
    }
}
// ���ƶ�����
void CopyTree(BiTree T, BiTree *new){
    if(T == NULL) *new = NULL;
    else{
        *new = (BiTree) malloc(sizeof(BiTNode));
        (*new)->data = T->data;
        CopyTree(T->lchild,&((*new)->lchild));
        CopyTree(T->rchild,&((*new)->rchild));
    }
}
// ������ĸλ��
int FindInRoot(const char ino[], char root, int low, int high){
    for(int i = low;i< high;i++){
        if(ino[i]==root)
            return i;
    }

}
// ����+����ָ�������
void restore(BiTree *T, char pre[], char ino[], int ps, int low, int high){
    if(low>=high) *T = NULL;
    else {
        *T = (BiTree) malloc(sizeof(BiTNode));
        (*T)->data.ch = pre[ps];
        int find = FindInRoot(ino,pre[ps],low,high);    // �����������в��Ҹ��ڵ��λ��
        restore(&((*T)->lchild),pre,ino,ps+1,low,find);   // �ָ�������
        restore(&((*T)->rchild),pre,ino,ps+1+(high-low)/2,find+1,high); // �ָ�������
    }
}
// ������������������
void InThreading(BiThrTree *H,BiTree T,BiThrTree *pre){
    if (T){
        *H = (BiThrTree) malloc(sizeof(BiThrNode));
        InThreading(&((*H)->lchild),T->lchild,pre); // ������������
        (*pre)->succ = (*H);
        (*H)->pred = (*pre);
        (*H)->data = T->data;
        (*pre) = (*H);
        InThreading(&((*H)->rchild),T->rchild,pre); // ������������
    }
}
void InOrderThreading(BiThrTree *H,BiTree T){
    *H = (BiThrTree) malloc(sizeof(BiThrNode));     // ͷ���
    (*H)->data.ch = ' ';
    if (!T){
        (*H)->pred = (*H);
        (*H)->succ = (*H);
    } else {
        BiThrTree pre = (*H);
        InThreading(&((*H)->lchild),T,&pre);
    }
}

// ���������������ı���
void InOrder(BiThrTree H){
    BiThrTree p = H->succ;
    while (p!=H){
        visit(p->data);
        p = p->succ;
    }
}

// �����־�������Ľ���
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

// �����־�������ı���
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
//    // ˳��洢�ṹ
//    SqBiTree bt;
//    bt[99].ch = 'A';
//    printf_s("SqBiTree [99] = %c\n",bt[99].ch);
//
//    // ������ʽ�洢�ṹ
//    BiTNode biTNode;
//    biTNode.data.ch = 'a';
//    printf_s("BiTNode.data = ");
//    visit(biTNode.data);
//    printf_s("\n");
//
//    // ������ʽ�洢��Ԫ
//    B3Tree b3Tree;
//    B3TNode b3TNode;
//    b3TNode.data.ch = 'b';
//    b3Tree = &b3TNode;
//    printf_s("B3Tree->data = ");
//    visit(b3Tree->data);
//    printf_s("\n");

    // ��������Ĵ���
    BiTree T=NULL;
//    char input[] = "ABC##DE#G##F###";
    char input[] = "ABDH##I##EG##K##CFL##M##GN##O##";
    int index = 0;
    CreateBiTree(&T,input,&index);
//
    // �������������
    printf("\n�������������:");
    InOrderTraverse(T);
    // �������������
    printf("\n�������������:");
    PreOrderTraverse(T);

//    // ����ͳ��Ҷ�ӽ�����
//    int Count = 0;
//    CountLeaf(T,&Count);
//    printf_s("\nҶ�ӽ�����Ϊ: %d",Count);
//
//    // ��������������
//    int depth = 0;
//    depth = Depth(T);
//    printf_s("\n�����������Ϊ: %d",depth);
//
//    // ���ƶ�����
//    BiTree new;
//    CopyTree(T,&new);
//    printf_s("\n�������T������");
//    InOrderTraverse(T);
//    printf_s("\n�������new������");
//    InOrderTraverse(new);
//
//    // ����+����ָ�������
//    BiTree reTree;
//    char pre[] = "ABDHIEGKCFLMGNO";
//    char ino[] = "HDIBGEKALFMCNGO";
//    restore(&reTree,pre,ino,0,0,strlen(ino));
//    printf_s("\n�ָ����������������������:");
//    InOrderTraverse(reTree);
//    printf_s("\n�ָ����������������������:");
//    PreOrderTraverse(reTree);
    BiThrTree H;
    InOrderThreading(&H,T);
    // �����������������
    printf_s("\n�����������������:");
    InOrder(H);

    ThrTree Thr;
    OrderThreading(&Thr,T);
    // ���������־������
    printf_s("\n���������־������:");
    OrderTraverse_Thr(Thr);


    return 0;
}