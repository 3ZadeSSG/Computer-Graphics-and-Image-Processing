function result=runLengthEncodingBinary(sequence)
    result="";
    array=num2str(sequence)-'0';
    count=0;
    temp=-1;
    for i=1:size(array,2)
        if(temp==-1)
            count=count+1;
            temp=array(i);
        elseif(temp~=array(i))
            result=result+count;
            count=1;
            temp=array(i);
        else
            count=count+1;
        end
    end
    result=result+count;
    result=str2num(result);
end