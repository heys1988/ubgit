# -*- coding: utf-8 -*-

s = r'ABC\-001'
print s

import re
ret = re.match(r'^\d{3}\-\d{3,8}$', '010-12345')
print ret
ret2 = re.match(r'^\d{3}\-\d{3,8}$', '010 12345')
print ret2

test = '用户输入的字符串'
if re.match(r'正则表达式', test):
    print 'ok'
else:
    print 'failed'


ret3 = 'a b   c'.split(' ')
print ret3

ret4 = re.split(r'\s+', 'a b   c')
print ret4

ret5 = re.split(r'[\s\,\;]+', 'a,b;; c  d')
print ret5

m = re.match(r'^(\d{3})-(\d{3,8})$', '010-12345')
print m

mg0 = m.group(0)
mg1 = m.group(1)
mg2 = m.group(2)
print mg0,mg1,mg2
print m.groups()

t = '19:05:30'
m2 = re.match(r'^(0[0-9]|1[0-9]|2[0-3]|[0-9])\:(0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]|[0-9])\:(0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]|[0-9])$', t)
#m2 = re.match(r'^(0[0-9]|1[0-9]|2[0-3]|[0-9])\:(\
#    0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]|[0-9])\:(\
#    0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]|[0-9])$', t)
print m2.groups()

#贪婪匹配
print re.match(r'^(\d+)(0*)$', '102300').groups()

#非贪婪匹配
print re.match(r'^(\d+?)(0*)$', '102300').groups()

#编译
re_telephone = re.compile(r'^(\d{3})-(\d{3,8})$')
print re_telephone.match('010-12345').groups()
print re_telephone.match('010-8086').groups()
