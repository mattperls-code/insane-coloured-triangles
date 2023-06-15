#include <string>
#include <math.h>

char resultant(char a, char b){
  return (a == b) ? a : ((a == 'G') || (b == 'G')) ? ((a == 'R') || (b == 'R')) ? 'B' : 'R' : 'G';
}

int largestPowerOf3(int size)
{
  int power = 0;
  
  while (std::pow(3, power) < size) power++;
  
  return power - 1;
}

std::string solveSimple(std::string row)
{
  if (row.size() == 1) return row;
  
  std::string nextRow;
  
  for (unsigned long i = 0;i<row.size() - 1;i++) nextRow += resultant(row[i], row[i + 1]);
  
  return solveSimple(nextRow);
}

std::string solveComplex(std::string row)
{
  if (row.size() == 1) return row;
  
  int n = std::pow(3, largestPowerOf3(row.size()));
  
  for(unsigned long i = 0;i<row.size() / n;i++)
  {
    std::string nextRow;
    
    for (unsigned long j = 0;j<row.size() - n;j++) nextRow += resultant(row[j], row[j + n]);
    
    row = nextRow;
  }
  
  return (row.size() >= 4) ? solveComplex(row) : solveSimple(row);
}

char triangle(const std::string &row)
{
  return solveComplex(row)[0];
}
