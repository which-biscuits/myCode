#include <iostream>
#include <string.h>
using namespace std;

// �洢�ṹ
typedef struct {
    float weight;    // ����Ȩֵ
    int parent,lChild,rChild;  // ����˫��/����/�Һ���
} HTNode, *HuffmanTree; // ��̬��������洢��������

// �����������
typedef char **HuffmanCode; // ��̬��������洢�����������

// ѡ������˫����Ϊ0��Ȩֵ��С�Ľ��,���������ǵ����
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

// ������������
void CreateHuffmanTree(HuffmanTree *HT,int n) {
    // w���n��Ȩֵ
    if(n<=1) return;
    int m = n * 2 - 1;
    (*HT) = new HTNode[m+1];    // Ϊ�շ���������һ��˳��ռ�
    for (int i = 1; i <= m; ++i) {
        (*HT)[i].parent=0;
        (*HT)[i].lChild=0;
        (*HT)[i].rChild=0;
    }
    cout << "������Ȩֵ:";
    for (int j = 1; j <= n; ++j) {   // ����ǰn����ԪҶ�ӽڵ��Ȩֵ
        cin >> (*HT)[j].weight;
    }
    int s1,s2;
    for (int k = n+1; k <= m; ++k) {    // ͨ��n-1��ѡ��/ɾ��/�ϲ���������������
        Select(*HT,k-1,&s1,&s2);
        (*HT)[s1].parent = k;
        (*HT)[s2].parent = k;
        (*HT)[k].lChild = s1;
        (*HT)[k].rChild = s2;
        (*HT)[k].weight = (*HT)[s1].weight + (*HT)[s2].weight;
    }
}

// ������������
void show(HuffmanTree HT, int n) {
    for (int i = 1; i <=2*n-1 ; ++i) {
        cout << i << '\t' << HT[i].weight << '\t' << HT[i].parent << '\t' << HT[i].lChild << '\t' << HT[i].rChild << "\n";
    }
}

// �����������
void CreateHuffmanCode(HuffmanTree HT, HuffmanCode *HC, int n) {
    (*HC) = new char *[n+1];    // ����洢n���ַ�����ı���ռ�
    char *cd = new char[n]; // ������ʱ�洢ÿ���ַ�����Ķ�̬����ռ�
    cd[n-1] = '\0'; // ���������
    for (int i = 1; i <= n; ++i) {  // ����ַ������������
        int start = n-1;    // start ��ʼʱָ�����, �����������λ��
        int th=i;
        int parent = HT[i].parent;
        while (parent != 0) {
            if (HT[parent].lChild == th) cd[--start] = '0';
            else cd[--start] = '1';
            th = parent;
            parent = HT[parent].parent; // �������ϻ���
        }
        (*HC)[i] = new char[n-start];   // Ϊ��i���ַ��������ռ�
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
