function result=sliceLSB(inputImage,sliceLength)
    height=size(inputImage,1);
    width=size(inputImage,2);
    result=zeros(height,width);
    for i=1:height
        for j=1:width
            intensity=de2bi(inputImage(i,j),8,'left-msb');
            a=bi2de(intensity(9-sliceLength:8),'left-msb');
            result(i,j)=a;
        end
    end
    result=uint8(result);
end