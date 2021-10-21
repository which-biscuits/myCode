#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#pragma warning(disable:4996)

#define OK 1
#define ERROR 0
#define OVERFLOW -2

typedef int Status;

typedef struct Student {
	char id[12];		// ѧ��
	char name[12];		// ����
	char grade[12];		// �꼶
	int score;			// �÷�
}Student;

typedef struct LNode {
	Student stu;					// �ڵ��������
	struct LNode *next;		// ����ָ����
}LNode,*LinkList;			// LinkList Ϊָ��ṹ�� LNode ָ������

Student CreateStudent(char id[12],char name[12],char grade[12],int score) {
	Student s;
	strcpy(s.id,id);
	strcpy(s.name,name);
	strcpy(s.grade,grade);
	s.score = score;
	return s;
}

Status InitList(LinkList *L) {
	*L = (LNode*)malloc(sizeof(LNode));		// �����µĽ����Ϊͷ���
	(*L)->next = NULL;						// ͷ����ָ�����ÿ�
	return OK;
}

Status GetElem(LinkList L, int i, Student *stu) {	// �ڴ�ͷ���ĵ�����L�и������i��ȡԪ�ص�ֵ����e����L�е�i������Ԫ�ص�ֵ
	LinkList p = L->next; int j = 1;				// ��ʼ����pָ����Ԫ��㣬������j��ֵ��Ϊ1
	while (p && j < i) {	// ˳�������ɨ�裬ֱ��pΪ�ջ�pָ���i��Ԫ��
		p = p->next;		// pָ����һ�����
		++j;				// ������+1
	}
	if (!p || j > i) return ERROR;		// iֵ���Ϸ� i>n �� i<=0
	*stu = p->stu;			// ȡ��i������������
	return OK;
}

Status ListInsert(LinkList L, int i, Student stu) {		// �ڴ�ͷ���ĵ�����L�е�i��λ�ò���ֵΪe���½ڵ�
	LinkList p = L; int j = 0;
	while (p && (j < i - 1)) {
		p = p->next;		// ���ҵ�i-1����㣬pָ��ý��
		++j;
	}
	if (!p || j > i - 1) return ERROR;				// i>n+1 ���� i<1
	LinkList s = (LNode*)malloc(sizeof(LNode));		// �����½�� *s
	s->stu = stu;			// ����� *s ����������Ϊstu
	s->next = p->next;		// ����� *s ��ָ����ָ����a
	p->next = s;			// ����� *p ��ָ����ָ���� *s
	return OK;
}

Status Compare(Student s1, Student s2) {
	if (!strcmp(s1.id, s2.id) && !strcmp(s1.name, s2.name) && !strcmp(s1.grade, s2.grade) && s1.score == s2.score)
		return OK;
	else
		return ERROR;
}

LNode* LocateElem(LinkList L, Student stu) {	// �ڴ�ͷ���ĵ�����L�в���ֵΪstu��Ԫ��
	LinkList p = L->next;		// ��ʼ���� pָ����Ԫ���
	while (p && !Compare(p->stu,stu)) {			// ˳�������ɨ�裬ֱ��pΪ�ջ�p��ָ������������� stu
		p = p->next;
	}
	return p;
}

LNode* ListDelete(LinkList* L, int i) {		// �ڴ�ͷ���ĵ�����L�У�ɾ����i��Ԫ��
	LinkList p = L; int j = 0;		
	while (p->next && j < i - 1) {		// ���ҵ� i-1 ����㣬pָ��ý��
		p = p->next;
		++j;
	}
	if (!(p->next) || j > i - 1) return ERROR;		// ��i>n �� i<1ʱ��ɾ��λ�ò�����
	LinkList q = p->next;
	p->next = q->next;
	free(q);
	return OK;
}

Student input() {
	Student new_stu;
	printf_s("������ѧ����Ϣ\n\n");
	printf_s("==>>����ѧ��:");
	scanf("%s", new_stu.id);
	printf_s("==>>��������:");
	scanf("%s", new_stu.name);
	printf_s("==>>�����꼶:");
	scanf("%s", new_stu.grade);
	printf_s("==>>����ɼ�:");
	scanf("%d", &new_stu.score);
	return new_stu;
}

