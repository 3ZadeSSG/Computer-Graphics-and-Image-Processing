function result=setBrightness(inputImage,brightnessFactor)
    %result=double(inputImage);
    result=zeros(size(inputImage,1),size(inputImage,2),'uint8');
    result(:,:)=uint8(double(inputImage(:,:))*brightnessFactor);
end