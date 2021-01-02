function result=applySauvola(inputImage)
    %{
      R= maximum standard deviation of image, 128 for gray scale
      k parameter ranges between 0<k<=0.5
      T=mean*(1-k*(1-(std/R))

      f'(x,y)=0 if f(x,y)<T
             =255 otherwise

    %}
    inputImage=double(inputImage);
    result=size(inputImage);
    maskHeight=20;
    maskWidth=20;
    R=128;
    k=0.2;
    expandedImage=double(padWithSame(inputImage,maskHeight,maskWidth));
    for i=1:size(inputImage,1)
        for j=1:size(inputImage,2)
            selectedRegion=expandedImage(i:i+maskHeight-1,j:j+maskWidth-1);
            mean=sum(sum(selectedRegion))/(maskHeight*maskWidth);
            standard_deviation=std(selectedRegion(:));
            Threshold=mean*(1-k*(1-(standard_deviation/R)));
            if(inputImage(i,j)<Threshold)
                result(i,j)=0;
            else
                result(i,j)=255;
            end
        end
    end
    result=uint8(result);
end