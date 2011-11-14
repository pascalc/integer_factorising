#!/usr/bin/env ruby

require_relative './MillerRabin.rb'

def f(x)
  x*x + 1
end

def divisor(n)
  x,y,d = 2,2,1
  while d == 1
    x = f(x) % n
    y = f(f(y)) % n

    d = (x-y).abs.gcd(n)
  end
  return d
end

def factor(n)
  return if n == 1
  if miller_rabin(n)
    puts n
    return
  end
  
  d = divisor(n)
  factor(d)
  factor(n/d)
end

N = ARGV[0].to_i
puts "Factorising #{N}..."
factor(N)
