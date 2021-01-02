function result=getBrightness(inputImage)
    sum=0;
    for i=1:size(inputImage,1)
        for j=1:size(inputImage,2)
            sum=sum+double(inputImage(i,j));
        end
    end
    result=sum/(size(inputImage,1)*size(inputImage,2));
end