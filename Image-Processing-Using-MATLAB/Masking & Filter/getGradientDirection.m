function result=getGradientDirection(inputImage)
    Gx=double(getGradientImageX(inputImage));
    Gy=double(getGradientImageY(inputImage));    
    GD=zeros(size(inputImage));
    GD=double(GD);
    for i=1:size(GD,1)
        for j=1:size(GD,2)
            GD(i,j)=atand(Gy(i,j)/Gx(i,j));
        end
    end
    result=uint8(GD);
end