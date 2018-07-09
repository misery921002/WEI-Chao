{\rtf1\ansi\ansicpg936\cocoartf1348\cocoasubrtf170
{\fonttbl\f0\fswiss\fcharset0 Helvetica;\f1\fswiss\fcharset0 ArialMT;}
{\colortbl;\red255\green255\blue255;}
\paperw11900\paperh16840\margl1440\margr1440\vieww10800\viewh10020\viewkind0
\pard\tx566\tx1133\tx1700\tx2267\tx2834\tx3401\tx3968\tx4535\tx5102\tx5669\tx6236\tx6803\pardirnatural

\f0\fs24 \cf0 \

\f1\fs28 This program is used to construct the new normalised, stemmed, and stop-worded feature vector attributes, and transform the training text data to CSV and ARFF  format.\
\pard\pardeftab720\sa240
\cf0 \expnd0\expndtw0\kerning0
\
The similarity in the number of training and development instances was inappropriate as ideally the number of training instances should be maximised whilst still allowing enough development instances to sufficiently test the explored machine learning algorithms. \
\pard\tx566\tx1133\tx1700\tx2267\tx2834\tx3401\tx3968\tx4535\tx5102\tx5669\tx6236\tx6803\pardirnatural
\cf0 \kerning1\expnd0\expndtw0 fvector-balancer.py is used to balance the training and developement files.\
\
fvector-extractor.py is used to remove stop words ,reducing inflected words to their stem and for Tweets by using NLPK library of python.\
\
fvector-instances.py is used to extract a list of tweets from the modified tweet files\
        or the ideal tweet-location pairs. Returns a dictionary.\
        key: user-id\
        value: tweet contents }