void CreateList_H(LinkList* L, int n) {		// ��λ������n��Ԫ�ص�ֵ����������ͷ���ĵ�����L
	*L = (LNode*)malloc(sizeof(LNode));		// �����µĽ����Ϊͷ���
	(*L)->next = NULL;		// �ȴ���һ����ͷ�ڵ�Ŀ�����
	for (int i = 0; i < n; ++i) {
		LinkList p = (LNode*)malloc(sizeof(LNode));		// �����½�� *p
		p->stu = input();		// ����Ԫ��ֵ�����½ڵ�*p��������
		p->next = (*L)->next;
		(*L)->next = p;		// ���½ڵ�*p���뵽ͷ���֮��
	}
}

void CreateList_R(LinkList* L, int n) {		// ��λ������n��Ԫ�ص�ֵ����������ͷ���ĵ�����L
	*L = (LNode*)malloc(sizeof(LNode));		
	(*L)->next = NULL;		// �Ƚ���һ����ͷ���Ŀ�����
	LinkList r = *L;		// βָ��rָ��ͷ���
	for (int i = 0; i < n; i++) {
		LinkList p = (LNode*)malloc(sizeof(LNode));		// �����½ڵ�
		p->stu = input();		// ����Ԫ�ظ�ֵ���½ڵ� *p
		p->next = NULL;	
		r->next = p;			// ���½ڵ�*p ����β���*r֮��
		r = p;
	}
}

main() {
	// ��ʼ��
	LinkList Head;
	Status ans = InitList(&Head);
	printf_s("***********��ʼ��************\n");
	printf_s("����ֵ %d\n",ans);
	printf_s("%s\n",Head->next);

	// ����
	Student new_stu = CreateStudent("1961310319", "������", "2019��", 91);
	ans = ListInsert(Head, 1, new_stu);
	LinkList First;
	First = Head->next;
	printf_s("***********����************\n");
	printf_s("����ֵ %d\n", ans);
	printf_s("%s\n",First->stu.id);
	printf_s("%s\n",First->stu.name);

	// ȡֵ
	Student get_stu;
	ans = GetElem(Head, 1, &get_stu);
	printf_s("***********ȡֵ************\n");
	printf_s("����ֵ %d\n", ans);
	printf_s("%s\n", get_stu.id);
	printf_s("%d\n", get_stu.score);

	// ����
	LinkList Find = LocateElem(Head, get_stu);
	printf_s("***********����************\n");
	printf_s("%s\n", Find->stu.id);
	printf_s("%d\n", Find->stu.score);

	// ɾ��
	ans = ListDelete(Head, 1);
	printf_s("***********ɾ��************\n");
	printf_s("%d\n", ans);

	// ǰ�巨����������
	LinkList Head_01;
	CreateList_H(&Head_01, 2);
	printf_s("***********ȡֵ************\n");
	ans = GetElem(Head_01, 1, &get_stu);
	printf_s("����ֵ %d\n", ans);
	printf_s("%s\n", get_stu.id);
	printf_s("%d\n", get_stu.score);
	ans = GetElem(Head_01, 2, &get_stu);
	printf_s("����ֵ %d\n", ans);
	printf_s("%s\n", get_stu.id);
	printf_s("%d\n", get_stu.score);

	// ��巨����������
	LinkList Head_02;
	CreateList_R(&Head_02, 2);
	printf_s("***********ȡֵ************\n");
	ans = GetElem(Head_02, 1, &get_stu);
	printf_s("����ֵ %d\n", ans);
	printf_s("%s\n", get_stu.id);
	printf_s("%d\n", get_stu.score);
	ans = GetElem(Head_02, 2, &get_stu);
	printf_s("����ֵ %d\n", ans);
	printf_s("%s\n", get_stu.id);
	printf_s("%d\n", get_stu.score);

	free(Head);
}