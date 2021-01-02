%{
 This function will take 3 parameters first one is source RGB image file,
 and other 2 filenames for result Gray and Binary images
%}
function result=imageConverter(filename,outputGrayFilename,outputBinaryFilename)
 RGBImage=imread(filename);
 GrayImage=convertToGrayScale(RGBImage);
 BinaryImage=convertToBinaryImage(GrayImage);
 %{
 imshow(RGBImage);
 imshow(GrayImage);
 imshow(BinaryImage);
 %}
 imwrite(GrayImage,outputGrayFilename);
 imwrite(BinaryImage,outputBinaryFilename);
end