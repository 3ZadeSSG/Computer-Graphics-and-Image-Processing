function cdf=getCDF(inputHist)
    cdf=inputHist;
    for i=2:256
        cdf(i)=cdf(i)+cdf(i-1);
    end
end