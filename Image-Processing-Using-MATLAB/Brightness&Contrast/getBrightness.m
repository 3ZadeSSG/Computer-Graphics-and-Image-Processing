function brightness=getBrightness(image)
    brightness=0.0;
    image=double(image);
    for i=1:size(image,1)
        for j=1:size(image,2)
            brightness=brightness+image(i,j);
        end
    end
    brightness=brightness/(size(image,1)*size(image,2));
end