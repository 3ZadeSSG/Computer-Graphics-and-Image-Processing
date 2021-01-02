function convertedImage=convertToBinaryImage(originalImage)
   convertedImage=originalImage; %here original image should be in Gray Scale 
   for i=1:size(convertedImage,1)
       for j=1:size(convertedImage,2)
           if convertedImage(i,j)<200
               convertedImage(i,j)=0;
           else
               convertedImage(i,j)=255;
           end
       end
   end
end