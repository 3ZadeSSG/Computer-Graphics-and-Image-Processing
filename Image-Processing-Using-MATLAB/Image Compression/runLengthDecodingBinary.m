function result=runLengthDecodingBinary(sequence)
    result="";
    array=num2str(sequence)-'0';
    temp=1;
    for i=1:size(array,2)
        for j=1:array(i)
            if(temp==1)
                result=result+1;
            else
                result=result+0;
            end
        end
        if(temp==1)
            temp=0;
        else
            temp=1;
        end
    end
    result=str2num(result);
end