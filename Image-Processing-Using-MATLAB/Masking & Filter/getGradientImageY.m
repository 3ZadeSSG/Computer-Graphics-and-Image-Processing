function result=getGradientImageY(inputImage)
    Gy=zeros(size(inputImage));
    Gy=double(Gy);
    for i=1:size(inputImage,1)-1
        for j=1:size(inputImage,2)
            Gy(i,j)=inputImage(i+1,j)-inputImage(i,j);
        end
    end
    result=uint8(Gy);
end