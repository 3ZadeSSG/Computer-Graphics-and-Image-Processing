function result=transformLog(inputImage,constant)
    result=inputImage;
    result=round(constant*log(double(1+result)));
end