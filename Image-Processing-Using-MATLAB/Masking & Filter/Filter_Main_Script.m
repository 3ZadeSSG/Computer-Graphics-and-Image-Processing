% grayImage=imread('screen.png');
 grayImage=imread('screen.png');

subplot(2,4,1),imshow(grayImage),title('Orginal Image');
subplot(2,4,2),imshow(doMaxFilter(grayImage)),title('Max Filter');
subplot(2,4,3),imshow(doMinFilter(grayImage)),title('Min Filter');
subplot(2,4,4),imshow(doMeanFilter(grayImage)),title('Mean Filter');
subplot(2,4,5),imshow(doWeightedMeanFilter(grayImage)),title('Weighted Mean Filter');
subplot(2,4,6),imshow(doGeometricMean(grayImage)),title('Geometric Mean Filter');
subplot(2,4,7),imshow(doHarmonicMeanFilter(grayImage)),title('Harmonic Mean Filter');
subplot(2,4,8),imshow(doModeFilter(grayImage)),title('Mode Filter');


