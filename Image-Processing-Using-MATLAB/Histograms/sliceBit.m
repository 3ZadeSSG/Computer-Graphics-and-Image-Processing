function result=sliceBit(inputImage,bitPosition)
    height=size(inputImage,1);
    width=size(inputImage,2);
    result=zeros(height,width);
    for i=1:height
        for j=1:width
            intensity=de2bi(inputImage(i,j),8,'left-msb');
            a=bi2de(intensity(bitPosition:bitPosition),'left-msb');
            result(i,j)=a;
        end
    end        
end