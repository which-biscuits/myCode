#include <iostream>
#include "Graph.h"
using namespace std;

int main() {
    //6 10 a b c d e f a b 5 b c 4 c a 8 d c 5 c f 9 a d 7 f a 3 f e 1 e d 5 d f 6
    //a b c d e f
    //a b 5 b c 4 c a 8 d c 5 c f 9 a d 7 f a 3 f e 1 e d 5 d f 6
    AMGraph AMG;
    Graph::CreateUDN(&AMG);
    Graph::ShowUDN(AMG);
    for (auto & i : Visited) {
        i = False;
    }
    cout << "\n�ڽӾ����������ȱ��� : ";
    Graph::DFS_AM(AMG, 0);
    cout << "\n�ڽӾ���Ĺ�����ȱ��� : ";
    Graph::BFS_AM(AMG, 0);
    cout << "\n��С��������Prim�㷨 : ";
    Graph::MiniSpanTree_Prim(AMG, 0);

//    ALGraph ALG;
//    Graph::CreateUDG(&ALG);
//    Graph::showUDG(ALG);
//    for (auto & i : Visited) {
//        i = False;
//    }
//    cout << "\n�ڽӱ��������ȱ��� : ";
//    Graph::DFS_AL(ALG, 0);
//    cout << "\n�ڽӱ�Ĺ�����ȱ��� : ";
//    Graph::BFS_AL(ALG, 0);
    return 0;
}