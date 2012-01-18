#!/usr/bin/env python
#coding = utf-8
#writer:Qiaoy<TraceurQ@gmail.com>
'''
android : put the movie name and choise the file
php : show the message and as a transit
python : The server for find movie

'''

import re,sys
import urllib2,urllib,httplib,cookielib
import time
from os.path import basename
from urlparse import urlsplit

def find():#the python spider
    page = urllib2.urlopen("http://www.Yousite/movie.php").read()#find the movie's name
    print 'find() running'
    print dict(page)
    if not dict(page):
        if urllib2.urlopen("http://www.Yousite/movie_choice.php").read() != 'Have Downloaded':
            post_movie('http://www.Yousite/movie_choice.php','Have Downloaded@@@0')
        print 'movie has download'
        sites = 0
    else:
        if page:
            data = {}
            data['q'] = page + " site:115.com"
            data['start'] = 500
            data['num'] = 100
            EnData = urllib.urlencode(data)
            url = "http://www.google.com.hk/search"
            fullurl = url + "?" + EnData
            request = urllib2.Request(fullurl)
            request.add_header('User-Agent', 'Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0)')#search the movie's for 115.com at google.com
            html = urllib2.urlopen(request).read()
            try:
                site = re.findall(r'<a href=\"(http://[0-9A-Za-z/_.-]*)\"',html)#use RE to get the useful url
                sites = []
                for i in site:
                    if "google.com" not in i:
                        if "115.com/file/" in i:
                            sites.append(i)
                print 'google search'
            except:
                sites = 'Google is closed ! Y could find after while@@@0'
        else:
            sites = 0
    return sites

def get_title(site):#get the url's title
    page = urllib2.urlopen(site).read()
    title = re.findall(r'<span class=\"file-name\">(.*?)<\/span>',page)[0]
    size = re.findall(r'file_size: \'(.*?)\'',page)[0]
    if not title:
        title = 'Null'
    if not size:
        size = '0'
    return size+'|||'+title


def url2name(url):
    return basename(urlsplit(url)[2])

def download(url, localFileName = None):#download the file
    print 'downloading......'
    post_movie('http://www.Yousite/movie_state.php','downloading....')
    post_movie('http://www.Yousite/movie_choice.php','')
    localName = url2name(url)
    req = urllib2.Request(url)
    r = urllib2.urlopen(req)
    if r.info().has_key('Content-Disposition'):
        localName = r.info()['Content-Disposition'].split('filename=')[1]
        if localName[0] == '"' or localName[0] == "'":
            localName = localName[1:-1]
    elif r.url != url:
        localName = url2name(r.url)
    if localFileName:
        localName = localFileName
    f = open(localName, 'wb')
    f.write(r.read())
    f.close()
    post_movie('http://www.Yousite/movie_state.php','success!')
    return 'ok'

def get_movie():#get the android's choice
    print 'get_movie() running'
    page = urllib2.urlopen("http://www.Yousite/down.php").read()
    print page
    return page

def down_115(site):#the re for 115.com
    print 'down_115() running'
    params=urllib.urlencode({'login[account]':'traceurq@gmail.com','login[passwd]':'123456'})
    cj = cookielib.LWPCookieJar()
    opener = urllib2.build_opener(urllib2.HTTPCookieProcessor(cj))
    urllib2.install_opener(opener)
    f=opener.open("http://passport.115.com/?ac=login",params)
    f=opener.open(site).read()
    try:
        url = re.findall(r'url: \'(.*?)\',',f)[0]
        down = urllib2.urlopen("http://115.com/%s"%url).read()
        url_real = re.findall(r'(http:\\/\\/.*?)\"',down)[0]# dianxin download
        real = url_real.replace('\\','')
    except:
        real = None
    return real
    
def post_movie(url,movie_message):#get the result from android to choice witch movie to download
    print 'post_movie() running'
    value = urllib.urlencode([('value',movie_message)])
    req = urllib2.Request(url)
    fd = urllib2.urlopen(req,value)
    print 'post'
    while 1:
        data = fd.read(1024)
        if not len(data):
            break
        sys.stdout.write(data)


def dict(name):#the log
    print 'dict() running'
    log = open('histroy.log','r').read()
    print name
    if name in log:
	result = 0
    else:
	result = name
        w = open('histroy.log','a')
        w.write(name+'\n')
	w.close()
    return result




if __name__ == '__main__':
    while 1:
        time.sleep(3)
        sites = find()
        if sites :
            if sites == 'Google is closed ! Y could find after a while@@@0':
                post_moive('http://www.Yousite/movie_choice.php',sites)
            else:
                movie_message = {}
                for site in sites:
                    title = get_title(site)
                    if site not in movie_message:
                        movie_message[title]=site #get messages for the movie to put it to PHP
                message = urllib.urlencode(movie_message)
                post_movie('http://www.Yousite/movie_choice.php',message)
                while get_movie():#get the 115.com's site
                    real_url = down_115(get_movie())
                    down_result = download(real_url)
                    if down_result:
                        print 'download succcss'
                    break
            time.sleep(120)
