## OpenCV with MLP & MNIST dataset 
- Dataset : https://s3.amazonaws.com/img-datasets/mnist.npz

- MLP
 `mlp = cv2.ml.ANN_MLP_create()`
 `mlp.setActivationFunction(cv2.ml.ANN_MLP_SIGMOID_SYM, 2.5, 1.0)`
 `cv2.ml.ANN_MLP_BACKPROP`
 `cv2.ml.ANN_MLP_RPROP`
 `mlp.setTrainMethod(cv2.ml.ANN_MLP_BACKPROP)`
