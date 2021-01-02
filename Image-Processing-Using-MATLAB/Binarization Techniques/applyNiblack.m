function result=applyNiblack(inputImage)
    %{
    T(x,y)=mean(x,y)+k*standard_deviation(x,y)
    k=0.2 by default
    seed point of window = top-left corner in this case
    padding = paddig with same
    %}
    maskHeight=20;
    maskWidth=20;
    k=0.2;
    inputImage=double(inputImage);
    result=size(inputImage);
    expandedImage=double(padWithSame(inputImage,maskHeight,maskWidth));
    for i=1:size(inputImage,1)
        for j=1:size(inputImage,2)
            selectedRegion=expandedImage(i:i+maskHeight-1,j:j+maskWidth-1);
            mean=sum(sum(selectedRegion))/(maskHeight*maskWidth);
            standard_deviation=std(selectedRegion(:));
            Threshold=mean+(k*standard_deviation);
            if(inputImage(i,j)<Threshold)
                result(i,j)=0;
            else
                result(i,j)=255;
            end
        end
    end
    result=uint8(result);
end