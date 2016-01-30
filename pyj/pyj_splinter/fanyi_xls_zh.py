# -*- coding: utf-8 -*-

from splinter import Browser
import time

with Browser() as browser:
    # Visit URL
    url = "http://fanyi.baidu.com/#auto/zh/"
    browser.visit(url)
    
    #browser.fill('baidu_translate_input', 'python acceptance testing for web applications')
    #browser.fill('textarea', 'python acceptance testing for web applications')
    baidu_trans_input = browser.find_by_id('baidu_translate_input')
    #print dir(baidu_trans_input)
    field = browser.find_by_id('baidu_translate_input').first
    field.value = u'hello world\ngood morning'
    #print dir(field)

    #pinyinck = browser.find_by_css('.op-checked')
    b_no_pinyin = browser.is_element_present_by_css('.op-checked')
    pinyin=browser.find_by_xpath('//*[@id="main-outer"]/div/\
div/div/div[2]/div[2]/div/div/div/div[1]/div[2]/div[2]/a[1]')
    while(True == b_no_pinyin):
        pinyin.click()
        time.sleep(1)
        b_no_pinyin = browser.is_element_present_by_css('.op-checked')        
        
    #print u'拼音有吗？',browser.is_element_present_by_css('.op-checked')
    
    trans_output = browser.find_by_css('.ordinary-output.target-output')
    for trans_ele in trans_output:
        print trans_ele.value
    time.sleep(10)
