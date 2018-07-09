{\rtf1\ansi\ansicpg936\cocoartf1348\cocoasubrtf170
{\fonttbl\f0\fnil\fcharset0 Monaco;}
{\colortbl;\red255\green255\blue255;\red127\green0\blue85;\red63\green95\blue191;\red0\green0\blue192;
\red106\green62\blue62;}
\paperw11900\paperh16840\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\deftab720
\pard\pardeftab720

\f0\fs22 \cf2 package\cf0  bean;\
\
\cf2 import\cf0  java.util.ArrayList;\
\cf2 import\cf0  java.util.List;\
\
\pard\pardeftab720
\cf3 /**\cf0 \
\cf3  * Created by \ul chao\ulnone  `\ul wei\ulnone  on 26/08/16.\cf0 \
\cf3  */\cf0 \
\pard\pardeftab720
\cf2 public\cf0  \cf2 class\cf0  MatchResultBean \{\
\
    \cf3 /**\cf0 \
\pard\pardeftab720
\cf3      * the distance of the query and \ul tweet\ulnone  (lower is better)\cf0 \
\cf3      */\cf0 \
    \cf2 private\cf0  Integer \cf4 distanceCnt\cf0  = 9999;\
    \cf3 /**\cf0 \
\cf3      * the result of calculating each matched string's order (lower is better)\cf0 \
\cf3      */\cf0 \
    \cf2 private\cf0  Integer \cf4 orderCnt\cf0  = 9999;\
\
    \cf2 private\cf0  TweetBean \cf4 tweetBean\cf0 ;\
\
    \cf2 private\cf0  List<String> \cf4 matchStrList\cf0  = \cf2 new\cf0  ArrayList<String>();\
\
    \cf2 public\cf0  List<String> getMatchStrList() \{\
        \cf2 return\cf0  \cf4 matchStrList\cf0 ;\
    \}\
\
    \cf2 public\cf0  \cf2 void\cf0  setMatchStrList(List<String> \cf5 matchStrList\cf0 ) \{\
        \cf2 this\cf0 .\cf4 matchStrList\cf0  = \cf5 matchStrList\cf0 ;\
    \}\
\
    \cf2 public\cf0  TweetBean getTweetBean() \{\
        \cf2 return\cf0  \cf4 tweetBean\cf0 ;\
    \}\
\
    \cf2 public\cf0  \cf2 void\cf0  setTweetBean(TweetBean \cf5 tweetBean\cf0 ) \{\
        \cf2 this\cf0 .\cf4 tweetBean\cf0  = \cf5 tweetBean\cf0 ;\
    \}\
\
    \cf2 public\cf0  Integer getDistanceCnt() \{\
        \cf2 return\cf0  \cf4 distanceCnt\cf0 ;\
    \}\
\
    \cf2 public\cf0  \cf2 void\cf0  setDistanceCnt(Integer \cf5 distanceCnt\cf0 ) \{\
        \cf2 this\cf0 .\cf4 distanceCnt\cf0  = \cf5 distanceCnt\cf0 ;\
    \}\
\
    \cf2 public\cf0  Integer getOrderCnt() \{\
        \cf2 return\cf0  \cf4 orderCnt\cf0 ;\
    \}\
\
    \cf2 public\cf0  \cf2 void\cf0  setOrderCnt(Integer \cf5 orderCnt\cf0 ) \{\
        \cf2 this\cf0 .\cf4 orderCnt\cf0  = \cf5 orderCnt\cf0 ;\
    \}\
\
\}}