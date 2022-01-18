#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

void _f_print(char * x){
    printf(x);
}

void _f_println(char * x){
    printf("%s\n",x);
}

void _f_printInt(int x){
    printf("%d",x);
}

void _f_printlnInt(int x){
    printf("%d\n",x);
}

int _f_getInt(){
    int n;
    scanf("%d",&n);
    return n;
}

char * _f_toString(int n){
    // int32_max: 2147483647 total digits: 10 + 1 + 1 = 12;
    char * newString = (char * ) malloc(13);
    sprintf(newString,"%d",n);
    return newString;
}

char * _f_getString(){
    char * newString = (char *) malloc(sizeof(char) * 1024);
    scanf("%s",newString);
    return newString;
}

char * _f__malloc(int n) {
    return (char *) malloc(n);
}

char * _f__str_splice(char * str1,char * str2){
    char * newStr = (char * ) malloc(strlen(str1) + strlen(str2) + 2);
    strcpy(newStr,str1);
    strcat(newStr,str2);
    return newStr;
}

bool _f__str_eq(char * str1, char * str2){
    return strcmp(str1,str2) == 0;
}

bool _f__str_ne(char * str1, char * str2){
    return strcmp(str1,str2) != 0;
}

bool _f__str_lt(char * str1, char * str2){
    return strcmp(str1,str2) < 0;
}

bool _f__str_le(char * str1, char * str2){
    return strcmp(str1,str2) <= 0;
}

bool _f__str_gt(char * str1, char * str2){
    return strcmp(str1,str2) > 0;
}

bool _f__str_ge(char * str1, char * str2){
    return strcmp(str1,str2) >= 0;
}

int _class_string_length(char * str){
    return strlen(str);
}

char * _class_string_substring(char * str,int l,int r){
    int size = r-l+1; // for \0
    char * newPtr = (char *) malloc(size);
    memcpy(newPtr,str+l,r-l);
    newPtr[size-1] = '\0';
    return newPtr;
}

int _class_string_parseInt(char * str){
    int val;
    sscanf(str,"%d",&val);
    return val;
}

int _class_string_ord(char * str,int pos){
    return (int)(str[pos]);
}