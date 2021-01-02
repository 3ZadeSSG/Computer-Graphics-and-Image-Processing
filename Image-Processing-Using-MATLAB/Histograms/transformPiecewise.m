function result=transformPiecewise(inputImage,a,b,constant)
    result=inputImage;
    for i=1:size(result,1)
        for j=1:size(result,2)
            intensity=double(result(i,j));
            if(intensity>=a && intensity<=b)
                result(i,j)=a;
            elseif(intensity>b && intensity<=255)
                result(i,j)=constant*(intensity^1.5);
            end
        end
    end
end