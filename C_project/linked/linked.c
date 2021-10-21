#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#pragma warning(disable:4996)

#define OK 1
#define ERROR 0
#define OVERFLOW -2

typedef int Status;

typedef struct Student {
	char id[12];		// 学号
	char name[12];		// 姓名
	char grade[12];		// 年级
	int score;			// 得分
}Student;

typedef struct LNode {
	Student stu;					// 节点的数据域
	struct LNode *next;		// 结点的指针域
}LNode,*LinkList;			// LinkList 为指向结构体 LNode 指针类型

Student CreateStudent(char id[12],char name[12],char grade[12],int score) {
	Student s;
	strcpy(s.id,id);
	strcpy(s.name,name);
	strcpy(s.grade,grade);
	s.score = score;
	return s;
}

Status InitList(LinkList *L) {
	*L = (LNode*)malloc(sizeof(LNode));		// 生成新的结点作为头结点
	(*L)->next = NULL;						// 头结点的指针域置空
	return OK;
}

Status GetElem(LinkList L, int i, Student *stu) {	// 在带头结点的单链表L中根据序号i获取元素的值，用e返回L中第i个数据元素的值
	LinkList p = L->next; int j = 1;				// 初始化，p指向首元结点，计数器j初值赋为1
	while (p && j < i) {	// 顺链域向后扫描，直到p为空或p指向第i个元素
		p = p->next;		// p指向下一个结点
		++j;				// 计数器+1
	}
	if (!p || j > i) return ERROR;		// i值不合法 i>n 或 i<=0
	*stu = p->stu;			// 取第i个结点的数据域
	return OK;
}

Status ListInsert(LinkList L, int i, Student stu) {		// 在带头结点的单链表L中第i个位置插入值为e的新节点
	LinkList p = L; int j = 0;
	while (p && (j < i - 1)) {
		p = p->next;		// 查找第i-1个结点，p指向该结点
		++j;
	}
	if (!p || j > i - 1) return ERROR;				// i>n+1 或者 i<1
	LinkList s = (LNode*)malloc(sizeof(LNode));		// 生成新结点 *s
	s->stu = stu;			// 将结点 *s 的数据域置为stu
	s->next = p->next;		// 将结点 *s 的指针域指向结点a
	p->next = s;			// 将结点 *p 的指针域指向结点 *s
	return OK;
}

Status Compare(Student s1, Student s2) {
	if (!strcmp(s1.id, s2.id) && !strcmp(s1.name, s2.name) && !strcmp(s1.grade, s2.grade) && s1.score == s2.score)
		return OK;
	else
		return ERROR;
}

LNode* LocateElem(LinkList L, Student stu) {	// 在带头结点的单链表L中查找值为stu的元素
	LinkList p = L->next;		// 初始化， p指向首元结点
	while (p && !Compare(p->stu,stu)) {			// 顺链域向后扫描，直到p为空或p所指结点的数据域等于 stu
		p = p->next;
	}
	return p;
}

LNode* ListDelete(LinkList* L, int i) {		// 在带头结点的单链表L中，删除第i个元素
	LinkList p = L; int j = 0;		
	while (p->next && j < i - 1) {		// 查找第 i-1 个结点，p指向该结点
		p = p->next;
		++j;
	}
	if (!(p->next) || j > i - 1) return ERROR;		// 当i>n 或 i<1时，删除位置不合理
	LinkList q = p->next;
	p->next = q->next;
	free(q);
	return OK;
}

Student input() {
	Student new_stu;
	printf_s("请输入学生信息\n\n");
	printf_s("==>>输入学号:");
	scanf("%s", new_stu.id);
	printf_s("==>>输入姓名:");
	scanf("%s", new_stu.name);
	printf_s("==>>输入年级:");
	scanf("%s", new_stu.grade);
	printf_s("==>>输入成绩:");
	scanf("%d", &new_stu.score);
	return new_stu;
}

void CreateList_H(LinkList* L, int n) {		// 逆位序输入n个元素的值，建立带表头结点的单链表L
	*L = (LNode*)malloc(sizeof(LNode));		// 生成新的结点作为头结点
	(*L)->next = NULL;		// 先创建一个带头节点的空链表
	for (int i = 0; i < n; ++i) {
		LinkList p = (LNode*)malloc(sizeof(LNode));		// 生成新结点 *p
		p->stu = input();		// 输入元素值赋给新节点*p的数据域
		p->next = (*L)->next;
		(*L)->next = p;		// 将新节点*p插入到头结点之后
	}
}

void CreateList_R(LinkList* L, int n) {		// 正位序输入n个元素的值，建立带表头结点的单链表L
	*L = (LNode*)malloc(sizeof(LNode));		
	(*L)->next = NULL;		// 先建立一个带头结点的空链表
	LinkList r = *L;		// 尾指针r指向头结点
	for (int i = 0; i < n; i++) {
		LinkList p = (LNode*)malloc(sizeof(LNode));		// 生成新节点
		p->stu = input();		// 输入元素赋值给新节点 *p
		p->next = NULL;	
		r->next = p;			// 将新节点*p 插入尾结点*r之后
		r = p;
	}
}

main() {
	// 初始化
	LinkList Head;
	Status ans = InitList(&Head);
	printf_s("***********初始化************\n");
	printf_s("返回值 %d\n",ans);
	printf_s("%s\n",Head->next);

	// 插入
	Student new_stu = CreateStudent("1961310319", "王子潇", "2019级", 91);
	ans = ListInsert(Head, 1, new_stu);
	LinkList First;
	First = Head->next;
	printf_s("***********插入************\n");
	printf_s("返回值 %d\n", ans);
	printf_s("%s\n",First->stu.id);
	printf_s("%s\n",First->stu.name);

	// 取值
	Student get_stu;
	ans = GetElem(Head, 1, &get_stu);
	printf_s("***********取值************\n");
	printf_s("返回值 %d\n", ans);
	printf_s("%s\n", get_stu.id);
	printf_s("%d\n", get_stu.score);

	// 查找
	LinkList Find = LocateElem(Head, get_stu);
	printf_s("***********查找************\n");
	printf_s("%s\n", Find->stu.id);
	printf_s("%d\n", Find->stu.score);

	// 删除
	ans = ListDelete(Head, 1);
	printf_s("***********删除************\n");
	printf_s("%d\n", ans);

	// 前插法创建单链表
	LinkList Head_01;
	CreateList_H(&Head_01, 2);
	printf_s("***********取值************\n");
	ans = GetElem(Head_01, 1, &get_stu);
	printf_s("返回值 %d\n", ans);
	printf_s("%s\n", get_stu.id);
	printf_s("%d\n", get_stu.score);
	ans = GetElem(Head_01, 2, &get_stu);
	printf_s("返回值 %d\n", ans);
	printf_s("%s\n", get_stu.id);
	printf_s("%d\n", get_stu.score);

	// 后插法创建单链表
	LinkList Head_02;
	CreateList_R(&Head_02, 2);
	printf_s("***********取值************\n");
	ans = GetElem(Head_02, 1, &get_stu);
	printf_s("返回值 %d\n", ans);
	printf_s("%s\n", get_stu.id);
	printf_s("%d\n", get_stu.score);
	ans = GetElem(Head_02, 2, &get_stu);
	printf_s("返回值 %d\n", ans);
	printf_s("%s\n", get_stu.id);
	printf_s("%d\n", get_stu.score);

	free(Head);
}