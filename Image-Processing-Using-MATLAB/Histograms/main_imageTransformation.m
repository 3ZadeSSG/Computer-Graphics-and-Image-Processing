ip=imread('screen.bmp');
%gry=convertToGray(ip);
gry=imread('roof.gif');
hist_gry=getNormalizedHistogram(gry);

img_negative=transformImageNegative(gry);
hist_negative=getNormalizedHistogram(img_negative);

img_log=transformLog(gry,0.5);
hist_log=getNormalizedHistogram(img_log);

img_gamma=transformGamma(gry,1.5,10);
hist_gamma=getNormalizedHistogram(img_gamma);

img_piecewise=transformPiecewise(gry,100,200,1.5);
hist_piecewise=getNormalizedHistogram(img_piecewise);

subplot(5,2,1),imshow(gry);
subplot(5,2,2),plot(hist_gry);
subplot(5,2,3),imshow(img_negative);
subplot(5,2,4),plot(hist_negative);
subplot(5,2,5),imshow(img_log);
subplot(5,2,6),plot(hist_log);
subplot(5,2,7),imshow(img_gamma);
subplot(5,2,8),plot(hist_gamma);
subplot(5,2,9),imshow(img_piecewise);
subplot(5,2,10),plot(hist_piecewise);
