function result=padWithSame(inputImage,maskHeight,maskWidth)
    %{
        Same usage as per command :-
            padImage=padarray(inputImage,[1,1],'replicate','both');
        pad with same will be added after the last column and last row and
        not before the first column and row.

        Considering seed point is at top-left corner of the mask.
    %}
    inputImage=double(inputImage);
    [height,width]=size(inputImage);
    expandedImage=zeros(height+maskHeight-1,width+maskWidth-1);
    
    expandedImage(1:height,1:width)=inputImage;
    for i=height+1:size(expandedImage,1)
        expandedImage(i:i,1:width)=inputImage(height:height,1:width);
    end
    for i=width+1:size(expandedImage,2)
        expandedImage(1:height,i:i)=inputImage(1:height,width:width);
    end
    expandedImage(height+1:size(expandedImage,1),width+1:size(expandedImage,2))=inputImage(height,width);
    result=uint8(expandedImage);
end