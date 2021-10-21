#include<stdio.h>
#include<string.h>

#define MAXLEN 255      // 串的最大长度

typedef struct
{
    char ch[MAXLEN + 1];      // 存储串的一维数组
    int length;
    int next[MAXLEN + 1];
    int nextval[MAXLEN + 1];
} SString;

int Index_BF(SString S, SString T, int pos)
{   // 返回模式T在主串中第pos个字符开始第一次出现的位置。若不存在，则返回值为0
    // 其中，T非空，1<=pos<=S.length
    int i = pos; int j=0;       // 初始化
    while (i <S.length && j <T.length )     // 两个串均未比较到串尾
    {

        if (S.ch[i]==T.ch[j]) {++i;++j;}    // 继续比较后续字符
        else{i = i-j+1;j = 0;}      // 指针后退重新开始匹配
    }
    if(j>=T.length-1) return i - T.length;     // 匹配成功
    else return 0;      // 匹配失败
}

void get_next(SString *T)
{   // 求模式串T的next函数值并存入数组中
    int i = 0,j = -1;
    T->next[0] = -1;
    while (i < T->length)
    {
        if(j==-1 || T->ch[i] == T->ch[j])
        {
            i++;
            j++;
            T->next[i] = j;
        } else {
            j = T->next[j];
        }
    }
}

void get_nextval(SString *T)
{
    int i = 0, j = -1;
    T->nextval[0] = -1;
    while (i < T->length)
    {
        if (j == -1 || T->ch[i] == T->ch[j])
        {
            i++;
            j++;
            if (T->ch[i] != T->ch[j])
            {
                T->nextval[i] = j;
            } else {
                T->nextval[i] = T->nextval[j];
            }

        } else {
            j = T->nextval[j];
        }
    }
}

int Index_KMP(SString S,SString T,int pos)
{
    int i = pos,j = -1;
    while(i < S.length && j < T.length)
    {
        if (j == -1 || S.ch[i] == T.ch[j])
        {
            i++;
            j++;
        } else {
            j = T.next[j];
        }
    }
    if(j >= T.length) return i - T.length;
    else return -1;
}

int Index_KMP_nextval(SString S,SString T,int pos)
{
    int i = pos,j = -1;
    while(i < S.length && j < T.length)
    {
        if (j == -1 || S.ch[i] == T.ch[j])
        {
            i++;
            j++;
        } else {
            j = T.nextval[j];
        }
    }
    if(j >= T.length) return i - T.length;
    else return -1;
}

void main()
{
    SString S,T;
    strcpy(S.ch,"abaaabaaaabca");
    S.length = 13;
    strcpy(T.ch,"aaaab");
    T.length = 5;

    // // BF算法
    // int ans = Index_BF(S,T,0);
    // printf("%d\n", ans);

    // // KMP算法
    // get_next(&T);
    // int ans = Index_KMP(S,T,0);
    // printf("%d\n", ans);

    // KMP改进算法
    get_nextval(&T);
    int ans = Index_KMP_nextval(S,T,0);
    printf("%d\n", ans);
}
