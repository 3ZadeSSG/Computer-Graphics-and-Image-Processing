function nil=mainFunction(filename)
 img=imread(filename);
 image=rgb2gray(img);
 image2=transformImage(image,0.5);
 image3=transformImage(image,2.5);
 image4=transformImage(image,10);
 subplot(2,2,1),imshow(image);
 subplot(2,2,2),imshow(image2);
 subplot(2,2,3),imshow(image3);
 subplot(2,2,4),imshow(image4);
end