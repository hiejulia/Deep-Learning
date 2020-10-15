# GAN 

- DCGAN
- CGAN 
- Progressive GAN
- StyleGAN

## TimeGan 
- Embedding(AE) : recovery network 
- Adversarial network : seqence G & D
- Reconstruction loss, unsupervised loss, supervised loss 
- train AE on real time series to optimize reconstruction 
- optimize supervised loss using real time series to capture temporal dynamics of historical data
- jointly train 4 components while minimizing all 3 loss function 
- implementation of TimeGan (tf) (GAN/TimeGAN)
    - TimeGAN architecture 
    - select & prepare real & random time series input 
        - financial data using 15 years of daily google stock prices (6 features)
        - synthetic data 
    - create model 
        - 2 AE components 
            - embedder 
        - 2 adversarial network 
        - RNN : 3 hidden layers - 24 GRU 
        - G 
        - D 
        - loss func: MSE, BCE 
    - define loss func 


## DCGAN in MNIst dataset 
- Deep CNN 
- Dense only 1st layer of G 
- Batch normalization 
- ReLU : all layers in G except output layer , tanh 
- sigmoid is used 
- Leaky ReLU : all layers in D 
- alpha = 0.2
- kernel size = 5 

- TransposeCNN is used
- G: 2 Conv2DTranspose strides = 2 -> batch normalization -> ReLU -> final> sigmoid activation 

- D: 4 CNN layers -> strides = 2 -> Leaky ReLU -> filter size -> flatten -> Dense -> 0 vs 1 (scale signmoid ) -> Binary cross entropy loss function is used

- Adversarial model 
    - RMSprop optimizer

- Batch size = 64 



- Use pretrained model : 
    - python3 dcgan-mnist.py --generator=dcgan_mnist.h5



## CGAN in Mnist dataset 
- Loss function D 
- Loss function G 


- Run : 
    python3 cgan-mnist.py --generator=cgan_mnist.h5
    python3 cgan-mnist.py --generator=cgan_mnist.h5 --digit=8



<a href="https://imgur.com/IsduGOU"><img src="https://i.imgur.com/IsduGOU.png" title="source: imgur.com" /></a>






## Ref
- Ian Goodfellow. NIPS 2016 Tutorial: Generative Adversarial Networks. arXiv preprint arXiv:1701.00160, 2016 (https://arxiv.org/pdf/1701.00160.pdf).
- Alec Radford, Luke Metz, and Soumith Chintala. Unsupervised Representation Learning with Deep Convolutional Generative Adversarial Networks. arXiv preprint arXiv:1511.06434, 2015 (https://arxiv.org/pdf/1511.06434.pdf).
- Mehdi Mirza and Simon Osindero. Conditional Generative Adversarial Nets. arXiv preprint arXiv:1411.1784, 2014 (https://arxiv.org/pdf/1411.1784.pdf).
- Tero Karras et al. Progressive Growing of GANs for Improved Quality, Stability, and Variation. ICLR, 2018 (https://arxiv.org/pdf/1710.10196.pdf).
- Tero Karras, , Samuli Laine, and Timo Aila. A Style-Based Generator Architecture for Generative Adversarial Networks. Proceedings of the IEEE Conference on Computer Vision and Pattern Recognition. 2019.
- Tero Karras et al. Analyzing and Improving the Image Quality of StyleGAN. 2019 (https://arxiv.org/abs/1912.04958).