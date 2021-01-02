function result=getGradientImageX(inputImage)
    result=zeros(size(inputImage));
    result=double(result);
    for i=1:size(inputImage,1)
        for j=1:size(inputImage,2)-1
            result(i,j)=inputImage(i,j+1)-inputImage(i,j);
        end
    end 
    result=uint8(result);
end