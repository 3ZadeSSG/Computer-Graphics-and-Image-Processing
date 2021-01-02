function result=getHistogram2Contrast(inputHisto)
    N=0.0;
    B=getHistogram2Brightness(inputHisto);
    sum=0.0;
    for i=1:size(inputHisto,2)
        N=N+inputHisto(1,i);
        sum=sum+inputHisto(1,i)*(i-1)*(i-1);
    end
    result=sqrt(sum/N-(B*B));
end