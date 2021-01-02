ip=imread('cat.png');
gry=convertToGray(ip);
a=100;
b=150;
c=2;

op_img=transformPiecewise(gry,a,b,c);
hist_gry=getNormalizedHistogram(gry);
hist_op=getNormalizedHistogram(op_img);

subplot(2,2,1),imshow(gry);
subplot(2,2,2),plot(hist_gry);
subplot(2,2,3),imshow(op_img);
subplot(2,2,4),plot(hist_op);
