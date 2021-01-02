function result=getHistogram(inputImage)
    %tempInput=[8,7,9,11;8,8,7,6;8,8,11,10;10,10,9,8];
    %result=zeros(1,12);
    %[height,width]=size(tempInput);
    
    result=(zeros(1,256));
    [height,width]=size(inputImage);
    for i=1:height
        for j=1:width
            index=uint16(inputImage(i,j))+1;
            result(1,index)=result(1,index)+1;
            %result(1,uint16(inputImage(i,j)+1))=result(1,uint16(inputImage(i,j)+1))+1;  
            %result(1,tempInput(i,j)+1)=result(1,tempInput(i,j)+1)+1;
        end
    end 
end