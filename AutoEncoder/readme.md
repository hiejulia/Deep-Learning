# AE

### AE on Mnist dataset 
- encoder 
    - input 
    - conv2d 
    - conv2d 
    - flatten 
    - latent vector : dense 

- decoder 
    - dense
    - reshape
    - conv2dTranspose
    - conv2dTranspose
    - 

- MSE loss func
- adam optim


## Denoising AE - DAE 
- add noise  Gaussian distribution
    - mean = 0.5
    - std = 0.5 
    - [0.1, 1.0]  pixel 

- MSE 
- Adam optim

## Automatic colorization AE
- Double number of filters each CNN block 
- add 1 more block of conv and transpose conv
- latent vector = 256 dim 
- tSNE 
- PCA 


## Colorization AE
- Dataset : cifar10


Ref 
- Deep Learning book 
