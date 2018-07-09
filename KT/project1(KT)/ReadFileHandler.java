{\rtf1\ansi\ansicpg936\cocoartf1348\cocoasubrtf170
{\fonttbl\f0\fnil\fcharset0 Monaco;}
{\colortbl;\red255\green255\blue255;\red127\green0\blue85;\red63\green95\blue191;\red106\green62\blue62;
\red42\green0\blue255;}
\paperw11900\paperh16840\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\deftab720
\pard\pardeftab720

\f0\fs22 \cf2 import\cf0  bean.TweetBean;\
\
\pard\pardeftab720
\cf3 /**\cf0 \
\cf3  * Created by \ul chao\ulnone  \ul wei\ulnone  on 26/08/16.\cf0 \
\cf3  */\cf0 \
\pard\pardeftab720
\cf2 public\cf0  \cf2 class\cf0  ReadFileHandler \{\
\
    \cf2 public\cf0  TweetBean covertTweet(String \cf4 tweet\cf0 ) \{\
        TweetBean \cf4 tweetBean\cf0  = \cf2 new\cf0  TweetBean();\
        \cf2 int\cf0  \cf4 i\cf0  = \cf4 tweet\cf0 .indexOf(\cf5 "\\t"\cf0 );\
        \cf2 if\cf0  (\cf4 i\cf0  == -1) \{\
            \cf4 tweetBean\cf0 .setId(\cf4 tweet\cf0 .substring(0, 10));\
            \cf4 tweetBean\cf0 .setTweetStr(\cf4 tweet\cf0 .substring(10, \cf4 tweet\cf0 .length()));\
        \} \cf2 else\cf0  \{\
            \cf4 tweetBean\cf0 .setId(\cf4 tweet\cf0 .substring(0, \cf4 i\cf0 ));\
            \cf4 tweetBean\cf0 .setTweetStr(\cf4 tweet\cf0 .substring(\cf4 i\cf0  + 1, \cf4 tweet\cf0 .length()));\
        \}\
        \cf2 return\cf0  \cf4 tweetBean\cf0 ;\
    \}\
\}}