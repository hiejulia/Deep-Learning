# Performance tuning for Tensorflow 
- nvidia-smi 
    - `watch -n .5 nvidia-smi`
    - `nvidia-smi --query-gpu=utilization.gpu --format=csv,noheader,nounits -f gpu_utilization.csv -l 1`
    - ` sort -n gpu_utilization.csv | grep -v '^0$' | datamash median 1`

- tf profiler + tf board 
    - 

- Data preparation 
    - TFRecord, TF Dataset 
- Data reading
    - tf.data, prefetch data, parallelize CPU processing, parallel IO processing, cache Data, turn on experimental optimization, autotune parameter values
- data augmentation 
    - GPU 
- Training
    - automatic mixed precision
    - large batch size
    - multiples of eight
    - find optimal learning rate
    - tf function 
    - overtrain, generalize
        - progressive aug
        - progressive resize
        - progressive sampling 
- install optimized stack for hardware 
- optimize number of parallel CPU threads 
- hardware 
- distirbuted training 
- inference 
    - efficient model 
    - quantize the model 
    - prune model 
    - fused opearations
    - GPU persistence 
- data preparation 
    - TFRecord 

- quantize the model 
- prune model 
    - magnitude based weight pruning 
    - keras API 
    - tencent pocketflow tool 
    - pruning methods in research papers 

- fused operation 
    

- enable GPU persistence 
    - NVIDIA GPU persistence daemon : nvidia-persistenced --user {YOUR_USERNAME}