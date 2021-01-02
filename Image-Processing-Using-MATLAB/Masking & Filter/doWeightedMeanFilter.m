function result=doWeightedMeanFilter(inputImage)
    weightMask=uint16([1,2,1;2,4,2;1,2,1]);
    weightSum=sum(sum(weightMask));
    padImage=uint16(padarray(inputImage,[1,1],'replicate','both'));
    newImage=padImage;
    [height,width]=size(padImage);
    for i=2:height-1
        for j=2:width-1
            mask=padImage(i-1:i+1,j-1:j+1);
            newImage(i,j)=(sum(sum(mask.*weightMask)))/weightSum;
        end
    end
    result=uint8(newImage(2:height-1,2:width-1));
end