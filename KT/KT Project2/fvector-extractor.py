#!/usr/bin/python


import re
import sys
from nltk.corpus import stopwords
from nltk.stem.porter import *

def read_tweets_file(filename):
    """ Read the raw user id.-tweet tupples in 
        (assume a tweet ends in a time-stamp) """
    f = open(filename, 'rU')
    text = f.read()
    f.close()

    # look for a time-stamp which signifies the end of a tweet
    tweets = re.findall(r'(\d+)\t(\d+)\t(.+)\d+-\d+-\d+ \d+:\d+:\d+', text)

    return tweets

def read_dictionary_file(filename):
    """ Reads a file mapping Twitter slang to a normalised word """
    f = open(filename, 'rU')
    lines = f.readlines()
    f.close()

    d = {}
    for line in lines:
        words = line.split('\t')

        if d.get(words[0]):
            print('Error: OOV word should not map to multiple ' 
                  'different entries!')
            sys.exit()
        else:
            d[words[0]] = re.sub(r'\n', '', words[1])

    return d

def modify_tweets(tweets, flag, d):
    """ Modify the raw user id.-tweet tupples to be lower case and contain only 
        alphabetic characters (including space)"""
    
    # sort the tweets by user id then tweet id then tweet text 
    tweets = sorted(tweets)
    
    stop = stopwords.words('english')
    mtweets = []
    for tweet in tweets:
        # intialise the Porter Stemmer
        stemmer = PorterStemmer()

        if flag == '-B':
            # make all letters in the tweet text lower case
            mtweet_text = tweet[2].lower()
            
            # split the tweet into words
            mtweet_words = mtweet_text.split(' ')

            # normalise the words using the Unimelb ESMN
            tweet_words = []
            for word in mtweet_words:
                if word in d:
                    tweet_words.append(d[word])
                else:
                    tweet_words.append(word)
            mtweet_words = tweet_words

            # remove all non-alphabetic characters (excluding space) 
            mtweet_words = [re.sub(r'[^a-z ]', '', mword) for mword in 
                            mtweet_words if re.sub(r'[^a-z ]', '', mword)]

            # stem the tweet words
            mtweet_words = [stemmer.stem(word) for word in mtweet_words]

            # remove words that are 1 characters or less
            mtweet_words = [word for word in mtweet_words if len(word) > 1]
            
            # remove common words
            mtweet_words = [word for word in mtweet_words if word not in stop]

            mtweet_text = ' '.join(mtweet_words)

        if mtweet_text:
            mtweets.append((tweet[0], tweet[1], mtweet_text))

    return mtweets


def write_tweets_to_file(tweets, filename):
    """ Write the modified user id.-tweet tupples to file 
        (seperated by a newline) """
    f = open(filename, 'w')
    # convert the tweets from a 2d tupple to a tab seperated string
    tweets = ['\t'.join(tweet) + '\n' for tweet in tweets]

    # write the tweets to file
    f.writelines(tweets)
    f.close()


def main():
    """ Entry point """
    if len(sys.argv) != 5:
        print('usage: tweets-extractor.py FLAG dict-file ' 
              'src-file destination-file')
        sys.exit()

    flag_str = sys.argv[1]
    dict_filename = sys.argv[2]
    src_filename = sys.argv[3]
    dest_filename = sys.argv[4]

    tweets = []
    tweets = read_tweets_file(src_filename)

    d = read_dictionary_file(dict_filename)

    tweets = modify_tweets(tweets, flag_str, d)
    write_tweets_to_file(tweets, dest_filename)

if __name__ == '__main__':
    main()