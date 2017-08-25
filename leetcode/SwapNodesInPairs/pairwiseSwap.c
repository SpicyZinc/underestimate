#include<stdio.h>
#include<stdlib.h>

struct LinkedListNode {
    int data;
    struct LinkedListNode *next;
};

struct LinkedListNode* createNode(int data) {
    struct LinkedListNode *newNode = (struct LinkedListNode *)malloc(sizeof(struct LinkedListNode));
    newNode->data = data;
    newNode->next = NULL;
    return newNode;
}

void printLinkedList(struct LinkedListNode *head) {
    while(head) {
        printf("%d ",head->data);
        head = head->next;
    }
    printf("\n");
}

void reverseNodeInPairs(struct LinkedListNode *head) {
    struct LinkedListNode *current = head;
    int temp;
    while(current && current->next!=NULL){
        temp = current->data;
        current->data = current->next->data;
        current->next->data = temp;
        current = current->next->next;
    }
}

int main() {
    struct LinkedListNode *head = createNode(1);
    head->next = createNode(2);
    head->next->next = createNode(3);
    head->next->next->next = createNode(4);
    head->next->next->next->next = createNode(5);
    head->next->next->next->next->next = createNode(6);
    head->next->next->next->next->next->next = createNode(7);
    printLinkedList(head);
    reverseNodeInPairs(head);
    printLinkedList(head);
    getchar();
    return 1;
}
