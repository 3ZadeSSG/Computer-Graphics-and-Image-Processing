ip=imread('Bridge.jpg');
gry=rgb2gray(ip);
img_bernsen=applyBernsen(gry);
img_sauvola=applySauvola(gry);
img_niblack=applyNiblack(gry);
img_iterative=applyIterativeThreshold(gry);

subplot(3,2,1),imshow(gry),title("Source");
subplot(3,2,2),imshow(img_niblack),title("Niblack");
subplot(3,2,3),imshow(img_sauvola),title("Sauvola");
subplot(3,2,4),imshow(img_bernsen),title("Bernsen");
subplot(3,2,5),imshow(img_iterative),title("Iterative");

