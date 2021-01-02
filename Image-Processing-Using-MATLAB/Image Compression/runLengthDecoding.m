function result=runLengthDecoding(string_sequence)
    result="";
    array=string_sequence.split("");
    temp="";
    count="";
    for i=2:size(array)
        if(array(i)>="0" && array(i)<="9")
            count=count+array(i);
        elseif(temp=="")
            temp=array(i);
        else
            for id=1:str2num(count)
                result=result+temp;
            end
            count="";
            temp=array(i);
        end
    end
end