function result=doContrastStretching(inputImage,m,n)
    result=int64(inputImage);
    a=min(min(result));
    b=max(max(result));
    for i=1:size(result,1)
        for j=1:size(result,2)
            result(i,j)=m+((n-m)/(b-a))*(result(i,j)-a);
        end
    end
    result=uint8(result);
end