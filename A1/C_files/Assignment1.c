/* Assignment1.c - driver code for the
                   lexical analyzer */
#include <stdio.h>
#include "LexicalAnalyzer.h"

/******************************************************/
/* main driver */
int main(int argc, char *argv[])
{
  FILE *in_fp;
  Token nextToken;

/* Open the input data file and process its contents */
  if  ((in_fp = fopen(argv[1], "r")) == NULL)
    printf("ERROR - cannot open input file \n");
  else
  {
    initialize(in_fp);
    do
    {
      nextToken = getToken();
      printf("Next token is: %d, Next lexeme is %s\n",
             nextToken.tokenCode, nextToken.lexemeString);
    } while (nextToken.tokenCode != EOF);
  }
}