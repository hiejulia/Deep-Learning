# Generating song lyrics using RNNs
- word level RNN 
- probability distribution
- forward propagation
- softmax on output 
- BPTT
    - Adam optimizer
- gradient clipping to avoid the exploding gradients problems 


# RNN 
- forward propagation in RNN 
- BPTT 
    - gradients with hdden -> output layer weight, V 
    - gradients with hdden -> hidden layer weight, W
    - gradients with input -> hidden layer weight, U 

### dataset 
- character to integer mapping 
- one-hot encoding : input + output in vector representation 
