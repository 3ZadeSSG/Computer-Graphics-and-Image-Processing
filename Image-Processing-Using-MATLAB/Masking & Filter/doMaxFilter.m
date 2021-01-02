function result=doMaxFilter(inputImage)
    padImage=padarray(inputImage,[1,1],'replicate','both');
    newImage=padImage;
    [height,width]=size(padImage);
    for i=2:height-1
        for j=2:width-1
            mask=padImage(i-1:i+1,j-1:j+1);
            newImage(i,j)=max(max(mask));
        end
    end
    result=uint8(newImage(2:height-1,2:width-1));
end