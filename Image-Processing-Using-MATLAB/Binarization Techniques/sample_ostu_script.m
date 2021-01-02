ip=imread('Bridge.jpg');
%gry=convertToGray(ip);
gry=rgb2gray(ip);
gry_hist=getNormalizedHistogram(gry);
T=otsuThreshold(gry_hist);
disp(T);
height=size(gry,1);
width=size(gry,2);
seg_img=zeros(height,width,'uint8');
for i=1:height
    for j=1:width
        if(gry(i,j)<=T)
            seg_img(i,j)=0;
        else
            seg_img(i,j)=255;
        end
    end
end
subplot(2,1,1),imshow(gry),title('Input');
subplot(2,1,2),imshow(seg_img),title('Segment');
