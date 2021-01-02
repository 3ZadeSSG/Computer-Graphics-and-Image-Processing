function result=transformImageNegative(inputImage)
    bitDepth=8;
    result=inputImage;
    result=(2^bitDepth)-1-result;
end