function result=convertToBinary(inputImage)
    result=inputImage;
    for i=1:size(inputImage,1)
        for j=1:size(inputImage,2)
            if inputImage(i,j)<200
                result(i,j)=0;
            else
                result(i,j)=255;
            end
        end
    end
end