function result=convertToGray(inputImage)
    result=zeros(size(inputImage,1),size(inputImage,2),'uint8');
    result(:,:)=(0.299*inputImage(:,:,1))+(0.587*inputImage(:,:,2))+(0.114*inputImage(:,:,3));
end