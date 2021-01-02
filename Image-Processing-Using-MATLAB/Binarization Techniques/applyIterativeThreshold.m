function result=applyIterativeThreshold(inputImage)
    height=size(inputImage,1);
    width=size(inputImage,2);
    hist_img=getNormalizedHistogram(inputImage);
    inputImage=double(inputImage);
    sumB=double(inputImage(1,1)+inputImage(1,width)+inputImage(height,1)+inputImage(height,width));
    mB=double(sumB/4);
    sumF=sum(sum(inputImage));
    mF=double((sumF-sumB)/((height*width)-4));
    T=getIterativeThreshold(mB,mF,hist_img);
    result=zeros(height,width,'uint8');
    for i=1:height
        for j=1:width
            if(inputImage(i,j)<=T)
                result(i,j)=0;
            else
                result(i,j)=255;
            end
        end
    end
    result=uint8(result);
end