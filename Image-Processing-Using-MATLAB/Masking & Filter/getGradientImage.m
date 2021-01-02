function result=getGradientImage(inputImage)

    Gx=double(getGradientImageX(inputImage));
    Gy=double(getGradientImageY(inputImage));    
    GM=zeros(size(inputImage));
    GM=double(GM);
    inputImage=double(inputImage);
    GM(:,:)=sqrt((Gx(:,:).^2)+(Gy(:,:).^2));
    result=uint8(GM);
end