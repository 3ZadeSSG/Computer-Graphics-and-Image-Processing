function convertedImage=convertToGrayScale(originalImage)
 convertedImage=zeros(size(originalImage,1),size(originalImage,2),'uint8');
 convertedImage(:,:)=(0.2989 *originalImage(:,:,1)) + (0.5870 * originalImage(:,:,2)) + (0.1140 *originalImage(:,:,3));
end