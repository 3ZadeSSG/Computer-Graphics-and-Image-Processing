function result=getEqualizedImage(inputImage,intensity)
%{
    INPUT: 
   inputImage: Gray Sacale Image; intensity: new Intensity array for each
   old one
    OUTPUT: New Gray Scale Image with updated intensity
%}
    inputImage=uint16(inputImage);
    result=inputImage;
    for i=1:size(result,1)
        for j=1:size(result,2)
            index=inputImage(i,j)+1;
            result(i,j)=intensity(1,index);
        end
    end
    result=uint8(result);
end