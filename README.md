App Layout :

Module 1 : 

Navigation :

Main Activity => Registration Page => Back to Main Activity
Main Activity => LogIn Activity => Send OTP => Verify OTP => Dashboard

All necessary features implemented.

username, password, retype password, type of user Select one of three, Location (better if enabled with Google API)
Login must be done using OTP verification (phone/email) every time.
Registration and signup using username and password or any social media (Gmail, Facebook login or Instagram)


Module 2 - 5: 

Navigation :

Based on the user type input from the login page, the user will be redirected to the appropriate fragments.

Tasks to complete :

Items’ card must have Image,name, textview to identify amount required.

Shops list cards must have distance textview, stock textview, price of product wrt shop and button to add to cart.

Cart (Another recycler view)

Place Order : Payment for online and reminder for offline mode

Order Status, Tracking Details Transaction Details, Feedback
Customer :
The navigation drawer must have the following menu items

Categories,
Cart, 
orders : ( payment,order status,feedback to be taken for every individual item ordered, updating stock after payment)
Logout

Retailer :

The navigation drawer must have the menu items

categories
cart
Orders: (items bought by the customer and items bought from the wholesalers; tracking system; feedback, updating stock after payment)
add and delete items
logout 

Wholesaler :

The navigation drawer must have the menu items :

Orders :( items bought by the retailer; tracking system; feedback )
add and delete items
Adding and deleting retailers
logout 









Navigation drawer - Recycler View

The dashboard will have a grid view and should consist of categories/subcategories with the necessary items required.
The tracking system will use a direction matrix. 

Customers: selection of items (by search, by using filters or by browsing), adding to cart, place order, payment of order, feedback/queries.  

Retailers: adding new items, deleting items, deciding the price of items while adding, maintaining record of each customer (items they have bought, transactions they have done till now etc..), update item quantities (after every order placed by customer), place order, order payment, feedback/queries, etc.  

Wholesalers: adding new items, deleting items, deciding the price of items while adding, adding or deleting retailers, maintaining record of each retailer (items they have bought, transactions they have done till now etc..), items that they supply to the retailers, update item quantities (after every order placed by retailer) etc.
Resources : 

Registration page: Google API Location
Login Page: OTP authentication https://www.youtube.com/playlist?list=PL4dbR4j6xH1tSGSeImdi95TYZ6mm40YVl

Firebase Authentication: OTP auth and Registration Details

https://www.youtube.com/watch?v=xQm5zCVaIZg&list=PLxefhmF0pcPlqmH_VfWneUjfuqhreUz-O&index=6&ab_channel=CodingCafe

How to move To Activity from Fragment Android Studio | Fragments Complete Tutorial
https://www.youtube.com/watch?v=fbB9L-gMSKo

Direction Matrix
https://www.youtube.com/watch?v=tXPEOJaeFm8

Safe Args
https://www.youtube.com/watch?v=CLzuMv0cFYg

Navigation Drawer
https://www.youtube.com/watch?v=ZodMjakYj3A

Dashboard - Grid View
https://www.youtube.com/watch?v=ixRXEoGAEZM

Items - Recycler View ( Use the first link for reference and try implementing the second link)
https://www.youtube.com/watch?v=sZ8D1-hNeWo

https://www.youtube.com/watch?v=V4E5ROnbrGk

https://www.youtube.com/watch?v=745ElNRjJew





Shopping Cart
https://www.youtube.com/watch?v=RrFkQ6o70d4&list=PLdHg5T0SNpN3-dPWdHlgu9lU3JnksBoHc


Location | Getting Current location | FusedLocationProviderClient | PlacePicker
https://www.youtube.com/watch?v=rNYaEFl6Fms&list=PLdHg5T0SNpN3GBUmpGqjiKGMcBaRT2A-m
https://www.youtube.com/watch?v=BeQbHTh6HlA
https://docs.mapbox.com/android/plugins/examples/place-picker/
https://www.youtube.com/watch?v=qY-xFxZ7HKY
https://www.youtube.com/watch?v=D5E9EzWOnqY

Offline Transaction: Time Notification
https://www.youtube.com/watch?v=L9XdV77NE_M

Sending notification via mail
https://www.youtube.com/watch?v=roruU4hVwXA

Order Update
https://www.youtube.com/watch?v=eUp0e1n8N58
https://www.youtube.com/watch?v=LYgf7u3gLzw











5th April :

Firebase commands upto #5 : https://www.youtube.com/playlist?list=PLS1QulWo1RIbKsL9GqxOLbToLNFFQFJW_

Navigation using Fragments :
 https://www.youtube.com/watch?v=DI0NIk-7cz8


Recycler View :
https://www.youtube.com/watch?v=745ElNRjJew == displaying from firebase
https://www.youtube.com/watch?v=3Cz3zjGL6oY&list=PLirRGafa75rTWVuf8gXSZgFOSqfFwpBC0&index=8  == lite ( recycler view fragments )
https://www.youtube.com/watch?v=LcdyI7G9Gds&list=PLxefhmF0pcPlqmH_VfWneUjfuqhreUz-O&index=45 == adding new products

https://drive.google.com/drive/folders/1B2EDHQ2viFfTnFKFacWWriw8X6taFb9w?fbclid=IwAR3iVbeoGBQIpboNsNoKsRbrm1wXY4ErR7nQBtT2yeIxSgifoPMxYkb-ovs
Source code

https://www.youtube.com/watch?v=QgfNl541oso&list=PLirRGafa75rTWVuf8gXSZgFOSqfFwpBC0&index=9
Jamal vidall


(CRUD) Operations : Watch the videos in this chronological order

https://www.youtube.com/watch?v=sZ8D1-hNeWo&list=PLirRGafa75rQOi3so_ngAHqDmq_Djifwu&index=9  
https://www.youtube.com/watch?v=QgfNl541oso&list=PLirRGafa75rTWVuf8gXSZgFOSqfFwpBC0&index=10

 (Recycler View, Create and Read )

https://www.youtube.com/watch?v=Do4tOzFk3jc&list=PLirRGafa75rQOi3so_ngAHqDmq_Djifwu&index=11  (Update and Delete)

https://www.youtube.com/watch?v=8KGN1272iuk ( Add )






How to retrieve specific node from firebase database in android

https://stackoverflow.com/questions/28601663/how-to-retrieve-specific-node-from-firebase-database-in-android

Spinner Tutorial

https://www.codeproject.com/Articles/1151816/Android-Spinner-Tutorial

