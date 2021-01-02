function result=getContrast(inputImage)
 brightness=getBrightness(inputImage);
 sum=0;
 inputImage=double(inputImage);
 for i=1:size(inputImage,1)
     for j=1:size(inputImage,2)
         sum=sum+((inputImage(i,j)-brightness)*(inputImage(i,j)-brightness));
     end
 end
 result=sqrt(sum/(size(inputImage,1)*size(inputImage,2)));
end