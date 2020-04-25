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


- t-SNE algo for visualize high dimensional data 
- Perform PCA over the features using 100 feature dimensions 


# Fine tuning 
- lable some amount of data 
- 87% accurancy improved 

# similarity search  
- 


# fine tune without fully connected layers 


# Siamese networks for one shot face verification 
- binary classifier 
- signature
- Siamese network
- metric learning 




1. [1-feature-extraction.ipynb]
- extract features from pretrained models like VGG-16, VGG-19, ResNet-50, InceptionV3 and MobileNet and benchmark them using the Caltech101 dataset.


2. [2-similarity-search-level-1.ipynb]
- indexer to index features and search for most similar features using various nearest neighbor algorithms, and explore various methods of visualizing plots.

- index feature 
- search using Nearest neighbor algo 



3. [2-similarity-search-level-2.ipynb]: we benchmark the algorithms based on the time it takes to index images and locate the most similar image based on its features using the Caltech-101 dataset. We also experiment with t-SNE and PCA.


4. [2-similarity-search-level-3.ipynb]2-similarity-search-level-3.ipynb): So far we experimented with different visualization techniques on the results, t-SNE and PCA on the results. Now we will calculate the accuracies of the features obtained from the pretrained and finetuned models. The finetuning here follows the same finetuning technique we learnt in Chapter 2.


5. [3-reduce-feature-length-with-pca.ipynb] We will experiment with PCA and figure out what is the optimum length of the features to use in our experiments.



6. [4-improving-accuracy-with-fine-tuning.ipynb]Many of the pre-trained models were trained on the ImageNet dataset. Therefore, they provide an incredible starting point for similarity computations in most situations. That said, if you tune these models to adapt to your specific problem, they would perform even more accurately for finding similar images.

