{\rtf1\ansi\ansicpg936\cocoartf1348\cocoasubrtf170
{\fonttbl\f0\fnil\fcharset0 Monaco;}
{\colortbl;\red255\green255\blue255;\red127\green0\blue85;\red63\green95\blue191;\red106\green62\blue62;
\red63\green127\blue95;}
\paperw11900\paperh16840\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\pard\tx560\tx1120\tx1680\tx2240\tx2800\tx3360\tx3920\tx4480\tx5040\tx5600\tx6160\tx6720\pardirnatural

\f0\fs22 \cf0 \
\pard\pardeftab720
\cf0 \
\pard\pardeftab720
\cf2 import\cf0  java.util.ArrayList;\
\cf2 import\cf0  java.util.List;\
\
\pard\pardeftab720
\cf3 /**\cf0 \
\cf3  * Created by \ul chao\ulnone  \ul wei\ulnone  on 26/08/16.\cf0 \
\cf3  */\cf0 \
\pard\pardeftab720
\cf2 public\cf0  \cf2 class\cf0  ApproximateMatchHandler \{\
\
    \cf2 public\cf0  Integer nGramDistance(String \cf4 query\cf0 , String \cf4 tweet\cf0 ) \{\
        \cf2 if\cf0 (\cf4 query\cf0 .length()<2 ||\cf4 tweet\cf0 .length()<2)\{\
            \cf2 return\cf0  99;\
        \}\cf2 else\cf0 \{\
        List<String> \cf4 tweetNgramList\cf0  = \cf2 new\cf0  ArrayList<String>();\
        \cf2 for\cf0  (\cf2 int\cf0  \cf4 i\cf0  = 0; \cf4 i\cf0  < \cf4 tweet\cf0 .length() - 1; \cf4 i\cf0 ++) \{\
            \cf4 tweetNgramList\cf0 .add(\cf4 tweet\cf0 .substring(\cf4 i\cf0 , \cf4 i\cf0  + 2));\
        \}\
        \cf2 int\cf0  \cf4 distance\cf0  = 0;\
        \cf2 for\cf0  (\cf2 int\cf0  \cf4 i\cf0  = 0; \cf4 i\cf0  < \cf4 query\cf0 .length() - 1; \cf4 i\cf0 ++) \{\
            \cf2 if\cf0  (\cf4 tweetNgramList\cf0 .contains(\cf4 query\cf0 .substring(\cf4 i\cf0 , \cf4 i\cf0  + 2))) \{\
                \cf4 distance\cf0 ++;\
            \}\
\
        \}\
        \cf2 return\cf0  \cf4 query\cf0 .length() + \cf4 tweet\cf0 .length() - 2 - \cf4 distance\cf0  * 2;\
    \}\}\
\
    \cf2 public\cf0  Integer globalEditDistance(String \cf4 query\cf0 , String \cf4 tweet\cf0 ) \{\
        \cf2 int\cf0  \cf4 lq\cf0  = \cf4 query\cf0 .length();\
        \cf2 int\cf0  \cf4 lt\cf0  = \cf4 tweet\cf0 .length();\
        Integer[][] \cf4 f\cf0  = \cf2 new\cf0  Integer[\cf4 lq\cf0  + 1][\cf4 lt\cf0  + 1];\
        \cf5 // initial operation\cf0 \
        \cf2 for\cf0  (\cf2 int\cf0  \cf4 i\cf0  = 0; \cf4 i\cf0  <= \cf4 lq\cf0 ; \cf4 i\cf0 ++) \{\
            \cf4 f\cf0 [\cf4 i\cf0 ][0] = \cf4 i\cf0 ;\
        \}\
        \cf2 for\cf0  (\cf2 int\cf0  \cf4 i\cf0  = 0; \cf4 i\cf0  <= \cf4 lt\cf0 ; \cf4 i\cf0 ++) \{\
            \cf4 f\cf0 [0][\cf4 i\cf0 ] = \cf4 i\cf0 ;\
        \}\
\
        \cf5 // calculate distance\cf0 \
        \cf2 for\cf0  (\cf2 int\cf0  \cf4 i\cf0  = 1; \cf4 i\cf0  <= \cf4 lq\cf0 ; \cf4 i\cf0 ++) \{\
            \cf2 for\cf0  (\cf2 int\cf0  \cf4 j\cf0  = 1; \cf4 j\cf0  <= \cf4 lt\cf0 ; \cf4 j\cf0 ++) \{\
                \cf5 // match/miss match\cf0 \
                \cf2 int\cf0  \cf4 c\cf0  = \cf4 query\cf0 .charAt(\cf4 i\cf0  - 1) == \cf4 tweet\cf0 .charAt(\cf4 j\cf0  - 1) ? \cf4 f\cf0 [\cf4 i\cf0  - 1][\cf4 j\cf0  - 1]: \cf4 f\cf0 [\cf4 i\cf0  - 1][\cf4 j\cf0  - 1] + 1;\
                \cf4 f\cf0 [\cf4 i\cf0 ][\cf4 j\cf0 ] = minDistance(\cf4 f\cf0 [\cf4 i\cf0  - 1][\cf4 j\cf0 ] + 1, \cf4 f\cf0 [\cf4 i\cf0 ][\cf4 j\cf0  - 1] + 1, \cf4 c\cf0 );\
            \}\
        \}\
        \cf2 return\cf0  \cf4 f\cf0 [\cf4 lq\cf0 ][\cf4 lt\cf0 ];\
    \}\
\
    \cf2 private\cf0  Integer minDistance(Integer \cf4 a\cf0 , Integer \cf4 b\cf0 , Integer \cf4 c\cf0 ) \{\
        \cf4 a\cf0  = \cf4 a\cf0  < \cf4 b\cf0  ? \cf4 a\cf0  : \cf4 b\cf0 ;\
        \cf2 return\cf0  \cf4 a\cf0  < \cf4 c\cf0  ? \cf4 a\cf0  : \cf4 c\cf0 ;\
    \}\
\}}