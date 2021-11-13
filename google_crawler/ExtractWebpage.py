#!/usr/bin/python
# -*- coding: utf-8 -*-
# google search results crawler

import StringIO
import gzip
import random
import re
import socket
import sys
import time
import types
import urllib2
import jpype
import os
import json
import shutil
from bs4 import BeautifulSoup

reload(sys)
sys.setdefaultencoding('utf-8')

#my custom search engine
# https://www.googleapis.com/customsearch/v1?q=lecture&cx=003809539422264464234%3A3qnie4y1xi6&key=AIzaSyACHuMUwMM7UtJiXpKK3CyLVWTM8gNZ2yc
base_url="https://www.googleapis.com/customsearch"
results_per_page=10
user_agents = list()
useful_website = ['stackoverflow.com', 'commons.apache.org', 'docs.oracle.com', 'www.programcreek.com', 'www.codota.com']

#proxy setting
proxy = urllib2.ProxyHandler({
                              'https': 'https://127.0.0.1:1080',
                              'http': 'http://127.0.0.1:1080'
        })
opener = urllib2.build_opener(proxy)
urllib2.install_opener(opener)

#setting for call the modified API extractor
jarpath = os.path.join(os.path.abspath('.'), 'apiminer.jar')
jpype.startJVM(jpype.getDefaultJVMPath(), "-ea", "-Djava.class.path=%s" % jarpath)
MyAPICallExtractor = jpype.JClass("My.MyAPICallExtractor")

# results from the search engine: basically include url, title, content
class SearchResult:
    def __init__(self):
        self.url = ''
        self.title = ''
        self.content = ''
        self.fullContent=''

    def getURL(self):
        return self.url

    def setURL(self, url):
        self.url = url

    def getTitle(self):
        return self.title

    def setTitle(self, title):
        self.title = title

    def getContent(self):
        return self.content
    def getFullContent(self):
        return self.fullContent

    def setContent(self, content):
        self.content = content
    def setFullContent(self,fullContent):
        self.fullContent=fullContent

    def printIt(self, prefix=''):
        print 'url\t->', self.url
        print 'title\t->', self.title
        print 'content\t->', self.content
        print

    def writeFile(self, file,number):
        try:
            file.write('Number'+str(number)+':\n')
            file.write('url:' + self.url + '\n')
            file.write('title:' + self.title + '\n')
            file.write('content:'+self.content+'\n\n')
        except IOError, e:
            print 'file error:', e
    def writeContent(self, file,number):
        try:
            file.write('Number' + str(number) + ':\n')
            file.write(self.fullContent+'\n\n')
        except IOError, e:
            print 'file error:', e


