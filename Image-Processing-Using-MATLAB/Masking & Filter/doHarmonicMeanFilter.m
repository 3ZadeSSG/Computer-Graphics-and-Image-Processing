function result=doHarmonicMeanFilter(inputImage)
    padImage=uint16(padarray(inputImage,[1,1],'replicate','both'));
    %padImage=padImage+1;
    
    newImage=padImage;
    [height,width]=size(padImage);
    for i=2:height-1
        for j=2:width-1
            mask=double(padImage(i-1:i+1,j-1:j+1));
            newImage(i,j)=(9/(sum(sum(gdivide(1,mask)))));
        end
    end
    result=uint8(newImage(2:height-1,2:width-1));
end