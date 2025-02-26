"""Data generator
This is a multi-threaded, scalable, and efficient way of reading huge images
from a filesystem as dataset.

Modified to generate a pair of image tensors and its corresponding pixel wise ground truth labels
or segmentation task
"""

from __future__ import absolute_import
from __future__ import division
from __future__ import print_function
from __future__ import unicode_literals

from tensorflow.keras.utils import Sequence

import numpy as np
import os
import skimage
from skimage.io import imread
from model_utils import parser

"""Multi-threaded data generator.
    Each thread reads a batch of images and their object labels.
    A label is a pixel-wise semantic mask.

    Arguments:
        args : User-defined configuration
        shuffle (Bool): If dataset should be shuffled 
            before sampling
    """
class DataGenerator(Sequence):
    def __init__(self,
                 args,
                 shuffle=True):
        self.args = args
        self.input_shape = (args.height, 
                            args.width,
                            args.channels)
        self.shuffle = shuffle
        self.get_dictionary()
        self.on_epoch_end()
    
    # Number batches per epoch
    def __len__(self):
        blen = np.floor(len(self.dictionary) / self.args.batch_size)
        return int(blen)
    
    # Load ground truth dictionary of image file name : segmentation masks
    def get_dictionary(self):
        path = os.path.join(self.args.data_path,
                            self.args.train_labels)
        self.dictionary = np.load(path,
                                  allow_pickle=True).flat[0]
        self.keys = np.array(list(self.dictionary.keys()))
        labels = self.dictionary[self.keys[0]]
        self.n_classes = labels.shape[-1]



    # Get a batch of data
    def __getitem__(self, index):
        start_index = index * self.args.batch_size
        end_index = (index+1) * self.args.batch_size
        keys = self.keys[start_index : end_index]
        x, y = self.__data_generation(keys)
        return x, y



    # Shuffle after epoch
    def on_epoch_end(self):
        if self.shuffle == True:
            np.random.shuffle(self.keys)


    """Generate train data: images and 
        segmentation ground truth labels 

        Arguments:
            keys (array): Randomly sampled keys
                (key is image filename)

        Returns:
            x (tensor): Batch of images
            y (tensor): Batch of pixel-wise categories
        """
    def __data_generation(self, keys):
        
        # a batch of images
        x = []
        # corresponding segmentation masks
        y = []

        for i, key in enumerate(keys):
            # images are assumed to be stored 
            # in self.args.data_path
            # key is the image filename 
            image_path = os.path.join(self.args.data_path, key)
            image = skimage.img_as_float(imread(image_path))
            
            x.append(image)
            
            labels = self.dictionary[key]
            y.append(labels)

        return np.array(x), np.array(y)



    




if __name__ == '__main__':
    parser = parser()
    args = parser.parse_args()
    data_gen = DataGenerator(args)
    images, labels = data_gen.__getitem__(0)
    
    import matplotlib.pyplot as plt
    
    plt.xlabel('x')
    plt.ylabel('y')
    plt.title('Input image', fontsize=14)
    plt.imshow(images[0])
    plt.savefig("input_image.png", bbox_inches='tight')
    plt.show()

    labels = labels * 255
    masks = labels[..., 1:]
    bgs = labels[..., 0]

    plt.xlabel('x')
    plt.ylabel('y')
    plt.title('Semantic segmentation', fontsize=14)
    plt.imshow(masks[0])
    plt.savefig("segmentation.png", bbox_inches='tight')
    plt.show()

    shape = (bgs[0].shape[0], bgs[0].shape[1])
    plt.xlabel('x')
    plt.ylabel('y')
    plt.title('Background', fontsize=14)
    plt.imshow(np.reshape(bgs[0], shape), cmap='gray', vmin=0, vmax=255)
    plt.savefig("background.png", bbox_inches='tight')
    plt.show()

