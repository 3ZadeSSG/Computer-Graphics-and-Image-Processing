function result=applyBernsen(inputImage)
    %{
        Threshold=(Imax+Imin)/2;
        Imax = max intensity value of selected window 
        Imin = min intensity value of selected window
    %}
    inputImage=double(inputImage);
    result=size(inputImage);
    maskHeight=20;
    maskWidth=20;
    
    expandedImage=double(padWithSame(inputImage,maskHeight,maskWidth));
    for i=1:size(inputImage,1)
        for j=1:size(inputImage,2)
            selectedRegion=expandedImage(i:i+maskHeight-1,j:j+maskWidth-1);
            Imax=max(max(selectedRegion));
            Imin=min(min(selectedRegion));
            Threshold=(Imax+Imin)/2;
            if(inputImage(i,j)<Threshold)
                result(i,j)=0;
            else
                result(i,j)=255;
            end
        end
    end
    result=uint8(result);
end