class GoogleAPI:
    def __init__(self):
        timeout = 40
        socket.setdefaulttimeout(timeout)

    def randomSleep(self):
        sleeptime = random.randint(60, 120)
        time.sleep(sleeptime)

    def not_empty(self, s):
        return s and s.strip()


    def parseArff(self):
        api_file = open('data/ExtractResult/code.arff', 'r')
        api_list = []

        for line in api_file.readlines():
            api_seq = line.split('\',\'')[1].strip()[:-1].split(' ')
            for api in api_seq:
                temp = re.findall(r'([A-Z]+.*)', api)
                if temp and not api.startswith('UNRESOLVED'):
                    if '.' in temp[0]:
                        api = temp[0]
                        api_list.append(api)
                if api.startswith('UNRESOLVED'):
                    api = api.replace('UNRESOLVED.', '')
                    api_list.append(api)
                else:
                    continue
        api_file.close()
        non_list = list(set(api_list))
        non_list.sort(key=api_list.index)
        return non_list


    cnt = 0

    def extractSearchResults(self, html):
        """Return a list
        of extract results from the html
        """

        results = list()
        url_list = []
        soup = BeautifulSoup(html, 'html.parser')
        url_list = re.findall(r'"link": "(.*)"', soup.get_text())
        for url in url_list:
            result = SearchResult()
            print 'current url:', url
            cur_website = re.findall(r'\//(.+?)\/', url)[0]

            #get each html of the link
            try:
                request_cur = urllib2.Request(url)
                length = len(user_agents)
                index = random.randint(0, length - 1)
                user_agent = user_agents[index]
                request_cur.add_header('User-agent', user_agent)
                request_cur.add_header('connection', 'keep-alive')
                request_cur.add_header('Accept-Encoding', 'gzip')
                request_cur.add_header('referer', base_url)
                response = urllib2.urlopen(request_cur)

                html_cur = response.read()
                if (response.headers.get('content-encoding', None) == 'gzip'):
                    html_cur = gzip.GzipFile(
                        fileobj=StringIO.StringIO(html_cur)).read()
            except urllib2.URLError, e:
                print 'url error:', e
                continue
            #extract the html
            soup_cur = BeautifulSoup(html_cur, 'lxml')
            non_list = []
            code_file_dir = 'data/ExtractData/code/'
            # recreate the code directory
            shutil.rmtree(code_file_dir)  # clear the front file
            os.mkdir(code_file_dir)  # re make the dir

            try:
                if cur_website == 'www.programcreek.com':
                    file_label = self.cnt
                    self.cnt += 1
                    full_content_cur = ''

                    if 'src' in url:
                        programcreek_code = soup_cur.find('pre').get_text()

                    else:
                        for soup_body in soup_cur.select('.exampleboxbody'):
                            full_content_cur += soup_body.get_text() + '\n'
                        programcreek_code = 'class Temp{\n' + full_content_cur + '\n' + '}'

                    code_file_path = code_file_dir + 'Temp' + str(file_label) +'.java'

                    code_file = open(code_file_path, 'w')
                    code_file.write(programcreek_code)
                    code_file.close()

                    apiminer = MyAPICallExtractor()
                    apiminer.main()

                    non_list = self.parseArff()
                    full_content_cur = '\n'.join(non_list)


                    popular_method = soup_cur.find('div', "sidebarboxbody")
                    filter_content = ''
                    if popular_method is not None:
                        filter_content = list(filter(self.not_empty, popular_method.get_text().split('\n')))
                        if 'View more ...' in filter_content:
                            filter_content.remove('View more ...')

                        content = '\n'.join([api.split(' ')[0] for api in filter_content])
                        full_content_cur += '\n' + content

                    result.setFullContent(full_content_cur)

                if cur_website == 'commons.apache.org':
                    class_soup = soup_cur.find('h2', 'title') # soup_cur.find('h2', 'title')
                    if class_soup is None:
                        continue
                    class_name = class_soup.get_text()
                    class_name = ' '.join(class_name.split(' ')[1:])
                    class_post = ''

                    if '<' in class_name:
                        class_post = class_name.split('<')[1]
                        class_name = class_name.split('<')[0]

                    # handling type T
                    replace_type = ''
                    if "T extends" in class_post:
                        replace_type = class_post.split(' ')[-1]
                        if '<' in replace_type:
                            replace_type = replace_type.split('<')[0]

                    details_soup = soup_cur.find('div', 'details')
                    if details_soup is None:
                        continue

                    method_content = ''

                    for pre in details_soup.find_all('pre'):
                        k = 0
                        operations = ['+', '-', '*', '/', '=', '|', '&']
                        for op in operations:
                            if op in pre.get_text():
                                k = 1
                                break
                        if k:
                            continue
                        method_name = pre.get_text()
                        if '(' not in method_name or 'protected' in method_name:
                            continue

                        method_name = method_name.split('(')[0].split()[-1] + '(' + '('.join(method_name.split('(')[1:])
                        if 'throws' in method_name:
                            method_name = method_name.split('throws')[0]
                        method_name = ' '.join(method_name.split())

                        method_pre = method_name.split('(')[0]
                        method_post = ''.join(method_name.split('(')[1:])[:-1]
                        type_list = []
                        para = ''
                        if method_post != '':
                            if ', ' in method_post:
                                for item in method_post.split(', '):
                                    t = item.split(' ')[0]
                                    if '<' in t:
                                        t = t.split('<')[0]
                                    if '.' in t:
                                        t = t.split('.')[-1]
                                    if (t == 'T' or t == 'T[]' or t == 'T[][]') and replace_type != '':
                                        type_list.append(t.replace('T', replace_type))
                                    else:
                                        type_list.append(t)
                            else:
                                t = method_post.split(' ')[0]
                                if '<' in t:
                                    t = t.split('<')[0]
                                if '.' in t:
                                    t = t.split('.')[-1]
                                if (t == 'T' or t == 'T[]' or t == 'T[][]') and replace_type != '':
                                    type_list.append(t.replace('T', replace_type))
                                else:
                                    type_list.append(t)

                        para = ','.join(type_list)

                        method_name = method_pre + '(' + para + ')'
                        method_name = class_name + '.' + method_name
                        method_content += method_name + '\n'
                    full_content_cur = method_content
                    result.setFullContent(full_content_cur)

                if cur_website == 'stackoverflow.com':
                    answers = soup_cur.find('div', id='answers')
                    if answers is None:
                        continue
                    file_label = 0
                    code_file = open(code_file_dir+ 'StackImportTemp.java', 'w')
                    if answers.select('pre > code') is None:
                        continue
                    for code_cur in answers.select('pre > code'):
                        code = code_cur.get_text()
                        # if import&class、 class + import、 no_class & '}' 、 no '}'
                        if code.find("=") == -1:  #judge if it's a complete sentence
                            continue
                        if code.find('import') != -1:
                            code_file_path = code_file_dir + 'StackImportTemp' + str(file_label) + '.java'
                            code_file = open(code_file_path, 'w')
                            content = code
                            code_file.write(content)

                        elif code.find('import') == -1 and code.find('class') != -1:
                            code_file_path = code_file_dir + 'StackClassTemp' + str(file_label) + '.java'
                            code_file = open(code_file_path, 'w')
                            content = code
                            code_file.write(content)

                        elif code.find('import') == -1 and code.find('class') == -1 and code.strip()[-1] == '}':
                            code_file_path = code_file_dir + 'StackMethodTemp' + str(file_label) + '.java'
                            code_file = open(code_file_path, 'w')
                            content = 'public class StackMethodTemp {\n' + code + '}'
                            # full_code_content.append(content)
                            code_file.write(content)

                        else:
                           # print code.find('import')
                            code_file_path = code_file_dir + 'StackSnippetTemp' + str(file_label) + '.java'
                            code_file = open(code_file_path, 'w')
                            content = 'public class StackSnippetTemp {\n' + 'public void test(){\n' + code + '\n}\n}'
                            code_file.write(content)

                        file_label += 1
                    code_file.close()
                   # print len(answers.select('pre > code'))
                    apiminer = MyAPICallExtractor()
                    apiminer.main()
                    non_list = self.parseArff()
                    full_content_cur = '\n'.join(non_list)
                    result.setFullContent(full_content_cur)

                if cur_website == 'docs.oracle.com':

                    class_soup = soup_cur.find('h2', 'title')
                    if class_soup is None:
                        continue
                    class_name = class_soup.get_text()
                    class_name = ' '.join(class_name.split(' ')[1:])
                    class_post = ''
                    if '<' in class_name:
                        class_post = class_name.split('<')[1]
                        class_name = class_name.split('<')[0]

                    details_soup = soup_cur.find('div', 'details')
                    method_list = []
                    if details_soup is None:
                        continue

                    method_content = ''
                    for pre in details_soup.findAll('pre'):
                        if pre.parent.name != 'li':
                            continue
                        # print pre.get_text()
                        if '(' not in pre.get_text() or 'protected' in pre.get_text():
                            continue

                        method_name = pre.get_text()
                        method_name = method_name.split('(')[0].split()[-1] + '(' + '('.join(method_name.split('(')[1:])
                        if 'throws' in method_name:
                            method_name = method_name.split('throws')[0]
                        method_name = ' '.join(method_name.split())

                        method_pre = method_name.split('(')[0]
                        method_post = ''.join(method_name.split('(')[1:])[:-1]
                        type_list = []
                        para = ''
                        if method_post != '':
                            if ', ' in method_post:
                                for item in method_post.split(', '):
                                    t = item.split(' ')[0]
                                    if '<' in t:
                                        t = t.split('<')[0]
                                    if '.' in t:
                                        t = t.split('.')[-1]
                                    if (t == 'T' or t == 'T[]' or t == 'T[][]') and replace_type != '':
                                        type_list.append(t.replace('T', replace_type))
                                    else:
                                        type_list.append(t)
                            else:
                                t = method_post.split(' ')[0]
                                if '<' in t:
                                    t = t.split('<')[0]
                                if '.' in t:
                                    t = t.split('.')[-1]
                                if (t == 'T' or t == 'T[]' or t == 'T[][]') and replace_type != '':
                                    type_list.append(t.replace('T', replace_type))
                                else:
                                    type_list.append(t)

                        para = ','.join(type_list)

                        method_name = method_pre + '(' + para + ')'

                        method_name = class_name + '.' + method_name
                        method_content += method_name + '\n'
                    full_content_cur = method_content
                    result.setFullContent(full_content_cur)

                if cur_website == 'www.codota.com':
                    file_label = self.cnt

                    full_content_cur = ''

                    for soup_body in soup_cur.select('.SnippetCode'):
                        code = soup_body.get_text()

                        code_file_path = code_file_dir + 'Temp' + str(file_label) + '.java'
                        if code.find("=") == -1:
                            continue
                        if code.find('import') != -1:
                            code_file_path = code_file_dir + 'CodotaImportTemp' + str(file_label) + '.java'
                            code_file = open(code_file_path, 'w')
                            content = code
                            code_file.write(content)

                        elif code.find('import') == -1 and code.find('class') != -1:
                            code_file_path = code_file_dir + 'CodotaClassTemp' + str(file_label) + '.java'
                            code_file = open(code_file_path, 'w')
                            content = code
                            code_file.write(content)

                        elif code.find('import') == -1 and code.find('class') == -1 and code.strip()[-1] == '}':
                            code_file_path = code_file_dir + 'CodotaMethodTemp' + str(file_label) + '.java'
                            code_file = open(code_file_path, 'w')
                            content = 'public class CodotaMethodTemp {\n' + code + '}'
                            # full_code_content.append(content)
                            code_file.write(content)

                        else:
                           # print code.find('import')
                            code_file_path = code_file_dir + 'StackSnippetTemp' + str(file_label) + '.java'
                            code_file = open(code_file_path, 'w')
                            content = 'public class CodotaSnippetTemp {\n' + 'public void test(){\n' + code + '\n}\n}'
                            code_file.write(content)

                        code_file.close()
                        file_label += 1

                    apiminer = MyAPICallExtractor()
                    apiminer.main()

                    non_list = self.parseArff()
                    full_content_cur = '\n'.join(non_list)
                    result.setFullContent(full_content_cur)

                if (cmp(url, '') == 0):
                    continue
                result.setURL(url)
                results.append(result)
            except Exception, e:
                print 'test error', e.message
                continue

        return results

    def search(self, query, lang='lang_en', num=results_per_page, cx = None, key = None):
        """Search web and return a list
        @param query -> query key words
        @param lang -> language of search results
        @param num -> number of search results to return
        """
        search_results = list()
        query = urllib2.quote(query) ####quote('abc def') -> 'abc%20def'
        if(num % results_per_page == 0):
            pages = num / results_per_page
        else:
            pages = num / results_per_page + 1


        for p in range(0, pages):
            start = p * results_per_page
            url = '%s/v1?q=%s&cx=%s&lr=%s&num=%d&key=%s' % (base_url, query, cx, lang, results_per_page, key)   #custom google
            print url
            retry = 3
            while(retry > 0):
                try:
                    request = urllib2.Request(url)
                    length = len(user_agents)
                    index = random.randint(0, length-1)
                    user_agent = user_agents[index]
                    # request.add_header('HOST', base_url)
                    request.add_header('User-agent', user_agent)
                    request.add_header('connection', 'keep-alive')
                    request.add_header('Accept-Encoding', 'gzip')
                    request.add_header('referer', base_url)
                    response = urllib2.urlopen(request)
                    html = response.read()
                    if(response.headers.get('content-encoding', None) == 'gzip'):
                        html = gzip.GzipFile(
                            fileobj=StringIO.StringIO(html)).read()
                    results = self.extractSearchResults(html)
                    search_results.extend(results)
                    break
                except urllib2.URLError, e:
                    print 'url error:', e
                    self.randomSleep()
                    retry = retry - 1
                    continue

                except Exception, e:
                    print 'error:', e
                    retry = retry - 1
                    self.randomSleep()
                    continue
        return search_results


