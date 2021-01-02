%ip=imread('screen.bmp');
%gry=convertToGray(ip);

gry=imread('roof.gif');
% Step 1: Get normalized histogram of image
hist_original=getNormalizedHistogram(gry);

% Step 2: Find CDF
cdf=getCDF(hist_original);

% Step 3: Find new intensity
cdf=1+round(cdf*255);

% Step 4: Find equalized image based on cdf and old image
equalized_image=getEqualizedImage(gry,cdf);

% Post Calculations and subplot
hist_equalized=getNormalizedHistogram(equalized_image);

subplot(3,2,1),imshow(gry),title('Original Image');
subplot(3,2,2),plot(hist_original),title('Normalized Histogram of Original Image');
subplot(3,2,3),plot(cdf),title('CDF');
subplot(3,2,4),imshow(equalized_image),title('Equalized Image');
subplot(3,2,5),plot(hist_equalized),title('Equalied Image Histogram');