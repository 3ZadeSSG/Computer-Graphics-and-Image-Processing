function result=getNormalizedHistogram(inputImage)
    %tempInput=[8,7,9,11;8,8,7,6;8,8,11,10;10,10,9,8];
    %result=getHistogram(tempInput);
    result=getHistogram(inputImage);
    %maxFrequency=max(result);
    %result(:,:)=result(:,:)/maxFrequency;
    result(:,:)=result(:,:)/sum(result);
end