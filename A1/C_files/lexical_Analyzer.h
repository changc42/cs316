#ifndef LEXICALANALYZER_H
#define LEXICALANALYZER_H

/* Token datatype declaration */
typedef struct
{
  int tokenCode;
  char lexemeString[100];
} Token;

/* Function declarations */
void initialize(FILE *);
Token getToken();

#endif