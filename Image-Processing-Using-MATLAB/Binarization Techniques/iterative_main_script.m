%iterative approach
%{
    
%}
ip=imread('Bridge.jpg');
gry=rgb2gray(ip);
height=size(gry,1);
width=size(gry,2);
hist_img=getNormalizedHistogram(gry);
gry=double(gry);
sumB=double(gry(1,1)+gry(1,width)+gry(height,1)+gry(height,width));
mB=double(sumB/4);
sumF=sum(sum(gry));
mF=double((sumF-sumB)/((height*width)-4));
T=getIterativeThreshold(mB,mF,hist_img);
disp('Threshold: ');
disp(T);

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
subplot(2,1,1),imshow(uint8(gry)),title('Input');
subplot(2,1,2),imshow(seg_img),title('Segment');

