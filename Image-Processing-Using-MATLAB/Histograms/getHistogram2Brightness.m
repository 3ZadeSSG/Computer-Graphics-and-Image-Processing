function result=getHistogram2Brightness(inputHisto)
    sum=0.0;
    resolution=0.0;
    for i=1:size(inputHisto,2)
        sum=sum+inputHisto(1,i)*(i-1);
        resolution=resolution+inputHisto(1,i);
    end
    result=sum/resolution;
end