# Android-Universal-Web-Content-Loader(UWCL)

###WHAT IS THIS :-

Universal Web Content loader is Native in App brower which help developers to embed web contents in there apps with just one line of code. Web contents can we of any type - youtube playlist, Facebook page, in App Google search or payUMoney payment trannsactions.

### WHEN TO USE  :- 

If your app requires loading of web contents in your app and you dont want to waste your timing embedding webviews and writing webview and chrome clients.


###HOW TO USE :-

Usin this project is very simple :-
 - Drop this fragment in your UI package along with universal_web_view.xml and video_progress.xml.
 - Load web contents in your application with the help of just single line of code.
 - 
   This Fragment class supports all type of web content & can be used in many scenarios such as :-

1) you can embed youtube playlist in your App. The UniversalWebContentLoader(UWCL) allow user to play Videos in portrait mode as well.

				getSupportFragmentManager()
						.beginTransaction()
						.add(R.id.frag_root,
								UniversalWebViewFragment.newInstance(
										YOU_TUBE_DEMO_URL, false)).commit();

2) You can use this fragment for payment gateway like PayUMoney.(*just a word of warning WebViews are not very secure ,feel free to share wisdom and make code better) ) 

			getSupportFragmentManager()
								.beginTransaction()
								.add(R.id.frag_root,
										UniversalWebViewFragment.newInstance(
												PAYU_MONEY_URL, false))
								.commit();

3) Display your Facebook page in the App for latest news or load your twitter page for latest tweets.

	getSupportFragmentManager()
						.beginTransaction()
						.add(R.id.frag_root,
								UniversalWebViewFragment.newInstance(
										FACEBOOK_DEMO_URL, false)).commit();


4) It also allow you to search contents in Google in your App.Just pass searchQuery and true in arguments to load search results.as shown in code below

						getSupportFragmentManager()
								.beginTransaction()
								.add(R.id.frag_root,
										UniversalWebViewFragment
												.newInstance(
														((EditText) findViewById(R.id.serach_edt))
																.getText()
																.toString(),
														true)).commit();
														
Attached sample will demostarte some of above use cases :-


###Android Studio Project Screenshots

![Alt text](https://github.com/hiteshsahu/Android-Universal-Web-Content-Loader/blob/master/Art/studio_youtube.png "YouTube Sample")
![Alt text](https://github.com/hiteshsahu/Android-Universal-Web-Content-Loader/blob/master/Art/studio_fb.png "Facebool Example")
![Alt text](https://github.com/hiteshsahu/Android-Universal-Web-Content-Loader/blob/master/Art/studio_payu.png "payment Gateway Example")
![Alt text](https://github.com/hiteshsahu/Android-Universal-Web-Content-Loader/blob/master/Art/studio_google.png "payment Google Search Example")
![Alt text](https://github.com/hiteshsahu/Android-Universal-Web-Content-Loader/blob/master/Art/studio_oofline.png "Offline Example")

###Eclipse Project Screenshots

Eclipse project  is depricated



Copyright 2015 Hitesh Kumar Sahu

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 
