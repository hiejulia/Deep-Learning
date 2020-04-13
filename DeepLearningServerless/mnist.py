import tensorflow as tf 


mnist = tf.keras.datasets.mnist


(x_train, y_train), (x_train, y_train) = mnist.load_data()



# Model 
model=tf.keras.models.Sequential([
    tf.keras.layers.Flatten(),
    tf.keras.layers.Dense
])
# Compile
model.compile(optimizer='adam',loss='sparse_categorical_crossentropy',metrics=['accuracy'])

# Fit 
model.fit()