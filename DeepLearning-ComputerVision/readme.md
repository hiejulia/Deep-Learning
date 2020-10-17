# Project 1: Segmentation

## Semantic segmentation network FCN
- 4dim one hot vector prediction 
- Extract feature pyramid from Resnet
- Adam loss 
- Categorical cross entropy loss func 
- Metrics : mean IoU (mIoU) metrics
- Save weights to file 


## Training & test datasets with ground truth labels 
- Dataset : https://drive.google.com/file/d/1AdMbVK110IKLG7wJKhga2N2fitV1bVPA/view

- Dataset comprising 1,000 640 x 480 RGB train images and 50 640 x 480 RGB test images collected using an inexpensive USB camera (A4TECH PK-635G).
- Dataset annotator VGG Image Annotator (VIA)
- Annotation saved JSON file 
- segmentation_train.json
- segmentation_test.json

## Semantic segmentation validation 
- `python3 fcn.py --train`
- IoU : ground truth seg task vs predicted seg mask 
- 0.91
- Acc : 97.9%
- python3 fcn.py --evaluate 
--restore-weights=ResNet56v2-3layer-drinks-best-iou.h5

## Other 
- Mask-R-CNN 


## Paper
- Pyramid Scene Parsing Network (PSPNet) by Zhao et al. (2017)
- Kirillov, Alexander, et al.: Panoptic Segmentation. Proceedings of the IEEE conference on computer vision and pattern recognition. 2019.
- Long, Jonathan, Evan Shelhamer, and Trevor Darrell: Fully Convolutional Networks for Semantic Segmentation. Proceedings of the IEEE conference on computer vision and pattern recognition. 2015.
- Zhao, Hengshuang, et al.: Pyramid Scene Parsing Network. Proceedings of the IEEE conference on computer vision and pattern recognition. 2017.
- Dutta, et al.: VGG Image Annotator http://www.robots.ox.ac.uk/~vgg/software/via/
- He Kaiming, et al.: Mask R-CNN. Proceedings of the IEEE international conference on computer vision. 2017.
- Advanced Deep Learning with Tensorflow and Keras book 


# Project 2: