# SnapFood
A food recognition Android app that tracks the users' diet and provides intelligent feedback to meet their personalized goals. I developed this on my own for my IB Computer Science Internal Assessment. 


## Functionalities
* Users can take pictures of their foods on the camera page within the app, and the app can recognize the foods from the pictures.
* The app can provide nutrition facts information of the meals.
* Users can input their body information, personalized calories goals, and time given for maintaining the diet.
* The app can calculate the recommended number of calories the users have left and update it whenever new meals are generated.
* The app can give appropriate suggestions to the user about the foods they should try based on personal preferences of certain types of foods and their calorie budgets.


## Frameworks and Libraries
The mobile application is built in Java for the Android mobile platform using the [Android Studio IDE](https://developer.android.com/studio/).

For food recognition, I used the Tensorflow library for mobile. To learn more check out the [website:](https://codelabs.developers.google.com/codelabs/tensorflow-for-poets/#0). The . 

The nutrition facts for the foods are acquired through online by using the [WolframAlpha API](https://products.wolframalpha.com/api/).


## Food Recognition
The food recognition model is created on top of the Inception Convolutional Neural Network Model. It trains and fits specifically on the Food-101 dataset by transfer learning using Tensorflow. The food dataset can be downloaded through this link[here](https://www.vision.ee.ethz.ch/datasets_extra/food-101/).

The CNN is trained on my laptop with processor i7-4720HQ, 12 GB of RAM, and graphics card GTX950M with 2 GB memory. It is trained for around 8 hours. I should have used a remote virtual machine with a more powerful CPU and GPU to train the neural network, but because of financial and time constraints, I trained it on my laptop for a baseline functional model.

The resulting accuracy ranges from 50% to 60% and can be increased should the model be trained for a longer period of time.


![GitHub Logo](/images/image1.jpg)
![GitHub Logo](/images/image2.jpg)
![GitHub Logo](/images/image3.jpg)
![GitHub Logo](/images/image4.jpg)
![GitHub Logo](/images/image5.jpg)
![GitHub Logo](/images/image6.jpg)
![GitHub Logo](/images/image7.jpg)
![GitHub Logo](/images/image8.jpg)



## Usage
* Download and install [Android Studio IDE](https://developer.android.com/studio/).
* Clone this repo and import the project to Android Studio.