def load_user_agent():
    fp = open('./user_agents', 'r')

    line = fp.readline().strip('\n')
    while(line):
        user_agents.append(line)
        line = fp.readline().strip('\n')
    fp.close()


def crawler():
    # Load use agent string from file
    load_user_agent()

    # Create a GoogleAPI instance
    api = GoogleAPI()

    # set expect search results to be crawled
    expect_num = 10
    # if no parameters, read query keywords from file
    if(len(sys.argv) < 2):#if reading query from a file
        keywords = open('./new_keywords', 'r')
        line=keywords.readline()

        while(line):
            if '#' in line:
                line=keywords.readline()
                continue
            lineSplit = line.split(':')
            ID = lineSplit[0]
            keyword = lineSplit[1]

            print 'searching:',keyword
            cx = "003809539422264464234%3A3qnie4y1xi6"
            key = "AIzaSyACHuMUwMM7UtJiXpKK3CyLVWTM8gNZ2yc"
            results = api.search(keyword, num=expect_num, cx = cx, key = key)
            while len(results) == 0:
                results = api.search(keyword, num=expect_num)
            #write result to file
            print "Number of results", len(results)
            f = file('./url_results/'+ID, "w")
            f_con=file('./content_results/'+ID+'_content',"w")
            number=1
            for r in results:
                r.writeFile(f,number)
                r.writeContent(f_con,number)
                number+=1
            f.close()
            f_con.close()
            line = keywords.readline()
        keywords.close()
        exit(0)
    else: #if getting args from console
        # keyword = sys.argv[1]
        # ID = sys.argv[2]
        keyword = input("Please input the task description:")
        ID = input("Please input a task ID:")
        print 'searching:', keyword
        cx = "003809539422264464234%3A3qnie4y1xi6"
        key = "AIzaSyACHuMUwMM7UtJiXpKK3CyLVWTM8gNZ2yc"
        results = api.search(keyword, num=expect_num, cx=cx, key=key)
        while len(results) == 0:
            results = api.search(keyword, num=expect_num)
        print "Number of results", len(results)
        f = file('D:\\ProgramFiles\\Ubuntu\\share\\url_results\\' + str(ID), "w")
        f_con = file('D:\\ProgramFiles\\Ubuntu\\share\\content_results\\' + str(ID) + '_content', "w")
        number = 1
        for r in results:
            r.writeFile(f, number)
            r.writeContent(f_con, number)
            number += 1
        f.close()
        f_con.close()
        exit(0)


if __name__ == '__main__':

    crawler()
    jpype.shutdownJVM()