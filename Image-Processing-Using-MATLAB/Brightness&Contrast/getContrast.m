function contrast=getContrast(image)
    contrast=0.0;
    brightness=getBrightness(image);
    image=double(image);
    for i=1:size(image,1)
        for j=1:size(image,2)
            contrast=contrast+(image(i,j)-brightness)*(image(i,j)-brightness);
        end
    end
    contrast=sqrt(contrast/(size(image,1)*size(image,2)));
end