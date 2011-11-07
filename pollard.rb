#!/usr/bin/env ruby

def gcd(a,b) a.gcd(b) end
def abs(a) a.abs end

def f(x) x**2 + 1 end

def pollard(n)
	x,y,d = 2,2,1

	puts n

	while d == 1
		x = f(x)
		y = f(f(y))
		d = gcd(abs(x-y),n)
	end

	return (d == n) ? "fail" : pollard(n/d)
end

loop do
	print "Pollard> "
	n = gets.chomp.to_i
	puts pollard(n)
end