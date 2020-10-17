### Set up env



##### 
- pip install tensorflow-gpu
- Anaconda Python
- conda install tensorflow-gpu.
- AMD GPU 
    - sudo apt install rocm-libs miopen-hip cxlactivitylogger
    - sudo apt install wget python3-pip 
    - pip3 install --user tensorflow-rocm 
- VM 
    - AWS, Azure, GPU, Alibaba
    - NVIDIA GPU cloud containers
- Multiple GPU 
    - CUDA_VISIBLE_DEVICES=GPU_ID python train.py

#### Useful python libs
- Telemetry : FastProgress progress bar
- Alert : 
- PlotNeuralNet - Create CNN diagrams 


#### Data 
- Fatkun Batch Download Image
- Scrape Google for images 
    - pip install google_images_download
    - googleimagesdownload -k=horse -l=50 -r=labeled-for-reuse 
    - selenium 
- https://github.com/ajschumacher/imagen 
- Google Dataset Search 
- tensorflow dataset 


#### Debug 
- TensorFlow Debugger tfdbg 
#### Tools 
- tensorflow hub 
