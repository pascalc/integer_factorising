#!/usr/bin/env ruby

require_relative './MillerRabin.rb'

def divisor(n)
  d = 1
  while d == 1
    x = rand(n)
    y = rand(n)

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
