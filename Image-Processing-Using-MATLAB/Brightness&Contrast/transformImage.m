function image=transformImage(image,factor)
    brightness=0.0;
    image=double(image);
    image(:,:)=image(:,:)*factor;
end