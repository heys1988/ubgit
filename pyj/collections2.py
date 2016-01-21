# -*- coding: utf-8 -*-

from collections import namedtuple
Point = namedtuple('Point', ['x', 'y'])
p = Point(1, 2)
print p.x, p.y

print isinstance(p, Point)
print isinstance(p, tuple)

# namedtuple('名称', [属性list]):
Circle = namedtuple('Circle', ['x', 'y', 'r'])
print Circle

from collections import deque
q = deque(['a', 'b', 'c'])
q.append('x')
q.append('y')
print q

from collections import defaultdict
dd = defaultdict(lambda: 'Heys:N/A')
dd['key1'] = 'abc'
print dd['key1']
print dd['key2']
