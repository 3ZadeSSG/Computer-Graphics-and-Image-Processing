function result=transformGamma(inputImage,constant,gamma)
    result=inputImage;
    result=constant*(result.^gamma);
end