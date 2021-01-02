function result=nCr(n,r)
  nFact=getFactorial(n);
  nrFact=getFactorial(n-r)*getFactorial(r);
  result=nFact/nrFact;
end