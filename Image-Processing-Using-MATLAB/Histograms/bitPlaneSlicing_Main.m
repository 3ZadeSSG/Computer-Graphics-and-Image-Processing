%bit plane slicing script
ip=imread('screen.bmp');
gry=convertToGray(ip);
%{
img1=sliceLSB(gry,4);
img2=sliceMSB(gry,4);
subplot(1,3,1),imshow(gry),title('original');
subplot(1,3,2),imshow(img1);
subplot(1,3,3),imshow(img2);
%}
%subplot(2,4,1),imshow(gry),title('Original');
subplot(2,4,1),imshow(sliceBit(gry,1)),title('Bit 1');
subplot(2,4,2),imshow(sliceBit(gry,2)),title('Bit 2');
subplot(2,4,3),imshow(sliceBit(gry,3)),title('Bit 3');
subplot(2,4,4),imshow(sliceBit(gry,4)),title('Bit 4');
subplot(2,4,5),imshow(sliceBit(gry,5)),title('Bit 5');
subplot(2,4,6),imshow(sliceBit(gry,6)),title('Bit 6');
subplot(2,4,7),imshow(sliceBit(gry,7)),title('Bit 7');
subplot(2,4,8),imshow(sliceBit(gry,8)),title('Bit 8');


