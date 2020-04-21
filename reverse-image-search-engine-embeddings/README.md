# reverse image search 
- instance retrieval 
# feature extract 
- dataset : http://www.vision.caltech.edu/Image_Datasets/Caltech101/
101_ObjectCategories.tar.gz


- ResNet-50 model 

- get_file_list 
- store features from dataset 

- write features to a pickle file 


# similarity search 
- principal components.
- curse of dimensionality



# visualized image cluster with t SNE 





- Comparision ANN lib 




# improve speed of similarity search 
- Length of Feature Vectors
Model	Bottleneck feature-length	Top-1% accuracy on ImageNet
VGG16	512	71.5%
VGG19	512	72.7%
MobileNet	1024	66.5%
InceptionV3	2048	78.8%
ResNet-50	2048	75.9%
Xception	2048	79.0%



# algorithms 
- Annoy : searching nearest neighbors 
- `pip install annoy`
- NGT : Yahoo Japan Neighborhood Graph and Tree 
- Faiss 
    - single GPU & multi GPU set up 
    - fastest ANN search on GPU 

- lib 
    - Annoy, NMSLIB, NGT, Faiss

# Fine tuning 
- lable some amount of data 
- 87% accurancy improved 




# fine tune without fully connected layers 


# Siamese networks for one shot face verification 
- binary classifier 
- signature
- Siamese network
- metric learning 
