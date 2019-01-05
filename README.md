# SnapFood
A food recognition Android app that tracks the users' diet and provide intelligent feedback to meet their personalized goals. I developed this on my own for my IB Computer Science Internal Assessment. 


## Functionalities
* Users can take pictures of their foods on the camera page within the app.
* The app can recognize the foods from the pictures.
* The app provides an interface that displays the meals in an organized list fashion.
* Users can add a new meal or delete an existing meal in the list.
* The app can return precise nutrition facts information of the meals in the list.
* Users can input their body information, personalized calories goals, and time given for maintaining the diet.
* The app can accurately display the calories of each meal.
* The app can calculate the recommended number of calories the users have left and update it whenever new meals are generated.
* The app can give appropriate suggestions to the user about the foods they should try based on personal preferences of certain types of foods and their calorie budgets.


## Frameworks and Libraries
The mobile application is built in Java for the Android mobile platform using the [Android Studio IDE](https://developer.android.com/studio/).
For food recognition, I used the Tensorflow library for mobile. To learn more check out the [website:](https://codelabs.developers.google.com/codelabs/tensorflow-for-poets/#0) 
The nutrition facts for the foods are acquired through online by using the [WolframAlpha API](https://products.wolframalpha.com/api/).


## Food Recognition
The food recognition model is created on top of the Inception Model by transfer learning and trained specifically on the different categories of foods from the Food-101 dataset, which can be downloaded [here](https://www.vision.ee.ethz.ch/datasets_extra/food-101/).
The model is trained on a laptop with processor i7-4720HQ, 12 GB of RAM, and graphics card GTX950M. It is trained for around 8 hours. I should have used a remote virtual machine with a more powerful CPU and GPU to train the neural network, but because of financial and time constraints, I trained it on my laptop.
The resulting accuracy is 50% to 60% and can be increased should the model be trained for a longer period of time.


![GitHub Logo](/images/image1.jpg)
![GitHub Logo](/images/image2.jpg)
![GitHub Logo](/images/image3.jpg)
![GitHub Logo](/images/image4.jpg)
![GitHub Logo](/images/image5.jpg)
![GitHub Logo](/images/image6.jpg)
![GitHub Logo](/images/image7.jpg)
![GitHub Logo](/images/image8.jpg)








