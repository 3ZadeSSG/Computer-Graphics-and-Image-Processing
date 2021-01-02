%function will generate the prime number in give range and write the result
%into passer file
function result=generatePrimeRange(lower,upper,filename)
ptr=fopen(filename,'w');
 for i=lower:upper
     if isPrimeNumber(i)
         fprintf(ptr,'%d ',i);
         %disp(i);
     end
 end
 fclose(ptr);
end