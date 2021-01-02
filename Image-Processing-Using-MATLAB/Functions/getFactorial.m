function factorial=getFactorial(number)
    factorial=1;
    while number>1
        factorial=factorial*number;
        number=number-1;
    end
end