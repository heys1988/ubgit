# -*- coding: utf-8 -*-
from splinter import Browser
import time
import datetime
import os
uploaded_excel = os.getcwd()+"/upload/"+"osd.xls"
print uploaded_excel
from py4j.java_gateway import JavaGateway
gateway = JavaGateway()                   # connect to the JVM
random = gateway.jvm.java.util.Random()   # create a java.util.Random instance
number1 = random.nextInt(10)              # call the Random.nextInt method
number2 = random.nextInt(10)
print(number1,number2)
fanyi_app = gateway.entry_point        # get the AdditionApplication instance
print fanyi_app.addition(number1,number2)    # call the addition method

fanyi_app.deliveryExcel(uploaded_excel)
ex_rows = fanyi_app.getRows()
ex_cols = fanyi_app.getCols()

print 'excel rows:', ex_rows
print 'excel cols:', ex_cols

with Browser() as browser:
    # Visit URL
    url = "http://fanyi.baidu.com/#auto/zh/"
    browser.visit(url)    
    
    baidu_trans_input = browser.find_by_id('baidu_translate_input')
    field = browser.find_by_id('baidu_translate_input').first
    #field.value = u'hello world\ngood morning'

    #row = 0
    row = 190
    col = ex_cols - 1
    starttime = datetime.datetime.now()
    block_value = '';
    start_row = row
    while row <= ex_rows:
        
        cell_value = fanyi_app.getExcelCellValue(row, col)
        
        if " " == cell_value:
            end_row = row
            row = row + 1
            continue
        if len(block_value) > 5000:
            end_row = row - 1
        field.value = cell_value
        pinyin=browser.find_by_xpath('//*[@id="main-outer"]/div/\
div/div/div[2]/div[2]/div/div/div/div[1]/div[2]/div[2]/a[1]')
        if False == pinyin.is_empty():
            pinyin.click()
            b_no_pinyin = browser.is_element_present_by_css('.op-checked')
            pinyin=browser.find_by_xpath('//*[@id="main-outer"]/div/\
div/div/div[2]/div[2]/div/div/div/div[1]/div[2]/div[2]/a[1]')
            while(True == b_no_pinyin):
                if False == pinyin.is_empty():
                    pinyin.click()
                    b_no_pinyin = browser.is_element_present_by_css('.op-checked')
                else:
                    break
    
        trans_output = browser.find_by_css('.ordinary-output.target-output')        
        fanyi_app.setExcelCellValue(row, col+1, trans_output.value)    
        
        for trans_ele in trans_output:
            print trans_ele.value

        row = row + 1
        if 0 == row % 10:
            endtime = datetime.datetime.now()
            print 'time elapsed:'(endtime - starttime).seconds
        qingkong = browser.find_by_css('.textarea-clear-btn')
        qingkong.click()
        fanyi_app.processEnd()

#fanyi_app.processEnd()
print 'total time elapsed:'(endtime - starttime).minutes
