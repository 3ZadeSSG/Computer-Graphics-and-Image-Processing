clear all; %clear all variables
clc; %clear the terminal screen
%{
%taking user input
num=input('Enter the number: ');
disp(num); %display the input number

%display using sprintf
message=sprintf('The input number is: %d',num);
disp(message);

%display using fprintf
fprintf('The input number is : %d\n',num); %by default the output file is console screen or terminal

%write result on a text file
fp=fopen('sample.txt','w');
fprintf(fp,'The input number is : %d\n',num);
fclose(fp);

%read text from a text file
fp=fopen('sample.txt','r');
disp(fscanf(fp,'%s'));
fclose(fp);
%}

%{
%print factorial of a number
number=input('Enter the number to find factorial: ');
fact=1;
while number>1
    fact=fact*number;
    number=number-1;
end
disp(fact);
%}

%{
%read numbers from a text file and write their factorial into a diff text file
fp=fopen('InputFeed.txt','r');
Array=fscanf(fp,'%d');
fclose(fp);
fp=fopen('Output.txt','w');
for i=1:size(Array,1)
   fact=1;
   for j=Array(i,1):-1:2
       fact=fact*j;
   end
   fprintf(fp,'%d  ',fact);
end
fclose(fp);
%}

%{
%read numbers from a text file using LOAD and print factorials in op file
load InputFeed.txt;
fp=fopen('Output.txt','w');
Array=InputFeed;
for i=1:size(Array,2)
   fact=1;
   for j=Array(1,i):-1:2
       fact=fact*j;
   end
   fprintf(fp,'%d  ',fact);
end
fclose(fp);
%}
A=importdata('WatchDogs2.jpg');
image(A);
image(A+A);


%transposing matrices
%{
A=[1 2 3];
B=[10 11 12];
disp(A'*B);
%}

%creating a matrix of 5x6 order with random numbers
%M=rand(5,6);
%disp(M);












