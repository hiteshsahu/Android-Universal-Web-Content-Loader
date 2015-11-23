# Android-Universal-Web-Content-Loader(UWCL)
Drop this fragment in your UI package and load web contents in your application just in a single line.
This Fragment class supports all type of web content & can be used in many scenarios such as :-

1) you can embed youtube playlist in your App. UniversalWebContentLoader(UWCL) allow user to play Videos in portrait mode as well.

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



Copyright 2015 Hitesh Kumar Sahu

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 
