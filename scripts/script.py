print 'Script: Greetings from sandbox'
print 'Script: I am alive and I can do math!'
print '\t2^10 = {}'.format(2 ** 10)
print '\t6/2(1+2) = {}'.format(1)  # xD
print 'Script: Now I\'ll try something illegal'
f = open('/etc/passwd')
userdetails = f.read()
print(userdetails)
