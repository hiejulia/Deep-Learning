# Set up with NVIDIA GPU 
- pip install tensorflow-gpu
- install NVIDIA GPU driver, CUDA, cuDNN, 


$ sudo apt update && sudo ubuntu-drivers autoinstall && sudo reboot 
$ export LAMBDA_REPO=$(mktemp) \
&& wget -O${LAMBDA_REPO} \
https://lambdalabs.com/static/misc/lambda-stack-repo.deb \
&& sudo dpkg -i ${LAMBDA_REPO} && rm -f ${LAMBDA_REPO} \
&& sudo apt-get update && sudo apt-get install -y lambda-stack-cuda \
&& sudo reboot




# AMD GPU 
- sudo apt install rocm-libs miopen-hip cxlactivitylogger

sudo apt install wget python3-pip

pip3 install --user tensorflow-rocm


# Test 
- `tf.test.is_gpu_available()`


# Multiple GPUs machine 
- GPU_ID 
