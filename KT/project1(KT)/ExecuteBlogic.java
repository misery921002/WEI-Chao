{\rtf1\ansi\ansicpg936\cocoartf1348\cocoasubrtf170
{\fonttbl\f0\fnil\fcharset0 Monaco;}
{\colortbl;\red255\green255\blue255;\red127\green0\blue85;\red63\green95\blue191;\red0\green0\blue192;
\red42\green0\blue255;\red106\green62\blue62;\red63\green127\blue95;}
\paperw11900\paperh16840\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\deftab720
\pard\pardeftab720

\f0\fs22 \cf2 import\cf0  bean.MatchResultBean;\
\cf2 import\cf0  bean.TweetBean;\
\
\cf2 import\cf0  java.io.*;\
\cf2 import\cf0  java.util.ArrayList;\
\cf2 import\cf0  \ul java.util.Date\ulnone ;\
\cf2 import\cf0  java.util.List;\
\
\pard\pardeftab720
\cf3 /**\cf0 \
\cf3  * Created by \ul chao\ulnone  \ul wei\ulnone  on 26/08/16.\cf0 \
\cf3  */\cf0 \
\pard\pardeftab720
\cf2 public\cf0  \cf2 class\cf0  ExecuteBlogic \{\
    \cf2 private\cf0  \cf2 static\cf0  ReadFileHandler \cf4 readFileHandler\cf0  = \cf2 new\cf0  ReadFileHandler();\
    \cf2 private\cf0  \cf2 static\cf0  ApproximateMatchHandler \cf4 approximateMatchHandler\cf0  = \cf2 new\cf0  ApproximateMatchHandler();\
    \cf2 private\cf0  \cf2 static\cf0  String \cf4 SPACE\cf0  = \cf5 " "\cf0 ;\
\
\
    \cf2 public\cf0  \cf2 static\cf0  \cf2 void\cf0  main(String[] \cf6 args\cf0 ) \{\
        String \cf6 queryFileName\cf0  = \cf6 args\cf0 [0];\
        String \cf6 tweetsFileName\cf0  = \cf6 args\cf0 [1];\
        approximateStrMatch(\cf6 queryFileName\cf0 , \cf6 tweetsFileName\cf0 , \cf5 "n-gram"\cf0 );\
        approximateStrMatch(\cf6 queryFileName\cf0 , \cf6 tweetsFileName\cf0 ,\cf5 "global_edit"\cf0 );\
    \}\
\
    \cf2 private\cf0  \cf2 static\cf0  \cf2 void\cf0  approximateStrMatch(String \cf6 queryFileName\cf0 , String \cf6 tweetsFileName\cf0 , String \cf6 matchMethod\cf0 ) \{\
        \cf7 // reader of queries and \cf7 \ul \ulc7 tweets\cf0 \ulnone \
        FileReader \cf6 qfFileReader\cf0  = \cf2 null\cf0 ;\
        BufferedReader \cf6 qfBufferReader\cf0  = \cf2 null\cf0 ;\
        FileReader \cf6 tfFileReader\cf0  = \cf2 null\cf0 ;\
        BufferedReader \cf6 tfBufferReader\cf0  = \cf2 null\cf0 ;\
        \cf7 // output writer\cf0 \
        FileWriter \cf6 fileWriter\cf0  = \cf2 null\cf0 ;\
        BufferedWriter \cf6 bufferedWriter\cf0  = \cf2 null\cf0 ;\
        String \cf6 outFilePath\cf0 ;\
        \cf2 if\cf0  (\cf5 "n-gram"\cf0 .equals(\cf6 matchMethod\cf0 ))\{\
            \cf6 outFilePath\cf0  =\cf5 "n-gram_distance.txt"\cf0 ;\
        \}\cf2 else\cf0 \{\
            \cf6 outFilePath\cf0  = \cf5 "global_edit_distance.txt"\cf0 ;\
        \}\
\
        \cf2 try\cf0  \{\
            \cf6 fileWriter\cf0  = \cf2 new\cf0  FileWriter(\cf6 outFilePath\cf0 );\
            \cf6 bufferedWriter\cf0  = \cf2 new\cf0  BufferedWriter(\cf6 fileWriter\cf0 );\
            \cf7 // \cf7 \ul \ulc7 tweet\cf7 \ulnone  list\cf0 \
            List<TweetBean> \cf6 tweetBeans\cf0  = \cf2 new\cf0  ArrayList<TweetBean>();\
\
            \cf7 // read query file\cf0 \
            \cf6 qfFileReader\cf0  = \cf2 new\cf0  FileReader(\cf6 queryFileName\cf0 );\
            \cf6 qfBufferReader\cf0  = \cf2 new\cf0  BufferedReader(\cf6 qfFileReader\cf0 );\
            \cf7 // read \cf7 \ul \ulc7 tweet\cf7 \ulnone  file\cf0 \
            \cf6 tfFileReader\cf0  = \cf2 new\cf0  FileReader(\cf6 tweetsFileName\cf0 );\
            \cf6 tfBufferReader\cf0  = \cf2 new\cf0  BufferedReader(\cf6 tfFileReader\cf0 );\
            \cf7 // read \cf7 \ul \ulc7 tweets\cf7 \ulnone  to the memory(list)\cf0 \
            String \cf6 tweetLine\cf0 ;\
            \cf2 while\cf0  ((\cf6 tweetLine\cf0  = \cf6 tfBufferReader\cf0 .readLine()) != \cf2 null\cf0 ) \{\
                \cf6 tweetBeans\cf0 .add(\cf4 readFileHandler\cf0 .covertTweet(\cf6 tweetLine\cf0 ));\
            \}\
            String \cf6 queryLine\cf0 ;\
            \cf2 while\cf0  ((\cf6 queryLine\cf0  = \cf6 qfBufferReader\cf0 .readLine()) != \cf2 null\cf0 ) \{\
                MatchResultBean \cf6 bestMatchBean\cf0  = \cf2 new\cf0  MatchResultBean();\
                \cf2 for\cf0  (TweetBean \cf6 tweetBean\cf0  : \cf6 tweetBeans\cf0 ) \{\
                    MatchResultBean \cf6 matchResultBean\cf0  = compareQueryAndTweet(\cf6 queryLine\cf0 , \cf6 tweetBean\cf0 .getTweetStr(),\cf6 matchMethod\cf0 );\
                    \cf2 if\cf0  (\cf6 matchResultBean\cf0 .getDistanceCnt() < \cf6 bestMatchBean\cf0 .getDistanceCnt()) \{\
                        \cf6 matchResultBean\cf0 .setTweetBean(\cf6 tweetBean\cf0 );\
                        \cf6 bestMatchBean\cf0  = \cf6 matchResultBean\cf0 ;\
                    \} \cf2 else\cf0  \cf2 if\cf0  (\cf6 matchResultBean\cf0 .getDistanceCnt() == \cf6 bestMatchBean\cf0 .getDistanceCnt() && \cf6 matchResultBean\cf0 .getOrderCnt() < \cf6 bestMatchBean\cf0 .getOrderCnt()) \{\
                        \cf6 matchResultBean\cf0 .setTweetBean(\cf6 tweetBean\cf0 );\
                        \cf6 bestMatchBean\cf0  = \cf6 matchResultBean\cf0 ;\
                    \}\
                \}\
                StringBuilder \cf6 matchesSB\cf0  = \cf2 new\cf0  StringBuilder();\
                \cf2 for\cf0  (String \cf6 matchstr\cf0  : \cf6 bestMatchBean\cf0 .getMatchStrList())\{\
                    \cf6 matchesSB\cf0 .append(\cf6 matchstr\cf0 );\
                    \cf6 matchesSB\cf0 .append(\cf4 SPACE\cf0 );\
                \}\
\
                \cf6 bufferedWriter\cf0 .write(\cf5 "Query String:"\cf0 +\cf6 queryLine\cf0 );\
                \cf6 bufferedWriter\cf0 .newLine();\
                \cf6 bufferedWriter\cf0 .write(\cf5 "Tweet ID:"\cf0 +\cf6 bestMatchBean\cf0 .getTweetBean().getId());\
                \cf6 bufferedWriter\cf0 .newLine();\
                \cf6 bufferedWriter\cf0 .write(\cf5 "Tweet String:"\cf0 +\cf6 bestMatchBean\cf0 .getTweetBean().getTweetStr());\
                \cf6 bufferedWriter\cf0 .newLine();\
                \cf6 bufferedWriter\cf0 .write(\cf5 "Distance:"\cf0 +\cf6 bestMatchBean\cf0 .getDistanceCnt().toString());\
                \cf6 bufferedWriter\cf0 .newLine();\
                \cf6 bufferedWriter\cf0 .newLine();\
                \cf6 bufferedWriter\cf0 .flush();\
            \}\
        \} \cf2 catch\cf0  (FileNotFoundException \cf6 e\cf0 ) \{\
            \cf6 e\cf0 .printStackTrace();\
        \} \cf2 catch\cf0  (IOException \cf6 e\cf0 ) \{\
            \cf6 e\cf0 .printStackTrace();\
        \} \cf2 finally\cf0  \{\
            \cf2 try\cf0  \{\
                \cf6 qfBufferReader\cf0 .close();\
                \cf6 tfBufferReader\cf0 .close();\
                \cf6 qfFileReader\cf0 .close();\
                \cf6 tfFileReader\cf0 .close();\
                \cf6 bufferedWriter\cf0 .close();\
                \cf6 fileWriter\cf0 .close();\
            \} \cf2 catch\cf0  (IOException \cf6 e\cf0 ) \{\
                \cf6 e\cf0 .printStackTrace();\
            \}\
\
        \}\
    \}\
\
    \cf2 private\cf0  \cf2 static\cf0  MatchResultBean compareQueryAndTweet(String \cf6 query\cf0 , String \cf6 tweet\cf0 , String \cf6 matchMethod\cf0 ) \{\
        String[] \cf6 queryWords\cf0  = \cf6 query\cf0 .trim().toLowerCase().split(\cf4 SPACE\cf0 );\
        String[] \cf6 tweetWords\cf0  = \cf6 tweet\cf0 .trim().toLowerCase().split(\cf4 SPACE\cf0 );\
        List<String> \cf6 matchStrList\cf0  = \cf2 new\cf0  ArrayList<String>();\
        \cf2 if\cf0 (\cf6 tweetWords\cf0 .\cf4 length\cf0 <\cf6 queryWords\cf0 .\cf4 length\cf0 )\{\
            MatchResultBean \cf6 matchResultBean\cf0  = \cf2 new\cf0  MatchResultBean();\
            \cf6 matchResultBean\cf0 .setDistanceCnt(99);\
            \cf6 matchResultBean\cf0 .setOrderCnt(99);\
            \cf2 return\cf0  \cf6 matchResultBean\cf0 ;\
        \}\
        Integer \cf6 queryDistance\cf0  = 0;\
        Integer \cf6 orderDistance\cf0  = 0;\
        Integer \cf6 lastIndex\cf0  = 0;\
        \cf2 for\cf0  (String \cf6 queryWord\cf0  : \cf6 queryWords\cf0 ) \{\
            Integer \cf6 matchIndex\cf0  = 0;\
            Integer \cf6 matchDistance\cf0  = 9999;\
            \cf2 for\cf0  (\cf2 int\cf0  \cf6 i\cf0  = 0; \cf6 i\cf0  < \cf6 tweetWords\cf0 .\cf4 length\cf0 ; \cf6 i\cf0 ++) \{\
                \cf2 if\cf0  (!\cf5 ""\cf0 .equals(\cf6 queryWord\cf0 .trim()) && !\cf5 ""\cf0 .equals(\cf6 tweetWords\cf0 [\cf6 i\cf0 ].trim())) \{\
                    Integer \cf6 distance\cf0  = 0;\
                    \cf2 if\cf0  (\cf5 "n-gram"\cf0 .equals(\cf6 matchMethod\cf0 ))\{\
                        \cf6 distance\cf0  = \cf4 approximateMatchHandler\cf0 .nGramDistance(\cf6 queryWord\cf0 , \cf6 tweetWords\cf0 [\cf6 i\cf0 ]);\
                    \} \cf2 else\cf0 \{\
                        \cf6 distance\cf0  = \cf4 approximateMatchHandler\cf0 .globalEditDistance(\cf6 queryWord\cf0 , \cf6 tweetWords\cf0 [\cf6 i\cf0 ]);\
\
                    \}\
                    \cf2 if\cf0  (\cf6 distance\cf0  < \cf6 matchDistance\cf0 ) \{\
                        \cf6 matchIndex\cf0  = \cf6 i\cf0 ;\
                        \cf6 matchDistance\cf0  = \cf6 distance\cf0 ;\
                    \}\
                \}\
                \cf2 if\cf0  (\cf6 matchIndex\cf0  < \cf6 lastIndex\cf0 ) \{\
                    \cf6 orderDistance\cf0 ++;\
                \}\
            \}\
            \cf6 lastIndex\cf0  = \cf6 matchIndex\cf0 ;\
            \cf6 queryDistance\cf0  = \cf6 queryDistance\cf0  + \cf6 matchDistance\cf0 ;\
            \cf6 matchStrList\cf0 .add(\cf6 tweetWords\cf0 [\cf6 matchIndex\cf0 ]);\
        \}\
\
        MatchResultBean \cf6 matchResultBean\cf0  = \cf2 new\cf0  MatchResultBean();\
        \cf6 matchResultBean\cf0 .setDistanceCnt(\cf6 queryDistance\cf0 );\
        \cf6 matchResultBean\cf0 .setOrderCnt(\cf6 orderDistance\cf0 );\
        \cf6 matchResultBean\cf0 .setMatchStrList(\cf6 matchStrList\cf0 );\
        \cf2 return\cf0  \cf6 matchResultBean\cf0 ;\
    \}\
\}}