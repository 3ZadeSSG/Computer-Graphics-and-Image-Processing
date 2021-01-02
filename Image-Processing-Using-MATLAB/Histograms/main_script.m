ip=imread('food.jpg');
gry=convertToGray(ip);
hist=getHistogram(gry);
n_hist=getNormalizedHistogram(gry);
cdf=getCDF(n_hist);
newIntensity=1+round(cdf*255);
eqImage=getEqualizedImage(gry,newIntensity);
newHist=getNormalizedHistogram(eqImage);
{
    subplot(2,3,1),plot(hist),title('Histogram');
    subplot(2,3,2),plot(n_hist),title('Normalized Histogram');
    subplot(2,3,3),plot(cdf),title('CDF');
    subplot(2,3,4),imshow(eqImage),title('Equalized Image');
    subplot(2,3,5),plot(newHist),title('New Histogram');
    subplot(2,3,6),imshow(gry),title('Gray Image');
}