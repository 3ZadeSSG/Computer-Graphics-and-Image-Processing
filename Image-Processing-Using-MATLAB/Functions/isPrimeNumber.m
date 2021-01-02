function result=isPrimeNumber(number)
result=1;
 for i=2:sqrt(number)
     if mod(number,i)==0
         result=0;
     end
 end
end