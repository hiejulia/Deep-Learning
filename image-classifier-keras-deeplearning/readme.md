# Image classification wiht Keras
+ Neural network 
- Load image
- 224x224 pixel
- range [0,1] or [–1,1]
- reuse pre trained model to build classifier that reach the state of the art accuracy 
- improve classification accuracy is to have a high threshold for accepting a classifier result 
- Reuse CNN pre trained in ImageNet dataset, remove the last few layers and replace with own classifier suited to the task 
- transfer learning : freeze the weights of the original model 

# Building a Custom Classifier in Keras with Transfer Learning
- Fine tuning a convolutional neural network
- Build data pipeline 
    - group multiple images together into batches
    - augment the data 
        - rotation, zooming, increase variation in trainign data
        - reduce overfiting
            - get more data 
            - heavily augment existing data 
            - fine tune fewer layers 
            - combine 

- model 
    - append a new classifier layer 
    - freee weights of original layers 
    - select optimizer algorithm 
- Reuse : multiclassificaion 
- backpropagation 
- rinse & repeat until the prediction become sufficiently accurate 
- batch size : variety of images from different class 
    - fit GPU memory 


- Start train model 
    - fit model to data 
    - epoch - reach 90 % -> last : 97% -> transfer learning 


- Test model 
    

<a href="https://imgur.com/nPbdXhr"><img src="https://i.imgur.com/nPbdXhr.jpg" title="source: imgur.com" /></a>

- misprediction 
    - illumination,unclear, background, lack of texture, smaller occupied area,etc 

# Data 
https://www.kaggle.com/c/dogs-vs-cats-redux-kernels-edition/data


# Training 
+ Use Google Colab offer free GPU runtime 


<a href="https://imgur.com/rEU93kt"><img src="https://i.imgur.com/rEU93kt.png" title="source: imgur.com" /></a>

<a href="https://imgur.com/jeOoCQ3"><img src="https://i.imgur.com/jeOoCQ3.png" title="source: imgur.com" /></a>

<a href="https://imgur.com/s909xZb"><img src="https://i.imgur.com/s909xZb.png" title="source: imgur.com" /></a>

<a href="https://imgur.com/o8FVRUt"><img src="https://i.imgur.com/o8FVRUt.png" title="source: imgur.com" /></a>

<a href="https://imgur.com/nPbdXhr"><img src="https://i.imgur.com/nPbdXhr.jpg" title="source: imgur.com" /></a>



# analyze result 
- accuracy 
- config pipeline for validate dataset
- find 


# how to run
+ Install :  
`pip install tensorflow`
`pip install keras -U`
`pip install numpy -U`
`pip install matplotlib -U`
`pip install tf-explain`
`pip install pillow`
`pip install pathlib`


