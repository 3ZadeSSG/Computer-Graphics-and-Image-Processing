function result=runLengthEncoding(string_sequence)
    result="";
    count=0;
    temp="";
    array=string_sequence.split("");
    for i=2:size(array)
        if(temp==""||temp==array(i))
            temp=array(i);
            count=count+1;
        elseif(temp~=array(i))
                result=result+temp+count;
                count=1;
                temp=array(i);        
        end
    end
end