#!/usr/bin/env python3

import argparse, random, string

def len_type(x):
    x = int(x)
    if x < 1 or x > 10:
        raise argparse.ArgumentTypeError(
            'Choose string max length between 1 and 10 chars')
    return x

parser = argparse.ArgumentParser(
            description='Generate input to Subset client.')
parser.add_argument('strings', metavar='n', type=int,
                    help='number of strings')
parser.add_argument('--maxlen', metavar='m', type=len_type, default=3,
                    help='string max length (default: 3)')

args = parser.parse_args()

# create population
population = list(string.ascii_uppercase)[:args.maxlen]

# print random strings
for i in range(args.strings):
    length = random.randint(1, args.maxlen)
    sample = random.sample(population, length)
    print(''.join(sample))
