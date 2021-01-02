ip=imread('cat.png');
gray_image=convertToGray(ip);

%gray_image=imread('screen.png');
imshow(gray_image);

gradX=getGradientImageX(gray_image);
gradY=getGradientImageY(gray_image);
gradGM=getGradientImage(gray_image);
gradGD=getGradientDirection(gray_image);

subplot(2,2,1),imshow(gradX),title('Gradient X');
subplot(2,2,2),imshow(gradY),title('Gradient Y');
subplot(2,2,3),imshow(gradGM),title('Gradient Magnitude');
subplot(2,2,4),imshow(gradGD),title('Gradient Direction');

