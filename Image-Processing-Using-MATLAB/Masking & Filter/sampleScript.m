 ip=imread('cat.png');
 inputImage=convertToGray(ip);
 
 xMask=[-1,0,1;-2,0,2;-1,0,1];
 yMask=[-1,-2,-1;0,0,0;1,2,1];
 height=size(inputImage,1);
 width=size(inputImage,2);
 inputImage=double(inputImage);
 
 XgradImage=zeros(height,width,'double');
 YgradImage=zeros(height,width,'double');
 magImage=zeros(height,width,'double');
 dirImage=zeros(height,width,'double');
 
 padImage=zeros(height+3,width+3);
 padImage(1:height,1:width)=inputImage;
 mHeight=3;
 mWidth=3;
 for i=1:height
     for j=1:width
         region=padImage(i:i+mHeight-1,j:j+mWidth-1);
         XgradImage(i,j)=(sum(sum(region.*xMask)));
         YgradImage(i,j)=(sum(sum(region.*yMask)));
         magImage(i,j)=sqrt((XgradImage(i,j).^2)+(YgradImage(i,j).^2));
         dirImage(i,j)=atand(YgradImage(i,j)/XgradImage(i,j));
     end
 end
 
 XgradImage=uint8(XgradImage);
 YgradImage=uint8(YgradImage);
 magImage=uint8(magImage);
 dirImage=mat2gray(dirImage);
 inputImage=uint8(inputImage);
 subplot(2,3,1),imshow(inputImage),title("Input");
 subplot(2,3,2),imshow(XgradImage),title("X");
 subplot(2,3,3),imshow(YgradImage),title("Y");
 subplot(2,3,4),imshow(magImage),title("GM");
 subplot(2,3,5),imshow(dirImage),title("GD");
 
 
 