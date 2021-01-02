function result=getRAWSize(inputImage)
    height=size(inputImage,1);
    width=size(inputImage,2);
    bitDepth=size(inputImage,3)*8;
    result=((height*width*bitDepth)/(1024*1024*8)) %result in MB
end